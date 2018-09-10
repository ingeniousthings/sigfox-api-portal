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

@Api(value="certificates", tags="sigfox-v2-certificates-api")
@RequestMapping(value = "/api/v2/devices")
@CrossOrigin
@RestController
public class SigfoxV2CertificatesApi {


    /**
     * Retrieve the product certificate associated with this device
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/certificate/product
     *
     * Parameters:
     *
     *     id - The device’s identifier (hexadecimal format)
     *
     */
    @ApiOperation(
            value = "Retrieve the product certificate",
            notes = "Retrieve the product certificate associated with this device. <br/>"+
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) /api/v2/devices endpoint</li>" +
                    "</ul>",
            response = SigfoxApiv2CertificateProductResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CertificateProductResponse.class)
    })
    @RequestMapping(
            value ="/{id}/certificate/product",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceProductCertificate(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier (hexadecimal format).")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<SigfoxApiv2CertificateProductResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CertificateProductResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Retrieve the modem certificate associated with this device
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/certificate/modem
     *
     * Parameters:
     *
     *     id - The device’s identifier (hexadecimal format)
     *
     */
    @ApiOperation(
            value = "Retrieve the modem certificate",
            notes = "Retrieve the modem certificate associated with this device. <br/>"+
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) /api/v2/devices endpoint</li>" +
                    "</ul>",
            response = SigfoxApiv2CertificateModemResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CertificateModemResponse.class)
    })
    @RequestMapping(
            value ="/{id}/certificate/modem",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceModemCerificate(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier (hexadecimal format).")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<SigfoxApiv2CertificateModemResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CertificateModemResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Retrieve the product certificate associated with the given device ID and PAC, even if the device has not already been created on portal
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/devices/{id}/certificate/product
     *
     * Parameters:
     *
     *     id - The device’s identifier (hexadecimal format)
     *
     */
    @ApiOperation(
            value = "Retrieve not registered product certificate",
            notes = "Retrieve the product certificate associated with the given device ID and PAC, even if the device has not already been created on portal. <br/>"+
                    "Parameters the device ID and Year are provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The device’s identifier (hexadecimal format) /api/v2/devices endpoint</li>" +
                    "<li>pac (query-String): The device’s PAC (hexadecimal format)" +
                    "</ul>",
            response = SigfoxApiv2CertificateProductWithPacResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CertificateProductWithPacResponse.class)
    })
    @RequestMapping(
            value ="/{id}/product-certificate",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getDeviceProductCertificateWthPac(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The device’s identifier (hexadecimal format).")
            @PathVariable("id") String id,
            @RequestParam("pac")
            @ApiParam(
                    required = true,
                    name = "pac",
                    value = "The device’s PAC (hexadecimal format)."
            ) String pac

            ) {

        SigfoxApiProxy<SigfoxApiv2CertificateProductWithPacResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CertificateProductWithPacResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}



