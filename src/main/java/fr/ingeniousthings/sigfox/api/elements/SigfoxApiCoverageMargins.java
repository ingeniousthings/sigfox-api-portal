/*
 *
 *  Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
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

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage margins.
 * ----------------------------------------------------------------------------------
 * Response Format :
 * {
 *     "locationCovered": true,
 *     "margins":[
 *         42.12,
 *         27.49,
 *         5.87
 *     ]
 * }
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMargins {

    @ApiModelProperty(
            notes = "The latitude in degrees. [only filled by Multiple point API]",
            required = false
    )
    protected double lat;

    @ApiModelProperty(
            notes = "The longitude in degrees. [only filled by Multiple point API]",
            required = false
    )
    protected double lng;


    @ApiModelProperty(
            notes = "True, if the requested location is considered covered.",
            required = false            // non sense but according to doc it could be
    )
    protected boolean locationCovered;

    @ApiModelProperty(
            notes = "The margins values (dB) for redundancy level 1, 2 and 3",
            required = true
    )
    protected double [] margins;


    // ====================================================
    // Getters & Setters
    // ====================================================

    public double[] getMargins() {
        return margins;
    }

    public void setMargins(double[] margins) {
        this.margins = margins;
    }

    public boolean isLocationCovered() {
        return locationCovered;
    }

    public void setLocationCovered(boolean locationCovered) {
        this.locationCovered = locationCovered;
    }

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

    @Override
    public String toString() {
        return "SigfoxApiCoverageMargins{" +
                "margins=" + Arrays.toString(margins) +
                '}';
    }
}