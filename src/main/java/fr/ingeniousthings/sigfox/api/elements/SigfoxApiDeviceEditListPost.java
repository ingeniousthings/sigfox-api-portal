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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "SigfoxApiDeviceEditReturn Entity")
public class SigfoxApiDeviceEditListPost {

    @ApiModelProperty(notes = "List of devices to be modified including modifications", required = false)
    private List<SigfoxApiDeviceEditPost> edits;

    // ------------------ Custom

    public SigfoxApiDeviceEditListPost() {
        this.edits = new ArrayList<SigfoxApiDeviceEditPost>();
    }

    public void addSigfoxApiDeviceEditPost(SigfoxApiDeviceEditPost _e) {
        this.edits.add(_e);
    }

    public String toPublish() {
        String r = "[";
        boolean first = true;
        for ( SigfoxApiDeviceEditPost _e : edits) {
            if ( !first ) {
                r+=",";
            } else first = false;
            r+= _e.toPublish();
        }
        r+="]";
        return r;
    }


}
