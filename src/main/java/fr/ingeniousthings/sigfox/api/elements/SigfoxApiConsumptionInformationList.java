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


