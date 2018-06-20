/*
 *
 *  Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import java.util.List;

import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * This class manage the sigfox Messages Component from the SigfoxApi
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
public class SigfoxApiMessage extends SigfoxApiConnector {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public SigfoxApiMessage(String login, String password) {
        super(login, password);
    }

    // ========================================================================
    // Get the list of all the available devices for a given deviceType
    // Parameters :
    //  - DeviceInit Id as a String
    //  - Time to start search from in ms ; end is now. Default is 7 day before now
    public List<SigfoxApiMessageInformation> getSigfoxMessagesForDevice(String did, long since) {

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        SigfoxApiMessageInformationList messages = null;
        ArrayList<SigfoxApiMessageInformation> ret = new ArrayList<SigfoxApiMessageInformation>();

        // When not specified reports last 7 days only
        if ( since <= 0 ) {
            long now = System.currentTimeMillis() / 1000;
            since = ( now - 24*3600*7 );
        } else {
            since /= 1000;      // Api is in seconds
        }

        do {
            if ( url == null ) {
                url = "devices/" + did + "/messages?since="+since;
            } else {
                if ( messages == null ) return null;
                url  = messages.getPaging().getNext();
                url  = url.substring(SigfoxApiConnector.API_PROTOCOL.length() + SigfoxApiConnector.API_BACKEND_URL.length());
            }
            ResponseEntity<SigfoxApiMessageInformationList> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    url,
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiMessageInformationList.class);
            messages = response.getBody();
            log.info(messages.toString());
            for ( int i =0 ; i < messages.getData().length ; i++ ) {
                ret.add(messages.getData()[i]);
            }

        } while ( messages.getPaging().getNext() != null );

        return ret;
    }

    // ========================================================================
    // Get the list of all messages in error for a given device
    // Parameters :
    //  - DeviceInit Id as a String
    //  - Time to start search from in ms ; end is now. Default is 7 day before now
    public List<SigfoxApiMessageInformation> getSigfoxErrorMessagesForDevice(String deviceId, long since) {
       return  getSigfoxErrorMessagesForAny( "hexId="+deviceId, since);
    }

    // ========================================================================
    // Get the list of all messages in error for a given device
    // Parameters :
    //  - DeviceInit Id as a String
    //  - Time to start search from in ms ; end is now. Default is 7 day before now
    public List<SigfoxApiMessageInformation> getSigfoxErrorMessagesForDeviceType(String deviceTypeId, long since) {
        return  getSigfoxErrorMessagesForAny( "deviceTypeId="+deviceTypeId, since);
    }

    // ========================================================================
    // Get the list of all messages in error generic function
    // Parameters :
    //  - Search string
    //  - Time to start search from in ms ; end is now. Default is 7 day before now
    protected List<SigfoxApiMessageInformation> getSigfoxErrorMessagesForAny(String limit, long since) {

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        SigfoxApiMessageInformationList messages = null;
        ArrayList<SigfoxApiMessageInformation> ret = new ArrayList<SigfoxApiMessageInformation>();

        // When not specified reports last 7 days only
        if ( since <= 0 ) {
            long now = System.currentTimeMillis();
            since = ( now - 24*3600*7*1000 );
        }

        do {
            if ( url == null ) {
                url = "callbacks/messages/error?"+limit+"&since="+since;
            } else {
                if ( messages == null ) return null;
                url  = messages.getPaging().getNext();
                url  = url.substring(SigfoxApiConnector.API_PROTOCOL.length() + SigfoxApiConnector.API_BACKEND_URL.length());
            }
            ResponseEntity<SigfoxApiMessageInformationList> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    url,
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiMessageInformationList.class);
            messages = response.getBody();
            log.info(messages.toString());
            for ( int i =0 ; i < messages.getData().length ; i++ ) {
                ret.add(messages.getData()[i]);
            }

        } while ( messages.getPaging().getNext() != null );

        return ret;
    }


}
