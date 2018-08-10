package fr.ingeniousthings.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.apis.models.ITSigfoxModelDeviceTypeFull;
import fr.ingeniousthings.sigfox.apiv2.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;

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
            String modeleStr,
            boolean withRollback
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
                modele,
                withRollback
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
     * @param withRollback - when true all what has been created is deleted in case of error
     *
     * @return
     */
    public static void importDeviceType(
            String apiLogin,
            String apiPassword,
            String newDeviceTypeName,
            String groupId,
            String contractId,
            ITSigfoxModelDeviceTypeFull modele,
            boolean withRollback
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

        // Now we can recreate the callbacks
        if ( modele.getCallbackList() == null || modele.getCallbackList().size() == 0) return;

        ArrayList<String> callbackIds = new ArrayList<>();
        ITSigfoxConnection<SigfoxApiv2CallbackCreation, SigfoxApiv2CallbackId> callbackCreation = new ITSigfoxConnection<>(
                apiLogin,
                apiPassword
        );
        for (SigfoxApiv2Callback cb : modele.getCallbackList() ) {

            SigfoxApiv2CallbackCreation cbc = new SigfoxApiv2CallbackCreation();
            cbc.setBodyTemplate(cb.getBodyTemplate());
            // @TODO : waiting for resolution of FRASIG-6022 this allow to keep the subType as NULL and not send it
            // Because Type Error does not have subtype and crash if you send a subtype
            if ( cb.getCallbackSubtype() >=0 ) cbc.setCallbackSubtype(cb.getCallbackSubtype());
            cbc.setCallbackType(cb.getCallbackType());
            cbc.setChannel(cb.getChannel());
            // @TODO : replace this hard coded ContentType as soon as Sigfox API export the content Type...
            // @TODO SR open FRASIG-6020
            cbc.setContentType("application/json");
            cbc.setDead(cb.isDead());
            cbc.setEnabled(cb.isEnabled());
            cbc.setHeaders(cb.getHeaders());
            cbc.setHttpMethod(cb.getHttpMethod());
            cbc.setLinePattern(cb.getLinePattern());
            cbc.setMessage(cb.getMessage());
            cbc.setPayloadConfig(cb.getPayloadConfig());
            cbc.setRecipient(cb.getRecipient());
            cbc.setSendDuplicate(cb.isSendDuplicate());
            cbc.setSendSni(cb.isSendSni());
            cbc.setUrl(cb.getUrlPattern());
            try {
                SigfoxApiv2CallbackId cbn = callbackCreation.execute(
                        "POST",
                        "/api/v2/device-types/"+deviceTypeId+"/callbacks",
                        null,
                        null,
                        cbc,
                        SigfoxApiv2CallbackId.class
                );
                callbackIds.add(cbn.getId());
                if ( cb.isDownlinkHook() ) {
                    // activate downlink on this callback
                    ITSigfoxConnection<String, String> downlink = new ITSigfoxConnection<>(
                            apiLogin,
                            apiPassword
                    );
                    String ret = downlink.execute(
                            "PUT",
                            "/api/v2/device-types/" + deviceTypeId + "/callbacks/" + cbn.getId()+"/downlink",
                            null,
                            null,
                            null,
                            String.class
                    );
                }
            } catch ( ITSigfoxConnectionException e) {
                log.error("Error during sigfox communication for creating the new callback :"+e.errorMessage);
                if ( withRollback ) {
                    log.warn("Deleting the callback & devicetype created");
                    // Delete each of the created callbacks
                    ITSigfoxConnection<String, String> delete = new ITSigfoxConnection<>(
                            apiLogin,
                            apiPassword
                    );
                    for ( String cid : callbackIds) {
                        try {
                            String ret = delete.execute(
                                    "DELETE",
                                    "/api/v2/device-types/" + deviceTypeId + "/callbacks/" + cid,
                                    null,
                                    null,
                                    null,
                                    String.class
                            );
                        } catch (ITSigfoxConnectionException x) {
                            log.warn("Network error, can't delete what have been created");
                        }
                    }
                    try {
                        String ret = delete.execute(
                                "DELETE",
                                "/api/v2/device-types/" + deviceTypeId,
                                null,
                                null,
                                null,
                                String.class
                        );
                    } catch (ITSigfoxConnectionException x) {
                        log.warn("Network error, can't delete DeviceType previously created");
                    }

                }
                throw new ITSigfoxException(e.status,e.errorMessage);
            }
        }

    }


}
