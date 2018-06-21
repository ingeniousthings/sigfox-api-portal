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
package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Summary
 *
 * Object returned by sigfox API corresponding to a All Devices ID/PAC for a device type.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *       "data" : [
 *         { "id" : "0B59", "pac" : "6A757C859B23471B" },
 *         { "id" : "C032", "pac" : "374FC61194FC4DA8" },
 *         ...
 *       ]
 *     }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceListInDeviceType {

    protected class SigfoxApiDeviceListLine {
        @ApiModelProperty(
                notes = "Identifier of the device",
                required = true
        )
        public String  id;
        @ApiModelProperty(
                notes = "Porting Authorization Code for this device",
                required = true
        )
        public String  pac;
    }


    @ApiModelProperty(
            notes = "The array of device ids and pacs records",
            required = true
    )
    protected List<SigfoxApiDeviceListLine> data;


    public List<SigfoxApiDeviceListLine> getData() {
        return data;
    }

    public void setData(List<SigfoxApiDeviceListLine> data) {
        this.data = data;
    }
}


