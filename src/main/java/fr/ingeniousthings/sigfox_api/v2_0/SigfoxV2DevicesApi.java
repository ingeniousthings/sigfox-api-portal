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

@Api(value="devices", tags="sigfox-v2-devices-api")
@RequestMapping(value = "/api/v2/devices")
@CrossOrigin
@RestController
public class SigfoxV2DevicesApi {

    /**
     * Devices list
     *
     * Fetches a list of devices according to some request filter parameters and right visibility
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/
     *
     *
     * Parameters:
     *
     *     operatorId - Returns all devices under the given operator
     *
     * Optionally, the request can also have the following parameter:
     *
     * id (string) - The device’s identifier (hexadecimal format)
     * avgSnrMin (float) - Inclusive filter on minimum average SNR (Signal Noise Ratio)
     * avgSnrMax (float) - Inclusive filter on maximum average SNR (Signal Noise Ratio)
     * avgRssiMin (float) - Inclusive filter on minimum average RSSI (Received Signal Strength Indicator)
     * avgRssiMax (float) - Inclusive filter on maximum average RSSI (Received Signal Strength Indicator)
     * groupIds (string,string) - Returns all devices under the given groups (included sub-groups if the parameter deep is equals to true)
     * deep (boolean) - if true, we search by groups and subgroups through the parameter ‘groupIds’ (default false)
     * deviceTypeId (string) - Returns all devices of the given device type
     * sort (string) - The field on which the list will be sorted. (field to sort ascending or -field to sort descending)
     *                 Available values : id, -id, name, -name, lastCom, -lastCom
     *                 Default value : name
     * fields (strings,strings) - defines fields to be returned in the response.
     *                  fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:
     *                  example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *                  for now available strings are
     *                  deviceType(name),group(name,type,level,bssId,customerBssId),contract(name),productCertificate(name),modemCertificate(name)
     * limit (int) - Defines the maximum number of devices to return, default is 100
     *               Default value : 100
     * offset (int) - Defines the number of devices to skip
     * pageId (int) - Token representing the page to retrieve
     *
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
                    "     </ul>"+
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2DevicesListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2DevicesListResponse.class)
    })
    @RequestMapping(
            value ="/",
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
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Group information
     *
     * Get the description of a particular group
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/groups/{id}
     *
     * id: the group identifier as returned by the /api/v2/groups endpoint.
     *
     */
    /*
    @ApiOperation(
            value = "Fetches the group's information",
            notes = "Get the description of a particular group. <br/>"+
                    "Parameters the group ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the group identifier as returned by the /api/v2/groups endpoint</li>" +
                    "<li>fields (query-String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "</ul>",
            response = SigfoxApiv2Group.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2Group.class)
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGroupInfo(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The group’s identifier")
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

        SigfoxApiProxy<SigfoxApiv2Group> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2Group>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Group creation
     *
     * Create a new Group
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/groups/
     *
     * Parameter :
     *    In the body a struture SigfoxApiV2GroupBase
     */
/*
    @ApiOperation(
            value = "Create a new group",
            notes = "Create a new Group. <br/>"+
                    "In the Body are provided the information related to the group to create<br/>",
            response = SigfoxApiv2GroupId.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Success", response = SigfoxApiv2GroupId.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createGroup(
            HttpServletRequest request,
            @ApiParam(required = true, name = "group", value = "Group description")
            @Valid @RequestBody SigfoxApiv2GroupBase group
    ) {

        SigfoxApiProxy<SigfoxApiv2GroupId> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2GroupId>(proxy.proxify(request, mapper.writeValueAsString(group)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Group edition
     *
     * Update the group
     *
     * Request
     *
     * PUT https://backend.sigfox.com/api/v2/groups/{id}
     *
     * Fields:
     *
     *     id: the id of the group to delete. This id must correspond to an existing group which is not an operator, has none device type, none BSS order,none child group, and none user.
     *
     */
/*
    @ApiOperation(
            value = "Update the group",
            notes = "Update the group. <br/>"+
                    "In the Body are provided the information related to the group to edit<br/>" +
                    "Parameters the group ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the group identifier as returned by the /api/v2/groups endpoint</li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No Content - Success", response = String.class)
    })
    @RequestMapping(
            value ="/{id}",
            //produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> editGroup(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The group’s identifier")
            @PathVariable("id") String id,

            @ApiParam(required = true, name = "group", value = "Group description")
            @Valid @RequestBody SigfoxApiv2GroupBase group
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(group)), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Group deletion
     *
     * Group deletion
     *
     * Request
     *
     * DELETE https://backend.sigfox.com/api/v2/groups/{id}
     *
     * Fields:
     *
     *     id: the id of the group to delete. This id must correspond to an existing group which is not an operator, has none device type, none BSS order,none child group, and none user.
     *
     * Response
     *
     *     204 if it was OK
     *
     */
/*
    @ApiOperation(
            value = "Delete the given group",
            notes = "Group deletion <br/>"+
                    "Parameters : the group ID is provided in the URL.<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the group identifier as returned by the /api/v2/groups endpoint</li>" +
                    "</ul>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 204, message= "No-Content, Success", response = String.class),

    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            //consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.DELETE
    )
    @CrossOrigin
    public ResponseEntity<?> deleteGroup(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The group’s identifier")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Fetches the group's error messages
     *
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/groups/{id}/callbacks-not-delivered
     *
     * id: the group identifier as returned by the /api/v2/groups endpoint.
     *
     */
/*
    @ApiOperation(
            value = "Fetches the group's error messages",
            notes = "Get the description of a particular group. <br/>"+
                    "Parameters the group ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String)*: the group identifier as returned by the /api/v2/groups endpoint</li>" +
                    "<li>since (long) : Starting timestamp (posix time)</li>" +
                    "<li>before (long) : Ending timestamp (posix time)</li>" +
                    "<li>limit (int) : Limit number of result</li>" +
                    "<li>offset (int) : Index of result to start with</li>" +
                    "</ul>",
            response = SigfoxApiv2DeviceTypeListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2DeviceTypeListResponse.class)
    })
    @RequestMapping(
            value ="/{id}/callbacks-not-delivered",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGroupCallbackErrors(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The group’s identifier")
            @PathVariable("id") String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)"
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)"
            ) Optional<Long> before,

            @RequestParam("limit")
            @ApiParam(
                    required = false,
                    name = "limit",
                    value = "Limit number of result"
            ) Optional<Integer> limit,

            @RequestParam("offset")
            @ApiParam(
                    required = false,
                    name = "offset",
                    value = "Index of result to start with"
            ) Optional<Integer> offset

            ) {

        SigfoxApiProxy<SigfoxApiv2DeviceTypeListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2DeviceTypeListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

*/
}



