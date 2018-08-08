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

@Api(value="group", tags="sigfox-v2-group-api")
@RequestMapping(value = "/api/v2/groups")
@CrossOrigin
@RestController
public class SigfoxV2GroupApi {

    /**
     * Group list
     *
     * Fetches a list of groups according to some request filter parameters and right visibility
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/groups/
     *
     * If parentIds is provided, fetches all direct sub-groups under the given parents.
     * If parentIds is not provided, fetches all direct sub-groups under the API user’s group.
     * If deep is true, fetches all sub-groups under either given parent groups or the API user group.
     *
     * Parameters:
     *
     *     None
     *
     * Optionally, the request can also have the following parameter:
     *
     * parentIds - arrsyString : The parent group’s identifiers from which the children will be fetched
     * name - String : Searches for groups containing the given text in their name
     * type - array[int] : see details
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
            value = "Fetches a list of groups.",
            notes = "Fetches a list of groups according to some request filter parameters and right visibility. <br/>" +
                    "If parentIds is provided, fetches all direct sub-groups under the given parents.<br/>" +
                    "If parentIds is not provided, fetches all direct sub-groups under the API user’s group.<br/>" +
                    "If deep is true, fetches all sub-groups under either given parent groups or the API user group.<br/>"+
                    "Parameters: Optionally, the request can also have the following parameters (see next response field below):<br/>" +
                    "<ul>" +
                    " <li>name (String): Searches for groups containing the given text in their name.</li>" +
                    " <li>parentIds (String[]): The parent group’s identifiers from which the children will be fetched. example :" +
                    "?parentIds=134523254,526527A45</li>" +
                    " <li>types (int[]): Group’s type : " +
                    "<ul>" +
                    "<li>0 -> SO</li>" +
                    "<li>2 -> Other</li>" +
                    "<li>5 -> SVNO</li>" +
                    "<li>6 -> Partner</li>" +
                    "<li>7 -> NIP</li>" +
                    "<li>8 -> DIST</li>" +
                    "<li>9 -> Channel</li>" +
                    "<li>10 -> Starter</li>" +
                    "<li>11 -> Partner</li>" +
                    "</ul>" +
                    "Example : ?types=0,2,5</li>" +
                    " <li>deep (boolean): If the group identifier is specified, also includes its subgroups.</li>" +
                    " <li>sort (String): The field on which the list will be sorted. (field to sort ascending or -field to sort descending)." +
                    "<ul>" +
                    "<li>id / -id</li>" +
                    "<li>name / -name</li>" +
                    "</ul>" +
                    "</li>" +
                    "<li>fields (String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2GroupListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2GroupListResponse.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGroupList(
            HttpServletRequest request,
            @RequestParam("parentIds")
            @ApiParam(
                    required = false,
                    name = "parentIds",
                    value = "The parent group’s identifiers from which the children will be fetched."
            ) Optional<String> parentIds,

            @RequestParam("deep")
            @ApiParam(
                    required = false,
                    name = "deep",
                    value = "If the group identifier is specified, also includes its subgroups."
            ) Optional<Boolean> deep,

            @RequestParam("name")
            @ApiParam(
                    required = false,
                    name = "name",
                    value = "Searches for groups containing the given text in their name."
            ) Optional<String> name,

            @RequestParam("types")
            @ApiParam(
                    required = false,
                    name = "types",
                    value = "Group’s type list String as a array of int"
            ) Optional<String> types,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device type’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields,

            @RequestParam("sort")
            @ApiParam(
                    required = false,
                    name = "sort",
                    allowableValues = "id,-id,name,-name",
                    value = "The field on which the list will be sorted. (field to sort ascending or -field to sort descending)."
            ) Optional<String> sort,

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

        SigfoxApiProxy<SigfoxApiv2GroupListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2GroupListResponse>(proxy.proxify(request), HttpStatus.OK);
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


}



