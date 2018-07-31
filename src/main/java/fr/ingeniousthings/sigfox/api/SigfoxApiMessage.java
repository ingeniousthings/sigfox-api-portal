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
