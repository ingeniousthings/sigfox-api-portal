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

@Api(value="coverage", tags="sigfox-coverage-api")
@RequestMapping(value = "/api/coverages")
@CrossOrigin
@RestController
public class SigfoxCoverageApi {


    /**
     * Redundancy
     *
     * Get base station redundancy coverage for a given latitude and longitude.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/coverages/redundancy?lat={lat}&lng={lng}
     *
     * Parameters:
     *
     *     lat: the latitude.
     *     lng: the longitude.
     *
     * Optionally, the request can also have the following parameter:
     *
     *     mode: the coverage mode (UNDERGROUND, INDOOR, OUTDOOR), default is INDOOR.
     *         OUTDOOR: max link budget
     *         INDOOR: link budget with 20dB margin
     *         UNDERGROUND: link budget with 30dB margin
     *     groupId: the group id related to the operator to get its coverage redundancy result,
     *     default is the group of your credentials.
     *
     * Response
     *
     * {
     * "redundancy":3
     * }
     *
     *
     * Fields:
     *
     *     redundancy: the base station redundancy : 0 = none, 1 = 1 base station, 2 = 2 base stations, 3 = 3 base stations or more
     *
     */
    @ApiOperation(
            value = "Redundancy",
            notes = "Get base station redundancy coverage for a given latitude and longitude." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>lat: the latitude.</li>" +
                    " <li>lng: the longitude.</li>" +
                    " <li>mode: the coverage mode (UNDERGROUND, INDOOR, OUTDOOR), default is INDOOR." +
                    "   <ul>" +
                    "    <li>OUTDOOR: max link budget </li>" +
                    "    <li>INDOOR: link budget with 20dB margin</li>" +
                    "    <li>UNDERGROUND: link budget with 30dB margin</li>" +
                    "   </ul>" +
                    " </li>" +
                    " <li>groupId: the group id related to the operator to get its coverage redundancy result, " +
                    " default is the group of your credentials.</li>" +
                    "</ul>",
            response = SigfoxApiCoverageRedundancy.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiCoverageRedundancy.class)
    })
    @RequestMapping(
            value ="/redundancy",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageRedundancy(
            HttpServletRequest request,
            @RequestParam("lat")
            @ApiParam(required = false, name = "lat", value = "The latitude.")
                    double lat,
            @RequestParam("lng")
            @ApiParam(required = false, name = "lng", value = "The longitude.")
                    double lng,
            @RequestParam("mode")
            @ApiParam(required = false, name = "mode", value = "The coverage mode (UNDERGROUND, INDOOR, OUTDOOR), default is INDOOR. ")
                    Optional<String> mode,
            @RequestParam("groupId")
            @ApiParam(required = false, name = "groupId", value = "The group id related to the operator to get its coverage redundancy result, default is the group of your credentials.")
                    Optional<String> groupId
    ) {

        SigfoxApiProxy<SigfoxApiCoverageRedundancy> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiCoverageRedundancy>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Global Coverage API (Single point)
     *
     * Get coverage levels for any location (latitude and longitude) in all public territories.
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/coverages/global/predictions?lat={lat}&lng={lng}
     *
     * Parameters:
     *
     *     lat: the latitude. Must be between -90° and 90°.
     *     lng: the longitude. Must be between -180° and 180°.
     *
     * Optionally, the request can also have the following parameter:
     *
     *     radius: (optional) the distance around the lat/lng point in meters, default is 300
     *
     * Response
     *
     * {
     *     "locationCovered": true,
     *     "margins":[
     *         42.12,
     *         27.49,
     *         5.87
     *     ]
     * }
     *
     *
     * Fields:
     *
     *     locationCovered: True, if the requested location is considered covered.
     *     margins: The margins values (dB) for redundancy level 1, 2 and 3
     *
     */
    @ApiOperation(
            value = "Global Coverage API (Single point)",
            notes = "Get coverage levels for any location (latitude and longitude) in all public territories." +
                    "<br/>"+
                    "Parameters: <br/>" +
                    "<ul>" +
                    " <li>lat: the latitude.</li>" +
                    " <li>lng: the longitude.</li>" +
                    " <li>radius: (optional) the distance around the lat/lng point in meters, default is 300</li>" +
                    "</ul>",
            response = SigfoxApiCoverageMargins.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiCoverageMargins.class)
    })
    @RequestMapping(
            value ="/global/predictions",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageSingle(
            HttpServletRequest request,
            @RequestParam("lat")
            @ApiParam(required = false, name = "lat", value = "The latitude.")
                    double lat,
            @RequestParam("lng")
            @ApiParam(required = false, name = "lng", value = "The longitude.")
                    double lng,
            @RequestParam("radius")
            @ApiParam(required = false, name = "radius", value = "(optional) the distance around the lat/lng point in meters, default is 300")
                    Optional<Integer> radius
    ) {

        SigfoxApiProxy<SigfoxApiCoverageMargins> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiCoverageMargins>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Global Coverage API (Multiple points)
     *
     * Get coverage levels for any location (latitude and longitude) in all public territories.
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/coverages/global/predictions
     *
     * {
     *   "locations":[
     *     {"lat": 43.5, "lng": 1.5},
     *     {"lat": 43.4, "lng": 1.5}
     *   ],
     *   "radius": 350
     * }
     *
     *
     * Fields:
     *
     *     locations: an array of positions. Valid locations have two properties, lat and lng.
     *         lat: a latitude in degrees. Must be between -90° and 90°.
     *         lng: a longitude in degrees. Must be between -180° and 180°.
     *     radius: (optional) the distance around the lat/lng locations in meters, default is 300.
     *
     */
    @ApiOperation(
            value = "Global Coverage API (Multiple points)",
            notes = "Get coverage levels for any location (latitude and longitude) in all public territories. <br/>",
            response = SigfoxApiCoverageMarginsList.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiCoverageMarginsList.class),
    })
    @RequestMapping(
            value ="/global/predictions",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> getCoverageMultiple(
            HttpServletRequest request,
            @ApiParam(required = true, name = "locationList", value = "List of Location")
            @Valid @RequestBody SigfoxApiCoverageMarginsListInput locationList
    ) {
        ObjectMapper mapper = new ObjectMapper();
        SigfoxApiProxy<SigfoxApiCoverageMarginsList> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiCoverageMarginsList>(proxy.proxify(request,mapper.writeValueAsString(locationList)), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Internal - Impossible to parse message",HttpStatus.BAD_REQUEST);
        }
    }


}



