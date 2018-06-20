/*
 * Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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


    private String id;
    private String channel;
    private int callbackType;
    private int callbackSubtype;
    private String payloadConfig;
    private boolean enabled;
    private boolean sendDuplicate;
    private boolean dead;
    private boolean sendSni;
    private String urlPattern;
    private String url;
    private String bodyTemplate;
    private KeyValue body;
    private KeyValue headers;
    private String contentType;
    private String httpMethod;
    private String method;
    private String linePattern;
    private boolean downlinkHook;               // true when the callback is used for downlink

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
