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

@Api(value="devices", tags="sigfox-devices-api")
@RequestMapping(value = "/api")
@CrossOrigin
@RestController
public class SigfoxDevicesApi {

    /**
     * Registering new devices
     *
     * Creates new devices by providing a list of identifiers, and associates them to a device type.
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/devicetypes/{devicetype-id}/devices/bulk/create/async
     *
     * {
     *   "prefix" : "test-device-",
     *   "ids" : [
     *     { "id" : "C031", "pac": "6A757C859B23471B" },
     *     { "id" : "C032", "pac": "374FC61194FC4DA8" },
     *     ...
     *   ],
     *   "productCertificate" : "P_0004_D356_03"
     * }
     *
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *
     * Fields:
     *
     *     prefix: used to create the name of each device, by prepending it to the device’s identifier
     *     ids: list of device identifiers that have to be registered
     *     id: identifier of the device
     *     pac: porting authorization code for this device
     *
     * Optionally, the request can also have the following parameter:
     *
     *     productCertificate: product certificate key to associate to each device.
     *
     */
    @ApiOperation(
            value = "Registering new devices (ASYNC) - Creates new devices by providing a list of identifiers",
            notes = "Creates new devices by providing a list of identifiers, and associates them to a device type. <br/>"+
                    "In the URL and BODY are provided the information related to the device list to create<br/>" +
                    "<ul>" +
                    "<li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    "</ul>",
            response = SigfoxApiDeviceCreateOutput.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceCreateOutput.class)
    })
    @RequestMapping(
            value ="/devicetypes/{devicetype_id}/devices/bulk/create/async",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> createDeviceTypeASync(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "The device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "devices", value = "Devices to be created list")
            @Valid @RequestBody SigfoxApiDeviceCreateInput devices
    ) {

        SigfoxApiProxy<SigfoxApiDeviceCreateOutput> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiDeviceCreateOutput>(proxy.proxify(request, mapper.writeValueAsString(devices)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Checking asynchronous devices creation status
     *
     * Returns the status of the given devices creation job.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/devicetypes/{devicetype-id}/registration/{job-id}
     *
     * Parameters:
     *
     *     devicetype-id: the device type identifier as returned by the /api/devicetypes endpoint.
     *     job-id: the devices creation job id as returned by the /api/devicetypes/{devicetype-id}/devices/bulk/create/async endpoint.
     */

    @ApiOperation(
            value = "Checking asynchronous devices creation status",
            notes = "Checking asynchronous devices creation status. Returns the status of the given devices creation job. <br/>"+
                    "Parameters:<br/>" +
                    "<ul>" +
                    " <li>devicetype_id : The device type identifier as returned by the /api/devicetypes endpoint.</li>" +
                    " <li>job_id : the devices creation job id as returned by the /api/devicetypes/{devicetype-id}/devices/bulk/create/async endpoint.</li> " +
                    "</ul>",
            response = SigfoxApiDeviceCreateJobStatus.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiDeviceCreateJobStatus.class)
    })
    @RequestMapping(
            value ="/devicetypes/{devicetype_id}/registration/{job_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> checkDeviceCreation(
            HttpServletRequest request,
            @ApiParam(required = true, name = "devicetype_id", value = "the device type identifier as returned by the /api/devicetypes endpoint.")
            @PathVariable("devicetype_id") String devicetype_id,
            @ApiParam(required = true, name = "job_id", value = "the devices creation job id as returned by the /api/devicetypes/{devicetype-id}/devices/bulk/create/async endpoint.")
            @PathVariable("job_id") String job_id
    ) {

        SigfoxApiProxy<SigfoxApiDeviceCreateJobStatus> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiDeviceCreateJobStatus>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }




    
}



