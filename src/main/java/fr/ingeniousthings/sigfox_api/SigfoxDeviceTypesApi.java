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

@Api(value="group", tags="sigfox-devicetypes-api")
@RequestMapping(value = "/api/devicetypes")
@CrossOrigin
@RestController
public class SigfoxDeviceTypesApi {

    /**
     * Device types list
     *
     * Lists all device types available to your group.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes
     *
     * Parameters:
     *
     *     None
     *
     * Optionally, the request can also have the following parameter:
     *
     *     includeSubGroups: true if you need also device types from child groups.
     *     contractInfoId: id of the contract info associated to the device types to retrieve
     */


    @ApiOperation(
            value = "Device types list - Lists all device types available to your group",
            notes = "Lists all device types available to your group. <br/>"+
                    "Parameters: Optionally you can add parameters :<br/>" +
                    "<ul>" +
                    " <li>includeSubGroups: true if you need also device types from child groups</li>" +
                    " <li>contractInfoId: id of the contract info associated to the device types to retrieve</li>" +
                    "</ul>",
            response = SigfoxApiDeviceInformationList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Sucess", response = SigfoxApiDeviceInformationList.class)
    })
    @RequestMapping(
            value ="",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTypesList(
            HttpServletRequest request,
            @RequestParam("includeSubGroups")
            @ApiParam(required = false, name = "includeSubGroups", value = "true if you need also device types from child groups.")
            Optional<Boolean> includeSubGroups,

            @RequestParam("contractInfoId")
            @ApiParam(required = false, name = "contractInfoId", value = "id of the contract info associated to the device types to retrieve")
            Optional<String> contractInfoId

    ) {

        SigfoxApiProxy<SigfoxApiDeviceInformationList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceInformationList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Device type information
     *
     * Get the description of a particular device type
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     */

    @ApiOperation(
            value = "Device type information - Get the description of a particular device type",
            notes = "Get the description of a particular device type. <br/>"+
                    "Parameters:<br/>" +
                    "<ul>" +
                    " <li>Get the description of a particular device type</li>" +
                    "</ul>",
            response = SigfoxApiDeviceInformation.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Sucess", response = SigfoxApiDeviceInformation.class)
    })
    @RequestMapping(
            value ="/{devicetype_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceTypes(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceInformation> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceInformation>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}



