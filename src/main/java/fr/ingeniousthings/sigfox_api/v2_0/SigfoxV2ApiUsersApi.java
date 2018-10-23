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

@Api(value="apiUsers", tags="sigfox-v2-api-users-api")
@RequestMapping(value = "/api/v2/api-users")
@CrossOrigin
@RestController
public class SigfoxV2ApiUsersApi {

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
     *    customRoleId (String) - Searches for API users who have the given custom role affected
     *    profileId (String) - Searches for users who have the given profile affected
     *    groupIds (String[]) - Searches for users who are attached to the given groups
     *
     *    fields - String[] - Defines the available users’s fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *    limit - int - Defines the maximum number of device types to return, default is 100
     *    offset - int - Defines the number of device types to skip
     */


    @ApiOperation(
            value = "Fetches a list of api users.",
            notes = "Fetches a list of api users according to some request filter parameters and right visibility. <br/>" +
                    "Parameters: Optionally, the request can also have the following parameters (see next response field below):<br/>" +
                    "<ul>" +
                    " <li>customRoleId (String): Searches for API users who have the given custom role affected.</li>" +
                    " <li>profileId (String) - Searches for users who have the given profile affected.</li>" +
                    " <li>groupIds (String[]) - Searches for users who are attached to the given groups</li>" +
                    "<ul>" +
                    "</li>" +
                    "<li>fields (String[]): Fetches users's " +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2UserApiListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2UserApiListResponse.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getApiUsersList(
            HttpServletRequest request,
            @RequestParam("customRoleId")
            @ApiParam(
                    required = false,
                    name = "customRoleId",
                    value = "Searches for API users who have the given custom role affected."
            ) Optional<String> customRoleId,

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
            ) Optional<Integer> offset


    ) {
        SigfoxApiProxy<SigfoxApiv2UserApiListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2UserApiListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Fetches the api user's information
     *
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/api-users/{id}
     *
     *    id: The api-user’s identifier or e-mail
     *    fields - String[] - Defines the available user's fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *
     *                     available : userRoles(group(name,type,level,bssId),profile(name,roles(name,perms(name))))
     */
    @ApiOperation(
            value = "Fetches the api user's information",
            notes = "Fetches the api user's information. <br/>"+
                    "Parameters the user's ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The api user’s identifier or e-mail</li>" +
                    "<li>fields (query-String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5)) " +
                    "available : userRoles(group(name,type,level,bssId),profile(name,roles(name,perms(name))))" +
                    "</li>" +
                    "</ul>",
            response = SigfoxApiv2UserApi.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2UserApi.class)
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getApiUserInfo(
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

        SigfoxApiProxy<SigfoxApiv2UserApi> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2UserApi>(proxy.proxify(request), HttpStatus.OK);
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
     * POST https://backend.sigfox.com/api/v2/api-users/
     *
     * Parameter :
     *    In the body a struture SigfoxApiv2UserApiBase2
     */

    @ApiOperation(
            value = "Create a new api-user",
            notes = "Create a new api-user. <br/>"+
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
    public ResponseEntity<?> createApiUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "user", value = "User description")
            @Valid @RequestBody SigfoxApiv2UserApiBase2 user
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
     * Update the api-user
     *
     * Request
     *
     * PUT https://backend.sigfox.com/api/v2/api-users/{id}
     *
     * Fields:
     *
     *     id: the id of the user to update. This id must correspond to an existing user
     *
     */
    @ApiOperation(
            value = "Update the api-user",
            notes = "Update the given api-user. <br/>"+
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
    public ResponseEntity<?> editApiUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The user’s identifier")
            @PathVariable("id") String id,

            @ApiParam(required = true, name = "user", value = "User description")
            @Valid @RequestBody SigfoxApiv2UserApiEdit user
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
     * Api User deletion
     *
     * Request
     *
     * DELETE https://backend.sigfox.com/api/v2/api-user/{id}
     *
     * Fields:
     *
     *     id: the id of the api-user to delete. This id must correspond to an existing user
     *
     * Response
     *
     *     204 if it was OK
     *
     */
    @ApiOperation(
            value = "Delete the given api-user",
            notes = "Api User deletion <br/>"+
                    "Parameters : the api user ID is provided in the URL.<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the apo user identifier</li>" +
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
    public ResponseEntity<?> deleteApiUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The api user’s identifier")
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
     * Generate a new password for the API user
     *
     * Request
     *
     * PUT https://backend.sigfox.com/api/v2/api-users/{id}
     *
     * Fields:
     *
     *     id: the id of the user to update. This id must correspond to an existing user
     *
     */
    @ApiOperation(
            value = "Renew password",
            notes = "Generate a new password for the API user. <br/>"+
                    "Parameters the user ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the api user identifier </li>" +
                    "</ul>",
            response = SigfoxApiv2UserApiAccessToken.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2UserApiAccessToken.class)
    })
    @RequestMapping(
            value ="/{id}/renew-credential",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT
    )
    @CrossOrigin
    public ResponseEntity<?> renewTokenApiUser(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The user’s identifier")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<SigfoxApiv2UserApiAccessToken> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2UserApiAccessToken>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}



