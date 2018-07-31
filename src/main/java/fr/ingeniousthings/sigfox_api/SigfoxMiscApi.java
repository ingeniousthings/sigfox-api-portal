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
import java.util.List;
import java.util.Optional;

@Api(value="misc", tags="sigfox-misc-api")
@RequestMapping(value = "/api")
@CrossOrigin
@RestController
public class SigfoxMiscApi {



    /**
     * Messages not sent this month
     *
     * Returns device messages where at least one callback has failed, in reverse chronological order
     * (most recent message first).
     *
     * GET https://backend.sigfox.com/api/callbacks/messages/error
     *
     * Optionally, the request can also have the following parameter (see next response field below):
     *
     *     limit: maximum number of messages to return, default 100.
     *     offset: number of messages skipped, used when paginated.
     *     since: return messages sent since this timestamp (in milliseconds since the Unix Epoch),
     *            default to a month ago.
     *     before: return messages sent before this timestamp (in milliseconds since the Unix Epoch).
     *     hexId: device identifier.
     *     deviceTypeId: device type identifier if no device identifier provided.
     *     groupId: group identifier if no device type identifier or device identifier provided.
     *
     */
    @ApiOperation(
            value = "Messages not sent this month",
            notes = "Returns device messages where at least one callback has failed, in reverse chronological order" +
                    " (most recent message first)." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>limit: maximum number of messages to return</li>" +
                    " <li>before: return messages sent before this timestamp (in seconds since the Unix Epoch)</li>" +
                    " <li>since: return messages sent since this timestamp (in seconds since the Unix Epoch).</li>" +
                    " <li>offset: number of messages to skip (between 0 and 5000). Normally you should not have " +
                    " to worry about this parameter as the app will set this parameter automatically " +
                    " in the response, in the URL of the next page (see field next in response)</li>" +
                    " <li>hexId: device identifier.</li>" +
                    " <li>deviceTypeId: device type identifier if no device identifier provided.</li>" +
                    " <li>groupId: group identifier if no device type identifier or device identifier provided.</li>" +
                    "</ul>",
            response = SigfoxApiMessagesNotSent.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiMessagesNotSent.class)
    })
    @RequestMapping(
            value ="/callbacks/messages/error",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceDetails(
            HttpServletRequest request,
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
            @RequestParam("hexId")
            @ApiParam(required = false, name = "hexId", value = "Device identifier.")
                    Optional<String> hexId,
            @RequestParam("deviceTypeId")
            @ApiParam(required = false, name = "deviceTypeId", value = "Device type identifier if no device identifier provided.")
                    Optional<String> deviceTypeId,
            @RequestParam("groupId")
            @ApiParam(required = false, name = "groupId", value = "Group identifier if no device type identifier or device identifier provided.")
                    Optional<String> groupId
    ) {

        SigfoxApiProxy<SigfoxApiMessagesNotSent> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiMessagesNotSent>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

}



