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

@Api(tags = "minimalGroup", description = "Defines a group entity minimal information")
public class SigfoxApiv2GroupMinimal {

    @ApiModelProperty(
            notes = "The group’s identifier",
            example = "57309674171c857460043087",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The group’s name",
            example = "Group 1",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "Group’s type." +
                    "<ul>" +
                    "<li>0 -> SO</li>" +
                    "<li>2 -> Basic</li>" +
                    "<li>5 -> SVNO</li>" +
                    "<li>6 -> Partners</li>" +
                    "<li>7 -> NIP</li>" +
                    "<li>8 -> DIST</li>" +
                    "<li>9 -> Channel</li>" +
                    "<li>10 -> Starter</li>" +
                    "<li>11 -> Partner</li>" +
                    "</ul>",
            example = "0",
            required = false
    )
    protected int type;

    @ApiModelProperty(
            notes = "The depth level of the group in hierarchy.",
            example = "1",
            required = false
    )
    protected int level;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
