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

@Api(value="coverage", tags="sigfox-v2-coverage-api")
@RequestMapping(value = "/api/v2")
@CrossOrigin
@RestController
public class SigfoxV2CoverageApi {

    /**
     * Sigfox Coverage Map
     *
     * Get information needed to display Sigfox public coverage.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/tiles/public-coverage
     *
     * Parameters:
     *
     *     None - returns a starting point for exploring the map
     *
     */
    @ApiOperation(
            value = "Sigfox coverage map.",
            notes = "Get information needed to display Sigfox public coverage. " +
                    "There are no parameters for this API, it returns a starting point structure " +
                    "for exploring the map.",
            response = SigfoxApiv2CoveragePublicMapResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CoveragePublicMapResponse.class)
    })
    @RequestMapping(
            value ="/tiles/public-coverage",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageTiles(
            HttpServletRequest request
    ) {

        SigfoxApiProxy<SigfoxApiv2CoveragePublicMapResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CoveragePublicMapResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Sigfox Coverage Prediction
     *
     * Get coverage margins for a selected latitude and longitude, for each redundancy level.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/coverages/global/predictions
     *
     * Parameters:
     *
     *     Lat - the latitude
     *     Lng - the longitude
     *     radius - The radius of the area in which the coverage results are averaged and returned for a selected location, in meters.
     *     groupId - the id of a group to include its operator in the global coverage
     *
     */
    @ApiOperation(
            value = "Fetches coverage predictions for any location.",
            notes = "Get coverage margins for a selected latitude and longitude, for each redundancy level. " +
                    "Parameters:<br/>" +
                    "<ul>" +
                    " <li>lat (Double) *: The latitude.</li>" +
                    " <li>lng (Double) *: The longitude.</li>" +
                    " <li>radius (Int) : The radius of the area in which the coverage results are averaged" +
                                       " and returned for a selected location, in meters. (default 300m).</li>" +
                    " <li>groupId (String) : The id of a group to include its operator in the global coverage.</li>" +
                    "</ul>",
            response = SigfoxApiv2CoverageMarginResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2CoverageMarginResponse.class)
    })
    @RequestMapping(
            value ="/coverages/global/predictions",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoveragePrediction(
            HttpServletRequest request,
            @RequestParam("lat")
            @ApiParam(
                    required = true,
                    name = "lat",
                    value = "The latitude."
            ) double lat,

            @RequestParam("lng")
            @ApiParam(
                    required = true,
                    name = "lng",
                    value = "The longitude."
            ) double lng,

            @RequestParam("radius")
            @ApiParam(
                    required = false,
                    name = "radius",
                    defaultValue = "300",
                    value = "The radius of the area in which the coverage results are averaged and returned for a selected location, in meters."
            ) Optional<Integer> radius,


            @RequestParam("groupId")
            @ApiParam(
                    required = false,
                    name = "groupId",
                    value = "The id of a group to include its operator in the global coverage."
            ) Optional<String> groupId

            ) {

        SigfoxApiProxy<SigfoxApiv2CoverageMarginResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2CoverageMarginResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


}


