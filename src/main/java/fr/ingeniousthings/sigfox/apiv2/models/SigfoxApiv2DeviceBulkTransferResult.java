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

import java.util.List;

@Api(tags = "bulkTransfer", description = "Result of bulk transfer")
public class SigfoxApiv2DeviceBulkTransferResult {

    @ApiModelProperty(
            notes = "The device type where new devices will be created",
            required = true
    )
    protected String deviceTypeId;

    @Api(tags = "deviceTransferDetails", description = "Device transfered details")
    public class DeviceTransferDetails {
        @ApiModelProperty(
                notes = "The device’s identifier (hexadecimal format)",
                required = true
        )
        protected String id;

        @ApiModelProperty(
                notes = "Whether to keep the device history or not",
                value = "false",
                required = false
        )
        protected boolean keepHistory;

        @ApiModelProperty(
                notes = "true if the device is activable and can take a token. " +
                        "Not used if the device has already a token and if the t" +
                        "ransferred is intra-order.",
                value = "false",
                required = false
        )
        protected boolean activable;

        // ============================================================
        // Generated Getters & Setters
        // ============================================================


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isKeepHistory() {
            return keepHistory;
        }

        public void setKeepHistory(boolean keepHistory) {
            this.keepHistory = keepHistory;
        }

        public boolean isActivable() {
            return activable;
        }

        public void setActivable(boolean activable) {
            this.activable = activable;
        }
    }

    @ApiModelProperty(
            notes = "List all the transfered devices",
            required = true
    )
    protected List<DeviceTransferDetails> devices;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public List<DeviceTransferDetails> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceTransferDetails> devices) {
        this.devices = devices;
    }
}
