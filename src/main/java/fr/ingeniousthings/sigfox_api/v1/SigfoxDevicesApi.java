/**
 * -------------------------------------------------------------------------------
 * This file is part of IngeniousThings Sigfox-Api.
 *
 * IngeniousThings Sigfox-Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IngeniousThings Sigfox-Api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * -------------------------------------------------------------------------------
 * Author : Paul Pinault aka disk91
 * See https://www.disk91.com
 * ----
 * More information about IngeniousThings : https://www.ingeniousthings.fr
 * ----
 * Commercial license of this software can be obtained contacting ingeniousthings
 * -------------------------------------------------------------------------------
 */
package fr.ingeniousthings.sigfox_api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.sigfox.api.elements.*;
import fr.ingeniousthings.sigfox_api.SigfoxApiProxy;
import fr.ingeniousthings.sigfox_api.SigfoxApiProxyException;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value="devices", tags="sigfox-devices-api")
@RequestMapping(value = "/api")
@CrossOrigin
@RestController
public class SigfoxDevicesApi {

    /**
     * Registering new devices
     *
     * Creates new devices by providing a list of identifiers, and associates them to a device type.
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/devices/bulk/create/async
     *
     * {
     *   "prefix" : "test-device-",
     *   "ids" : [
     *     { "id" : "C031", "pac": "6A757C859B23471B" },
     *     { "id" : "C032", "pac": "374FC61194FC4DA8" },
     *     ...
     *   ],
     *   "productCertificate" : "P_0004_D356_03"
     * }
     *
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *
     * Fields:
     *
     *     prefix: used to create the name of each device, by prepending it to the device’s identifier
     *     ids: list of device identifiers that have to be registered
     *     id: identifier of the device
     *     pac: porting authorization code for this device
     *
     * Optionally, the request can also have the following parameter:
     *
     *     productCertificate: product certificate key to associate to each device.
     *
     */
    @ApiOperation(
            value = "Registering new devices (ASYNC)",
            notes = "Creates new devices by providing a list of identifiers, and associates them to a device type. <br/>"+
                    "In the URL and BODY are provided the information related to the device list to create<br/>" +
                    "<ul>" +
                    "<li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceCreateOutput.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceCreateOutput.class)
    })
    @RequestMapping(
            value ="/devicetypes/{devicetype_id}/devices/bulk/create/async",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createDeviceTypeASync(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "The device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "devices", value = "Devices to be created list")
            @Valid @RequestBody SigfoxApiDeviceCreateInput devices
    ) {

        SigfoxApiProxy<SigfoxApiDeviceCreateOutput> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceCreateOutput>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Checking asynchronous devices creation status
     *
     * Returns the status of the given devices creation job.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/registration/{job-id}
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *     job-id: the devices creation job id as returned by the /api/devicetypes/{devicetype-id}/devices/bulk/create/async endpoint.
     */

    @ApiOperation(
            value = "Checking asynchronous devices creation status",
            notes = "Checking asynchronous devices creation status. Returns the status of the given devices creation job. <br/>"+
                    "Parameters:<br/>" +
                    "<ul>" +
                    " <li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    " <li>job_id : the devices creation job id as returned by the /api/devicetypes/{devicetype-id}/devices/bulk/create/async endpoint.</li> " +
                    "</ul>",
            response = SigfoxApiDeviceCreateJobStatus.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceCreateJobStatus.class)
    })
    @RequestMapping(
            value ="/devicetypes/{devicetype_id}/registration/{job_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> checkDeviceCreation(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "job_id", value = "the devices creation job id as returned by the /api/devicetypes/{devicetype-id}/devices/bulk/create/async endpoint.")
            @PathVariable("job_id") String job_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceCreateJobStatus> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceCreateJobStatus>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Device information
     *
     * Gets information about a device.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{device-id}
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     */
    @ApiOperation(
            value = "Device information",
            notes = "Gets information about a device." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceInformation.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceInformation.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceDetails(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceInformation> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceInformation>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Device token state
     *
     * Gets information about a device’s token.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{device-id}/token-state
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     *
     */
    @ApiOperation(
            value = "Device token state",
            notes = "Gets information about a device’s token." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceTokenState.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceTokenState.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/token-state",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTokenState(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceTokenState> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceTokenState>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Messages sent by a device
     *
     * Get the messages that were sent by a device, in reverse chronological order (most recent messages first).
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{device-id}/messages
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of messages to return
     *     before: return messages sent before this timestamp (in seconds since the Unix Epoch).
     *     since: return messages sent since this timestamp (in seconds since the Unix Epoch).
     *     offset: number of messages to skip (between 0 and 5000). Normally you should not have to worry about this parameter as the app will set this parameter automatically in the response, in the URL of the next page (see field next in response)
     *     cbStatus: return also callbacks status, default to false.
     *
     */
    @ApiOperation(
            value = "Messages sent by a device",
            notes = "Get the messages that were sent by a device, in reverse chronological order (most recent messages first)." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
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
            value ="/devices/{device_id}/messages",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getMessagesByDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id,
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
     * Messages location for one device
     *
     * Get the messages location from the following sources: GPS data or the Sigfox Geolocation service in reverse chronological order
     * (most recent messages first). GPS data is used if the device has a device type with payload type "Geolocation",
     * while Sigfox Geolocation service is used if the device is attached to a contract with the Sigfox Geolocation service
     * option enabled.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{device-id}/locations
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of messages to return
     *     before: return messages sent before this timestamp (in seconds since the Unix Epoch).
     *     since: return messages sent since this timestamp (in seconds since the Unix Epoch).
     *     offset: number of messages to skip (between 0 and 5000). Normally you should not have to worry about this parameter as the app will set this parameter automatically in the response, in the URL of the next page (see field next in response)
     *
     */
    @ApiOperation(
            value = "Messages sent by a device",
            notes = "Get the messages location from the following sources: GPS data or the Sigfox Geolocation service in reverse chronological order" +
                    "(most recent messages first). GPS data is used if the device has a device type with payload type \"Geolocation\", " +
                    "while Sigfox Geolocation service is used if the device is attached to a contract with the Sigfox Geolocation service " +
                    "option enabled." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    " <li>limit: maximum number of messages to return</li>" +
                    " <li>before: return messages sent before this timestamp (in seconds since the Unix Epoch)</li>" +
                    " <li>since: return messages sent since this timestamp (in seconds since the Unix Epoch).</li>" +
                    " <li>offset: number of messages to skip (between 0 and 5000). Normally you should not have " +
                    " to worry about this parameter as the app will set this parameter automatically " +
                    " in the response, in the URL of the next page (see field next in response)</li>" +
                    "</ul>",
            response = SigfoxApiDeviceLocationList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceLocationList.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/locations",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getMessageLocationsByDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id,
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
                    Optional<Integer> offset
    ) {

        SigfoxApiProxy<SigfoxApiDeviceLocationList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceLocationList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Replacing a list of devices
     *
     * Replaces devices by providing a list of ids couples.
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devices/bulk/replace
     *
     * [
     *   {
     *     "deviceId":"0001",
     *     "targetDeviceId":"0A6E"
     *   }, {
     *     "deviceId":"0002",
     *     "targetDeviceId": "1ABC"
     *   }
     * ]
     *
     *
     * Parameters:
     *
     *     None
     *
     * Fields:
     *
     *     deviceId: device id. This device will be replaced by the target device.
     *     targetDeviceId: the target device id.
     *
     */
    @ApiOperation(
            value = "Replacing a list of devices",
            notes = "Replaces devices by providing a list of ids couples. <br/>"+
                    "In the BODY are provided the information related to the device list replace<br/>" +
                    "",
            response = SigfoxApiDeviceReplaceResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceReplaceResponse.class)
    })
    @RequestMapping(
            value ="/devices/bulk/replace",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> replaceListOfDevices(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicesList", value = "List of devices to be replaced")
            @Valid @RequestBody List<SigfoxApiDeviceReplaceInput> devicesList
    ) {

        SigfoxApiProxy<SigfoxApiDeviceReplaceResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceReplaceResponse>(proxy.proxify(request, mapper.writeValueAsString(devicesList)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Editing a list of devices
     *
     * Modifies devices by providing a list of changes.
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devices/bulk/edit
     *
     * [
     *   {
     *     "id":"0001",
     *     "name":"Device 1 name",
     *     "lat":44.238767642997374,
     *     "lng":4.296564571559429,
     *     "deviceTypeId":"4dc3ff652f0f79bbe5c6785a",
     *     "tokenRenewForbidden":true
     *   }, {
     *     "id":"0002",
     *     "name":"Device 2 name",
     *     "lat":45.167965154987326,
     *     "lng":3.998845362154745
     *   }
     * ]
     *
     *
     * Parameters:
     *
     *     None
     *
     * Fields:
     *
     *     id: device id to change
     *     name: new device name
     *     lat: new device latitude
     *     lng: new device longitude
     *     tokenRenewForbidden: prevent the device to renew its token after the end of its current contract, even if a new one is linked. (DEPRECATED - It will be removed on the 30th of March 2016. Replaced by /api/devices/token/forbid-renewal method)
     *     deviceTypeId: id of the new device type
     *     productCertificate: new product certificate
     *
     * all fields except id are optional.
     *
     */
    @ApiOperation(
            value = "Editing a list of devices",
            notes = "Modifies devices by providing a list of changes. <br/>"+
                    "In the BODY are provided the information related to the device list replace<br/>" +
                    "",
            response = SigfoxApiDeviceEditReturn.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceEditReturn.class)
    })
    @RequestMapping(
            value ="/devices/bulk/edit",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> editListOfDevices(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicesList", value = "List of modification on devices")
            @Valid @RequestBody List<SigfoxApiDeviceEditPost> devicesList
    ) {

        SigfoxApiProxy<SigfoxApiDeviceEditReturn> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceEditReturn>(proxy.proxify(request, mapper.writeValueAsString(devicesList)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deleting devices
     *
     * Deletes a device, if it has no associated messages and no BSS order token. Use with care!
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devices/{device-id}/delete
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     *
     * Response
     *
     * The response has no body. A status code 200 is returned when the device have been deleted successfully.
     *
     */
    @ApiOperation(
            value = "Deleting devices",
            notes = "Deletes a device, if it has no associated messages and no BSS order token. Use with care!<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/delete",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> deleteDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Error status events sent for a device
     *
     * Get the communication down events that were sent for a device, in reverse chronological
     * order (most recent events first).
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{device-id}/status/error
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of status events to return, default 100.
     *     before: return status events sent before this timestamp in milliseconds since Unix Epoch.
     *     since: return status events sent since this timestamp in milliseconds since Unix Epoch.
     *     offset: number of events to skip (between 0 and 5000). Normally you should not have to worry about this parameter
     *     as the app will set this parameter automatically in the response, in the URL of the next page (see field next in
     *     response)
     */
    @ApiOperation(
            value = "Error status events sent for a device",
            notes = "Get the communication down events that were sent for a device, in reverse chronological " +
                    "order (most recent events first)." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    " <li>limit: maximum number of messages to return</li>" +
                    " <li>before: return messages sent before this timestamp (in seconds since the Unix Epoch)</li>" +
                    " <li>since: return messages sent since this timestamp (in seconds since the Unix Epoch).</li>" +
                    " <li>offset: number of messages to skip (between 0 and 5000). Normally you should not have " +
                    " to worry about this parameter as the app will set this parameter automatically " +
                    " in the response, in the URL of the next page (see field next in response)</li>" +
                    "</ul>",
            response = SigfoxApiStatusErrorResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiStatusErrorResponse.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/status/error",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceErrors(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id,
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
                    Optional<Integer> offset
    ) {

        SigfoxApiProxy<SigfoxApiStatusErrorResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiStatusErrorResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Warning status events sent for a device
     *
     * Get the network issues events that were sent for a device, in reverse chronological order (most recent events first).
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{device-id}/status/warn
     *
     * Parameters:
     *
     *     device-id the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of status events to return, default 100.
     *     before: return status events sent before this timestamp in milliseconds since Unix Epoch.
     *     since: return status events sent since this timestamp in milliseconds since Unix Epoch.
     *     offset: number of events to skip (between 0 and 5000). Normally you should not have to worry about this parameter
     *     as the app will set this parameter automatically in the response, in the URL of the next page (see field next in
     *     response)
     */
    @ApiOperation(
            value = "Warning status events sent for a device",
            notes = "Get the network issues events that were sent for a device, in reverse chronological order (most recent events first)." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    " <li>limit: maximum number of messages to return</li>" +
                    " <li>before: return messages sent before this timestamp (in seconds since the Unix Epoch)</li>" +
                    " <li>since: return messages sent since this timestamp (in seconds since the Unix Epoch).</li>" +
                    " <li>offset: number of messages to skip (between 0 and 5000). Normally you should not have " +
                    " to worry about this parameter as the app will set this parameter automatically " +
                    " in the response, in the URL of the next page (see field next in response)</li>" +
                    "</ul>",
            response = SigfoxApiStatusWarningResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiStatusWarningResponse.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/status/warn",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceWarnings(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id,
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
                    Optional<Integer> offset
    ) {

        SigfoxApiProxy<SigfoxApiStatusWarningResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiStatusWarningResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Network status of a device
     *
     * Return the network status for a specific device. The value is based on the computed state
     * of all the base stations which received the last messages for this device.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{id}/networkstate
     *
     * Parameters:
     *
     *     id the identifier of the device.
     *
     * Response
     *
     * {
     *   "networkStatus" : "OK|NOK"
     * }
     *
     *
     * Fields:
     *
     *     networkStatus: the network status for the device. OK, the network works, NOK otherwise.
     */
    @ApiOperation(
            value = "Network status of a device",
            notes = "Return the network status for a specific device. The value is based on the computed state " +
                    "of all the base stations which received the last messages for this device." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceNetworkStatus.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceNetworkStatus.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/networkstate",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceWarnings(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceNetworkStatus> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceNetworkStatus>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Update the token renewal forbidden state of a device
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devices/token/forbid-renewal
     *
     *     {
     *         "forbidden": ["0DFE", "4125", "5169"],
     *         "allowed" : ["420E", "7F8C"]
     *     }
     *
     *
     * forbidden and allowed are mandatory. Provide empty arrays if no device has to be updated.
     *
     * Response
     *
     * {
     *     "total" : 12,
     *     "error" : 2,
     *     "failed": ["420E", "4125"]
     * }
     *
     *
     * Fields:
     *
     *     total: the total number of devices that should be updated.
     *     error: the number of errors.
     *     failed: the id of the devices that failed to update.
     */
    @ApiOperation(
            value = "Update the token renewal forbidden state of a device",
            notes = "Update the token renewal forbidden state of a device: <br/>",
            response = SigfoxApiDeviceTokenRenewalResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceTokenRenewalResponse.class),
    })
    @RequestMapping(
            value ="/devices/token/forbid-renewal",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> tokenForbidRenewal(
            HttpServletRequest request,
            @ApiParam(required = true, name = "renewalInput", value = "List of renewal changes")
            @Valid @RequestBody SigfoxApiDeviceTokenRenewalInput renewalInput
    ) {
        SigfoxApiProxy<SigfoxApiDeviceTokenRenewalResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceTokenRenewalResponse>(proxy.proxify(request, mapper.writeValueAsString(renewalInput)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Disengage sequence number
     *
     * Disengage sequence number check for next message of a device.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{id}/disengage
     *
     * Parameters:
     *
     *     id the identifier of the device.
     *
     * Response
     *
     * The response has no body. A status code 200 is returned when the disengagment have been done successfully.
     */
    @ApiOperation(
            value = "Disengage sequence number",
            notes = "Disengage sequence number check for next message of a device." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/disengage",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> disengageSeqNumber(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Device message metrics
     *
     * Returns the total number of device messages for one device, this day, this week and this month.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devices/{id}/messages/metric
     *
     * Parameters:
     *
     *     id the identifier of the device (in hexadecimal).
     *
     * Response
     *
     * {
     *   "lastDay": 47,
     *   "lastWeek": 276,
     *   "lastMonth": 784
     * }
     *
     *
     * Fields:
     *
     *     lastDay: The number of message received from this device during the last 24 hours.
     *     lastWeek: The number of message received from this device during the last 7 days.
     *     lastMonth: The number of message received from this device during the last 30 days.
     *
     */
    @ApiOperation(
            value = "Device message metrics",
            notes = "Returns the total number of device messages for one device, this day, this week and this month." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiMessageMetric.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiMessageMetric.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/messages/metric",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceMessageMetrics(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id
    ) {

        SigfoxApiProxy<SigfoxApiMessageMetric> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiMessageMetric>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Device consumptions
     *
     * Get a Device’s consumptions for a year
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/device/{device-id}/consumptions/{year}
     *
     * Parameters:
     *
     *     device-id: the identifier of the device (in hexa).
     *     year: the year of consumption.
     *
     */
    @ApiOperation(
            value = "Device consumptions",
            notes = "Get a Device’s consumptions for a year" +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>device-id : the identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.</li>" +
                    " <li>year : the year of consumption.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceYearlyConsumption.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceYearlyConsumption.class)
    })
    @RequestMapping(
            value ="/devices/{device_id}/consumptions/{year}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceYearlyMetric(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device_id", value = "The identifier of the device, as returned by the /api/devicetypes/{devicetype-id}/devices endpoint.")
            @PathVariable("device_id") String device_id,
            @ApiParam(required = true, name = "year", value = "The year of consumption.")
            @PathVariable("year") String year
    ) {

        SigfoxApiProxy<SigfoxApiDeviceYearlyConsumption> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceYearlyConsumption>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}



