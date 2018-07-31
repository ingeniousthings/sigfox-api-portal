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

@Api(tags = "SigfoxApiDeviceEditReturn Entity")
public class SigfoxApiDeviceEditPost {

    @ApiModelProperty(notes = "device id to change", required = true)
    private String id;
    @ApiModelProperty(notes = "new device name", required = false)
    private String name;
    @ApiModelProperty(notes = "new device latitude", required = false)
    private double lat;
    @ApiModelProperty(notes = "new device longitude", required = false)
    private double lng;
    @ApiModelProperty(notes = "id of the new device type", required = false)
    private String deviceTypeId;
    @ApiModelProperty(notes = "new product certificate", required = false)
    private boolean tokenRenewForbidden;

    // To identify what property have to be changed
    private boolean _name = false;
    private boolean _lat = false;
    private boolean _lng = false;
    private boolean _deviceTypeId = false;
    private boolean _tokenRenewForbidden = false;


    // -------------------- CUSTOM


    public String toPublish() {
        String r="{";
        r+="\"id\" :\""+this.id+"\"";
        r+=(this._name)?",\"name\" :\""+this.name+"\"":"";
        r+=(this._lat)?",\"lat\" : "+this.lat:"";
        r+=(this._lng)?",\"lng\" : "+this.lng:"";
        r+=(this._deviceTypeId)?",\"deviceTypeId\" :\""+this.deviceTypeId+"\"":"";
        r+=(this._tokenRenewForbidden)?",\"tokenRenewForbidden\" : "+((this.tokenRenewForbidden)?"true":"false"):"";
        r+="}";
        return r;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        this._name = true;
    }

    public void setLat(double lat) {
        this.lat = lat;
        this._lat = true;
    }

    public void setLng(double lng) {
        this.lng = lng;
        this._lng = true;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
        this._deviceTypeId = true;
    }

    public void setTokenRenewForbidden(boolean tokenRenewForbidden) {
        this.tokenRenewForbidden = tokenRenewForbidden;
        this._tokenRenewForbidden = true;
    }


    // --------------------- GENERATED


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public boolean isTokenRenewForbidden() {
        return tokenRenewForbidden;
    }
}
