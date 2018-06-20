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

    protected SigfoxApiMessageInformation[] data;
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


