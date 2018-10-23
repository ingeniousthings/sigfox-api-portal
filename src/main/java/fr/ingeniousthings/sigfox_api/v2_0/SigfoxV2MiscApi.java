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
     * Fetches a list of geolocation payload according to some request filter parameters and right visibility.
     *
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/groups/{id}/geoloc-payloads
     *
     *    id: The group’s identifier
     *
     *    fields - String[] - Defines the available user's fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *
     *  limit (int) - Defines the maximum number of devices to return, default is 100
     *               Default value : 100
     *  offset (int) - Defines the number of devices to skip
     *  pageId (int) - Token representing the page to retrieve
     */
    @ApiOperation(
            value = "Fetches list of geolocation payload",
            notes = "Fetches a list of geolocation payload according to some request filter parameters and right visibility. <br/>"+
                    "Parameters the user's ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The group’s identifier</li>" +
                    "<li>fields (query-String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5)) " +
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of elements to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of elements to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2GeolocPayloadResponseList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2GeolocPayloadResponseList.class)
    })
    @RequestMapping(
            value ="/groups/{id}/geoloc-payloads",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGeolocPayloadByGroup(
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

        SigfoxApiProxy<SigfoxApiv2GeolocPayloadResponseList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2GeolocPayloadResponseList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Fetches profiles according to the API user right visibility
     *
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/profiles/
     *
     *  id - Root, SNO, NIP or DIST group’s identifier
     *  inherit - also returns profiles inherited from parent’s group
     *
     *  fields - String[] - Defines the available user's fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     *
     *  limit (int) - Defines the maximum number of devices to return, default is 100
     *               Default value : 100
     *  offset (int) - Defines the number of devices to skip
     */
    @ApiOperation(
            value = "Fetches profiles according to the API user right visibility",
            notes = "Fetches profiles according to the API user right visibility. <br/>"+
                    "Parameters the group's ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (query-String): The group’s identifier</li>" +
                    "<li>inherit (string/boolean): also returns profiles inherited from parent’s group.</li>" +
                    "<li>fields (query-String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5)) " +
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of elements to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of elements to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2ProfileListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2ProfileListResponse.class)
    })
    @RequestMapping(
            value ="/profiles/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getProfile(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "Root, SNO, NIP or DIST group’s identifier")
            @RequestParam("id") String id,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device user’s fields to be returned in the response. " +
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

        SigfoxApiProxy<SigfoxApiv2ProfileListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2ProfileListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }



}



