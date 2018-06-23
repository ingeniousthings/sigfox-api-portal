package fr.ingeniousthings.sigfox_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.sigfox.api.elements.*;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value="devicetypes", tags="sigfox-devicetypes-api")
@RequestMapping(value = "/api/devicetypes")
@CrossOrigin
@RestController
public class SigfoxDeviceTypesApi {

    /**
     * Device types list
     *
     * Lists all device types available to your group.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes
     *
     * Parameters:
     *
     *     None
     *
     * Optionally, the request can also have the following parameter:
     *
     *     includeSubGroups: true if you need also device types from child groups.
     *     contractInfoId: id of the contract info associated to the device types to retrieve
     */


    @ApiOperation(
            value = "Device types list - Lists all device types available to your group",
            notes = "Lists all device types available to your group. <br/>"+
                    "Parameters: Optionally you can add parameters :<br/>" +
                    "<ul>" +
                    " <li>includeSubGroups: true if you need also device types from child groups</li>" +
                    " <li>contractInfoId: id of the contract info associated to the device types to retrieve</li>" +
                    "</ul>",
            response = SigfoxApiDeviceInformationList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceInformationList.class)
    })
    @RequestMapping(
            value ="",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTypesList(
            HttpServletRequest request,
            @RequestParam("includeSubGroups")
            @ApiParam(required = false, name = "includeSubGroups", value = "true if you need also device types from child groups.")
            Optional<Boolean> includeSubGroups,

            @RequestParam("contractInfoId")
            @ApiParam(required = false, name = "contractInfoId", value = "id of the contract info associated to the device types to retrieve")
            Optional<String> contractInfoId

    ) {

        SigfoxApiProxy<SigfoxApiDeviceInformationList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceInformationList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Device type information
     *
     * Get the description of a particular device type
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     */

    @ApiOperation(
            value = "Device type information - Get the description of a particular device type",
            notes = "Get the description of a particular device type. <br/>"+
                    "Parameters:<br/>" +
                    "<ul>" +
                    " <li>Get the description of a particular device type</li>" +
                    "</ul>",
            response = SigfoxApiDeviceInformation.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceInformation.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTypes(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceInformation> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceInformation>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Device type creation
     *
     * Create a new device type
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devicetypes/new (DEPRECATED - It will be removed on the 30th of March 2016)
     *
     * POST https://backend.sigfox.com/api/devicetypes/create
     *
     *     {
     *         "name" : "dtname",
     *         "description" : "the description",
     *         "keepAlive" : 3000,
     *         "alertEmail" : "alert@email.com",
     *         "payloadType" : "None",
     *         "group" : "deadbeeffaceb00cbebef070",
     *         "downlinkMode" : 0,
     *         "downlinkDataString" : "deadbeefcafebabe",
     *         "contractId" : "c0c0beeffaceb00cbebef070"
     *     }
     *
     */


    @ApiOperation(
            value = "Device type creation - Create a new device type",
            notes = "Create a new device type. <br/>"+
                    "In the Body are provided the information related to the devicetype to create<br/>",
            response = SigfoxApiDeviceTypeCreationResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceTypeCreationResponse.class)
    })
    @RequestMapping(
            value ="/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "deviceType", value = "Device Type description")
            @Valid @RequestBody SigfoxApiDeviceTypeCreation deviceType
    ) {

        SigfoxApiProxy<SigfoxApiDeviceTypeCreationResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceTypeCreationResponse>(proxy.proxify(request, mapper.writeValueAsString(deviceType)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Device type edition
     *
     * Edit a device type
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/edit
     *
     *     {
     *         "id" : "deadbeef0486300cbebef070",
     *         "name" : "dtname",
     *         "description" : "the description",
     *         "keepAlive" : 3000,
     *         "alertEmail" : "alert@email.com",
     *         "payloadType" : "None",
     *         "downlinkMode" : 0,
     *         "downlinkDataString" : "deadbeefcafebabe",
     *     }
     *
     *
     */


    @ApiOperation(
            value = "Device type edition - Edit a device type",
            notes = "Edit a device type. <br/>"+
                    "In the Body are provided the information related to the devicetype to edition<br/>",
            response = SigfoxApiDeviceTypeEditionResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceTypeEditionResponse.class)
    })
    @RequestMapping(
            value ="/edit",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> editDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "deviceType", value = "Device Type description")
            @Valid @RequestBody SigfoxApiDeviceTypeEdition deviceType
    ) {

        SigfoxApiProxy<SigfoxApiDeviceTypeEditionResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceTypeEditionResponse>(proxy.proxify(request, mapper.writeValueAsString(deviceType)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Device type deletion
     *
     * Delete a device type
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/delete
     *
     *     {
     *         "id" : "deadbeef0486300cbebef070"
     *     }
     *
     *
     * Fields:
     *
     *     id: the id of the device type to delete. This id must correspond to an existing device type.
     */
    @ApiOperation(
            value = "Device type deletion - Delete a device type",
            notes = "Delete a device type <br/>"+
                    "In the Body are provided the information related to the devicetype to deletion<br/>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class),
            @ApiResponse(code = 403, message= "The device type is referenced by at least one device", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist ", response = String.class)
    })
    @RequestMapping(
            value ="/delete",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> deleteDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "deviceType", value = "Device Type id")
            @Valid @RequestBody SigfoxApiDeviceTypeDeletion deviceType
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(deviceType)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Callback creation
     *
     * Create a callback
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/callbacks/new
     *
     *     [{
     *         "channel" : "URL",
     *         "callbackType" : 0,
     *         "callbackSubtype" : 2,
     *         "url" : "http://myserver.com/sigfox/callback",
     *         "httpMethod" : "POST",
     *         "enabled" : true,
     *         "sendDuplicate" : false,
     *         "sendSni": false,
     *         "payloadConfig" : "var1::bool:1",
     *         "bodyTemplate" : "device : {device} / {customData#var1}",
     *         "headers" : {
     *             "time" : "{time}"
     *         },
     *         "contentType" : "text/plain"
     *     }, {
     *         "channel" : "BATCH_URL",
     *         "callbackType" : 0,
     *         "callbackSubtype" : 2,
     *         "url" : "http://myserver.com/sigfox/callback/batch",
     *         "linePattern" : "{device};{data};",
     *         "enabled" : true,
     *         "sendDuplicate" : false,
     *         "sendSni": false
     *     }]
     *
     *
     */

    @ApiOperation(
            value = "Callback creation - Create a new device type",
            notes = "Create a callback. <br/>"+
                    "In the Body are provided the information related to the callback to create<br/>",
            response = SigfoxApiCallbackCreationResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiCallbackCreationResponse.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/callbacks/new",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "devicetype_id")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "deviceType", value = "The device type identifier as returned by the /api/devicetypes endpoint")
            @Valid @RequestBody SigfoxApiCallbackCreation deviceType
    ) {

        SigfoxApiProxy<SigfoxApiCallbackCreationResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiCallbackCreationResponse>(proxy.proxify(request, mapper.writeValueAsString(deviceType)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Callback list
     *
     * List the callbacks for a device type
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/callbacks
     *
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     */


    @ApiOperation(
            value = "Callback list - List the callbacks for a device type",
            notes = "List the callbacks for a device type <br/>"+
                    "Parameters: you need to add parameters :<br/>" +
                    "<ul>" +
                    " <li>devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint</li>" +
                    "</ul>",
            response = SigfoxApiCallbackList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiCallbackList.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/callbacks",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> getCallbackList(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "devicetype_id")
            @PathVariable("devicetype_id") String devicetype_id
    ) {

        SigfoxApiProxy<SigfoxApiCallbackList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiCallbackList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Callback deletion
     *
     * Delete a callback
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/callbacks/{callback-id}/delete
     *
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *     callback-id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.
     *
     * Response
     *
     *     200 if it was OK
     *     404 when the device type does not exist, or when the callback id does not correspond to this device type.
     *
     */
    @ApiOperation(
            value = "Callback deletion - Delete a callback",
            notes = "Delete a callback <br/>"+
                    "The needed information (ids) are given in the URL path: <br/>" +
                    "<ul>" +
                    "<li>devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.<li>" +
                    "<li>callback-id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.<li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist or when the callback id does not correspond to this device type. ", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/callbacks/{callback_id}/delete",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> deleteCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "callback_id", value = "the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.")
            @PathVariable("callback_id") String callback_id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Callback enable/disable
     *
     * Enable or disable a callback
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/callbacks/{callback-id}/enable?enabled=true
     *
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *     callback-id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.
     *     enabled: true to enable the callback, false to disable it.
     *
     */
    @ApiOperation(
            value = "Callback enable/disable - Enable or disable a callback",
            notes = "Enable or disable a callback <br/>"+
                    "The needed information (ids) are given in the URL path: <br/>" +
                    "<ul>" +
                    "<li>devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.<li>" +
                    "<li>callback-id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.<li>" +
                    "<li>enabled: true to enable the callback, false to disable it.</li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist or when the callback id does not correspond to this device type. ", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/callbacks/{callback_id}/enable",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> enableCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "callback_id", value = "the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.")
            @PathVariable("callback_id") String callback_id,
            @RequestParam("enable")
            @ApiParam(required = true, name = "enable", value = "true to enable the callback, false to disable it.")
                    String enable

            ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Callback downlink selection
     *
     * Select a downlink callback. The given callback will be selected as the downlink one, the one that was previously selected will be no more be selected for downlink.
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/callbacks/{callback-id}/downlink
     *
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *     callback-id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.
     *
     * Response
     *
     *     200 if it was OK
     *     404 when the device type does not exist, or when the callback id does not correspond to this device type.
     */
    @ApiOperation(
            value = "Callback downlink selection - Select a downlink callback",
            notes = "Select a downlink callback. The given callback will be selected as the downlink one, the one that was previously selected will be no more be selected for downlink.<br/>"+
                    "The needed information (ids) are given in the URL path: <br/>" +
                    "<ul>" +
                    "<li>devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.<li>" +
                    "<li>callback-id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.<li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist or when the callback id does not correspond to this device type. ", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/callbacks/{callback_id}/downlink",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> selectForDownlink(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "callback_id", value = "the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.")
            @PathVariable("callback_id") String callback_id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }



    /**
     * All Devices ID/PAC for a device type
     *
     * Lists the ids and PACs associated to a specific device type.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/id-pac
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint
     *
     * Response
     *
     *     {
     *       "data" : [
     *         { "id" : "0B59", "pac" : "6A757C859B23471B" },
     *         { "id" : "C032", "pac" : "374FC61194FC4DA8" },
     *         ...
     *       ]
     *     }
     *
     *
     * Fields:
     *
     *     data: the array of device ids and pacs records
     *     id: the device’s identifier
     *     pac: porting authorization code for this device
     */
    @ApiOperation(
            value = "All Devices ID/PAC for a device type",
            notes = "Lists the ids and PACs associated to a specific device type. <br/>"+
                    "Parameters: Optionally you can add parameters :<br/>" +
                    "<ul>" +
                    " <li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceListInDeviceType.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceListInDeviceType.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/id-pac",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevicesListInDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceListInDeviceType> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceListInDeviceType>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Error status events sent for a list of devices
     *
     * Get the communication down events that were sent for a list of devices belonging to the same device type,
     * in reverse chronological order (most recent events first).
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/status/error
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of status events to return, default 100.
     *     before: return status events sent before this timestamp in milliseconds since Unix Epoch.
     *     since: return status events sent since this timestamp in milliseconds since Unix Epoch.
     *     offset: number of events to skip (between 0 and 5000). Normally you should not have to worry
     *             about this parameter as the app will set this parameter automatically in the response,
     *             in the URL of the next page (see field next in response)
     */
    @ApiOperation(
            value = "Error status events sent for a list of devices",
            notes = "Get the communication down events that were sent for a list of devices belonging to the same device type, " +
                    "in reverse chronological order (most recent events first). <br/>"+
                    "Parameters: Optionally you can add parameters :<br/>" +
                    "<ul>" +
                    " <li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    " <li>limit (optional) : maximum number of status events to return, default 100.</li>" +
                    " <li>before (optional) : return status events sent before this timestamp in milliseconds since Unix Epoch.</li>" +
                    " <li>since (optional) : return status events sent since this timestamp in milliseconds since Unix Epoch.</li>" +
                    " <li>offset (optional) : number of events to skip (between 0 and 5000). Normally you should not have to worry" +
                    " about this parameter as the app will set this parameter automatically in the response," +
                    " in the URL of the next page (see field next in response)</li>" +
                    "</ul>",
            response = SigfoxApiDeviceTypeStatusErrorResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceTypeStatusErrorResponse.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/status/error",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevicesStatusErrorInDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @RequestParam("limit")
            @ApiParam(required = false, name = "limit", value = "Maximum number of status events to return, default 100.")
                    Optional<Integer> limit,
            @RequestParam("before")
            @ApiParam(required = false, name = "before", value = "Return status events sent before this timestamp in milliseconds since Unix Epoch")
                    Optional<Long> before,
            @RequestParam("since")
            @ApiParam(required = false, name = "since", value = "Return status events sent since this timestamp in milliseconds since Unix Epoch.")
                    Optional<Long> since,
            @RequestParam("offset")
            @ApiParam(required = false, name = "offset", value = "Number of events to skip (between 0 and 5000). ")
                    Optional<Integer> offset
            ) {

        SigfoxApiProxy<SigfoxApiDeviceTypeStatusErrorResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceTypeStatusErrorResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Get the network issues events that were sent for a list of devices belonging to the same device type,
     * in reverse chronological order (most recent events first).
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/status/warn
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of status events to return, default 100.
     *     before: return status events sent before this timestamp in milliseconds since Unix Epoch.
     *     since: return status events sent since this timestamp in milliseconds since Unix Epoch.
     */
    @ApiOperation(
            value = "Warning status events sent for a list of devices",
            notes = "Get the network issues events that were sent for a list of devices belonging to the same device type," +
                    " in reverse chronological order (most recent events first).<br/>"+
                    "Parameters: Optionally you can add parameters :<br/>" +
                    "<ul>" +
                    " <li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    " <li>limit (optional) : maximum number of status events to return, default 100.</li>" +
                    " <li>before (optional) : return status events sent before this timestamp in milliseconds since Unix Epoch.</li>" +
                    " <li>since (optional) : return status events sent since this timestamp in milliseconds since Unix Epoch.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceTypeStatusWarningResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceTypeStatusWarningResponse.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/status/warn",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevicesStatusWarningInDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @RequestParam("limit")
            @ApiParam(required = false, name = "limit", value = "Maximum number of status events to return, default 100.")
                    Optional<Integer> limit,
            @RequestParam("before")
            @ApiParam(required = false, name = "before", value = "Return status events sent before this timestamp in milliseconds since Unix Epoch")
                    Optional<Long> before,
            @RequestParam("since")
            @ApiParam(required = false, name = "since", value = "Return status events sent since this timestamp in milliseconds since Unix Epoch.")
                    Optional<Long> since
    ) {

        SigfoxApiProxy<SigfoxApiDeviceTypeStatusWarningResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceTypeStatusWarningResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * List custom geoloc configurations
     *
     * Get the list of the custom geoloc configurations available for the given group
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/geolocs-config
     *
     * Parameters:
     *
     *     groupId: the id of the given group.
     */
    @ApiOperation(
            value = "List custom geoloc configurations",
            notes = "Get the list of the custom geoloc configurations available for the given group<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>groupId : The id of the given group.</li>" +
                    "</ul>",
            //response=String.class,
            response = SigfoxApiCustomGeolocConfigResponse.class,
            responseContainer = "List",
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiCustomGeolocConfigResponse.class,responseContainer = "List")
//            @ApiResponse(code = 200, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/geolocs-config",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCustomGeolocConfigurationList(
            HttpServletRequest request,
            @RequestParam("devicetype_id")
            @ApiParam(required = false, name = "devicetype_id", value = "The id of the device-type")
                    String devicetype_id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Messages sent by the devices of a device type
     *
     * Get the messages that were sent by all the devices of a device type, in reverse chronological
     * order (most recent messages first). All device messages are listed, including those from device
     * are not associated to this device type anymore.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/messages
     *
     * Parameters:
     *
     *     devicetype-id the identifier of the device type, as returned by the /api/devicetypes endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of messages to return
     *     before: return messages sent before this timestamp (in seconds since the Unix Epoch).
     *     since: return messages sent since this timestamp (in seconds since the Unix Epoch).
     *     offset: number of messages to skip (between 0 and 5000). Normally you should not have
     *             to worry about this parameter as the app will set this parameter automatically
     *             in the response, in the URL of the next page (see field next in response)
     *     cbStatus: return also callbacks status, default to false.
     */
    @ApiOperation(
            value = "Messages sent by the devices of a device type",
            notes = "Get the messages that were sent by all the devices of a device type, in reverse chronological " +
                    "order (most recent messages first). All device messages are listed, including those from device " +
                    "are not associated to this device type anymore." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>devicetype-id : The identifier of the device type, as returned by the /api/devicetypes endpoint.</li>" +
                    " <li>limit: maximum number of messages to return</li>" +
                    " <li>before: return messages sent before this timestamp (in seconds since the Unix Epoch)</li>" +
                    " <li>since: return messages sent since this timestamp (in seconds since the Unix Epoch).</li>" +
                    " <li>offset: number of messages to skip (between 0 and 5000). Normally you should not have " +
                    " to worry about this parameter as the app will set this parameter automatically " +
                    " in the response, in the URL of the next page (see field next in response)</li>" +
                    " <li>cbStatus: return also callbacks status, default to false.</li>" +
                    " " +
                    "</ul>",
            response = SigfoxApiMessageInformationList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiMessageInformationList.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/messages",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getMessagesByDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @RequestParam("limit")
            @ApiParam(required = false, name = "limit", value = "Maximum number of status events to return, default 100.")
                    Optional<Integer> limit,
            @RequestParam("before")
            @ApiParam(required = false, name = "before", value = "Return status events sent before this timestamp in milliseconds since Unix Epoch")
                    Optional<Long> before,
            @RequestParam("since")
            @ApiParam(required = false, name = "since", value = "Return status events sent since this timestamp in milliseconds since Unix Epoch.")
                    Optional<Long> since,
            @RequestParam("offset")
            @ApiParam(required = false, name = "offset", value = "Number of messages to skip (between 0 and 5000)")
                    Optional<Integer> offset,
            @RequestParam("cbStatus")
            @ApiParam(required = false, name = "cbStatus", value = "Return also callbacks status, default to false.")
                    Optional<Boolean> cbStatus

    ) {

        SigfoxApiProxy<SigfoxApiMessageInformationList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiMessageInformationList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Disengage sequence number
     *
     * Disengage sequence number check for next message of each device of the device type.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/disengage
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *
     * Response
     *
     * The response has no body. A status code 200 is returned when the disengagment have been done successfully.
     */
    @ApiOperation(
            value = "Disengage sequence number",
            notes = "Disengage sequence number check for next message of each device of the device type." +
                    "The response has no body. A status code 200 is returned when the disengagment have been done successfully" +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>devicetype-id : The identifier of the device type, as returned by the /api/devicetypes endpoint.</li>" +
                    " " +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/disengage",
            produces = {MediaType.TEXT_HTML_VALUE},
            //produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> disengageDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }



    /**
     * Devices for a given device type
     *
     * Lists the devices associated to a specific device type.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/devices?snr={snr}
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint
     *     snr: optional, filter the device list according to the average signal to noise ratio of
     *     the last 25 received messages. Values can be :
     *         1, for SNR values from 0 to 10 dB
     *         2, for SNR values from 10 to 13 dB
     *         3, for SNR values from 13 to 20 dB
     *         4, for SNR values above 20 dB
     *     limit: maximum number of status events to return, default 100.
     *     offset: number of devices to skip (between 0 and 5000).
     */
    @ApiOperation(
            value = "Devices for a given device type",
            notes = "Lists the devices associated to a specific device type." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>devicetype-id : The identifier of the device type, as returned by the /api/devicetypes endpoint.</li>" +
                    " <li>snr: optional, filter the device list according to the average signal to noise ratio of" +
                    "the last 25 received messages. Values can be :" +
                    "<ul>" +
                    "<li>1, for SNR values from 0 to 10 dB</li>" +
                    "<li>2, for SNR values from 10 to 13 dB</li>" +
                    "<li>3, for SNR values from 13 to 20 dB</li>" +
                    "<li>4, for SNR values above 20 dB</li>" +
                    "</ul></li>" +
                    " <li>limit: maximum number of status events to return, default 100.</li>" +
                    " <li>offset: number of devices to skip (between 0 and 5000).</li>" +
                    "</ul>",
            response = SigfoxApiDeviceInformationList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceInformationList.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}/devices",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevicesByDeviceType(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @RequestParam("snr")
            @ApiParam(required = false, name = "snr", value = "Optional, filter the device list according to the average signal to noise ratio of the last 25 received messages.")
                    Optional<Integer> snr,
            @RequestParam("limit")
            @ApiParam(required = false, name = "limit", value = "Maximum number of status events to return, default 100.")
                    Optional<Integer> limit,
            @RequestParam("offset")
            @ApiParam(required = false, name = "offset", value = "Number of messages to skip (between 0 and 5000)")
                    Optional<Integer> offset
    ) {

        SigfoxApiProxy<SigfoxApiDeviceInformationList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceInformationList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }
}



