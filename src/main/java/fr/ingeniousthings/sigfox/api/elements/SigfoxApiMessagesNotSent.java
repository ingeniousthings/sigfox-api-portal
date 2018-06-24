package fr.ingeniousthings.sigfox.api.elements;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SigfoxApiMessagesNotSent {


    public static class KeyValue {
        @JsonIgnore
        private Map<String,Object> entry = new HashMap<String, Object>();

        @JsonAnyGetter
        public Map<String, Object> getEntry() {
            return entry;
        }

        @JsonAnySetter
        public void setEntry(String name, Object value) {
            this.entry.put(name,value);
        }

        @Override
        public String toString() {
            boolean first = true;
            String s = "{";
            Iterator it = entry.entrySet().iterator();
            while ( it.hasNext() ) {
                if ( !first ) s+= ",";
                Map.Entry pair = (Map.Entry)it.next();
                s += "\""+pair.getKey()+"\" : \""+pair.getValue()+"\"";
                first = false;
            }
            s+="}";
            return s;
        }
    }


    public class SigfoxApiMessageResponseCallback {

        @ApiModelProperty(
                notes = "The subject of the mail which have been sent [email only]",
                required = false
        )
        public String subject;

        @ApiModelProperty(
                notes = "The body of the mail which have been sent [email only]",
                required = false
        )
        public String message;

        @ApiModelProperty(
                notes = "The URL called when this event has been emitted [HTTP only]",
                required = false
        )
        public String url;

        @ApiModelProperty(
                notes = "The headers sent in the request. If no header is defined, this field is not present.",
                required = false
        )
        public KeyValue headers;

        @ApiModelProperty(
                notes = "The body of the request, if any. It is only present if the request method is POST.",
                required = false
        )
        public String body;

        @ApiModelProperty(
                notes = "The content type of the request. It is only present if the request is a POST.",
                required = false
        )
        public String contentType;

        @ApiModelProperty(
                notes = "The HTTP method, currently only GET or POST.",
                required = false
        )
        public String method;


    }


    public class SigfoxApiMessagesErrorDetail {

        @ApiModelProperty(
                notes = "The device identifier which has been detected down",
                required = true
        )
        public String device;

        @ApiModelProperty(
                notes = "The device type identifier",
                required = true
        )
        public String deviceType;

        @ApiModelProperty(
                notes = "The time where the event was sent, in milliseconds since the Unix Epoch",
                required = true
        )
        public long time;

        @ApiModelProperty(
                notes = "Message content, hex encoded",
                required = true
        )
        public String data;

        @ApiModelProperty(
                notes = "The SNR of the messages received by the network so far",
                required = true
        )
        public double snr;

        @ApiModelProperty(
                notes = "Contains the callback response status.",
                required = true
        )
        public int status;

        @ApiModelProperty(
                notes = "Contains additional information on the response.",
                required = true
        )
        public String message;


        @ApiModelProperty(
                notes = "Information depends on the callback type.",
                required = true
        )
        public SigfoxApiMessageResponseCallback callback;

        @ApiModelProperty(
                notes = "All the parameters which have served to build the callback, see CALLBACK doc for an exhaustive list.",
                required = true
        )
        public KeyValue parameters;


    }


    @ApiModelProperty(
            notes = "The array of device messages error ",
            required = true
    )
    protected SigfoxApiMessagesErrorDetail [] data;

    @ApiModelProperty(
            notes = "Paging information, if more event are available",
            required = false
    )
    protected SigfoxApiPagingModel paging;


    // =====================================


    public SigfoxApiMessagesErrorDetail[] getData() {
        return data;
    }

    public void setData(SigfoxApiMessagesErrorDetail[] data) {
        this.data = data;
    }

    public SigfoxApiPagingModel getPaging() {
        return paging;
    }

    public void setPaging(SigfoxApiPagingModel paging) {
        this.paging = paging;
    }
}
