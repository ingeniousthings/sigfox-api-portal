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
package fr.ingeniousthings.sigfox.apiv2.models;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

// Be Carefull, some Callback Object are CallbackExecutionReport... One name
// multiple model
@Api(tags = "callback", description = "details on a callback")
public class SigfoxApiv2Callback extends SigfoxApiv2CallbackCommonDef {

    @ApiModelProperty(
            notes = "The callback’s identifier.",
            example = "6ad0b2ef5004741d21cee6d3",
            required = false
    )
    protected String id;


    @ApiModelProperty(
            notes = "The callback URL. Mandatory for URL and BATCH_URL callbacks.",
            example = "https://foor.bar/sigfoxCallback/",
            required = false
    )
    protected String urlPattern;

    @ApiModelProperty(
            notes = "The HTTP method used to send the callback (GET, POST or PUT). Mandatory for URL callbacks:<br/>" +
                    "<ul>" +
                    "<li>GET</li>" +
                    "<li>POST</li>" +
                    "<li>PUT</li>" +
                    "</ul>",
            example = "POST",
            required = false
    )
    protected String httpMethod;

    @ApiModelProperty(
            notes = "True if this callback is used for downlink, else false.",
            example = "false",
            required = false
    )
    protected boolean downlinkHook;

    @ApiModelProperty(
            notes = "Headers of the request. The header value can contain a variable " +
                    "(predefined or custom). Mandatory for URL callbacks.",
            required = false
    )
    protected KeyValue headers;


    @ApiModelProperty(
            notes = "Send SNI (Server Name Indication) for SSL/TLS connections. Used by BATCH_URL and URL callbacks (optional).",
            example = "true",
            required = false
    )
    protected boolean sendSni;

    @ApiModelProperty(
            notes = "The body template of the request, only if httpMethpd is set to POST Or PUT. It can contain predefined " +
                    "and custom variables. Mandatory for URL callbacks",
            example = "'{ 'device' : '{device}', 'station' : '{station}', 'seq' : {seqNumber} ... }'",
            required = false
    )
    protected String bodyTemplate;

    @ApiModelProperty(
            notes = "The line pattern representing a message. Mandatory for BATCH_URL callbacks.",
            example = "",
            required = false
    )
    protected String linePattern;


    @ApiModelProperty(
            notes = "The mail subject. Mandatory for EMAIL callbacks.",
            example = "Sigfox callback received",
            required = false
    )
    protected String subject;

    @ApiModelProperty(
            notes = "The recipient of the email. Must be a valid email address. " +
                    "Mandatory for EMAIL callbacks.",
            example = "callback@foo.bar",
            required = false
    )
    protected String recipient;

    @ApiModelProperty(
            notes = "The content of the message. Mandatory for EMAIL callbacks.",
            example = "A new callback has been reeived from {device} ...",
            required = false
    )
    protected String message;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public boolean isDownlinkHook() {
        return downlinkHook;
    }

    public void setDownlinkHook(boolean downlinkHook) {
        this.downlinkHook = downlinkHook;
    }

    public KeyValue getHeaders() {
        return headers;
    }

    public void setHeaders(KeyValue headers) {
        this.headers = headers;
    }

    public boolean isSendSni() {
        return sendSni;
    }

    public void setSendSni(boolean sendSni) {
        this.sendSni = sendSni;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
    }

    public String getLinePattern() {
        return linePattern;
    }

    public void setLinePattern(String linePattern) {
        this.linePattern = linePattern;
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
}
