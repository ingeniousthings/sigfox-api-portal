package fr.ingeniousthings.sigfox.api.elements;

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
        public String  id;
        public String  pac;
    }

    protected String prefix;
    protected List<SigfoxApiDeviceCreateInputLine> ids;
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



}
