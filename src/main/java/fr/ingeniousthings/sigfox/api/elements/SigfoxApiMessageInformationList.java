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
 *          "device" : "002C",
 *          "time" : 1343321977,
 *          "data" : "3235353843fc",
 *          "snr" : "38.2",
 *          "computedLocation": {
 *              "lat" : 43.45,
 *              "lng" : 6.54,
 *              "radius": 500
 *          },
 *          "linkQuality" : "GOOD",
 *          "downlinkAnswerStatus" : {
 *              "data" : "1511000a00007894"
 *          }
 *     },{
 *        SigfoxApiMessageInformation
 *     }
 *   ],
 *   paging : {
 *       next : "url for the next page"
 *   }
 *
 * @author Paul Pinault
 */
public class SigfoxApiMessageInformationList {

    public class MessageListPaging {
        @ApiModelProperty(
                notes = "URL of the next «page» of messages",
                required = false
        )
        protected String next;

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "MessageListPaging{" +
                    "next='" + next + '\'' +
                    '}';
        }
    }

    @ApiModelProperty(
            notes = "The array of device messages",
            required = false
    )
    protected SigfoxApiMessageInformation[] data;

    @ApiModelProperty(
            notes = "Paging information, if more messages are available",
            required = false
    )
    protected MessageListPaging paging;

    // ------------------------------------------------------
    // Getter & Setters

    public SigfoxApiMessageInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiMessageInformation[] data) {
        this.data = data;
    }

    public SigfoxApiMessageInformationList.MessageListPaging getPaging() {
        return paging;
    }

    public void setPaging(SigfoxApiMessageInformationList.MessageListPaging paging) {
        this.paging = paging;
    }


    // ------------------------------------------------------
    // Serialization


    @Override
    public String toString() {
        return "SigfoxApiMessageInformationList{" +
                "data=" + Arrays.toString(data) +
                ", paging=" + paging +
                '}';
    }
}


