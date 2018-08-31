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

@Api(value="misc", tags="sigfox-v2-misc-api")
@RequestMapping(value = "/api/v2")
@CrossOrigin
@RestController
public class SigfoxV2MiscApi {

    /**
     * Authenticate
     *
     * Check if the credentials are good
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/authenticate/
     *
     *
     * Parameters:
     *
     *     Body contains the credential to be used
     *
     */
    @ApiOperation(
            value = "Check if the credentials are good.",
            notes = "Check if the credentials are good.<br/>"+
                    "Parameters: Body contains a Credential structure with login information.<br/>" +
                    "",
            response = String.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/authenticate/",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> authenticate(
            HttpServletRequest request,
            @ApiParam(required = true, name = "usercredentials", value = "User credentials")
            @Valid @RequestBody SigfoxApiv2Credential usercredentials
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(usercredentials)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse body", HttpStatus.BAD_REQUEST);
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

*/
}



