package fr.ingeniousthings.apis;

import fr.ingeniousthings.apis.models.ITSigfoxModelDeviceTypeFull;
import fr.ingeniousthings.apis.services.ITSigfoxException;
import fr.ingeniousthings.apis.services.ITSigfoxExportDeviceType;
import fr.ingeniousthings.apis.services.ITSigfoxImportDeviceType;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(value="ItDevicetypesService", tags="IngeniousThings-api-devicetypes")
@RequestMapping(value = "/it/device-types")
@CrossOrigin
@RestController
public class ITDeviceTypesApi {


    /**
     * Export a device type and all it's attached callback and configuration
     *
     * Request
     *
     * GET https://foo.bar/it/device-types/export/{id}/?apiLogin=...&apiPassword=...
     *
     * Parameters:
     *
     *     id - device-type id
     *     apiLogin - login for sigfox API access
     *     apiPassword - password for sigfox API access
     *
     * Returns:
     *     device-type description as a JSON document
     *
     */
    @ApiOperation(
            value = "Export a device-type description",
            notes = "Export a device-type description including all the callback configuration. The result is " +
                    "a JSON document with all the information. <br/>" +
                    "Limits : currently the Content-type of callbacks is not exposed by Sigfox Api. This is the only missing " +
                    "element waiting for Sigfox resolution.<br/>" +
                    "You need to have a Sigfox API account for accessing your device-type data. For reading these information " +
                    "the API account needs to be in one of these profiles :" +
                    "<ul>" +
                    "<li>DEVICE_MANAGER[R/W]</li>" +
                    "<li>CUSTOMER[R/W]</li>" +
                    "<li>DEVICE[R]</li>" +
                    "<li>LIMITED_ADMIN</li>" +
                    "<li>OPT_DEVICETYPE_ORDER[W]</li>" +
                    "</ul>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String): The device-type id (obtained from device-type list API or from the device type URL in the backend.</li>" +
                    " <li>apiLogin (String[]): The API account login given by Sigfox backend.</li>" +
                    " <li>apiPassword (String): The API account passord given by Sigfox backend.</li>" +
                    "<ul>" +
                    "",
            response = ITSigfoxModelDeviceTypeFull.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message= "Success", response = ITSigfoxModelDeviceTypeFull.class)
    })
    @RequestMapping(
            value ="/export/{id}/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET
    )
    @CrossOrigin
    public ResponseEntity<?> exportDeviceTypes(
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("id") String id,

            @RequestParam("apiLogin")
            @ApiParam(
                    required = true,
                    name = "apiLogin",
                    value = "The API account login as given by sigfox backend."
            ) String apiLogin,

            @RequestParam("apiPassword")
            @ApiParam(
                    required = true,
                    name = "apiPassword",
                    value = "The API account login as given by sigfox backend."
            ) String apiPassword

    ) {

        try {
            ITSigfoxModelDeviceTypeFull ret = ITSigfoxExportDeviceType.exportDeviceType(
                    apiLogin,
                    apiPassword,
                    id
            );
            return new ResponseEntity<ITSigfoxModelDeviceTypeFull>(ret, HttpStatus.OK);
        }  catch (ITSigfoxException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }

    }



    /**
     * Import a device type and all it's attached callback and configuration
     * Eventually rename the deviceType, change its group of contract
     *
     * Request
     *
     * POST https://foo.bar/it/device-types/import/?apiLogin=...&apiPassword=...&name=...&groupId=...&contractId=...
     *
     * The devicetype structure is passed in the body (the same as exported one)
     * name, groupId, contractId can also be changed in the BODY instead of using parameters
     *
     * Parameters:
     *
     *     apiLogin - login for sigfox API access
     *     apiPassword - password for sigfox API access
     *     name - DeviceType name
     *     groupId - Identification of group to deploy device-type
     *     contractId - Identification of the contract to deploy device-type
     *
     * Returns:
     *      Nothing (else than Http Code)
     *
     */
    @ApiOperation(
            value = "Import a device-type description",
            notes = "Import a device-type description including all the callback configuration.<br/>" +
                    "During the import, the deviceType name, deviceType Group and deviceType contract can be changed if these " +
                    "parameters are filled, otherwise the one in the JSON deviceType description are used." +
                    "Limits : currently the Content-type of callbacks is not exposed by Sigfox Api. By default the callback Content-type is " +
                    "forced to 'application/json' <br/>" +
                    "You need to have a Sigfox API account for accessing your device-type data. For reading these information " +
                    "the API account needs to be in one of these profiles :" +
                    "<ul>" +
                    "<li>DEVICE_MANAGER[W]</li>" +
                    "<li>CUSTOMER[W]</li>" +
                    "<li>LIMITED_ADMIN</li>" +
                    "</ul>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>apiLogin (String[]): The API account login given by Sigfox backend.</li>" +
                    " <li>apiPassword (String): The API account passord given by Sigfox backend.</li>" +
                    " <li>name (Optional - String): The device-type name as it will be displayed in the backend.</li>" +
                    " <li>groupId (Optinal - String): The groupId to attach the device-type if different than the one in the BODY.</li>" +
                    " <li>contractId (Optinal - String): The contractId to attach the device-type if different than the one in the BODY.</li>" +
                    " <li>BODY (Json String):  The device type definition as returned by /it/device-types/export/{id}/ endpoint.</li>" +
                    "<ul>" +
                    "",
            response = String.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/import/",
            produces = {MediaType.TEXT_HTML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> importDeviceTypes(
            @RequestParam("apiLogin")
            @ApiParam(
                    required = true,
                    name = "apiLogin",
                    value = "The API account login as given by sigfox backend."
            ) String apiLogin,

            @RequestParam("apiPassword")
            @ApiParam(
                    required = true,
                    name = "apiPassword",
                    value = "The API account login as given by sigfox backend."
            ) String apiPassword,

            @RequestParam("name")
            @ApiParam(
                    required = false,
                    name = "name",
                    value = "The device-type name once imported."
            ) Optional<String> name,

            @RequestParam("groupId")
            @ApiParam(
                    required = false,
                    name = "groupId",
                    value = "The groupId to attach the device-type if different than the one in the BODY."
            ) Optional<String> groupId,

            @RequestParam("contractId")
            @ApiParam(
                    required = false,
                    name = "contractId",
                    value = "The contractId to attach the device-type if different than the one in the BODY."
            ) Optional<String> contractId,


            @ApiParam(required = true, name = "deviceType", value = "The device type definition to import")
            @Valid @RequestBody  ITSigfoxModelDeviceTypeFull deviceType
    ) {

        try {

            ITSigfoxImportDeviceType.importDeviceType(
                    apiLogin,
                    apiPassword,
                    ((name.isPresent())?name.get():null),
                    ((groupId.isPresent()?groupId.get():null)),
                    ((contractId.isPresent()?contractId.get():null)),
                    deviceType,
                    true
            );
            return new ResponseEntity<String>("Success", HttpStatus.CREATED);
        }  catch (ITSigfoxException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }

    }


    /**
     * Import a device type and all it's attached callback and configuration
     * Eventually rename the deviceType, change its group of contract
     *
     * Request
     *
     * POST https://foo.bar/it/device-types/duplicate/{id}/?apiLogin=...&apiPassword=...&name=...&groupId=...&contractId=...
     *
     * The devicetype structure is passed in the body (the same as exported one)
     * name, groupId, contractId can also be changed in the BODY instead of using parameters
     *
     * Parameters:
     *
     *     id - device-type id
     *     apiLogin - login for sigfox API access
     *     apiPassword - password for sigfox API access
     *     name - DeviceType name
     *     groupId - Identification of group to deploy device-type
     *     contractId - Identification of the contract to deploy device-type
     *
     * Returns:
     *      Nothing (else than Http Code)
     *
     */
    @ApiOperation(
            value = "Duplicate a device-type",
            notes = "Duplicate a device-type description including all the callback configuration.<br/>" +
                    "This is cumulating the export and import operations and works the same way.<br/>" +
                    "During the import, the deviceType name, deviceType Group and deviceType contract can be changed." +
                    "Limits : currently the Content-type of callbacks is not exposed by Sigfox Api. By default the callback Content-type is " +
                    "forced to 'application/json' <br/>" +
                    "You need to have a Sigfox API account for accessing your device-type data. For reading these information " +
                    "the API account needs to be in one of these profiles :" +
                    "<ul>" +
                    "<li>DEVICE_MANAGER[W]</li>" +
                    "<li>CUSTOMER[W]</li>" +
                    "<li>LIMITED_ADMIN</li>" +
                    "</ul>" +
                    "Parameters: " +
                    "<ul>" +
                    " <li>id (String): The device-type id (obtained from device-type list API or from the device type URL in the backend.</li>" +
                    " <li>apiLogin (String[]): The API account login given by Sigfox backend.</li>" +
                    " <li>apiPassword (String): The API account passord given by Sigfox backend.</li>" +
                    " <li>name (Optional - String): The device-type name as it will be displayed in the backend.</li>" +
                    " <li>groupId (Optinal - String): The groupId to attach the device-type if different than the one in the BODY.</li>" +
                    " <li>contractId (Optinal - String): The contractId to attach the device-type if different than the one in the BODY.</li>" +
                    " <li>BODY (Json String):  The device type definition as returned by /it/device-types/export/{id}/ endpoint.</li>" +
                    "<ul>" +
                    "Note: at least one of the name, (groupId, contractId) must be specified otherwise a conflicting devicetype will be created...",
            response = String.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message= "Success", response = String.class)
    })
    @RequestMapping(
            value ="/duplicate/{id}",
            produces = {MediaType.TEXT_HTML_VALUE},
            method = RequestMethod.POST
    )
    @CrossOrigin
    public ResponseEntity<?> duplicateDeviceTypes(
            @ApiParam(
                    required = true,
                    name = "id",
                    value = "The device type identifier as returned by the /api/v2/device-types endpoint.")
            @PathVariable("id") String id,

            @RequestParam("apiLogin")
            @ApiParam(
                    required = true,
                    name = "apiLogin",
                    value = "The API account login as given by sigfox backend."
            ) String apiLogin,

            @RequestParam("apiPassword")
            @ApiParam(
                    required = true,
                    name = "apiPassword",
                    value = "The API account login as given by sigfox backend."
            ) String apiPassword,

            @RequestParam("name")
            @ApiParam(
                    required = false,
                    name = "name",
                    value = "The device-type name once imported."
            ) Optional<String> name,

            @RequestParam("groupId")
            @ApiParam(
                    required = false,
                    name = "groupId",
                    value = "The groupId to attach the device-type if different than the one in the BODY."
            ) Optional<String> groupId,

            @RequestParam("contractId")
            @ApiParam(
                    required = false,
                    name = "contractId",
                    value = "The contractId to attach the device-type if different than the one in the BODY."
            ) Optional<String> contractId

    ) {

        try {

            ITSigfoxModelDeviceTypeFull deviceType = ITSigfoxExportDeviceType.exportDeviceType(
                    apiLogin,
                    apiPassword,
                    id
            );

            ITSigfoxImportDeviceType.importDeviceType(
                    apiLogin,
                    apiPassword,
                    ((name.isPresent())?name.get():null),
                    ((groupId.isPresent()?groupId.get():null)),
                    ((contractId.isPresent()?contractId.get():null)),
                    deviceType,
                    true
            );
            return new ResponseEntity<String>("Success", HttpStatus.CREATED);
        }  catch (ITSigfoxException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.errorMessage,e.status);
        }

    }


}
