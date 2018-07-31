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


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to DeviceInit / Message metric.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "lastDay": 47,
 *      "lastWeek": 276,
 *      "lastMonth": 784
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiMessageMetric {

    @ApiModelProperty(
            notes = "The number of message received from this device during the last 24 hours.",
            required = true
    )
    protected int lastDay;

    @ApiModelProperty(
            notes = "The number of message received from this device during the last 7 days.",
            required = true
    )
    protected int lastWeek;

    @ApiModelProperty(
            notes = "The number of message received from this device during the last 30 days.",
            required = true
    )
    protected int lastMonth;

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public int getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(int lastWeek) {
        this.lastWeek = lastWeek;
    }

    public int getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(int lastMonth) {
        this.lastMonth = lastMonth;
    }

    @Override
    public String toString() {
        return "SigfoxApiMessageMetric{" +
                "lastDay=" + lastDay +
                ", lastWeek=" + lastWeek +
                ", lastMonth=" + lastMonth +
                '}';
    }
}