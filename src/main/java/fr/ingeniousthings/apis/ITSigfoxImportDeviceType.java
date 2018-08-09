package fr.ingeniousthings.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.apis.models.ITSigfoxModelDeviceTypeFull;
import fr.ingeniousthings.sigfox.apiv2.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * Export a devicetype to a serialized string.
 * Export
 *   - devicetype
 *   - associated callbacks
 *   - enabled downlink
 */
public class ITSigfoxImportDeviceType {

    private static final Logger log = LoggerFactory.getLogger(ITSigfoxImportDeviceType.class);

    public static void importDeviceType(
            String apiLogin,
            String apiPassword,
            String newDeviceTypeName,
            String groupId,
            String contractId,
            String modeleStr
    ) throws ITSigfoxException {
        ITSigfoxModelDeviceTypeFull modele = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            modele = mapper.readValue(modeleStr, ITSigfoxModelDeviceTypeFull.class);
        } catch (IOException e) {
            throw new ITSigfoxException(HttpStatus.UNPROCESSABLE_ENTITY,"Impossible to deserialize given deviceType modele");
        }
        importDeviceType(
                apiLogin,
                apiPassword,
                newDeviceTypeName,
                groupId,
                contractId,
                modele
        );
    }


    /**
     * Export deviceType
     * @param apiLogin
     * @param apiPassword
     * @param newDeviceTypeName - new name for the deviceType if need to be changed (null otherwize)
     * @param groupId - group to be used, when null, use the first one found.
     * @param contractId - new contractId if this one have to be changed
     * @param modele - the object to import - a special function exists for String(JSON) import
     *
     * @return
     */
    public static void importDeviceType(
            String apiLogin,
            String apiPassword,
            String newDeviceTypeName,
            String groupId,
            String contractId,
            ITSigfoxModelDeviceTypeFull modele
    ) throws ITSigfoxException {

        // Map the devicetype modele to a cdevicetype creation modele
        SigfoxApiv2DeviceTypeCreate dtCreate = new SigfoxApiv2DeviceTypeCreate();
        dtCreate.setAlertEmail(modele.getDeviceTypeDefinition().getAlertEmail());
        dtCreate.setAutomaticRenewal(modele.getDeviceTypeDefinition().isAutomaticRenewal());
        dtCreate.setDescription(modele.getDeviceTypeDefinition().getDescription());
        dtCreate.setDownlinkDataString(modele.getDeviceTypeDefinition().getDownlinkDataString());
        dtCreate.setDownlinkMode(modele.getDeviceTypeDefinition().getDownlinkMode());
        dtCreate.setKeepAlive(modele.getDeviceTypeDefinition().getKeepAlive());
        dtCreate.setPayloadConfig(modele.getDeviceTypeDefinition().getPayloadConfig());
        dtCreate.setPayloadType(modele.getDeviceTypeDefinition().getPayloadType());
        if (modele.getDeviceTypeDefinition().getGeolocPayloadConfig() != null ) {
            dtCreate.setGeolocPayloadConfigId(modele.getDeviceTypeDefinition().getGeolocPayloadConfig().getId());
        }
        if ( newDeviceTypeName != null) {
            dtCreate.setName(newDeviceTypeName);
        } else {
            dtCreate.setName(modele.getDeviceTypeDefinition().getName());
        }
        if ( groupId != null ) {
            dtCreate.setGroupId(groupId);
        } else {
            // search for first group acessible with this API account
            ITSigfoxConnection<String, SigfoxApiv2GroupListResponse> groupRequest = new ITSigfoxConnection<>(
                    apiLogin,
                    apiPassword
            );
            try {
                SigfoxApiv2GroupListResponse gl = groupRequest.execute(
                        "GET",
                        "/api/v2/groups/",
                        null,
                        null,
                        null,
                        SigfoxApiv2GroupListResponse.class
                );
                if ( gl.getData().size() > 0 ) {
                    dtCreate.setGroupId(gl.getData().get(0).getId());
                } else {
                    throw new ITSigfoxException(HttpStatus.NOT_FOUND,"Impossible to get a groupId");
                }
            } catch ( ITSigfoxConnectionException e) {
                log.error("Error during sigfox communication for getting group list :"+e.errorMessage);
                throw new ITSigfoxException(e.status,e.errorMessage);
            }
        }
        if( contractId != null) {
            dtCreate.setContractId(contractId);
        } else {
            dtCreate.setContractId(modele.getDeviceTypeDefinition().getContract().getId());
        }
        ITSigfoxConnection<SigfoxApiv2DeviceTypeCreate, SigfoxApiv2DeviceTypeId> groupRequest = new ITSigfoxConnection<>(
                apiLogin,
                apiPassword
        );
        String deviceTypeId = null;
        try {
            SigfoxApiv2DeviceTypeId dt = groupRequest.execute(
                    "POST",
                    "/api/v2/device-types/",
                    null,
                    null,
                    dtCreate,
                    SigfoxApiv2DeviceTypeId.class
            );
            deviceTypeId = dt.getId();
        } catch ( ITSigfoxConnectionException e) {
            log.error("Error during sigfox communication for creating the new deviceType :"+e.errorMessage);
            throw new ITSigfoxException(e.status,e.errorMessage);
        }


    }


}
