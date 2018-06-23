package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiLocationModel {

    @ApiModelProperty(
            notes = "The estimated latitude",
            required = false
    )
    protected double lat;

    @ApiModelProperty(
            notes = "The estimated longitude",
            required = false
    )
    protected double lng;

    @ApiModelProperty(
            notes = "The radius of the circle",
            required = false
    )
    protected int radius;

    @ApiModelProperty(
            notes = "Define from which source the geolocation has been computed :" +
                    "<ul>" +
                    "<li>0 Location computed by legacy mode using RSSI and position of station</li>" +
                    "<li>1 Location computed using the GPS data inside the payload</li>" +
                    "<li>2 Location computed by Atlas Network </li>" +
                    "<li>3 Location computed by Atlas POI</li>" +
                    "<li>4 Location computed by Atlas HD</li>" +
                    "</ul>",
            required = false
    )
    protected int source;

    @ApiModelProperty(
            notes = "Time the message was sent, in milliseconds since the Unix Epoch",
            required = false
    )
    protected long time;

    @ApiModelProperty(
            notes = "True, if a valid estimation for this message is available (GPS or RSSI), false otherwise",
            required = false
    )
    protected boolean valid;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
