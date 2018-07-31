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