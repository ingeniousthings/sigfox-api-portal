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
 * Object retruned by sigfox API corresponding to device consumption history
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "consumption": {
 *      "id" : "110160_2015",                       // Format : deviceId_year (deviceId is converted in base 10 )
 *      "consumptions": [
 *          {
 *              "frameCount": 12,                   // Daily information 0 = 1-Jan ... 1 = 2-Jan
 *              "downlinkFrameCount": 3,
 *              "roamingFrameCount": 2,
 *              "roamingDownlinkFrameCount": 1
 *          },
 *              ...
 *          ]
 *      }
 *   }
 *
 * @author Paul Pinault
 */
public class SigfoxApiConsumptionInformationList {

    static class Consumption {
        protected String id;
        protected Consumptions[] consumptions;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public SigfoxApiConsumptionInformationList.Consumptions[] getConsumptions() {
            return consumptions;
        }

        public void setConsumptions(SigfoxApiConsumptionInformationList.Consumptions[] consumptions) {
            this.consumptions = consumptions;
        }

        @Override
        public String toString() {
            return "Consumption{" +
                    "id='" + id + '\'' +
                    ", consumptions=" + Arrays.toString(consumptions) +
                    '}';
        }
    }
    static class Consumptions {
        protected int frameCount;
        protected int downlinkFrameCount;
        protected int roamingFrameCount;
        protected int roamingDownlinkFrameCount;

        public int getFrameCount() {
            return frameCount;
        }

        public void setFrameCount(int frameCount) {
            this.frameCount = frameCount;
        }

        public int getDownlinkFrameCount() {
            return downlinkFrameCount;
        }

        public void setDownlinkFrameCount(int downlinkFrameCount) {
            this.downlinkFrameCount = downlinkFrameCount;
        }

        public int getRoamingFrameCount() {
            return roamingFrameCount;
        }

        public void setRoamingFrameCount(int roamingFrameCount) {
            this.roamingFrameCount = roamingFrameCount;
        }

        public int getRoamingDownlinkFrameCount() {
            return roamingDownlinkFrameCount;
        }

        public void setRoamingDownlinkFrameCount(int roamingDownlinkFrameCount) {
            this.roamingDownlinkFrameCount = roamingDownlinkFrameCount;
        }

        @Override
        public String toString() {
            return "Consumption{" +
                    "frameCount=" + frameCount +
                    ", downlinkFrameCount=" + downlinkFrameCount +
                    ", roamingFrameCount=" + roamingFrameCount +
                    ", roamingDownlinkFrameCount=" + roamingDownlinkFrameCount +
                    '}';
        }
    }

    protected Consumption consumption;

    // ------------------------------------------------------
    // Getter & Setters

    public SigfoxApiConsumptionInformationList.Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(SigfoxApiConsumptionInformationList.Consumption consumption) {
        this.consumption = consumption;
    }


    // ------------------------------------------------------
    // Serialization

    @Override
    public String toString() {
        return "SigfoxApiConsumptionInformationList{" +
                "consumption=" + consumption +
                '}';
    }
}


