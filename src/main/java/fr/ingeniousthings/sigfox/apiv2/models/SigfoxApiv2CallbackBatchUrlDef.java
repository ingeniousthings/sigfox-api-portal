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

@Api(tags = "batchUrlCbDef", description = "Callback Batch Url Definition")
public class SigfoxApiv2CallbackBatchUrlDef {

    @ApiModelProperty(
            notes = "The callback’s url.",
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
            required = false
    )
    protected String httpMethod;

    @ApiModelProperty(
            notes = "The line pattern representing a message.",
            required = false
    )
    protected String linePattern;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


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

    public String getLinePattern() {
        return linePattern;
    }

    public void setLinePattern(String linePattern) {
        this.linePattern = linePattern;
    }
}
