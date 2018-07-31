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

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiStatusWarningResponse {

    public class SigfoxApiDeviceTypeStatusWarningResponseCallback {

        @ApiModelProperty(
                notes = "The subject of the mail which have been sent [email only]",
                required = false
        )
        protected String subject;

        @ApiModelProperty(
                notes = "The body of the mail which have been sent [email only]",
                required = false
        )
        protected String message;

        @ApiModelProperty(
                notes = "Present only if the response has already been processed, 200 if OK, otherwise 600.",
                required = false
        )
        protected int status;

        @ApiModelProperty(
                notes = "The URL called when this event has been emitted [HTTP only]",
                required = false
        )
        protected String url;

        @ApiModelProperty(
                notes = "Present only if the response has already been processed, contains additional information.[HTTP only]",
                required = false
        )
        protected String info;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    public class SigfoxApiDeviceTypeStatusWarningResponseDetail {

        @ApiModelProperty(
                notes = "The device identifier which has been detected down",
                required = true
        )
        protected List<String> deviceIds;

        @ApiModelProperty(
                notes = "The time where the event was sent, in milliseconds since the Unix Epoch",
                required = true
        )
        protected long time;

        @ApiModelProperty(
                notes = "The reason of this event",
                required = true
        )
        protected String message;

        @ApiModelProperty(
                notes = "WARN",
                required = true
        )
        protected String severity;

        @ApiModelProperty(
                notes = "The device type identifier",
                required = true
        )
        protected String deviceTypeId;

        @ApiModelProperty(
                notes = "List of ERROR callbacks if any, see doc for information about ERROR callbacks.",
                required = true
        )
        protected List<SigfoxApiDeviceTypeStatusWarningResponseCallback> callbacks;

        public List<String> getDeviceIds() {
            return deviceIds;
        }

        public void setDeviceIds(List<String> deviceIds) {
            this.deviceIds = deviceIds;
        }

        public void setCallbacks(List<SigfoxApiDeviceTypeStatusWarningResponseCallback> callbacks) {
            this.callbacks = callbacks;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(String deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

    }


    public class Paging {
        @ApiModelProperty(
                notes = "URL of the next «page»",
                required = true
        )
        protected String next;
        public String getNext() {
            return next;
        }
        public void setNext(String next) {
            this.next = next;
        }
    }


    @ApiModelProperty(
            notes = "The array of device communication down events",
            required = true
    )
    protected SigfoxApiDeviceTypeStatusWarningResponseDetail [] data;

    @ApiModelProperty(
            notes = "Paging information, if more event are available",
            required = false
    )
    protected Paging paging;

    public SigfoxApiDeviceTypeStatusWarningResponseDetail[] getData() {
        return data;
    }

    public void setData(SigfoxApiDeviceTypeStatusWarningResponseDetail[] data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
