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
