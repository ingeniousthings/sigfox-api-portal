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
import java.util.Arrays;
import java.util.List;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a device type list
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "data" : [
 *                  {
 *                     "id" : "4d3091a05ee16b3cc86699ab",
 *                     "name" : "Sigfox test device",
 *                     "group" : "4d39a4c9e03e6b3c430e2188",
 *                     "description" : "Little things in the black boxes",
 *                     "keepAlive" : 7200,
 *                     "payloadType" : "None",
 *                     "contract" : "523b1d10d777d3f5ae038a02"
 *                  }, {
 *                          [SigfoxApiDeviceTypeInformation structure]
 *                  }
 *              ]
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceTypeInformationList {

    private SigfoxApiDeviceTypeInformation[] data;


    public SigfoxApiDeviceTypeInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiDeviceTypeInformation[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String s =  "SigfoxApiDeviceTypeInformationList{" +
                    "data= [";
        List<SigfoxApiDeviceTypeInformation> l = Arrays.asList(data);
        for ( SigfoxApiDeviceTypeInformation o : l ){
            s+="{";
            s+= o.toString();
            s+="},";
        }
        s +=  "]}";
        return s;
    }
}