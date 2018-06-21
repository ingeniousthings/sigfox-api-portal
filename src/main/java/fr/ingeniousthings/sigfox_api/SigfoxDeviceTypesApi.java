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

}



