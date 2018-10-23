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

@Api(value="messages", tags="sigfox-v2-messages-api")
@RequestMapping(value = "/api/v2/")
@CrossOrigin
@RestController
public class SigfoxV2MessageApi {

    /**
     *
     * Fetches device type's error messages
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/device-types/{id}/callbacks-not-delivered
     *
     *
     * Parameters:
     *
     *     operatorId - Returns all devices under the given operator
     *
     * Optionally, the request can also have the following parameter:
     *
     * id (string) - The device’s identifier (hexadecimal format)
     * limit (int) - Defines the maximum number of devices to return, default is 100
     *               Default value : 100
     * offset (int) - Defines the number of devices to skip
     *
     */
    @ApiOperation(
            value = "Fetches device type's error messages.",
            notes = "Fetches device type's error messages - list devices'smessages not distributed. <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The device type identifier</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2CallbackErrorListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CallbackErrorListResponse.class)
    })
    @RequestMapping(
            value ="/device-types/{id}/callbacks-not-delivered",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDTCallbackErrorList(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device type identifier (hexadecimal format)."
            ) String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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

        SigfoxApiProxy<SigfoxApiv2CallbackErrorListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CallbackErrorListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     *
     * Fetches Group's error messages
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/groups/{id}/callbacks-not-delivered
     *
     *
     * Parameters:
     *
     *     operatorId - Returns all devices under the given operator
     *
     * Optionally, the request can also have the following parameter:
     *
     * id (string) - The device’s identifier (hexadecimal format)
     * limit (int) - Defines the maximum number of devices to return, default is 100
     *               Default value : 100
     * offset (int) - Defines the number of devices to skip
     *
     */
    @ApiOperation(
            value = "Fetches the group's error messages.",
            notes = "Fetches the group's error messages - list devices'smessages not distributed. <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The group identifier</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2CallbackErrorListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CallbackErrorListResponse.class)
    })
    @RequestMapping(
            value ="/groups/{id}/callbacks-not-delivered",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getGroupCallbackErrorList(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The group identifier (hexadecimal format)."
            ) String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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

        SigfoxApiProxy<SigfoxApiv2CallbackErrorListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CallbackErrorListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     *
     * Fetches the device's error messages
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/callbacks-not-delivered
     *
     *
     * Parameters:
     *
     *     id - The device’s identifier (hexadecimal format)
     *
     * Optionally, the request can also have the following parameter:
     *
     *      since (int): Starting timestamp (posix time).
     *      before (int): Ending timestamp (posix time).
     *      limit (int): Defines the maximum number of device types to return, default is 100.
     *      offset (int): Defines the number of device types to skip.
     *
     */
    @ApiOperation(
            value = "Fetches the device's error messages.",
            notes = "Fetches the device's error messages - list devices's messages not distributed. <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The device’s identifier (hexadecimal format)</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2CallbackErrorListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CallbackErrorListResponse.class)
    })
    @RequestMapping(
            value ="/devices/{id}/callbacks-not-delivered",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevCallbackErrorList(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device’s identifier (hexadecimal format)."
            ) String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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

        SigfoxApiProxy<SigfoxApiv2CallbackErrorListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CallbackErrorListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     *
     *
     * Fetches the device's messages by device Type
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/device-types/{id}/messages
     *
     *
     * Parameters:
     *
     *      id (string) - The device type’s identifier (hexadecimal format)
     *
     * Optionally, the request can also have the following parameter:
     *
     *      fields (String) : list the Fields you want to get in the response
     *      since (int): Starting timestamp (posix time).
     *      before (int): Ending timestamp (posix time).
     *      limit (int): Defines the maximum number of device types to return, default is 100.
     *      offset (int): Defines the number of device types to skip.
     *
     */
    @ApiOperation(
            value = "Fetches the device's messages by DevType",
            notes = "Fetches the device's messages per Device Type<br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The device type identifier</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    " <li>fields (String[]): defines fields to be returned in the response.<br/>" +
                    "     fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:</br>" +
                    "     example : ?fields=attr1,attr2(attr3,attr4(attr5))</br>" +
                    "     for now available strings are " +
                    "     <ul>" +
                    "      <li> oob</li>" +
                    "      <li> ackRequired</li>" +
                    "      <li> device(name) </li>" +
                    "      <li> rinfos(cbStatus,rep,repetitions,baseStation(name))</li>" +
                    "      <li> downlinkAnswerStatus(baseStation(name))</li>" +
                    "     </ul>"+
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2MessageListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2MessageListResponse.class)
    })
    @RequestMapping(
            value ="/device-types/{id}/messages",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getUplinkMessagesPerDeviceType(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The deviceType’s identifier (hexadecimal format)."
            ) String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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
                            "      <li> oob</li>" +
                            "      <li> ackRequired</li>" +
                            "      <li> device(name) </li>" +
                            "      <li> rinfos(cbStatus,rep,repetitions,baseStation(name))</li>" +
                            "      <li> downlinkAnswerStatus(baseStation(name))</li>" +
                            "     </ul>"

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
                    value = "Index of result to start with."
            ) Optional<Integer> offset

    ) {

        SigfoxApiProxy<SigfoxApiv2MessageListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2MessageListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     *
     *
     * Fetches the device's messages by Station
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/base-stations/{id}/messages
     *
     *
     * Parameters:
     *
     *      id (string) - The base station’s identifier (hexadecimal format)
     *
     * Optionally, the request can also have the following parameter:
     *
     *      fields (String) : list the Fields you want to get in the response
     *      since (int): Starting timestamp (posix time).
     *      before (int): Ending timestamp (posix time).
     *      limit (int): Defines the maximum number of device types to return, default is 100.
     *      offset (int): Defines the number of device types to skip.
     *
     */
    @ApiOperation(
            value = "Fetches the device's messages by Station",
            notes = "Fetches the device's messages per Station<br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The base station’s identifier (hexadecimal format)</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    " <li>fields (String[]): defines fields to be returned in the response.<br/>" +
                    "     fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:</br>" +
                    "     example : ?fields=attr1,attr2(attr3,attr4(attr5))</br>" +
                    "     for now available strings are " +
                    "     <ul>" +
                    "      <li> oob</li>" +
                    "      <li> ackRequired</li>" +
                    "      <li> device(name) </li>" +
                    "      <li> rinfos(cbStatus,rep,repetitions,baseStation(name))</li>" +
                    "      <li> downlinkAnswerStatus(baseStation(name))</li>" +
                    "     </ul>"+
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2MessageListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2MessageListResponse.class)
    })
    @RequestMapping(
            value ="/base-stations/{id}/messages",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getUplinkMessagesPerStation(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The base station’s identifier (hexadecimal format)."
            ) String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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
                            "      <li> oob</li>" +
                            "      <li> ackRequired</li>" +
                            "      <li> device(name) </li>" +
                            "      <li> rinfos(cbStatus,rep,repetitions,baseStation(name))</li>" +
                            "      <li> downlinkAnswerStatus(baseStation(name))</li>" +
                            "     </ul>"

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
                    value = "Index of result to start with."
            ) Optional<Integer> offset

    ) {

        SigfoxApiProxy<SigfoxApiv2MessageListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2MessageListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     *
     *
     * Fetches the device's messages
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/messages
     *
     *
     * Parameters:
     *
     *      id (string) - The device’s identifier (hexadecimal format)
     *
     * Optionally, the request can also have the following parameter:
     *
     *      fields (String) : list the Fields you want to get in the response
     *      since (int): Starting timestamp (posix time).
     *      before (int): Ending timestamp (posix time).
     *      limit (int): Defines the maximum number of device types to return, default is 100.
     *      offset (int): Defines the number of device types to skip.
     *
     */
    @ApiOperation(
            value = "Fetches the device's messages",
            notes = "Fetches the device's messages <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The device identifier</li>" +
                    "</ul>" +
                    "Optionally, the request can also have the following parameters:<br/>" +
                    "<ul>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    " <li>fields (String[]): defines fields to be returned in the response.<br/>" +
                    "     fields is a suite of string separated by comma, nested object fields can be given with parenthesis recursively:</br>" +
                    "     example : ?fields=attr1,attr2(attr3,attr4(attr5))</br>" +
                    "     for now available strings are " +
                    "     <ul>" +
                    "      <li> oob</li>" +
                    "      <li> ackRequired</li>" +
                    "      <li> device(name) </li>" +
                    "      <li> rinfos(cbStatus,rep,repetitions,baseStation(name))</li>" +
                    "      <li> downlinkAnswerStatus(baseStation(name))</li>" +
                    "     </ul>"+
                    "</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2MessageListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2MessageListResponse.class)
    })
    @RequestMapping(
            value ="/devices/{id}/messages",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getUplinkMessagesPerDevice(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device’s identifier (hexadecimal format)."
            ) String id,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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
                            "      <li> oob</li>" +
                            "      <li> ackRequired</li>" +
                            "      <li> device(name) </li>" +
                            "      <li> rinfos(cbStatus,rep,repetitions,baseStation(name))</li>" +
                            "      <li> downlinkAnswerStatus(baseStation(name))</li>" +
                            "     </ul>"

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
                    value = "Index of result to start with."
            ) Optional<Integer> offset

    ) {

        SigfoxApiProxy<SigfoxApiv2MessageListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2MessageListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }



    /**
     *
     *
     * Returns the total number of device messages for one device, this day, this week and this months
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/messages/metric
     *
     *
     * Parameters:
     *
     *      id (string) - The device’s identifier (hexadecimal format)
     *
     */
    @ApiOperation(
            value = "Returns the total number of device messages",
            notes = "Returns the total number of device messages for one device, this day, this week and this months <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The device identifier</li>" +
                    "</ul>",
            response = SigfoxApiv2MessageStats.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2MessageStats.class)
    })
    @RequestMapping(
            value ="/devices/{id}/messages/metric",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevUplinkStatistics(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device’s identifier (hexadecimal format)."
            ) String id

    ) {

        SigfoxApiProxy<SigfoxApiv2MessageStats> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2MessageStats>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     *
     * Fetches the device's location
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/locations
     *
     *
     * Parameters:
     *
     *      id (string) - The device’s identifier (hexadecimal format)
     *
     * Optional:
     *      since (int): Starting timestamp (posix time).
     *      before (int): Ending timestamp (posix time).
     *      limit (int): Defines the maximum number of device types to return, default is 100.
     *      offset (int): Defines the number of device types to skip.
     */
    @ApiOperation(
            value = "Fetches the device's location",
            notes = "Fetches the device's location <br/>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String)* : The device identifier</li>" +
                    "</ul>" +
                    "Optional:" +
                    "<ul>" +
                    " <li>oob (boolean / string) :if true, the method return also the location from out of band Messages </li>" +
                    " <li>since (int): Starting timestamp (posix time).</li>" +
                    " <li>before (int): Ending timestamp (posix time).</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "</ul>",
            response = SigfoxApiv2DeviceLocationsResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2DeviceLocationsResponse.class)
    })
    @RequestMapping(
            value ="/devices/{id}/locations",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDevMessageLocations(
            HttpServletRequest request,
            @PathVariable("id")
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device’s identifier (hexadecimal format)."
            ) String id,

            @RequestParam("oob")
            @ApiParam(
                    required = false,
                    name = "oob",
                    value = "if true, the method return also the location from out of band Messages."
            ) Optional<String> oob,

            @RequestParam("since")
            @ApiParam(
                    required = false,
                    name = "since",
                    value = "Starting timestamp (posix time)."
            ) Optional<Long> since,

            @RequestParam("before")
            @ApiParam(
                    required = false,
                    name = "before",
                    value = "Ending timestamp (posix time)."
            ) Optional<Long> before,

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
                    value = "Index of result to start with."
            ) Optional<Integer> offset


    ) {

        SigfoxApiProxy<SigfoxApiv2DeviceLocationsResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2DeviceLocationsResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }




}



