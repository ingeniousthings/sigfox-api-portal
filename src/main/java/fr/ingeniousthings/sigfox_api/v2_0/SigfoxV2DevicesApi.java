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

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value="devices", tags="sigfox-v2-devices-api")
@RequestMapping(value = "/api/v2/devices")
@CrossOrigin
@RestController
public class SigfoxV2DevicesApi {

    /**
     * Devices list
     * <p>
     * Fetches a list of devices according to some request filter parameters and right visibility
     * <p>
     * Request
     * <p>
     * GET https://backend.sigfox.com/api/v2/devices/
     * <p>
     * <p>
     * Parameters:
     * <p>
     * operatorId - Returns all devices under the given operator
     * <p>
     * Optionally, the request can also have the following parameter:
     * <p>
     * id (string) - The device’s identifier (hexadecimal format)
     * avgSnrMin (float) - Inclusive filter on minimum average SNR (Signal Noise Ratio)
     * avgSnrMax (float) - Inclusive filter on maximum average SNR (Signal Noise Ratio)
     * avgRssiMin (float) - Inclusive filter on minimum average RSSI (Received Signal Strength Indicator)
     * avgRssiMax (float) - Inclusive filter on maximum average RSSI (Received Signal Strength Indicator)
     * groupIds (string,string) - Returns all devices under the given groups (included sub-groups if the parameter deep is equals to true)
     * deep (boolean) - if true, we search by groups and subgroups through the parameter ‘groupIds’ (default false)
     * deviceTypeId (string) - Returns all devices of the given device type
     * sort (string) - The field on which the list will be sorted. (field to sort ascending or -field to sort descending)
     * Available values : id, -id, name, -name, lastCom, -lastCom
     * Default value : name
     * fields (strings,strings) - defines fields to be returned in the response.
     * fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:
     * example : ?fields=attr1,attr2(attr3,attr4(attr5))
     * for now available strings are
     * deviceType(name),group(name,type,level,bssId,customerBssId),contract(name),productCertificate(name),modemCertificate(name)
     * limit (int) - Defines the maximum number of devices to return, default is 100
     * Default value : 100
     * offset (int) - Defines the number of devices to skip
     * pageId (int) - Token representing the page to retrieve
     */
    @ApiOperation(
            value = "Devices list.",
            notes = "Fetches a list of devices according to some request filter parameters and right visibility. <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>operatorId (String)* : Returns all devices under the given operator</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>id (String): The device’s identifier (hexadecimal format).</li>" +
                    " <li>avgSnrMin (float): Inclusive filter on minimum average SNR (Signal Noise Ratio).</li>" +
                    " <li>avgSnrMax (float): Inclusive filter on maximum average SNR (Signal Noise Ratio).</li>" +
                    " <li>avgRssiMin (float): Inclusive filter on minimum average RSSI (Received Signal Strength Indicator).</li>" +
                    " <li>avgRssiMax (float): Inclusive filter on maximum average RSSI (Received Signal Strength Indicator).</li>" +
                    " <li>groupIds (string[]): Returns all devices under the given groups (included sub-groups if the parameter deep is equals to true).</li>" +
                    " <li>deep (boolean): if true, we search by groups and subgroups through the parameter ‘groupIds’ (default false).</li>" +
                    " <li>deviceTypeId (string): Returns all devices of the given device type</li>" +
                    " <li>sort (string): The field on which the list will be sorted. (field to sort ascending or -field to sort descending)<br/>" +
                    "     Available values : id, -id, name, -name, lastCom, -lastCom<br/>" +
                    "     Default value : name</li>" +
                    " <li>fields (String[]): defines fields to be returned in the response.<br/>" +
                    "     fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:</br>" +
                    "     example : ?fields=attr1,attr2(attr3,attr4(attr5))</br>" +
                    "     for now available strings are " +
                    "     <ul>" +
                    "      <li> deviceType(name)</li>" +
                    "      <li> group(name,type,level,bssId,customerBssId)</li>" +
                    "      <li> contract(name) </li>" +
                    "      <li> productCertificate(name)</li>" +
                    "      <li> modemCertificate(name)</li>" +
                    "     </ul>" +
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2DevicesListResponse.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2DevicesListResponse.class)
    })
    @RequestMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevicesList(
            HttpServletRequest request,
            @RequestParam("operatorId")
            @ApiParam(
                    required = true,
                    name = "operatorId",
                    value = "Returns all devices under the given operator."
            ) String operatorId,

            @RequestParam("id")
            @ApiParam(
                    required = false,
                    name = "id",
                    value = "The device’s identifier (hexadecimal format)."
            ) Optional<String> id,

            @RequestParam("deviceTypeId")
            @ApiParam(
                    required = false,
                    name = "deviceTypeId",
                    value = "Returns all devices of the given device type."
            ) Optional<String> deviceTypeId,

            @RequestParam("avgSnrMin")
            @ApiParam(
                    required = false,
                    name = "avgSnrMin",
                    value = "Inclusive filter on minimum average SNR (Signal Noise Ratio)."
            ) Optional<Double> avgSnrMin,

            @RequestParam("avgSnrMax")
            @ApiParam(
                    required = false,
                    name = "avgSnrMax",
                    value = "Inclusive filter on maximum average SNR (Signal Noise Ratio)."
            ) Optional<Double> avgSnrMax,

            @RequestParam("avgRssiMin")
            @ApiParam(
                    required = false,
                    name = "avgRssiMin",
                    value = "Inclusive filter on minimum average RSSI (Received Signal Strength Indicator)."
            ) Optional<Double> avgRssiMin,

            @RequestParam("avgRssiMax")
            @ApiParam(
                    required = false,
                    name = "avgRssiMax",
                    value = "Inclusive filter on maximum average RSSI (Received Signal Strength Indicator)."
            ) Optional<Double> avgRssiMax,

            @RequestParam("groupIds")
            @ApiParam(
                    required = false,
                    name = "groupIds",
                    value = "Returns all devices under the given groups (included sub-groups if the parameter deep is equals to true).",
                    example = "group1,group2,group3..."
            ) Optional<String> groupIds,

            @RequestParam("deep")
            @ApiParam(
                    required = false,
                    name = "deep",
                    value = "if true, we search by groups and subgroups through the parameter ‘groupIds’.",
                    defaultValue = "false"
            ) Optional<Boolean> deep,

            @RequestParam("sort")
            @ApiParam(
                    required = false,
                    name = "sort",
                    allowableValues = "id, -id, name, -name, lastCom, -lastCom",
                    defaultValue = "name",
                    value = "The field on which the list will be sorted. (field to sort ascending or -field to sort descending)."
            ) Optional<String> sort,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device type’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively" +
                            "for now available strings are " +
                            "     <ul>" +
                            "      <li> deviceType(name)</li>" +
                            "      <li> group(name,type,level,bssId,customerBssId)</li>" +
                            "      <li> contract(name) </li>" +
                            "      <li> productCertificate(name)</li>" +
                            "      <li> modemCertificate(name)</li>" +
                            "     </ul>"

            ) Optional<String> fields,


            @RequestParam("limit")
            @ApiParam(
                    required = false,
                    name = "limit",
                    value = "Defines the maximum number of groups to return, default is 100.",
                    defaultValue = "100"
            ) Optional<Integer> limit,

            @RequestParam("offset")
            @ApiParam(
                    required = false,
                    name = "offset",
                    value = "Defines the number of groups to skip."
            ) Optional<Integer> offset,

            @RequestParam("pageId")
            @ApiParam(
                    required = false,
                    name = "pageId",
                    value = "Token representing the page to retrieve."
            ) Optional<Integer> pageId

    ) {

        SigfoxApiProxy<SigfoxApiv2DevicesListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2DevicesListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }


    /**
     * Device information
     * <p>
     * Fetches the device's information
     * <p>
     * Request
     * <p>
     * GET https://backend.sigfox.com/api/v2/devices/{id}
     * <p>
     * Parameters:
     * <p>
     * id - The device’s identifier (hexadecimal format)
     * <p>
     * Optionally, the request can also have the following parameter:
     * <p>
     * fields (strings,strings) - defines fields to be returned in the response.
     * fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:
     * example : ?fields=attr1,attr2(attr3,attr4(attr5))
     * for now available strings are
     * deviceType(name),group(name,type,level,bssId,customerBssId),contract(name),productCertificate(name),modemCertificate(name)
     */
    @ApiOperation(
            value = "Fetches the device's information",
            notes = "Get the description of a particular device. <br/>" +
                    "Parameters the device ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) /api/v2/devices endpoint</li>" +
                    "<li>fields (query-String[]): Defines fields to be returned in the response." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "</ul>",
            response = SigfoxApiv2Device.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2Device.class)
    })
    @RequestMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceInfo(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier (hexadecimal format).")
            @PathVariable("id") String id,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields

    ) {

        SigfoxApiProxy<SigfoxApiv2Device> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2Device>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }

    /**
     * Device creation
     * <p>
     * Create a new device
     * <p>
     * Request
     * <p>
     * POST https://backend.sigfox.com/api/v2/devices/
     * <p>
     * Parameter :
     * In the body a struture SigfoxApiV2GroupBase
     */
    @ApiOperation(
            value = "Create a new device",
            notes = "Create a new device. <br/>" +
                    "In the Body are provided the information related to the device to be created<br/>",
            response = SigfoxApiv2DeviceId.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success", response = SigfoxApiv2DeviceId.class)
    })
    @RequestMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "device", value = "Device description")
            @Valid @RequestBody SigfoxApiv2DeviceCreation device
    ) {
        SigfoxApiProxy<SigfoxApiv2DeviceId> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2DeviceId>(proxy.proxify(request, mapper.writeValueAsString(device)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Device edition
     * <p>
     * Update the device
     * <p>
     * Request
     * <p>
     * PUT https://backend.sigfox.com/api/v2/devices/{id}
     * <p>
     * Fields:
     * <p>
     * id: The device’s identifier (hexadecimal format) to update
     * <p>
     * body: The device information to be updated
     */
    @ApiOperation(
            value = "Update the device",
            notes = "Update the device. <br/>" +
                    "In the Body are provided the information related to the device to update<br/>" +
                    "Parameters the device ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) to update</li>" +
                    "</ul>",
            response = String.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content - Success", response = String.class)
    })
    @RequestMapping(
            value = "/{id}",
            //produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> editDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier")
            @PathVariable("id") String id,

            @ApiParam(required = true, name = "device", value = "device description")
            @Valid @RequestBody SigfoxApiv2DeviceUpdate device
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(device)), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Device deletion
     * <p>
     * device deletion
     * <p>
     * Request
     * <p>
     * DELETE https://backend.sigfox.com/api/v2/devices/{id}
     * <p>
     * Fields:
     * <p>
     * id: the id of the device to delete.
     * <p>
     * Response
     * <p>
     * 204 if it was OK
     */
    @ApiOperation(
            value = "Delete the given device",
            notes = "Device deletion <br/>" +
                    "Parameters : The device’s identifier (hexadecimal format).<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format)</li>" +
                    "</ul>",
            response = String.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "No-Content, Success", response = String.class),

    })
    @RequestMapping(
            value = "/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            //consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.DELETE
    )
    @CrossOrigin
    public ResponseEntity<?> deleteDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier")
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
     * Disable sequence number check for next message
     * <p>
     * Request
     * <p>
     * POST https://backend.sigfox.com/api/v2/devices/{id}/disengage
     * <p>
     * Fields:
     * <p>
     * id: The device’s identifier (hexadecimal format)
     * <p>
     * Response
     * <p>
     * 204 if it was OK
     */
    @ApiOperation(
            value = "Disable sequence number check for next message",
            notes = "Disable sequence number check for next message <br/>" +
                    "Parameters : The device’s identifier (hexadecimal format).<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format)</li>" +
                    "</ul>",
            response = String.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "No-Content, Success", response = String.class),

    })
    @RequestMapping(
            value = "/{id}/disengage",
            produces = {MediaType.TEXT_HTML_VALUE},
            //consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> disengageDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier")
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
     * Get a Device's consumptions for a year
     * <p>
     * Request
     * <p>
     * GET https://backend.sigfox.com/api/v2/devices/{id}/consumptions/{year}
     * <p>
     * Parameters:
     * <p>
     * id - The device’s identifier (hexadecimal format)
     * year - The year of the consumptions
     */
    @ApiOperation(
            value = "Get a Device's consumptions for a year",
            notes = "Get a Device's consumptions for a year. <br/>" +
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) /api/v2/devices endpoint</li>" +
                    "<li>year (path-Integer): The year of the consumptions." +
                    "</ul>",
            response = SigfoxApiv2Consumption.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2Consumption.class)
    })
    @RequestMapping(
            value = "/{id}/consumptions/{year}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceConsumptionsPerYear(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier (hexadecimal format).")
            @PathVariable("id") String id,
            @ApiParam(required = true, name = "year", value = "The year of the consumptions.")
            @PathVariable("year") String year
    ) {

        SigfoxApiProxy<SigfoxApiv2Consumption> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2Consumption>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }

    /**
     * Get a Device's consumptions for a given month
     *
     * Request
     * GET https://backend.sigfox.com/api/v2/devices/{id}/consumptions/{year}/{month}
     *
     * Parameters:
     * id - The device’s identifier (hexadecimal format)
     * year - The year of the consumptions
     * month - The month of the consumptions
     */
    @ApiOperation(
            value = "Get a Device's consumptions for a given month",
            notes = "Get a Device's consumptions for a given month. <br/>" +
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) /api/v2/devices endpoint</li>" +
                    "<li>year (path-Integer): The year of the consumptions." +
                    "<li>month (path-Integer): The month of the consumptions." +
                    "</ul>",
            response = SigfoxApiv2Consumption.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2Consumption.class)
    })
    @RequestMapping(
            value = "/{id}/consumptions/{year}/{month}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceConsumptionsPerYearMonth(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier (hexadecimal format).")
            @PathVariable("id") String id,
            @ApiParam(required = true, name = "year", value = "The year of the consumptions.")
            @PathVariable("year") String year,
            @ApiParam(required = true, name = "month", value = "The month of the consumptions.")
            @PathVariable("month") String month
    ) {

        SigfoxApiProxy<SigfoxApiv2Consumption> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2Consumption>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }


    /**
     * Unsubscribe a token
     *
     * Request
     *
     * PUT https://backend.sigfox.com/api/v2/devices/{id}/unsubscribe
     *
     * Fields:
     *
     * id: The device’s identifier (hexadecimal format) to update
     *
     * body:
     *  {
     *      "unsubscriptionTime": 1487065942000
     *  }
     */
    @ApiOperation(
            value = "Unsubscribe a token",
            notes = "Unsubscribe a token. <br/>" +
                    "In the Body are provided the information related to unsubscription time<br/>" +
                    "Parameters the device ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) to update</li>" +
                    "<li>unsubscriptionTime (Json) : as part of the body" +
                    "</ul>",
            response = String.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content - Success", response = String.class)
    })
    @RequestMapping(
            value = "/{id}/unsubscribe",
            //produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> unsubscribeDevice(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier")
            @PathVariable("id") String id,

            @ApiParam(required = true, name = "unsubscriptionTime", value = "device description")
            @Valid @RequestBody SigfoxApiv2TokenUnsubscribe unsubscriptionTime
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(unsubscriptionTime)), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message", HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * Create multiple devices with async job
     *
     * Create multiple devices with async job
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/devices/bulk
     *
     * Parameter :
     *    In the body a struture SigfoxApiv2DeviceBulk
     *
     * Response :
     *     SigfoxApiv2JobDevCreateResponse
     */

    @ApiOperation(
            value = "Create multiple devices with async job",
            notes = "Create multiple devices with async job. <br/>"+
                    "In the Body are provided the information related to the devices to create<br/>",
            response = SigfoxApiv2JobDevCreateResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobDevCreateResponse.class)
    })
    @RequestMapping(
            value ="/bulk",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> bulkDeviceCreation(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devices", value = "Device list")
            @Valid @RequestBody SigfoxApiv2DeviceBulk devices
    ) {

        SigfoxApiProxy<SigfoxApiv2JobDevCreateResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobDevCreateResponse>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Edit multiple devices with async job
     *
     * Edit multiple devices with async job
     *
     * Request
     *
     * PUT https://backend.sigfox.com/api/v2/devices/bulk
     *
     * Parameter :
     *    In the body a struture SigfoxApiv2DeviceBulkEdit
     *
     * Response :
     *     SigfoxApiv2JobDevEditionResponse
     */

    @ApiOperation(
            value = "Edit multiple devices with async job",
            notes = "Edit multiple devices with async job. <br/>"+
                    "In the Body are provided the information related to the devices to edit<br/>",
            response = SigfoxApiv2JobDevEditionResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobDevEditionResponse.class)
    })
    @RequestMapping(
            value ="/bulk",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> bulkDeviceEdition(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devices", value = "Device list")
            @Valid @RequestBody SigfoxApiv2JobDevEditionResponse devices
    ) {

        SigfoxApiProxy<SigfoxApiv2JobDevEditionResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobDevEditionResponse>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Fetches the devices async job status
     *
     * Request:
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/consumptions/{year}/{month}
     *
     * Parameters:
     *
     * jobId (path-String): The job’s identidier (hexademical format)
     *
     */
    @ApiOperation(
            value = "Fetches the devices registration async job status",
            notes = "Fetches the devices registration async job status. <br/>" +
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>jobId (path-String): The job’s identidier (hexademical format)</li>" +
                    "</ul>",
            response = SigfoxApiv2RegistrationJob.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2RegistrationJob.class)
    })
    @RequestMapping(
            value = "/bulk/{jobId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevicesJobStatus(
            HttpServletRequest request,
            @ApiParam(required = true, name = "jobId", value = "The job’s identidier (hexademical format).")
            @PathVariable("jobId") String jobId
    ) {

        SigfoxApiProxy<SigfoxApiv2RegistrationJob> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2RegistrationJob>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }





    /**
     * Suspend multiple devices
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/devices/bulk/suspend
     *
     * Parameter :
     *    In the body a struture List<String> ["007B480F","007B4810","007B4811"]
     *
     * Response :
     *     SigfoxApiv2JobDevCreateResponse
     */

    @ApiOperation(
            value = "Suspend multiple devices",
            notes = "Suspend multiple devices. <br/>"+
                    "In the Body the list of devices to suspend<br/>",
            response = SigfoxApiv2JobIdResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobIdResponse.class)
    })
    @RequestMapping(
            value ="/bulk/suspend",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> suspendMultipleDevicesAction(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devices", value = "Device list", example = "[\"007B480F\",\"007B4810\",\"007B4811\"]" )
            @Valid @RequestBody List<String> devices
    ) {
        SigfoxApiProxy<SigfoxApiv2JobIdResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobIdResponse>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Fetches the devices async job status for suspend action
     *
     * Request:
     *
     * GET https://backend.sigfox.com/api/v2/devices/bulk/suspend/{jobId}
     *
     * Parameters:
     *
     * jobId (path-String): The job’s identidier (hexademical format)
     *
     */
    @ApiOperation(
            value = "Fetches the devices async job status for suspend action",
            notes = "Fetches the devices async job status for suspend action. <br/>" +
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>jobId (path-String): The job’s identidier (hexademical format)</li>" +
                    "</ul>",
            response = SigfoxApiv2JobAction.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2JobAction.class)
    })
    @RequestMapping(
            value = "/bulk/suspend/{jobId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getSuspendActionJobStatus(
            HttpServletRequest request,
            @ApiParam(required = true, name = "jobId", value = "The job’s identidier (hexademical format).")
            @PathVariable("jobId") String jobId
    ) {

        SigfoxApiProxy<SigfoxApiv2JobAction> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2JobAction>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }

    /**
     * Resume multiple devices
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/devices/bulk/resume
     *
     * Parameter :
     *    In the body a struture List<String> ["007B480F","007B4810","007B4811"]
     *
     * Response :
     *     SigfoxApiv2JobDevCreateResponse
     */

    @ApiOperation(
            value = "Resume multiple devices",
            notes = "Resume multiple devices - a device is resumed after being suspend. <br/>"+
                    "In the Body the list of devices to resume<br/>",
            response = SigfoxApiv2JobIdResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobIdResponse.class)
    })
    @RequestMapping(
            value ="/bulk/resume",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> resumeMultipleDevicesAction(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devices", value = "Device list", example = "[\"007B480F\",\"007B4810\",\"007B4811\"]" )
            @Valid @RequestBody List<String> devices
    ) {
        SigfoxApiProxy<SigfoxApiv2JobIdResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobIdResponse>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Fetches the devices async job status for resume action
     *
     * Request:
     *
     * GET https://backend.sigfox.com/api/v2/devices/bulk/resume/{jobId}
     *
     * Parameters:
     *
     * jobId (path-String): The job’s identidier (hexademical format)
     *
     */
    @ApiOperation(
            value = "Fetches the devices async job status for resume action",
            notes = "Fetches the devices async job status for resume action. <br/>" +
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>jobId (path-String): The job’s identidier (hexademical format)</li>" +
                    "</ul>",
            response = SigfoxApiv2JobAction.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2JobAction.class)
    })
    @RequestMapping(
            value = "/bulk/resume/{jobId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getResumeActionJobStatus(
            HttpServletRequest request,
            @ApiParam(required = true, name = "jobId", value = "The job’s identidier (hexademical format).")
            @PathVariable("jobId") String jobId
    ) {

        SigfoxApiProxy<SigfoxApiv2JobAction> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2JobAction>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }




    /**
     * Restart multiple devices
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/devices/bulk/restart
     *
     * Parameter :
     *    In the body a struture List<String> ["007B480F","007B4810","007B4811"]
     *
     * Response :
     *     SigfoxApiv2JobDevCreateResponse
     */

    @ApiOperation(
            value = "Restart multiple devices",
            notes = "Restart multiple devices - a device is restarted once its subscription has ended and " +
                    "was not in autorenewal mode. This consumes a new subscription. <br/>"+
                    "In the Body the list of devices to restart<br/>",
            response = SigfoxApiv2JobIdResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobIdResponse.class)
    })
    @RequestMapping(
            value ="/bulk/restart",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> restartMultipleDevicesAction(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devices", value = "Device list", example = "[\"007B480F\",\"007B4810\",\"007B4811\"]" )
            @Valid @RequestBody List<String> devices
    ) {
        SigfoxApiProxy<SigfoxApiv2JobIdResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobIdResponse>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * Fetches the devices async job status for restart action
     *
     * Request:
     *
     * GET https://backend.sigfox.com/api/v2/devices/bulk/restart/{jobId}
     *
     * Parameters:
     *
     * jobId (path-String): The job’s identidier (hexademical format)
     *
     */
    @ApiOperation(
            value = "Fetches the devices async job status for restart action",
            notes = "Fetches the devices async job status for restart action. <br/>" +
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>jobId (path-String): The job’s identidier (hexademical format)</li>" +
                    "</ul>",
            response = SigfoxApiv2JobAction.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2JobAction.class)
    })
    @RequestMapping(
            value = "/bulk/restart/{jobId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getRestartActionJobStatus(
            HttpServletRequest request,
            @ApiParam(required = true, name = "jobId", value = "The job’s identidier (hexademical format).")
            @PathVariable("jobId") String jobId
    ) {

        SigfoxApiProxy<SigfoxApiv2JobAction> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2JobAction>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }

    /**
     * Transfer devices to a new device type with async job
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/devices/bulk/restart
     *
     * Parameter :
     *    In the body a structure SigfoxApiv2DeviceBulkTransferResult
     *
     * Response :
     *     SigfoxApiv2JobDevCreateResponse
     */

    @ApiOperation(
            value = "Transfer devices to a new device type",
            notes = "Transfer devices to a new device type with async job. <br/>"+
                    "In the Body the list of devices to transfered<br/>",
            response = SigfoxApiv2JobDevEditionResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobDevEditionResponse.class)
    })
    @RequestMapping(
            value ="/bulk/transfer",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> transferMultipleDevicesAction(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devices", value = "Device list" )
            @Valid @RequestBody SigfoxApiv2DeviceBulkTransferResult devices
    ) {
        SigfoxApiProxy<SigfoxApiv2JobDevEditionResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobDevEditionResponse>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }




    /**
     * Replace multiple devices (moving tokens from a device to another)
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/devices/bulk/replace
     *
     * Parameter :
     *    In the body a structure List<SigfoxApiv2DeviceBulkReplaceElement>
     *
     * Response :
     *     SigfoxApiv2JobReplaceResponse
     */

    @ApiOperation(
            value = "Replace multiple devices",
            notes = "Replace multiple devices - moving tokens from a device to another<br/>" +
                    "In the body a list of devices Pair to be replaced.",
            response = SigfoxApiv2JobReplaceResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job started", response = SigfoxApiv2JobReplaceResponse.class)
    })
    @RequestMapping(
            value ="/bulk/replace",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> replaceMultipleDevicesAction(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicePairs", value = "Device pair list" )
            @Valid @RequestBody List<SigfoxApiv2DeviceBulkReplaceElement> devicePairs
    ) {
        SigfoxApiProxy<SigfoxApiv2JobReplaceResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2JobReplaceResponse>(proxy.proxify(request, mapper.writeValueAsString(devicePairs)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }




}

