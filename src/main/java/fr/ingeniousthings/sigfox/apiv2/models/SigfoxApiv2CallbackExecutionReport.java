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

// Model Callback
@Api(tags = "CallbackExecutionReport", description = "Callback of type HTTP Report")
public class SigfoxApiv2CallbackExecutionReport {

    @ApiModelProperty(
            notes = "The subject of the mail which have been sent",
            required = false
    )
    protected String subject;

    @ApiModelProperty(
            notes = "The body of the mail which have been sent",
            required = false
    )
    protected String message;

    @ApiModelProperty(
            notes = "The URL called when this message has been processed",
            required = false
    )
    protected String url;

    @ApiModelProperty(
            notes = "The headers sent in the request. If no header is defined, this field is not present.",
            required = false
    )
    protected KeyValue headers;

    @ApiModelProperty(
            notes = "The body of the request, if any. It is only present if the request method is POST.",
            required = false
    )
    protected String body;

    @ApiModelProperty(
            notes = "The content type of the request. It is only present if the request is a POST.",
            required = false
    )
    protected String contentType;

    @ApiModelProperty(
            notes = "The HTTP method, currently only GET, POST or PUT.",
            required = false
    )
    protected String method;

    @ApiModelProperty(
            notes = "If there was an error, for instance if the body is JSON and could not be evaluated.",
            required = false
    )
    protected String error;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public KeyValue getHeaders() {
        return headers;
    }

    public void setHeaders(KeyValue headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
