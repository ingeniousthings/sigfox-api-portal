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

@Api(tags = "MessageStat", description = "Message sent statistics")
public class SigfoxApiv2MessageStats {

    @ApiModelProperty(
            notes = "Number of device messages for the last day",
            example = "10",
            required = false
    )
    protected int lastDay;

    @ApiModelProperty(
            notes = "Number of device messages for the last week",
            example = "10",
            required = false
    )
    protected int lastWeek;

    @ApiModelProperty(
            notes = "Number of device messages for the last month",
            example = "10",
            required = false
    )
    protected int lastMonth;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


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
}
