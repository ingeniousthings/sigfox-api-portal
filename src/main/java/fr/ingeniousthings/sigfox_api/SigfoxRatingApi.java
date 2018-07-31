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

import fr.ingeniousthings.sigfox.api.elements.SigfoxApiRating;
import fr.ingeniousthings.sigfox.api.elements.SigfoxApiRatingDetailed;
import fr.ingeniousthings.sigfox.api.elements.SigfoxApiUsersDetailList;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Api(value="rating", tags="sigfox-rating-api")
@RequestMapping(value = "/api/contracts")
@CrossOrigin
@RestController
public class SigfoxRatingApi {


    /**
     * Rating information
     * Get a BSS order’s rating for a date and a contract
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/contracts/{contract-id}/rating?date=2014-01-20
     * Parameters:
     *
     * contract-id: the identifier of the BSS order.
     * date: the date of rating (format: yyyy-MM-dd). This date must be in the past.
     */
    @ApiOperation(
            value = "Rating information",
            notes = "Get a BSS order’s rating for a date and a contract" +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>contract-id: the identifier of the BSS order.</li>" +
                    " <li>date: the date of rating (format: yyyy-MM-dd). This date must be in the past.</li>" +
                    "</ul>",
            response = SigfoxApiRating.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiRating.class)
    })
    @RequestMapping(
            value ="/{contract_id}/rating",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getRatingInformation(
            HttpServletRequest request,
            @ApiParam(required = true, name = "contract_id", value = "The identifier of the BSS order.")
            @PathVariable("contract_id") String contract_id,
            @RequestParam("date")
            @ApiParam(required = true, name = "date", value = "the date of rating (format: yyyy-MM-dd). This date must be in the past.")
                    String date
    ) {

        SigfoxApiProxy<SigfoxApiRating> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiRating>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }



    /**
     * Detailed rating information
     *
     * Get the detailed rating for a date and a contract
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/contracts/{contract-id}/rating/detailed?date=2014-01-03
     * Parameters:
     *
     * contract-id: the identifier of the BSS order.
     * date: the date of rating (format: yyyy-MM-dd). This date must be in the past.
     */
    @ApiOperation(
            value = "Detailed rating information",
            notes = "Get the detailed rating for a date and a contract" +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>contract-id: the identifier of the BSS order.</li>" +
                    " <li>date: the date of rating (format: yyyy-MM-dd). This date must be in the past.</li>" +
                    "</ul>",
            response = SigfoxApiRatingDetailed.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiRatingDetailed.class)
    })
    @RequestMapping(
            value ="/{contract_id}/rating/detailed",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getRatingDetails(
            HttpServletRequest request,
            @ApiParam(required = true, name = "contract_id", value = "The identifier of the BSS order.")
            @PathVariable("contract_id") String contract_id,
            @RequestParam("date")
            @ApiParam(required = true, name = "date", value = "the date of rating (format: yyyy-MM-dd). This date must be in the past.")
                    String date
    ) {

        SigfoxApiProxy<SigfoxApiRatingDetailed> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiRatingDetailed>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}



