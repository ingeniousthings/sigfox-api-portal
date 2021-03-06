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
package fr.ingeniousthings.sigfox_api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ingeniousthings.sigfox.api.elements.*;
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

@Api(value="billing", tags="sigfox-billing-api")
@RequestMapping(value = "/api")
@CrossOrigin
@RestController
public class SigfoxBillingApi {


    /**
     * BSS Orders list
     *
     * Lists all BSS orders available to your group.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/contracts?deviceTypeId={deviceTypeId}
     * Parameters:
     *
     * deviceTypeId: (Optional) the id of a device type. If this field is present,
     * returns only the BSS orders of that device type
     */
    @ApiOperation(
            value = "BSS Orders (contracts) list",
            notes = "Lists all BSS orders (contracts) available to your group." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>deviceTypeId: (Optional) the id of a device type. If this field is present</li>" +
                    "</ul>",
            response = SigfoxApiContractList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiContractList.class)
    })
    @RequestMapping(
            value ="/contracts",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageRedundancy(
            HttpServletRequest request,
            @RequestParam("deviceTypeId")
            @ApiParam(required = false, name = "deviceTypeId", value = " (Optional) the id of a device type. If this field is present, returns only the BSS orders of that device type.")
                    Optional<String> deviceTypeId
    ) {

        SigfoxApiProxy<SigfoxApiContractList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiContractList>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * BSS Order information
     * Get the description of a particular BSS order
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/contracts/{contract-id}
     * Parameters:
     *
     * contract-id the identifier of the BSS order.
     */
    @ApiOperation(
            value = "BSS Order (contract) information",
            notes = "Get the description of a particular BSS order." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>contract-id the identifier of the BSS order.</li>" +
                    "</ul>",
            response = SigfoxApiContractInformation.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiContractInformation.class)
    })
    @RequestMapping(
            value ="/contracts/{contract_id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageRedundancy(
            HttpServletRequest request,
            @ApiParam(required = true, name = "contract_id", value = "The identifier of the BSS order.")
            @PathVariable("contract_id") String contract_id
    ) {

        SigfoxApiProxy<SigfoxApiContractInformation> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiContractInformation>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}



