/*
 * Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a call back list corresponding to a
 * specified DeviceType
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "data" : [
 *                  {
 *                      id: "584871ac50057472d9f5d8ca",
 *                      channel: "URL",
 *                      callbackType: 0,
 *                      payloadConfig: "",
 *                      callbackSubtype: 3,
 *                      enabled: true,
 *                      sendDuplicate: true,
 *                      dead: false,
 *                      urlPattern: "http://foo.bar?key=value&key=value",
 *                      httpMethod: "GET",
 *                      downlinkHook: true,
 *                      headers: { }
 *                  }, {
 *                          [SigfoxApiCallbackInformation structure]
 *                  }
 *              ],
 *      "service" : [
 *                         [SigfoxApiCallbackInformation structure]
 *                  ],
 *      "error" :   [
 *                         [SigfoxApiCallbackInformation structure]
 *                  ]
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiCallbackList {

    @ApiModelProperty(
            notes = "List of callback in the DATA channel",
            required = false
    )
    private SigfoxApiCallbackInformation[] data;
    @ApiModelProperty(
            notes = "List of callback in the SERVICE channel",
            required = false
    )
    private SigfoxApiCallbackInformation[] service;
    @ApiModelProperty(
            notes = "List of callback in the ERROR channel",
            required = false
    )
    private SigfoxApiCallbackInformation[] error;

    // =================================================
    // Custom getters & setters
    // =================================================

    public List<SigfoxApiCallbackInformation> getDataCallbackAsList() {
        return (data!=null)?Arrays.asList(data):new ArrayList<SigfoxApiCallbackInformation>();
    }

    public List<SigfoxApiCallbackInformation> getServiceCallbackAsList() {
        return (service!=null)?Arrays.asList(service):new ArrayList<SigfoxApiCallbackInformation>();
    }

    public List<SigfoxApiCallbackInformation> getErrorCallbackAsList() {
        return (error!=null)?Arrays.asList(error):new ArrayList<SigfoxApiCallbackInformation>();
    }

    public List<SigfoxApiCallbackInformation> getAllCallbackAsList() {
        List<SigfoxApiCallbackInformation> l = new ArrayList<SigfoxApiCallbackInformation>();
        l.addAll(this.getDataCallbackAsList());
        l.addAll(this.getServiceCallbackAsList());
        l.addAll(this.getErrorCallbackAsList());
        return l;
    }

    // Search for the callback index using the same order as in the callbackList
    // one found set the given id for this callback.
    public boolean setCallbackId(int i, String id) {
        // search for callback created with the corresponding index
        List<SigfoxApiCallbackInformation> l = this.getAllCallbackAsList();
        int k = 0;
        for (SigfoxApiCallbackInformation c : l ) {
            if ( i == k ) {
                c.setId(id);
                return true;
            }
            k++;
        }
        return false;
    }

    // Search in the data callback list if one of them have a downlink flag set
    // retruns corresponding object when found otherwize null.
    public SigfoxApiCallbackInformation getCallbackIdWithDownlinkHookSet() {
        List<SigfoxApiCallbackInformation> l = this.getDataCallbackAsList();
        for (SigfoxApiCallbackInformation c : l ) {
            if ( c.isDownlinkHook() )
            return c;
        }
        return null;
    }

    // =================================================
    // Generated getters & setters
    // =================================================


    public SigfoxApiCallbackInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiCallbackInformation[] data) {
        this.data = data;
    }

    public SigfoxApiCallbackInformation[] getService() {
        return service;
    }

    public void setService(SigfoxApiCallbackInformation[] service) {
        this.service = service;
    }

    public SigfoxApiCallbackInformation[] getError() {
        return error;
    }

    public void setError(SigfoxApiCallbackInformation[] error) {
        this.error = error;
    }

    @Override
    public String toString() {
        String s =  "SigfoxApiCallbackList{" +
                    "data= [";
        List<SigfoxApiCallbackInformation> l = Arrays.asList(data);
        for ( SigfoxApiCallbackInformation o : l ){
            s+="{";
            s+= o.toString();
            s+="},";
        }
        s +=  "],service= [";
        if ( service != null ) {
            l = Arrays.asList(service);
            for (SigfoxApiCallbackInformation o : l) {
                s += "{";
                s += o.toString();
                s += "},";
            }
        }
        s +=  "],error= [";
        if ( error != null ) {
            l = Arrays.asList(error);
            for (SigfoxApiCallbackInformation o : l) {
                s += "{";
                s += o.toString();
                s += "},";
            }
        }
        s +=  "]}";

        return s;
    }
}