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

import java.util.List;

public class SigfoxApiGroupInfoList {
    public class GroupListPaging {
        @ApiModelProperty(
                notes = "URL of the next «page» of groups",
                required = true
        )
        protected String next;
        public String getNext() {
            return next;
        }
        public void setNext(String next) {
            this.next = next;
        }
    }

    @ApiModelProperty(
            notes = "Array of group information records",
            required = true
    )
    protected List<SigfoxApiGroupInfo> data;

    @ApiModelProperty(
            notes = "Paging information, if more groups are available",
            required = false
    )
    protected GroupListPaging paging;

    /**
     * Getter & Setters
     */
    public List<SigfoxApiGroupInfo> getData() {
        return data;
    }

    public void setData(List<SigfoxApiGroupInfo> data) {
        this.data = data;
    }

    public GroupListPaging getPaging() {
        return paging;
    }

    public void setPaging(GroupListPaging paging) {
        this.paging = paging;
    }
}
