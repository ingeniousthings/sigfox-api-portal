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
package fr.ingeniousthings.sigfox_api.v2_0;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.sigfox.apiv2.models.*;
import fr.ingeniousthings.sigfox_api.SigfoxApiProxy;
import fr.ingeniousthings.sigfox_api.SigfoxApiProxyException;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Api(value="devicetypes", tags="sigfox-v2-devicetypes-api")
@RequestMapping(value = "/api/v2/device-types")
@CrossOrigin
@RestController
public class SigfoxV2DeviceTypesApi {

    /**
     * Device types list
     *
     * Fetches a list of device types according to request filter parameters and the user's visibility permissions.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/device-types/
     *
     * Parameters:
     *
     *     None
     *
     * Optionally, the request can also have the following parameter:
     *
     * name - String : Searches by device type name.
     * groupIds - String[] : Searches for device types which are attached to the given groups.
     * contactId - String : Searches for device types which are attached to the given contract.
     * payloadType - int : Searches device types by payload type
     *                      1 -> Regular (raw payload)
     *                      2 -> Custom grammar
     *                      3 -> Geolocation
     *                      4 -> Display in ASCII
     *                      5 -> Radio planning frame
     *                      6 -> Sensitv2
     * deep - boolean : If the group identifier is specified, also includes its subgroups.
     * sort - String : The field on which the list will be sorted. (field to sort ascending or -field to sort descending).
     *                      id / -id
     *                      name / -name
     * fields - String[] - Defines the available device type’s fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     * limit - int - Defines the maximum number of device types to return, default is 100
     * offset - int - Defines the number of device types to skip
     * pageId -int - Token representing the page to retrieve
     */


    @ApiOperation(
            value = "Device types list",
            notes = "Fetches a list of device types according to request filter parameters and the user's visibility permissions <br/>"+
                    "Parameters: Optionally you can add parameters :<br/>" +
                    "<ul>" +
                    " <li>name (String): Searches by device type name.</li>" +
                    " <li>groupIds (String[]): Searches for device types which are attached to the given groups list.</li>" +
                    " <li>contactId (String): Searches for device types which are attached to the given contract.</li>" +
                    " <li>payloadType (int): Searches device types by payload type : " +
                    "<ul>" +
                        "<li>1 -> Regular (raw payload)</li>" +
                        "<li>2 -> Custom grammar</li>" +
                        "<li>3 -> Geolocation</li>" +
                        "<li>4 -> Display in ASCII</li>" +
                        "<li>5 -> Radio planning frame</li>" +
                        "<li>6 -> Sensitv2</li>" +
                    "</ul>" +
                    ".</li>" +
                    " <li>deep (boolean): If the group identifier is specified, also includes its subgroups.</li>" +
                    " <li>sort (String): The field on which the list will be sorted. (field to sort ascending or -field to sort descending)." +
                        "<ul>" +
                            "<li>id / -id</li>" +
                            "<li>name / -name</li>" +
                        "</ul>" +
                    "</li>" +
                    "<li>fields (String[]): Defines the available device type’s fields to be returned in the response." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2DeviceTypeListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2DeviceTypeListResponse.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTypesList(
            HttpServletRequest request,
            @RequestParam("name")
            @ApiParam(
                    required = false,
                    name = "name",
                    value = "Searches by device type name."
            ) Optional<String> name,

            @RequestParam("groupIds")
            @ApiParam(
                    required = false,
                    name = "groupIds",
                    example = "group1,group2,...",
                    value = "Searches for device types which are attached to the given groups list."
            ) Optional<String> groupIds,

            @RequestParam("contactId")
            @ApiParam(
                    required = false,
                    name = "contactId",
                    value = "Searches for device types which are attached to the given contract."
            ) Optional<String> contactId,

            @RequestParam("payloadType")
            @ApiParam(
                    required = false,
                    name = "payloadType",
                    allowableValues = "1,2,3,4,5,6",
                    value = "Searches device types by payload type"
            ) Optional<Integer> payloadType,

            @RequestParam("deep")
            @ApiParam(
                    required = false,
                    name = "deep",
                    value = "If the group identifier is specified, also includes its subgroups."
            ) Optional<Boolean> deep,

            @RequestParam("sort")
            @ApiParam(
                    required = false,
                    name = "sort",
                    allowableValues = "id,-id,name,-name",
                    value = "The field on which the list will be sorted. (field to sort ascending or -field to sort descending)."
            ) Optional<String> sort,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device type’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields,

            @RequestParam("limit")
            @ApiParam(
                    required = false,
                    name = "limit",
                    value = "Defines the maximum number of device types to return, default is 100.",
                    defaultValue = "100"
            ) Optional<Integer> limit,

            @RequestParam("offset")
            @ApiParam(
                    required = false,
                    name = "offset",
                    value = "Defines the number of device types to skip."
            ) Optional<Integer> offset,

            @RequestParam("pageId")
            @ApiParam(
                    required = false,
                    name = "pageId",
                    value = "Token representing the page to retrieve."
            ) Optional<Integer> pageId

    ) {

        SigfoxApiProxy<SigfoxApiv2DeviceTypeListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2DeviceTypeListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Fetches the device type's information
     *
     * Get the description of a particular device type
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/device-types/{id}
     *
     * Parameters:
     *     id: the device type identifier as returned by the /api/v2/device-types endpoint.
     *
     * Optionally, the request can also have the following parameter:
     *
     * fields - String[] - Defines the available device type’s fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *
     */
    @ApiOperation(
            value = "Fetches the device type's information",
            notes = "Get the description of a particular device type. <br/>"+
                    "Parameter:<br/>" +
                    "<ul>" +
                    " <li>id (String): The device type’s identifier</li>" +
                    "</ul>" +
                    "Optional Parameter:<br/>" +
                    "<ul>" +
                    "<li>fields (String[]): Defines the available device type’s fields to be returned in the response." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "</ul>",
            response = SigfoxApiv2DeviceType.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2DeviceType.class)
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTypes(
            HttpServletRequest request,
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("id") String id,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device type’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields

            ) {

        SigfoxApiProxy<SigfoxApiv2DeviceType> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2DeviceType>(proxy.proxify(request), HttpStatus.OK);
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
     * POST https://backend.sigfox.com/api/v2/device-types/
     *
     * {
     *  "name": "string",
     *  "keepAlive": 0,
     *  "alertEmail": "string",
     *  "payloadType": 0,
     *  "payloadConfig": "string",
     *  "downlinkMode": 0,
     *  "downlinkDataString": "string",
     *  "description": "string",
     *  "groupId": "string",
     *  "contractId": "string",
     *  "geolocPayloadConfigId": "string",
     *  "automaticRenewal": true
     * }
     *
     */

    @ApiOperation(
            value = "Create a new device type",
            notes = "Create a new device type. <br/>"+
                    "In the Body are provided the information related to the devicetype to create<br/>" +
                    "Returns a structure with deviceTypeId.",
            response = SigfoxApiv2DeviceTypeId.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2DeviceTypeId.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createDeviceType(
            HttpServletRequest request,

            @ApiParam(required = true, name = "deviceType", value = "The device type definition to create")
            @Valid @RequestBody SigfoxApiv2DeviceTypeCreate deviceType
    ) {

        SigfoxApiProxy<SigfoxApiv2DeviceTypeId> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2DeviceTypeId>(proxy.proxify(request, mapper.writeValueAsString(deviceType)), HttpStatus.OK);
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
     *     PUT https://backend.sigfox.com/api/v2/device-types/{id}
     *
     *     {
     *          "keepAlive": 12000,
     *          "payloadType": 3,
     *          "name": "MyNewName",
     *          "payloadConfig": "Device_Type::uint:8 Firmware_Version_Battery_status_byte::uint:8 Voltage_Value::uint:16:little-endian",
     *          "downlinkMode": 0,
     *          "downlinkDataString": "eeeeeeeeeeeeeeee",
     *          "automaticRenewal": true
     *      }
     *
     *
     */
    @ApiOperation(
            value = "Update the device type",
            notes = "Update the device type. <br/>"+
                    "In the Body are provided the information related to the devicetype to update.<br/>" +
                    "The structure is deviceTypeUpdate" +
                    "Parameter:<br/>" +
                    "<ul>" +
                    " <li>id (String): The device type’s identifier</li>" +
                    "</ul>" +
                    "",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No Content - Success", response = String.class)
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> editDeviceType(
            HttpServletRequest request,
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("id") String id,

            @ApiParam(required = true, name = "deviceType", value = "The device type description to update")
            @Valid @RequestBody SigfoxApiv2DeviceTypeUpdate deviceType
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(deviceType)), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Delete the given device type
     *
     * Delete the given device type
     *
     * Request
     *
     *     DELETE https://backend.sigfox.com/api/v2/device-types/{id}
     *
     * Fields:
     *
     *     id: the id of the device type to delete. This id must correspond to an existing device type.
     */

    @ApiOperation(
            value = "Delete the given device type",
            notes = "Delete the given device type <br/>"+
                    "Parameter:<br/>" +
                    "<ul>" +
                    " <li>id (String): The device type’s identifier</li>" +
                    "</ul>" +
                    "",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No Content - Success", response = String.class),
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE
    )
    @CrossOrigin
    public ResponseEntity<?> deleteDeviceType(
            HttpServletRequest request,
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }


    /**
     * Callback creation
     *
     * Create new callback
     *
     * Request
     *
     *     POST https://backend.sigfox.com/api/v2/device-types/{devicetypeId}/callbacks
     *
     */

    @ApiOperation(
            value = "Create new callback",
            notes = "Create new callback. <br/>"+
                    "In the Body are provided the information related to the callback to create<br/>" +
                    "The structure is callbackCreation <br/>"+
                    "Parameter:<br/>" +
                    "<ul>" +
                    " <li>deviceTypeId (String): The device type’s identifier from which callbacks will be fetched</li>" +
                    "</ul>" +
                    "",
            response = SigfoxApiv2CallbackId.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Success", response = SigfoxApiv2CallbackId.class)
    })
    @RequestMapping(
            value ="/{devicetypeId}/callbacks",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetypeId", value = "The device type’s identifier from which callbacks will be created")
            @PathVariable("devicetypeId") String devicetypeId,
            @ApiParam(required = true, name = "callback", value = "The details of callback to create")
            @Valid @RequestBody SigfoxApiv2CallbackCreation callback
    ) {

        SigfoxApiProxy<SigfoxApiv2CallbackCreation> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2CallbackCreation>(proxy.proxify(request, mapper.writeValueAsString(callback)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Callback Update
     *
     * Update an existing callback
     *
     * Request
     *
     *     PUT https://backend.sigfox.com/api/v2/device-types/{devicetypeId}/callbacks/{id}
     *
     * Parameters:
     *
     *     devicetypeId: the device type identifier as returned by the /api/v2/device-types endpoint.
     *     id : the callback id as returned by the /api/v2/device-types/{devicetypeId}/callbacks
     */

    @ApiOperation(
            value = "Update callback",
            notes = "Update an existing callback. <br/>"+
                    "In the Body are provided the information related to the callback to update<br/>" +
                    "The structure is callbackCreation <br/>"+
                    "Parameter:<br/>" +
                    "<ul>" +
                    " <li>deviceTypeId (String): The device type’s identifier from which callbacks will be fetched</li>" +
                    " <li>id (String): the id of the callback as found in the /api/v2/devicetypes/{devicetypeId}/callbacks endpoint.<li>" +
                    "</ul>" +
                    "",
            response = SigfoxApiv2CallbackId.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No Content - Success", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetypeId}/callbacks/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> updateCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetypeId", value = "The device type’s identifier from which callbacks will be created")
            @PathVariable("devicetypeId") String devicetypeId,
            @ApiParam(required = true, name = "id", value = "The callback’s identifier.")
            @PathVariable("id") String id,
            @ApiParam(required = true, name = "callback", value = "The details of callback to update")
            @Valid @RequestBody SigfoxApiv2CallbackCreation callback
    ) {

        SigfoxApiProxy<SigfoxApiv2CallbackCreation> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2CallbackCreation>(proxy.proxify(request, mapper.writeValueAsString(callback)), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Callback list
     *
     * List the callbacks for a given device type
     *
     * Request
     *
     *     GET https://backend.sigfox.com/api/v2/device-types/{devicetypeId}/callbacks
     *
     *
     * Parameters:
     *
     *     devicetypeId: the device type identifier as returned by the /api/devicetypes endpoint.
     */

    @ApiOperation(
            value = "Fetches list of callbacks for a device-type.",
            notes = "Fetches a list of callbacks according to request filter parameters and the user's visibility permissions.<br/>"+
                    "Parameters: you need to add parameters :<br/>" +
                    "<ul>" +
                    " <li>deviceTypeId (String): The device type’s identifier from which callbacks will be fetched</li>" +
                    "</ul>",
            response = SigfoxApiv2CallbackResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Successful response", response = SigfoxApiv2CallbackResponse.class)
    })
    @RequestMapping(
            value ="/{devicetypeId}/callbacks",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCallbackList(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetypeId", value = "The device type’s identifier from which callbacks will be fetched")
            @PathVariable("devicetypeId") String devicetypeId
    ) {

        SigfoxApiProxy<SigfoxApiv2CallbackResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CallbackResponse>(proxy.proxify(request), HttpStatus.OK);
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
     *     DELETE https://backend.sigfox.com/api/v2/device-types/{devicetypeId}/callbacks/{id}
     *
     *
     * Parameters:
     *
     *     devicetypId: the device type identifier as returned by the /api/devicetypes endpoint.
     *     id: the id of the callback as found in the /api/devicetypes/{devicetype-id}/callbacks endpoint.
     *
     * Response
     *
     *     204 if it was OK
     *     404 when the device type does not exist, or when the callback id does not correspond to this device type.
     *
     */
    @ApiOperation(
            value = "Delete the given callback",
            notes = "Delete a callback <br/>"+
                    "The needed information (ids) are given in the URL path: <br/>" +
                    "<ul>" +
                    " <li>deviceTypeId (String): The device type’s identifier from which callbacks will be fetched</li>" +
                    " <li>id (String): the id of the callback as found in the /api/v2/devicetypes/{devicetypeId}/callbacks endpoint.<li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No content - Success", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist or when the callback id does not correspond to this device type. ", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetypId}/callbacks/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.DELETE
    )
    @CrossOrigin
    public ResponseEntity<?> deleteCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetypeId", value = "The device type’s identifier from which callbacks will be fetched.")
            @PathVariable("devicetypeId") String devicetypeId,
            @ApiParam(required = true, name = "id", value = "The callback’s identifier.")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.NO_CONTENT);
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
     *     PUT https://backend.sigfox.com/api/v2/device-types/{devicetypeId}/callbacks/{id}/enable?enabled=true
     *
     *
     * Parameters:
     *
     *     devicetypeId: the device type identifier as returned by the /api/v2/device-types endpoint.
     *     id: the id of the callback as found in the /api/v2/device-types/{devicetypeId}/callbacks endpoint.
     *     enabled: true to enable the callback, false to disable it.
     *
     */
    @ApiOperation(
            value = "Callback enable/disable",
            notes = "Enable or disable a callback <br/>"+
                    "The needed information (ids) are given in the URL path: <br/>" +
                    "<ul>" +
                    " <li>deviceTypeId (String): The device type’s identifier from which callbacks will be fetched</li>" +
                    " <li>id (String) d: the id of the callback as found in the /api/v2/devicetypes/{devicetypeId}/callbacks endpoint.<li>" +
                    "<li>enabled: true to enable the callback, false to disable it.</li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No-content - Success", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist or when the callback id does not correspond to this device type. ", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetypeId}/callbacks/{id}/enable",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> enableCallback(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetypeId", value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("devicetypeId") String devicetypeId,
            @ApiParam(required = true, name = "id", value = "The id of the callback as found in the /api/v2/device-types/{devicetypeId}/callbacks endpoint.")
            @PathVariable("id") String id,
            @RequestParam("enable")
            @ApiParam(required = true, name = "enable", value = "true to enable the callback, false to disable it.")
            String enable
            ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.NO_CONTENT);
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
     *     POST https://backend.sigfox.com/api/v2/device-types/{devicetypeId}/callbacks/{id}/downlink
     *
     *
     * Parameters:
     *
     *     devicetypeId: the device type identifier as returned by the /api/v2/device-types endpoint.
     *     id: the id of the callback as found in the /api/v2/device-types/{devicetypeId}/callbacks endpoint.
     *
     * Response
     *
     *     204 if it was OK
     *     404 when the device type does not exist, or when the callback id does not correspond to this device type.
     */
    @ApiOperation(
            value = "Callback downlink selection",
            notes = "Select a downlink callback. The given callback will be selected as the downlink one, the one that was previously selected will be no more be selected for downlink.<br/>"+
                    "The needed information (ids) are given in the URL path: <br/>" +
                    "<ul>" +
                    " <li>deviceTypeId (String): The device type’s identifier from which callbacks will be fetched</li>" +
                    " <li>id (String) d: the id of the callback as found in the /api/v2/devicetypes/{devicetypeId}/callbacks endpoint.<li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No-Content, Success", response = String.class),
            @ApiResponse(code = 404, message= "The device type does not exist or when the callback id does not correspond to this device type. ", response = String.class)
    })
    @RequestMapping(
            value ="/{devicetypeId}/callbacks/{id}/downlink",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> selectForDownlink(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetypeId", value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("devicetypeId") String devicetypeId,
            @ApiParam(required = true, name = "id", value = "The id of the callback as found in the /api/v2/device-types/{devicetypeId}/callbacks endpoint.")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.NO_CONTENT);
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
/*
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
/*
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
            response = SigfoxApiStatusErrorResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiStatusErrorResponse.class)
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

        SigfoxApiProxy<SigfoxApiStatusErrorResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiStatusErrorResponse>(proxy.proxify(request), HttpStatus.OK);
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
/*
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
            response = SigfoxApiStatusWarningResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiStatusWarningResponse.class)
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

        SigfoxApiProxy<SigfoxApiStatusWarningResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiStatusWarningResponse>(proxy.proxify(request), HttpStatus.OK);
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
/*
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
/*
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
/*
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
/*
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
*/

}



