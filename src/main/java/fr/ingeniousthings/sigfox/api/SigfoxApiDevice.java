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

package fr.ingeniousthings.sigfox.api;

import fr.ingeniousthings.sigfox.api.elements.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import java.util.*;
import java.util.List;


/**
 * Summary
 *
 * This class manage the sigfox DeviceInit Component from the SigfoxApi
 * ----------------------------------------------------------------------------------
 * Requires:
 *   This class requieres SpringBoot framework
 *   This class requieres
 *     compile("org.apache.httpcomponents:httpcore:4.4.6")
 *     compile("commons-codec:commons-codec:1.10")
 * ----------------------------------------------------------------------------------
 * Support :
 *
 *
 * ----------------------------------------------------------------------------------
 *
 * @author Paul Pinault
 */
public class SigfoxApiDevice extends SigfoxApiConnector {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SigfoxApiDevice(String login, String password) {
        super(login, password);
    }

    // ========================================================================
    // Get the list of all the available devices for a given deviceType
    public List<SigfoxApiDeviceInformation> getSigfoxDevicesForDeviceType(String dtid) {

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        SigfoxApiDeviceInformationList devices = null;
        ArrayList<SigfoxApiDeviceInformation> ret = new ArrayList<SigfoxApiDeviceInformation>();

        do {
            if (url == null) {
                url = "devicetypes/" + dtid + "/devices";
            } else {
                if (devices == null) return null;
                url = devices.getPaging().getNext();
                url = url.substring(SigfoxApiConnector.API_PROTOCOL.length() + SigfoxApiConnector.API_BACKEND_URL.length());
            }
            ResponseEntity<SigfoxApiDeviceInformationList> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    url,
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiDeviceInformationList.class);
            devices = response.getBody();
            log.info(devices.toString());
            for (int i = 0; i < devices.getData().length; i++) {
                ret.add(devices.getData()[i]);
            }

        } while (devices.getPaging().getNext() != null);

        return ret;
    }

    // ========================================================================
    // Get a specific device id information with the associated message metrics or not
    public SigfoxApiDeviceInformation getSigfoxDevice(String id) {
        return getSigfoxDevice(id, false);
    }

    public SigfoxApiDeviceInformation getSigfoxDevice(String id, boolean metric) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceInformation> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devices/" + id,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiDeviceInformation.class);
        SigfoxApiDeviceInformation device = response.getBody();

        if (metric) {
            ResponseEntity<SigfoxApiMessageMetric> response2 =
                    restTemplate.exchange(
                            this.connectionString(
                                    "devices/" + id + "/messages/metric",
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiMessageMetric.class);
            device.setMetric(response2.getBody());
        }

        log.info("getSigfoxDevice by id (" + id + ") : " + device.toString());
        return device;

    }

    // ========================================================================
    // Create a new device

    public static final int NEWDEVICE_REGISTER_ERROR = 0;
    public static final int NEWDEVICE_REGISTER_DELAYED = 1;
    public static final int NEWDEVICE_REGISTER_SUCESS = 2;

    /**
     * Register a new device on sigfox backend. Process is asynchronous so we will have to
     * scan for execution result. Return status NEWDEVICE_REGISTER_xxxx
     *
     * @param id
     * @param pac
     * @param deviceTypeId
     * @param certificate
     * @return
     */
    public int registerNewSigfoxDevice(String id, String pac, String deviceTypeId, String certificate) {

        RestTemplate restTemplate = new RestTemplate();

        // delete the created devicetype
        ResponseEntity<SigfoxApiDeviceCreateOutput> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/" + deviceTypeId + "/devices/bulk/create/async",
                                null
                        ),
                        HttpMethod.POST,
                        this.generateRequestHeaders(
                                true,
                                (new SigfoxApiDeviceCreateInput("ItD_", certificate, id, pac)).toPublish()),
                        SigfoxApiDeviceCreateOutput.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            SigfoxApiDeviceCreateOutput r = response.getBody();
            if (r.getTotal() == 1) {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }
                    ;
                    RestTemplate restTemplate2 = new RestTemplate();
                    ResponseEntity<SigfoxApiDeviceCreateJobStatus> response2 =
                            restTemplate.exchange(
                                    this.connectionString(
                                            "devicetypes/" + deviceTypeId + "/registration/" + r.getJobId(),
                                            null
                                    ),
                                    HttpMethod.GET,
                                    this.generateRequestHeaders(),
                                    SigfoxApiDeviceCreateJobStatus.class);
                    try {
                        SigfoxApiDeviceCreateJobStatus r2 = response2.getBody();
                        if (r2.isJobDone()) {
                            if (r2.getTotal() == 0) return NEWDEVICE_REGISTER_ERROR;
                            else return NEWDEVICE_REGISTER_SUCESS;
                        }
                    } catch (Exception e) {
                    }
                    ;
                }
            } else {
                return NEWDEVICE_REGISTER_ERROR;
            }
            return NEWDEVICE_REGISTER_DELAYED;
        } else {
            log.warn("Status code is not success is : " + response.getStatusCode());
        }
        return NEWDEVICE_REGISTER_ERROR;

    }


    // ========================================================================
    // Get a specific device consumption history
    public SigfoxApiConsumptionInformationList getSigfoxDeviceConsumption(String id, int year) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiConsumptionInformationList> response =
                restTemplate.exchange(
                        this.connectionString(
                                "device/" + id + "/consumptions/" + year,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiConsumptionInformationList.class);
        SigfoxApiConsumptionInformationList consumption = response.getBody();

        log.info("getSigfoxDeviceConsumption with id (" + id + ") & year (" + year + ") : " + consumption.toString());
        return consumption;

    }

    // ========================================================================
    // Change a device type value for a given device
    public boolean SigfoxApiSwitchDeviceType(String did, String dtidDst) {
        log.info("SigfoxApiSwitchDeviceType(" + did + " / " + dtidDst + ")");
        return SigfoxUpdateDeviceInformation(did, dtidDst, null);
    }

    // ========================================================================
    // Change a device type or device name
    public boolean SigfoxUpdateDeviceInformation(String did, String dtidDst, String name) {

        log.info("SigfoxUpdateDeviceInformation(" + did + " / " + dtidDst + " / " + name + ")");

        RestTemplate restTemplate = new RestTemplate();
        SigfoxApiDeviceEditListPost o = new SigfoxApiDeviceEditListPost();
        SigfoxApiDeviceEditPost p = new SigfoxApiDeviceEditPost();
        p.setId(did);
        if (dtidDst != null)
            p.setDeviceTypeId(dtidDst);
        if (name != null)
            p.setName(name);
        o.addSigfoxApiDeviceEditPost(p);

        ResponseEntity<SigfoxApiDeviceEditReturn> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devices/bulk/edit",
                                null
                        ),
                        HttpMethod.POST,
                        this.generateRequestHeaders(true, o.toPublish()),
                        SigfoxApiDeviceEditReturn.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            SigfoxApiDeviceEditReturn ret = response.getBody();
            log.info("DeviceInit update (deviceTypeSwitch) - error/total (" + ret.getError() + "/" + ret.getTotal() + ")");
            if (ret.getError() == 0) {
                // success
                return true;
            } else {
                // error
                for (String err : ret.getLog()) {
                    log.warn(err);
                }
                return false;
            }
        }
        return false;
    }

}
