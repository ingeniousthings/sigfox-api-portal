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

import java.util.ArrayList;
import java.util.List;

/*
{
  "prefix" : "test-device-",
  "ids" : [
    { "id" : "C031", "pac": "6A757C859B23471B" },
    { "id" : "C032", "pac": "374FC61194FC4DA8" },
    ...
  ],
  "productCertificate" : "P_0004_D356_03"
}
 */

public class SigfoxApiDeviceCreateInput {

    protected class SigfoxApiDeviceCreateInputLine {
        @ApiModelProperty(
                notes = "Identifier of the device",
                required = true
        )
        public String  id;
        @ApiModelProperty(
                notes = "Porting Authorization Code for this device",
                required = true
        )
        public String  pac;
    }

    @ApiModelProperty(
            notes = "Used to create the name of each device, by prepending it to the device’s identifier",
            required = true
    )
    protected String prefix;

    @ApiModelProperty(
            notes = "List of device identifiers that have to be registered { \"id\" : \"XXXXXXXX\",\"pac\" : \"xxxx...xxx\" },",
            required = true
    )
    protected List<SigfoxApiDeviceCreateInputLine> ids;


    @ApiModelProperty(
            notes = "Product certificate key to associate to each device.",
            required = false
    )
    protected String productCertificate;

    public SigfoxApiDeviceCreateInput(
            String _prefix,
            String _productCertificate
    ) {
        this.prefix = _prefix;
        this.productCertificate = _productCertificate;
        this.ids = new ArrayList<SigfoxApiDeviceCreateInputLine>();
    }

    public SigfoxApiDeviceCreateInput(
            String _prefix,
            String _productCertificate,
            String _id,
            String _pac
    ) {
        this.prefix = _prefix;
        this.productCertificate = _productCertificate;
        this.ids = new ArrayList<SigfoxApiDeviceCreateInputLine>();
        this.addDevice(_id,_pac);
    }


    public void addDevice(String _id, String _pac) {
        SigfoxApiDeviceCreateInputLine l = new SigfoxApiDeviceCreateInputLine();
        l.id = _id;
        l.pac = _pac;
        this.ids.add(l);
    }

    public String toPublish(){
        String r = "{";
        r+="\"prefix\" : \""+this.prefix+"\",";
        r+="\"ids\" :[";
        boolean first = true;
        for ( SigfoxApiDeviceCreateInputLine l : this.ids) {
            if ( !first ) {
                r+=",";
            } else first = false;
            r+="{ \"id\" : \""+l.id+"\", \"pac\" : \""+l.pac+"\" }";
        }
        r+="]";
        if ( this.productCertificate != null ) {
            r += "\", productCertificate\" :\"" + this.productCertificate + "\"";
        }
        r+="}";
        return r;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<SigfoxApiDeviceCreateInputLine> getIds() {
        return ids;
    }

    public void setIds(List<SigfoxApiDeviceCreateInputLine> ids) {
        this.ids = ids;
    }

    public String getProductCertificate() {
        return productCertificate;
    }

    public void setProductCertificate(String productCertificate) {
        this.productCertificate = productCertificate;
    }
}
