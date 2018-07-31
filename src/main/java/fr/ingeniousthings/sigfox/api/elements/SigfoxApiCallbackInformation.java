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


package fr.ingeniousthings.sigfox.api.elements;

import java.lang.Override;
import java.lang.String;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage redundancy. (number of antennas covereing the area)
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *                      id: "584871ac50057472d9f5d8ca",
 *                      channel: "URL" or "BATCH_URL" or "EMAIL",
 *                      callbackType: 0,                                        - 0 = data / 1 = service / 2 = error
 *                      payloadConfig: "",
 *                      callbackSubtype: 3,                                     - 2 = uplink / 3 = bidir / 0 = status / 4 = ack / 5 = repeater
 *                      enabled: true,
 *                      sendDuplicate: true,
 *                      dead: false,                                            - last callback call failed
 *                      urlPattern: "http://foo.bar?key=value&key=value",       - when channel URL or BATCH_URL
 *                      httpMethod: "GET" or "POST",                            - when channel URL or BATCH_URL
 *                      downlinkHook: true,                                     - when channel URL
 *                      headers: { }                                            - when channel URL (@TODO : see what can be the values)
 *                      linePattern: "{device}:{data}"                          - when channel BATCH_URL
 *                      subject: ??                                             - when channel EMAIL (@TODO ...)
 *                      recipient: ??                                           - when channel EMAIL (@TODO ...)
 *                      message:??                                              - when channel EMAIL (@TODO ...)
 *  }
 * ExtraField for calback the creation
 *  {
 *                      url: "http:// ...",                                     - equivalent to urlPattern
 *                      sendSni: true,
 *                      bodyTemplate: "free text",
 *                      headers: { "key":"value" },
 *                      contentType: "application/json",
 *  }
 *  Fields coming from the Message Error Api with different names
 *  {
 *              url: "https://api.staging.ingeniousthings.fr/capture/api/sigfox",   - existing
 *              headers: { },                                                       - existing
 *              method: "POST",                                                     - equivalent to httpMethod
 *              contentType: "application/json",                                    - existing
 *              body: {                                                             - equivalent with bodyTemplate ( but updated one way )
 *                  time: 1501941440,
 *                  your body content
 *              }
 *  }
 * ----------------------------------------------------------------------------------
 * Support
 *  - channel "URL" and "BATCH_URL" only actually
 *  - callbacktype 0
 *
 * ----------------------------------------------------------------------------------
 *
 * @author Paul Pinault
 */
public class SigfoxApiCallbackInformation {

    public static class KeyValue {
        @JsonIgnore
        private Map<String,Object> entry = new HashMap<String, Object>();

        @JsonAnyGetter
        public Map<String, Object> getEntry() {
            return entry;
        }

        @JsonAnySetter
        public void setEntry(String name, Object value) {
            this.entry.put(name,value);
        }

        @Override
        public String toString() {
            boolean first = true;
            String s = "{";
            Iterator it = entry.entrySet().iterator();
            while ( it.hasNext() ) {
                if ( !first ) s+= ",";
                Map.Entry pair = (Map.Entry)it.next();
                s += "\""+pair.getKey()+"\" : \""+pair.getValue()+"\"";
                first = false;
            }
            s+="}";
            return s;
        }
    }

    @ApiModelProperty(
            notes = "Uniq ID of the callback",
            required = true
    )
    private String id;

    @ApiModelProperty(
            notes = "Define the medium of callback to create : URL, BATCH_URL or EMAIL",
            required = true
    )
    private String channel;

    @ApiModelProperty(
            notes = "Define the type of callback to create : 0 = DATA, 1 = SERVICE, 2 = ERROR, ",
            required = true
    )
    private int callbackType;

    @ApiModelProperty(
            notes = "Define the subtype for:" +
                    "<ul>" +
                    "<li> Type 0 = DATA " +
                    "<ul>" +
                    "<li> Subtype 2 : UPLINK -  callback for an uplink message </li>" +
                    "<li> Subtype 3 : BIDIR - callback for a bidirectional message</li>" +
                    "</ul>" +
                    "</li>,<li> 1 = SERVICE" +
                    "<ul>" +
                    "<li> Subtype 0 : STATUS - callback sending information about the status of a device</li>" +
                    "<li> Subtype 1 : GEOLOC - callback sent on a message that can be geolocated</li>" +
                    "<li> Subtype 4 : ACKNOWLEDGE - callback sent on a downlink acknowledge message</li>" +
                    "<li> Subtype 5 : REPEATER - callback triggered when a repeater sends an OOB (available for SERVICE callbacks)</li>" +
                    "</ul>" +
                    "</li>,<li> 2 = ERROR - No Subtype </li>" +
                    "</ul>",
            required = false
    )
    private int callbackSubtype;

    @ApiModelProperty(
            notes = "This is a configuration to define custom variables that you then can use in the bodyTemplate, url, headers.",
            required = true
    )
    private String payloadConfig;

    @ApiModelProperty(
            notes = "Enabled true to enable the callback, false else",
            required = true
    )
    private boolean enabled;

    @ApiModelProperty(
            notes = "True to duplicates callback, false else",
            required = false
    )
    private boolean sendDuplicate;

    @ApiModelProperty(
            notes = "True if last use of the callback fails, false else",
            required = false
    )
    private boolean dead;

    @ApiModelProperty(
            notes = "Type URL or BATCH_URL (optional) - Send SNI (Server Name Indication) for SSL/TLS connections",
            required = false
    )
    private boolean sendSni;
    @ApiModelProperty(
            notes = "Type URL or BATCH_URL (optional) - The callback url",
            required = false
    )
    private String urlPattern;
    private String url;

    @ApiModelProperty(
            notes = "Type URL (optional) - The body template of the request. Only if httpMethpd is set to POST Or PUT. It can contain predefined and custom variables.",
            required = false
    )
    private KeyValue body;
    private String bodyTemplate;

    @ApiModelProperty(
            notes = "Type URL (optional) - headers of the request. The header value can contain a variable (predefined or custom)." +
                    "The headers format is { \"headername\" : \"headerValue\", ... }",
            required = false
    )
    private KeyValue headers;

    @ApiModelProperty(
            notes = "Type URL (optional) - The content type of the request. Only taken into account for POST requests. Available content types are :" +
                    "<ul><li>text/plain</li><li>application/json</li><li>application/x-www-form-urlencoded, last but not least, the default one.</li></ul>",
            required = false
    )
    private String contentType;

    @ApiModelProperty(
            notes = "Type URL (optional) - The HTTP method used to send the callback (GET, POST or PUT)",
            required = false
    )
    private String httpMethod;
    private String method;

    @ApiModelProperty(
            notes = "Type BATCH_URL (optional) - The line pattern representing a message.",
            required = false
    )
    private String linePattern;

    @ApiModelProperty(
            notes = "Type DATA - True when the callback is used for downlink",
            required = false
    )
    private boolean downlinkHook;               // true when the callback is used for downlink

    @ApiModelProperty(
            notes = "Type EMAIL (optional) - The mail subject",
            required = false
    )
    private String subject;

    @ApiModelProperty(
            notes = "Type EMAIL (optional) - The recipient of the email. Must be a valid email address.",
            required = false
    )
    private String recipient;

    @ApiModelProperty(
            notes = "Type EMAIL (optional) - The content of the message.",
            required = false
    )
    private String message;


    // ========================================================================
    // ADVANCED GETTER / SETTER
    // ========================================================================

    public boolean isUplink() {
        return (this.callbackType == 0 && this.callbackSubtype == 2);
    }

    public boolean isBidir() {
        return (this.callbackType == 0 && this.callbackSubtype == 3);
    }

    public boolean isError() { return (this.callbackType == 2); }

    public boolean isService() { return (this.callbackType == 1); }

    public boolean isChannelUrl() {
        return ( this.channel.compareToIgnoreCase("url") == 0 );
    }

    public boolean isChannelBatchUrl() {
        return ( this.channel.compareToIgnoreCase("batch_url") == 0 );
    }

    public void setUrl(String url) {
        this.url = url;
        this.urlPattern = url;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
        this.url = urlPattern;
    }

//    public void setHeadersStr(String stringHeaders) {
//        StringTokenizer st1 = new StringTokenizer(stringHeaders,",");
//        while ( st1.hasMoreTokens() ) {
//            String s2 = st1.nextToken();
//            StringTokenizer st2 = new StringTokenizer(s2,":");
//            String key = st2.nextToken();
//            String value = st2.nextToken();
//            System.out.println("<< "+key+" / "+value);
//            this.headers.setEntry(key,value);
//        }
//    }

    // ========================================================================
    // String dump for deviceType creation with API
    // ========================================================================
    public String toPublication() {
        String str = "{" +
                "  'channel' : '" + channel + '\'' +
                ", 'callbackType' : " + callbackType +
                ", 'url' : '" + url + '\'' +
                ", 'httpMethod' : '" + httpMethod + '\'' +
                ", 'enabled' : " + enabled +
                ", 'sendDuplicate' : " + sendDuplicate +
                ", 'sendSni' : " + sendSni;
         if (! isError() ) {
             str += ", 'callbackSubtype' : " + callbackSubtype;
         }

        if ( this.isChannelUrl() ) {
                if (this.payloadConfig != null) str+= ", 'payloadConfig' : '" + payloadConfig + '\'';
                if (this.bodyTemplate != null) str+= ", 'bodyTemplate' : '" + bodyTemplate + '\'' ;
                if (this.headers != null ) str+= ", 'headers' : " + headers;
                if (this.contentType != null) str += ", 'contentType' : '" + contentType + '\'';
         } else if ( this.isChannelBatchUrl() ) {
             str += ""+
                     ", 'linePattern' : '" + linePattern + '\'';
         }

         str += "}";
         return str.replace('\'','"');
    }



    // ========================================================================
    // GENERATED GETTER / SETTER
    // ========================================================================


    public boolean isSendSni() {
        return sendSni;
    }

    public void setSendSni(boolean sendSni) {
        this.sendSni = sendSni;
    }

    public String getUrl() {
        return url;
    }


    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
    }

    public SigfoxApiCallbackInformation.KeyValue getBody() {
        return body;
    }

    public void setBody(SigfoxApiCallbackInformation.KeyValue  body) {
        this.body = body;
        this.bodyTemplate = body.toString();
    }

    public SigfoxApiCallbackInformation.KeyValue getHeaders() {
        return headers;
    }

    public void setHeaders(SigfoxApiCallbackInformation.KeyValue headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isDownlinkHook() {
        return downlinkHook;
    }

    public void setDownlinkHook(boolean downlinkHook) {
        this.downlinkHook = downlinkHook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPayloadConfig() {
        return payloadConfig;
    }

    public void setPayloadConfig(String payloadConfig) {
        this.payloadConfig = payloadConfig;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSendDuplicate() {
        return sendDuplicate;
    }

    public void setSendDuplicate(boolean sendDuplicate) {
        this.sendDuplicate = sendDuplicate;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getUrlPattern() {
        return urlPattern;
    }


    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        this.method = httpMethod;
    }

    public int getCallbackSubtype() {
        return callbackSubtype;
    }

    public void setCallbackSubtype(int callbackSubtype) {
        this.callbackSubtype = callbackSubtype;
    }

    public int getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(int callbackType) {
        this.callbackType = callbackType;
    }

    public String getLinePattern() {
        return linePattern;
    }

    public void setLinePattern(String linePattern) {
        this.linePattern = linePattern;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
        this.httpMethod = method;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SigfoxApiCallbackInformation{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                ", callbackType=" + callbackType +
                ", callbackSubtype=" + callbackSubtype +
                ", payloadConfig='" + payloadConfig + '\'' +
                ", enabled=" + enabled +
                ", sendDuplicate=" + sendDuplicate +
                ", dead=" + dead +
                ", sendSni=" + sendSni +
                ", urlPattern='" + urlPattern + '\'' +
                ", url='" + url + '\'' +
                ", bodyTemplate='" + bodyTemplate + '\'' +
                ", headers='" + headers + '\'' +
                ", contentType='" + contentType + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", linePattern='" + linePattern + '\'' +
                ", downlinkHook=" + downlinkHook +
                '}';
    }

}
