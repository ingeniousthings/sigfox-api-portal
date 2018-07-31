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

@Api(value="group", tags="sigfox-group-api")
@RequestMapping(value = "/api/groups")
@CrossOrigin
@RestController
public class SigfoxGroupApi {

    /**
     * Group list
     *
     * Lists all children groups of your group.
     *
     * DEPRECATION notice : Until the 30th of June 2016, the API will list all groups (without paging) when neither limit nor offset query parameters are provided. After this date, specifying neither limit nor offset will return paginated results using default values for those parameters
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/groups
     *
     * Parameters:
     *
     * Optionally, the request can also have the following parameters (see next response field below):
     *
     *     limit: maximum number of groups to return.
     *     offset: number of groups skipped.
     *     parentId: id of the group for which the group list must be retrieved.
     */


    @ApiOperation(
            value = "Group list - Lists all children groups of your group",
            notes = "Lists all children groups of your group. <br/>"+
                    "Parameters: Optionally, the request can also have the following parameters (see next response field below):<br/>" +
                    "<ul>" +
                    " <li>limit: maximum number of groups to return.</li>" +
                    " <li>offset: number of groups skipped.</li>" +
                    " <li>parentId: id of the group for which the group list must be retrieved.</li>" +
                    "</ul>",
            response = SigfoxApiGroupInfoList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiGroupInfoList.class)
    })
    @RequestMapping(
            value ="",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGroupList(
            HttpServletRequest request,
            @RequestParam("limit")
            @ApiParam(required = false, name = "limit", value = "Maximum number of groups to return")
            Optional<Integer> limit,

            @RequestParam("offset")
            @ApiParam(required = false, name = "offset", value = "Number of groups skipped")
            Optional<Integer> offset,

            @RequestParam("parentId")
            @ApiParam(required = false, name = "parentId", value = "Id of the group for which the group list must be retrieved")
            Optional<Integer> parentId
    ) {

        SigfoxApiProxy<SigfoxApiGroupInfoList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiGroupInfoList>(proxy.proxify(request), HttpStatus.OK);
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
     * GET https://backend.sigfox.com/api/groups/{group-id}
     *
     * group-id: the group identifier as returned by the /api/groups endpoint.
     *
     */


    @ApiOperation(
            value = "Group information - Get the description of a particular group",
            notes = "Get the description of a particular group. <br/>"+
                    "Parameters the group ID is provide in the URL:<br/>" +
                    "<ul>" +
                    " <li>group-id: the group identifier as returned by the /api/groups endpoint</li>" +
                    "</ul>",
            response = SigfoxApiGroupInfo.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiGroupInfo.class)
    })
    @RequestMapping(
            value ="/{group_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGroupInfo(
            HttpServletRequest request,
            @ApiParam(required = true, name = "group_id", value = "group_id")
            @PathVariable("group_id") String group_id
    ) {

        SigfoxApiProxy<SigfoxApiGroupInfo> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiGroupInfo>(proxy.proxify(request), HttpStatus.OK);
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
     * POST https://backend.sigfox.com/api/groups/create
     *
     * {
     *     "name" : "groupName",
     *     "description" : "the description",
     *     "parent" : "50f13484b846618c7bae77b7",
     *     "billable" : true,
     *     "clientName" : "The client name",
     *     "clientEmail" : "client@mail.com",
     *     "clientAddress" : "1 client street"
     * }
     *
     */


    @ApiOperation(
            value = "Group creation - Create a new Group",
            notes = "Create a new Group. <br/>"+
                    "In the Body are provided the information related to the group to create<br/>",
            response = SigfoxApiGroupCreationResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiGroupCreationResponse.class)
    })
    @RequestMapping(
            value ="/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createGroup(
            HttpServletRequest request,
            @ApiParam(required = true, name = "group", value = "Group description")
            @Valid @RequestBody SigfoxApiGroupCreation group
    ) {

        SigfoxApiProxy<SigfoxApiGroupCreationResponse> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiGroupCreationResponse>(proxy.proxify(request, mapper.writeValueAsString(group)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Group edition
     *
     * Edit a group
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/groups/edit
     *
     * {
     *     "id" : "50f13484b846618c7bae77b7",
     *     "name" : "groupName modified",
     *     "description" : "the description modified",
     *     "billable" : true,
     *     "clientName" : "The client name modified",
     *     "clientEmail" : "client@mail.com modified",
     *     "clientAddress" : "1 client street modified"
     * }
     *
     */

    @ApiOperation(
            value = "Group edition - Edit a group",
            notes = "Edit a group. <br/>"+
                    "In the Body are provided the information related to the group to edit<br/>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/edit",
            //produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> editGroup(
            HttpServletRequest request,
            @ApiParam(required = true, name = "group", value = "Group description")
            @Valid @RequestBody SigfoxApiGroupEdition group
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(group)), HttpStatus.OK);
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
     * POST https://backend.sigfox.com/api/groups/delete
     *
     * {
     *     "id" : "deadbeef0486300cbebef070"
     * }
     *
     * Fields:
     *
     *     id: the id of the group to delete. This id must correspond to an existing group which is not an operator, has none device type, none BSS order,none child group, and none user.
     *
     * Response
     *
     *     200 if it was OK
     *     403 when the group is referenced by at least one: device type/contract/user, or has a child group.
     *     404 when the group does not exist
     *
     */

    @ApiOperation(
            value = "Group deletion - Group deletion",
            notes = "Group deletion <br/>"+
                    "In the Body are provided the information related to the group to delete<br/>",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class),
            @ApiResponse(code = 403, message= "The group is referenced by at least one: device type/contract/user, or has a child group.", response = String.class),
            @ApiResponse(code = 404, message= "The group does not exist.", response = String.class)

    })
    @RequestMapping(
            value ="/delete",
            //produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> deleteGroup(
            HttpServletRequest request,
            @ApiParam(required = true, name = "group", value = "Group id to delete")
            @Valid @RequestBody SigfoxApiGroupDeletion group
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(group)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }
}



