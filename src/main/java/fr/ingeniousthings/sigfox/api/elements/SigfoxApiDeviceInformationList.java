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

import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a DeviceInit List
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *    data : [ {
 *      "id" : "0B59",
 *      "name" : "Test 0B59",
 *      "type" : "4d3091a05ee16b3cc86699ab",        // Link to the device Type id
 *      "last" : 0,                                 // Last message sent ( EPOC in S)
 *      "averageSignal": 8.457,                     // based on last 25 messages
 *      "averageSnr": 8.457,                        //   ...
 *      "averageRssi": -125.967,                    //   ...
 *      "state": 0,
 *      "lat" : 43.45,
 *      "lng" : 1.54,
 *      "computedLocation": {                       // null when the device is not positionned by network
 *          "lat" : 43.45,
 *          "lng" : 6.54,
 *          "radius": 500
 *      },
 *      "activationTime": 1404096340556,
 *      "pac": "545CB3B17AC98BA4",
 *      "tokenType": "CONTRACT",
 *      "contractId": "7896541254789654aedfba4c",
 *      "tokenEnd": 1449010800000,
 *      "preventRenewal": false
 *     },{
 *        SigfoxApiDeviceInformation
 *     }
 *   ],
 *   paging : {
 *       next : "url for the next page"
 *   }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceInformationList {


    @ApiModelProperty(
            notes = "The array of device information records",
            required = true
    )
    protected SigfoxApiDeviceInformation[] data;
    @ApiModelProperty(
            notes = "Access to the next page of devices",
            required = true
    )
    protected SigfoxApiPagingModel paging;

    // ------------------------------------------------------
    // Getter & Setters

    public SigfoxApiDeviceInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiDeviceInformation[] data) {
        this.data = data;
    }

    public SigfoxApiPagingModel getPaging() {
        return paging;
    }

    public void setPaging(SigfoxApiPagingModel paging) {
        this.paging = paging;
    }


    // ------------------------------------------------------
    // Serialization

    @Override
    public String toString() {
        return "SigfoxApiDeviceInformationList{" +
                "data=" + Arrays.toString(data) +
                ", paging=" + paging +
                '}';
    }
}


