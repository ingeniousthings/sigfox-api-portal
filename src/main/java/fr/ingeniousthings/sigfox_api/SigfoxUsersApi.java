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

@Api(value="user", tags="sigfox-user-api")
@RequestMapping(value = "/api/users")
@CrossOrigin
@RestController
public class SigfoxUsersApi {


    /**
     * User list by Group
     * Lists all users registered with a role associated to a specific group
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/users?groupId={groupId}
     * Parameters:
     *
     * groupId: id of the group used to filter the users to return
     * Optionally, the request can also have the following parameters (see next response field below):
     *
     * limit: maximum number of messages to return, default 100.
     * offset: number of messages skipped, used when paginated.
     */
    @ApiOperation(
            value = "User list by Group",
            notes = "Lists all users registered with a role associated to a specific group" +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>groupId: id of the group used to filter the users to return</li>" +
                    " <li>limit: (optional) maximum number of messages to return, default 100.</li>" +
                    " <li>offset: (optional) number of messages skipped, used when paginated.</li>" +
                    "</ul>",
            response = SigfoxApiUsersDetailList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiUsersDetailList.class)
    })
    @RequestMapping(
            value ="",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageRedundancy(
            HttpServletRequest request,
            @RequestParam("groupId")
            @ApiParam(required = false, name = "groupId", value = "Id of the group used to filter the users to return.")
                    String groupId,
            @RequestParam("limit")
            @ApiParam(required = false, name = "limit", value = "(Optional) maximum number of messages to return, default 100.")
                    Optional<Integer> limit,
            @RequestParam("offset")
            @ApiParam(required = false, name = "offset", value = "(Optional) number of messages skipped, used when paginated.")
                    Optional<Integer> offset
    ) {

        SigfoxApiProxy<SigfoxApiUsersDetailList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiUsersDetailList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }



}



