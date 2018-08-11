package fr.ingeniousthings.apis.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.apis.models.ITSigfoxModelDeviceTypeFull;
import fr.ingeniousthings.sigfox.apiv2.models.SigfoxApiv2Callback;
import fr.ingeniousthings.sigfox.apiv2.models.SigfoxApiv2DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Export a devicetype to a serialized string.
 * Export
 *   - devicetype
 *   - associated callbacks
 *   - enabled downlink
 */
public class ITSigfoxExportDeviceType {

    private static final Logger log = LoggerFactory.getLogger(ITSigfoxExportDeviceType.class);


    public static String exportDeviceTypeAsString(
            String apiLogin,
            String apiPassword,
            String deviceTypeId
    ) throws ITSigfoxException {
        ITSigfoxModelDeviceTypeFull _ret = exportDeviceType (
                apiLogin,
                apiPassword,
                deviceTypeId
        );
        String ret;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ret = mapper.writeValueAsString(_ret);

        } catch ( JsonProcessingException e) {
            log.error("Error during sigfox response parsing :");
            e.printStackTrace();
            throw new ITSigfoxException(HttpStatus.NOT_ACCEPTABLE,"Parsing message impossible");
        }
        return ret;
    }

    /**
     * Export deviceType
     * @param apiLogin
     * @param apiPassword
     * @param deviceTypeId
     * @return
     */
    public static ITSigfoxModelDeviceTypeFull exportDeviceType(
            String apiLogin,
            String apiPassword,
            String deviceTypeId
    ) throws ITSigfoxException {
        ITSigfoxModelDeviceTypeFull _ret = new ITSigfoxModelDeviceTypeFull();
        String ret = "";

        // Search for the device Type
        ITSigfoxConnection<String, SigfoxApiv2DeviceType> deviceTypeRequest = new ITSigfoxConnection<>(
                apiLogin,
                apiPassword
        );
        try {
            SigfoxApiv2DeviceType dt = deviceTypeRequest.execute(
                    "GET",
                    "/api/v2/device-types/" + deviceTypeId,
                    null,
                    null,
                    null,
                    SigfoxApiv2DeviceType.class
            );
            _ret.setDeviceTypeDefinition(dt);

        } catch ( ITSigfoxConnectionException e) {
            log.error("Error during sigfox communication :"+e.errorMessage);
            if ( e.status == HttpStatus.NOT_FOUND ) {
                throw new ITSigfoxException(HttpStatus.NOT_FOUND,"DeviceType Id does not exists");
            } else {
                throw new ITSigfoxException(e.status,e.errorMessage);
            }
        }

        // Search for the associated callback
        ITSigfoxConnection<String, SigfoxApiv2Callback[]> callbackListRequest = new ITSigfoxConnection<>(
                apiLogin,
                apiPassword
        );
        try {
            SigfoxApiv2Callback[] cbs = callbackListRequest.execute(
                    "GET",
                    "/api/v2/device-types/" + deviceTypeId + "/callbacks",
                    null,
                    null,
                    null,
                    SigfoxApiv2Callback[].class
            );

            if ( cbs != null && cbs.length > 0 ) {
                for (SigfoxApiv2Callback cb : cbs) {
                    _ret.addCallback(cb);
                }
            }

        } catch ( ITSigfoxConnectionException e) {
            log.error("Error during sigfox communication :"+e.errorMessage);
            if ( e.status == HttpStatus.NOT_FOUND ) {
                throw new ITSigfoxException(HttpStatus.NOT_FOUND,"DeviceType Id does not exists");
            } else {
                throw new ITSigfoxException(e.status,e.errorMessage);
            }
        }

        return _ret;
    }


}
