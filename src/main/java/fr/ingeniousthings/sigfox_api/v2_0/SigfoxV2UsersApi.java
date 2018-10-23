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

@Api(value="users", tags="sigfox-v2-users-api")
@RequestMapping(value = "/api/v2/users")
@CrossOrigin
@RestController
public class SigfoxV2UsersApi {

    /**
     * User list
     *
     * Fetches a list of users according to some request filter parameters and right visibility
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/users/
     *
     * Parameters:
     *
     *     None
     *
     * Optionally, the request can also have the following parameter:
     *
     *    text (String) - Searches for users containing the given text in their name or email
     *    profileId (String) - Searches for users who have the given profile affected
     *    groupIds (String[]) - Searches for users who are attached to the given groups
     *    deep (boolean) - Deep search in the sub group hierarchy
     *    sort - String : The field on which the list will be sorted. (field to sort ascending or -field to sort descending).
     *                      id / -id
     *                      name / -name
     *                      email / -email
     *                      default ID
     *
     *    fields - String[] - Defines the available users’s fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *    limit - int - Defines the maximum number of device types to return, default is 100
     *    offset - int - Defines the number of device types to skip
     *    pageId -int - Token representing the page to retrieve
     */


    @ApiOperation(
            value = "Fetches a list of users.",
            notes = "Fetches a list of users according to some request filter parameters and right visibility. <br/>" +
                    "Parameters: Optionally, the request can also have the following parameters (see next response field below):<br/>" +
                    "<ul>" +
                    " <li>text (String): Searches for users containing the given text in their name or email.</li>" +
                    " <li>profileId (String) - Searches for users who have the given profile affected.</li>" +
                    " <li>groupIds (String[]) - Searches for users who are attached to the given groups</li>" +
                    " <li>deep (boolean): Deep search in the sub group hierarchy.</li>" +
                    " <li>sort (String): The field on which the list will be sorted. (field to sort ascending or -field to sort descending)." +
                    "<ul>" +
                    "<li>id (default) / -id</li>" +
                    "<li>name / -name</li>" +
                    "<li>email / -email</li>" +
                    "</ul>" +
                    "</li>" +
                    "<li>fields (String[]): Fetches users's " +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2UserListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2UserListResponse.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getUsersList(
            HttpServletRequest request,
            @RequestParam("text")
            @ApiParam(
                    required = false,
                    name = "text",
                    value = "Searches for users containing the given text in their name or email."
            ) Optional<String> text,

            @RequestParam("profileId")
            @ApiParam(
                    required = false,
                    name = "profileId",
                    value = "Searches for users who have the given profile affected."
            ) Optional<String> profileId,

            @RequestParam("groupIds")
            @ApiParam(
                    required = false,
                    name = "groupIds",
                    value = "Searches for users who are attached to the given groups (group list)"
            ) Optional<String> groupIds,

            @RequestParam("deep")
            @ApiParam(
                    required = false,
                    name = "deep",
                    value = "Deep search in the sub group hierarchy."
            ) Optional<Boolean> deep,

            @RequestParam("sort")
            @ApiParam(
                    required = false,
                    name = "sort",
                    allowableValues = "id,-id,name,-name,email,-email",
                    value = "The field on which the list will be sorted. (field to sort ascending or -field to sort descending)."
            ) Optional<String> sort,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available users’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
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
        SigfoxApiProxy<SigfoxApiv2UserListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2UserListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Fetches the user's information
     *
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/users/{id}
     *
     *    id: The user’s identifier or e-mail
     *    fields - String[] - Defines the available user's fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *
     *                     available : userRoles(group(name,type,level,bssId),profile(name,roles(name,perms(name))))
     */
    @ApiOperation(
            value = "Fetches the user's information",
            notes = "Fetches the user's information. <br/>"+
                    "Parameters the user's ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The user’s identifier or e-mail</li>" +
                    "<li>fields (query-String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5)) " +
                    "available : userRoles(group(name,type,level,bssId),profile(name,roles(name,perms(name))))" +
                    "</li>" +
                    "</ul>",
            response = SigfoxApiv2UserRead.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2UserRead.class)
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getUserInfo(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The user’s identifier")
            @PathVariable("id") String id,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device user’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields

            ) {

        SigfoxApiProxy<SigfoxApiv2UserRead> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2UserRead>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Create a new user
     *
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/users/
     *
     * Parameter :
     *    In the body a struture SigfoxApiv2User2
     */

    @ApiOperation(
            value = "Create a new user",
            notes = "Create a new user. <br/>"+
                    "In the Body are provided the information related to the user to create<br/>",
            response = SigfoxApiv2UserId.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Success", response = SigfoxApiv2UserId.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "user", value = "User description")
            @Valid @RequestBody SigfoxApiv2User2 user
    ) {

        SigfoxApiProxy<SigfoxApiv2UserId> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2UserId>(proxy.proxify(request, mapper.writeValueAsString(user)), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Update the user
     *
     * Request
     *
     * PUT https://backend.sigfox.com/api/v2/users/{id}
     *
     * Fields:
     *
     *     id: the id of the user to update. This id must correspond to an existing user
     *
     * In the body a struture SigfoxApiv2User2
     */
    @ApiOperation(
            value = "Update the user",
            notes = "Update the given user. <br/>"+
                    "In the Body are provided the information related to the user to edit<br/>" +
                    "Parameters the user ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the user identifier </li>" +
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
    public ResponseEntity<?> editUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The user’s identifier")
            @PathVariable("id") String id,

            @ApiParam(required = true, name = "user", value = "User description")
            @Valid @RequestBody SigfoxApiv2UserRoleEdit user
    ) {

        SigfoxApiProxy<String> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<String>(proxy.proxify(request, mapper.writeValueAsString(user)), HttpStatus.NO_CONTENT);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * User deletion
     *
     * Request
     *
     * DELETE https://backend.sigfox.com/api/v2/user/{id}
     *
     * Fields:
     *
     *     id: the id of the user to delete. This id must correspond to an existing user
     *
     * Response
     *
     *     204 if it was OK
     *
     */
    @ApiOperation(
            value = "Delete the given user",
            notes = "User deletion <br/>"+
                    "Parameters : the user ID is provided in the URL.<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the user identifier</li>" +
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
    public ResponseEntity<?> deleteUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The user’s identifier")
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



}



