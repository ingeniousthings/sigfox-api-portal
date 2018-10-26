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

@Api(value="contract", tags="sigfox-v2-contract-api")
@RequestMapping(value = "/api/v2/contract-infos")
@CrossOrigin
@RestController
public class SigfoxV2ContractApi {

    /**
     * Contract list
     *
     * Fetches a list of contract infos according to parameters and right visibility
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/contract-infos/
     *
     * Parameters:
     *
     *     None
     *
     * Optionally, the request can also have the following parameter:
     *
     * name - String : Searches for groups containing the given text in their name
     * groupId - Searches for contract infos who are attached to the given group
     * groupType - Searches for contract infos who are attached to groups of given type.
     *              2 -> BASIC
     *              9 -> CHANNEL
     * deep - boolean : If the group identifier is specified, also includes its subgroups.
     * orderIds - String[] : Searches for contract infos who have as order one given in the list. The elements of the list are separated by comma.
     * contractIds - String[] : Searches for contract infos who have as contractId one given in the list. The elements of the list are separated by comma.
     * fromTime - long : Searches for contract infos starting after the given time (in milliseconds since Unix Epoch).
     * toTime - long : Searches for contract infos ending before the given time (in milliseconds since Unix Epoch).
     * tokenDuration - int : Searches for contract infos with the given token duration in months.
     * pricingModel - int : Searches for contract infos with the given pricing model (see the list above for the allowed values).
     * subscriptionPlan - int : Searches for contract infos with the given subscription plan (see the list above for the allowed values).
     * geolocationMode - int : Searches for contract infos with the given geolocation mode (see the list above for the allowed values).
     *
     * fields - String[] - Defines the available device type’s fields to be returned in the response.
     *                     fields is a suite of string separated by comma, nested object fields can
     *                     be given with parenthesis recursively:
     *                     example : ?fields=attr1,attr2(attr3,attr4(attr5))
     * limit - int - Defines the maximum number of device types to return, default is 100
     * offset - int - Defines the number of device types to skip
     * pageId -int - Token representing the page to retrieve
     */


    @ApiOperation(
            value = "Fetches a list of contracts.",
            notes = "Fetches a list of contracts infos according to parameters and right visibility<br/>"+
                    "Parameters: Optionally, the request can also have the following parameters (see next response field below):<br/>" +
                    "<ul>" +
                    " <li>name (String): Searches for groups containing the given text in their name.</li>" +
                    " <li>groupId (String) : Searches for contract infos who are attached to the given group.</li>" +
                    " <li>groupType (Int) : Searches for contract infos who are attached to groups of given type" +
                    "  <ul>" +
                    "   <li>0 -> SO</li>" +
                    "   <li>2 -> Basic</li>" +
                    "   <li>5 -> SVNO</li>" +
                    "   <li>6 -> Partners</li>" +
                    "   <li>7 -> NIP</li>" +
                    "   <li>8 -> DIST</li>" +
                    "   <li>9 -> Channel</li>" +
                    "   <li>10 -> Starter</li>" +
                    "   <li>11 -> Partner</li>" +
                    " </ul>" +
                    " </li>" +
                    " <li>deep (boolean): If the group identifier is specified, also includes its subgroups.</li>" +
                    " <li>orderIds (String[]): Searches for contract infos who have as order one given in the list. " +
                           "The elements of the list are separated by comma.</li>" +
                    " <li>contractIds (String[]): Searches for contract infos who have as contractId one given in the list. " +
                          "The elements of the list are separated by comma.</li>" +
                    " <li>fromTime (long): Searches for contract infos starting after the given time (in milliseconds since Unix Epoch).</li>" +
                    " <li>toTime (long): Searches for contract infos ending before the given time (in milliseconds since Unix Epoch).</li>" +
                    " <li>tokenDuration (int): Searches for contract infos with the given token duration in months.</li>" +
                    " <li>pricingModel (int) : Searches for contract infos with the given pricing model." +
                        "<ul>" +
                        "<li>1 -> Pricing model version 1.</li>" +
                        "<li>2 -> Pricing model version 2.</li>" +
                        "</ul>"+
                    " </li>" +
                    " <li>subscriptionPlan (int) : Searches for contract infos with the given subscription plan." +
                        "<ul>" +
                        "<li>0 -> Free order</li>" +
                        "<li>1 -> Pay As You Grow (PAYG)</li>" +
                        "<li>2 -> Committed Volume Plan (CVP)</li>" +
                        "<li>3 -> Flexible Committed Volume Plan (CVP Flex)</li>" +
                        "</ul>" +
                    " </li>" +
                    " <li>geolocationMode (int)  : Searches for contract infos with the given geolocation mode." +
                        "<ul>" +
                        "<li>0 -> no geoloc</li>" +
                        "<li>1 -> high service level (1km precision)</li>" +
                        "<li>2 -> low service level (10km precision)</li>" +
                        "</uL>" +
                     " </li>" +
                    " <li>fields (String[]): Fetches all sub-groups (default false)." +
                        "fields is a suite of string separated by comma, nested object fields can " +
                        "be given with parenthesis recursively: " +
                        "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "<li>limit (int): Defines the maximum number of device types to return, default is 100.</li>" +
                    "<li>offset (int): Defines the number of device types to skip.</li>" +
                    "<li>pageId (int): Token representing the page to retrieve.</li>" +
                    "</ul>",
            response = SigfoxApiv2ContractListResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2ContractListResponse.class)
    })
    @RequestMapping(
            value ="/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getContractList(
            HttpServletRequest request,
            @RequestParam("name")
            @ApiParam(
                    required = false,
                    name = "name",
                    value = "Searches for groups containing the given text in their name."
            ) Optional<String> name,

            @RequestParam("groupId")
            @ApiParam(
                    required = false,
                    name = "groupId",
                    value = "Searches for contract infos who are attached to the given group."
            ) Optional<String> groupId,

            @RequestParam("groupType")
            @ApiParam(
                    required = false,
                    name = "groupType",
                    value = "Searches for contract infos who are attached to groups of given type : " +
                    "  <ul>" +
                    "   <li>0 -> SO</li>" +
                    "   <li>2 -> Basic</li>" +
                    "   <li>5 -> SVNO</li>" +
                    "   <li>6 -> Partners</li>" +
                    "   <li>7 -> NIP</li>" +
                    "   <li>8 -> DIST</li>" +
                    "   <li>9 -> Channel</li>" +
                    "   <li>10 -> Starter</li>" +
                    "   <li>11 -> Partner</li>" +
                    " </ul>",
                    allowableValues = "0,2,5,6,7,8,9,10,11"
            ) Optional<Integer> groupType,

            @RequestParam("deep")
            @ApiParam(
                    required = false,
                    name = "deep",
                    value = "If the group identifier is specified, also includes its subgroups."
            ) Optional<Boolean> deep,

            @RequestParam("orderIds")
            @ApiParam(
                    required = false,
                    name = "orderIds",
                    value = "Searches for contract infos who have as order one given in the list. " +
                            "The elements of the list are separated by comma."
            ) Optional<String> orderIds,

            @RequestParam("contractIds")
            @ApiParam(
                    required = false,
                    name = "contractIds",
                    value = "Searches for contract infos who have as contractId one given in the list. " +
                            "The elements of the list are separated by comma."
            ) Optional<String> contractIds,

            @RequestParam("fromTime")
            @ApiParam(
                    required = false,
                    name = "fromTime",
                    value = "Searches for contract infos starting after the given time (in milliseconds since Unix Epoch)."
            ) Optional<Long> fromTime,

            @RequestParam("toTime")
            @ApiParam(
                    required = false,
                    name = "toTime",
                    value = "Searches for contract infos ending before the given time (in milliseconds since Unix Epoch)."
            ) Optional<Long> toTime,

            @RequestParam("tokenDuration")
            @ApiParam(
                    required = false,
                    name = "tokenDuration",
                    value = "Searches for contract infos with the given token duration in months."
            ) Optional<Integer> tokenDuration,

            @RequestParam("pricingModel")
            @ApiParam(
                    required = false,
                    name = "pricingModel",
                    value = "Searches for contract infos with the given pricing model : " +
                            "<ul>" +
                            "<li>1 -> Pricing model version 1.</li>" +
                            "<li>2 -> Pricing model version 2.</li>" +
                            "</ul>",
                    allowableValues = "1,2"
            ) Optional<Integer> pricingModel,

            @RequestParam("subscriptionPlan")
            @ApiParam(
                    required = false,
                    name = "subscriptionPlan",
                    value = "Searches for contract infos with the given subscription plan : " +
                            "<ul>" +
                            "<li>0 -> Free order</li>" +
                            "<li>1 -> Pay As You Grow (PAYG)</li>" +
                            "<li>2 -> Committed Volume Plan (CVP)</li>" +
                            "<li>3 -> Flexible Committed Volume Plan (CVP Flex)</li>" +
                            "</ul>",
                    allowableValues = "0,1,2,3"
            ) Optional<Integer> subscriptionPlan,

            @RequestParam("geolocationMode")
            @ApiParam(
                    required = false,
                    name = "geolocationMode",
                    value = "Searches for contract infos with the given geolocation mode : " +
                            "<ul>" +
                            "<li>0 -> no geoloc</li>" +
                            "<li>1 -> high service level (1km precision)</li>" +
                            "<li>2 -> low service level (10km precision)</li>" +
                            "</uL>",
                    allowableValues = "0,1,2"
            ) Optional<Integer> geolocationMode,


            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device type’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields,

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
            ) Optional<Integer> offset,

            @RequestParam("pageId")
            @ApiParam(
                    required = false,
                    name = "pageId",
                    value = "Token representing the page to retrieve."
            ) Optional<Integer> pageId


    ) {

        SigfoxApiProxy<SigfoxApiv2ContractListResponse> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2ContractListResponse>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Contract information
     *
     * Get the description of a particular contract
     *
     * Request
     *
     * GET https://backend.sigfox.com/api/v2/contract-infos/{id}
     *
     * id: the contract identifier as returned by the /api/v2/contract-infos endpoint.
     *
     */
    @ApiOperation(
            value = "Fetches a contract info information",
            notes = "Fetches a particular contract info information. <br/>"+
                    "Parameters the contract ID is provide in the URL:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): the contract identifier as returned by the /api/v2/contract-infos endpoint</li>" +
                    "<li>fields (query-String[]): Fetches all sub-groups (default false)." +
                    "fields is a suite of string separated by comma, nested object fields can " +
                    "be given with parenthesis recursively: " +
                    "example : ?fields=attr1,attr2(attr3,attr4(attr5))</li>" +
                    "</ul>",
            response = SigfoxApiv2ContractInfo.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = SigfoxApiv2ContractInfo.class)
    })
    @RequestMapping(
            value ="/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getContractInfo(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The contract’s identifier")
            @PathVariable("id") String id,

            @RequestParam("fields")
            @ApiParam(
                    required = false,
                    name = "fields",
                    example = "attr1,attr2(attr3,attr4(attr5))",
                    value = "Defines the available device type’s fields to be returned in the response. " +
                            "fields is a suite of string separated by comma, nested object fields can " +
                            "be given with parenthesis recursively"
            ) Optional<String> fields

            ) {

        SigfoxApiProxy<SigfoxApiv2ContractInfo> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2ContractInfo>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }


    /**
     * Restart device by contractId
     *
     * Create a job to restart the devices associated to a contract
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/contract-infos/{id}/bulk/restart
     *
     * Fields:
     *
     *     id: the id of the contract the device to be reseted are belonging to
     *
     */
    /*
    @ApiOperation(
            value = "Restart device by contractId",
            notes = "Create a job to restart the devices associated to a contract.<br/>"+
                    "Parameters the contract ID is provided in the URL path:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The contract’s identifier (hexadecimal format)</li>" +
                    "</ul>",
            response = SigfoxApiv2JobIdResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job Started", response = SigfoxApiv2JobIdResponse.class)
    })
    @RequestMapping(
            value ="/{id}/bulk/restart",
            produces = {MediaType.APPLICATION_JSON_VALUE},
           // consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> resetdevicesByContractId(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The contract’s identifier")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<SigfoxApiv2GroupId> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2GroupId>(proxy.proxify(request), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }
    */

    /**
     * Restart device by contractId
     *
     * Create a job to restart the devices associated to a contract
     *
     * Request
     *
     * POST https://backend.sigfox.com/api/v2/contract-infos/{id}/bulk/restart
     *
     * Fields:
     *
     *     id: the id of the contract the device to be reseted are belonging to
     *
     */

    @ApiOperation(
            value = "Restart device by contractId",
            notes = "Create a job to restart the devices associated to a contract.<br/>"+
                    "Parameters the contract ID is provided in the URL path:<br/>" +
                    "<ul>" +
                    "<li>id (path-String): The contract’s identifier (hexadecimal format)</li>" +
                    "</ul>",
            response = SigfoxApiv2JobIdResponse.class,
            authorizations = { @Authorization(value="basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Job Started", response = SigfoxApiv2JobIdResponse.class)
    })
    @RequestMapping(
            value ="/{id}/bulk/restart",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            // consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> restartdevicesByContractId(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "The contract’s identifier")
            @PathVariable("id") String id
    ) {

        SigfoxApiProxy<SigfoxApiv2GroupId> proxy = new SigfoxApiProxy<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<SigfoxApiv2GroupId>(proxy.proxify(request), HttpStatus.CREATED);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }
    }

    /**
     * Fetches the contract async job status for restart action
     *
     * Request:
     *
     * GET https://backend.sigfox.com/api/v2/contract-infos/bulk/restart/{jobId}
     *
     * Parameters:
     *
     * jobId (path-String): The job’s identidier (hexademical format)
     *
     */
    @ApiOperation(
            value = "Fetches the contract async job status for restart action",
            notes = "Fetches the contract async job status for restart action. <br/>" +
                    "Parameters:<br/>" +
                    "<ul>" +
                    "<li>jobId (path-String): The job’s identifier (hexademical format)</li>" +
                    "</ul>",
            response = SigfoxApiv2JobAction.class,
            authorizations = {@Authorization(value = "basicAuth")}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = SigfoxApiv2JobAction.class)
    })
    @RequestMapping(
            value = "/bulk/restart/{jobId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            //consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> getRestartByContractIdJobStatus(
            HttpServletRequest request,
            @ApiParam(required = true, name = "jobId", value = "The job’s identidier (hexademical format).")
            @PathVariable("jobId") String jobId
    ) {

        SigfoxApiProxy<SigfoxApiv2JobAction> proxy = new SigfoxApiProxy<>();
        try {
            return new ResponseEntity<SigfoxApiv2JobAction>(proxy.proxify(request), HttpStatus.OK);
        } catch (SigfoxApiProxyException e) {
            return new ResponseEntity<String>(e.errorMessage, e.status);
        }
    }

}



