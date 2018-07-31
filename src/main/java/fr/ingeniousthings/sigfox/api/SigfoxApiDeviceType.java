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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import java.lang.System;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * This class manage the SigfoxApiv2DeviceType Component from the SigfoxApi
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
 * DeviceInit Type definition exemple
 * [
 *  {
 *     "channel" : "URL",
 *     "callbackType" : 0,
 *     "callbackSubtype" : 3,
 *     "url" : "http://iot.disk91.com/test",
 *     "httpMethod" : "POST",
 *     "enabled" : true,
 *     "sendDuplicate" : true,
 *     "sendSni" : true,
 *     "payloadConfig" : "",
 *     "bodyTemplate" : "{ @device@ : @{device}@, @rssi@ : @{rssi}@ }",     // @ is later replaced by the right string
 *     "headers" : { §time§ : §{time}§ },                                   // § is later replaced by the right string
 *     "contentType" : "application/json"
 *  },{
 *     "channel" : "URL",
 *     "callbackType" : 0,
 *     "callbackSubtype" : 3,
 *     "url" : "http://iot.disk91.com/test1",
 *     "httpMethod" : "GET",
 *     "enabled" : true,
 *     "sendDuplicate" : true,
 *     "sendSni" : true,
 *     "downlinkHook" : true,                                   // make default downlink
 *  },{
 *      "channel" : "URL",
 *      "callbackType" : 0,
 *      "callbackSubtype" : 2,
 *      "url" : "http://iot.disk91.com/test2",
 *      "httpMethod" : "GET",
 *      "enabled" : true,
 *      "sendDuplicate" : false,
 *      "sendSni" : true
 *  }]
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceType extends SigfoxApiConnector {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SigfoxApiDeviceType(String login, String password) {
        super(login, password);
    }

    // ========================================================================
    // Get the list of all the Devicetype you can access to
    // This returns all the information about the device type itself but not the
    // associated callback
    public List<SigfoxApiDeviceTypeInformation> getSigfoxAllDeviceType() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceTypeInformationList> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        /*new ParameterizedTypeReference<List<SigfoxApiDeviceTypeInformation>>(){}*/
                        SigfoxApiDeviceTypeInformationList.class);
        SigfoxApiDeviceTypeInformationList devicetype = response.getBody();

        log.info("getSigfoxAllDeviceType :"+devicetype.toString());
        return Arrays.asList(devicetype.getData());
    }


    // ========================================================================
    // Return DeviceTypeId from SigfoxApiv2DeviceType Name
    public String getSigfoxDeviceTypeIdFromName(String name,String contract) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceTypeInformationList> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiDeviceTypeInformationList.class);
        SigfoxApiDeviceTypeInformationList devicetype = response.getBody();
        for (SigfoxApiDeviceTypeInformation dt : devicetype.getData()) {
            if (   dt.getName().compareToIgnoreCase(name) == 0
                && dt.getContractId().compareToIgnoreCase(contract) == 0) return dt.getId();
        }
        return null;
    }

    // ========================================================================
    // Return true if a device type already exists for a given name
    public boolean isSigfoxDeviceTypeExists(String name, String contract) {
        return (getSigfoxDeviceTypeIdFromName(name,contract) != null);
    }


    // ========================================================================
    // Return true if a device type already exists for a given id
    public boolean isSigfoxDeviceTypeByIdExists(String id) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceTypeInformationList> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/" + id,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiDeviceTypeInformationList.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }


    // ========================================================================
    // Get the information about a Specific Devicetype based on its Id
    // the input is a deviceType id. this function also request the information
    // about the callbacks when type is custom
    public SigfoxApiDeviceTypeInformation getSigfoxDeviceTypeById(String id) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiDeviceTypeInformation> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/" + id,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiDeviceTypeInformation.class);
        SigfoxApiDeviceTypeInformation devicetype = response.getBody();


        ResponseEntity<SigfoxApiCallbackList> response2 =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/" + id + "/callbacks",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiCallbackList.class);
        SigfoxApiCallbackList callbacks = response2.getBody();
        devicetype.setCallback(callbacks);

            /*
            ResponseEntity<String> response2 =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/"+id+"/callbacks",
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        String.class);
            String callbacks = response2.getBody();
            System.out.println(callbacks);
            */

        log.info("getSigfoxDeviceTypeById : "+devicetype.toString());
        return devicetype;
    }

    // ========================================================================
    // Create a device type from an initialized object
    public SigfoxApiDeviceTypeInformation publishSigfoxDeviceType(SigfoxApiDeviceTypeInformation o) {
        RestTemplate restTemplate = new RestTemplate();

        // Create device type
        ResponseEntity<SigfoxApiDeviceTypeInformation> response;
        try {
            response =
                    restTemplate.exchange(
                            this.connectionString(
                                    "devicetypes/create",
                                    null
                            ),
                            HttpMethod.POST,
                            this.generateRequestHeaders(true, o.toPublication()),
                            SigfoxApiDeviceTypeInformation.class);
        } catch (HttpClientErrorException e) {
            System.err.println(e);
            System.err.println(o.toPublication());
            log.error("Callback devicetype creation rejected");
            return null;
        }
        if (response.getStatusCode() == HttpStatus.OK) {
            SigfoxApiDeviceTypeInformation _t = response.getBody();
            log.info("Devicetype creation success (" + _t.getId() + ")");
            o.setId(_t.getId());

            // Create the associated callback
            if (o.getCallback() != null) {
                String body = "[";
                List<SigfoxApiCallbackInformation> callbacks = o.getCallback().getAllCallbackAsList();
                for (SigfoxApiCallbackInformation callback : callbacks) {
                    if (body.length() > 1) body += ",";
                    body += callback.toPublication();
                }
                body += "]";

                // Hook for correctly protect the " char in the message for being intepreted correctly in sigfox backen
                body = body.replaceAll("@", "\\\\\"");
                body = body.replaceAll("§", "\\\"");

                //System.out.println(body);
                try {
                    ResponseEntity<String[]> response2 =
                            restTemplate.exchange(
                                    this.connectionString(
                                            "devicetypes/" + _t.getId() + "/callbacks/new",
                                            null
                                    ),
                                    HttpMethod.POST,
                                    this.generateRequestHeaders(true, body),
                                    String[].class);

                    if (response2.getStatusCode() == HttpStatus.OK) {
                        log.info("Callback creation success");

                        // update the callback Id
                        String[] _s = response2.getBody();
                        // Sigfox Bug : the id are not corectly ordered... reorder as the id is incremented
                        // this is a workaround waiting for sigfox fix.
                        Arrays.sort(_s);

                        for (int i = 0; i < _s.length; i++) {
                            if (o.getCallback().setCallbackId(i, _s[i])) {
                                log.info("Callback id assigned properly(" + _s[i] + ")");
                            } else {
                                log.warn("callback id assignation failed");
                            }
                        }

                        // set the corresponding callback as downlink if downlinkHook is set
                        SigfoxApiCallbackInformation downlink = o.getCallback().getCallbackIdWithDownlinkHookSet();
                        if (downlink != null) {
                            //log.info("downlink url : " + "devicetypes/" + _t.getId() + "/callbacks/" + downlink.getId() + "/downlink");
                            ResponseEntity<String> response4 =
                                    restTemplate.exchange(
                                            this.connectionString(
                                                    "devicetypes/" + _t.getId() + "/callbacks/" + downlink.getId() + "/downlink",
                                                    null
                                            ),
                                            HttpMethod.POST,
                                            this.generateRequestHeaders(),
                                            String.class);
                            if (response4.getStatusCode() == HttpStatus.OK) {
                                String coverage = response4.getBody();
                                log.info("Downlink assigned properly");
                            } else {
                                log.error("Downlink assignation failed");
                            }
                        }


                    } else {
                        log.error("Callback creation not possible, do nothing");
                    }
                } catch (HttpClientErrorException e) {
                    System.err.println(e);
                    log.error("Callback creation not possible, removing devicetype");
                    // delete the created devicetype
                    ResponseEntity<String> response3 =
                            restTemplate.exchange(
                                    this.connectionString(
                                            "devicetypes/delete",
                                            null
                                    ),
                                    HttpMethod.POST,
                                    this.generateRequestHeaders(true, o.toDelete()),
                                    String.class);
                    return null;
                }
            }

        } else {
            log.error("DeviceInit type creation not possible");
            return null;
        }
        return o;

    }


    // ============================================================
    // Delete a deviceType assuming no device are attached
    public boolean deleteSigfoxDeviceType(String id) {
        RestTemplate restTemplate = new RestTemplate();

        // delete the created devicetype
        ResponseEntity<String> response =
                restTemplate.exchange(
                        this.connectionString(
                                "devicetypes/delete",
                                null
                        ),
                        HttpMethod.POST,
                        this.generateRequestHeaders(true, SigfoxApiDeviceTypeInformation.toDeleteById(id)),
                        String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }
}