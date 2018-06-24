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

/**
 * Summary
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMarginsListInput {

    public class Location {
        @ApiModelProperty(
                notes = "A latitude in degrees. Must be between -90° and 90°.",
                required = true
        )
        public double lat;

        @ApiModelProperty(
                notes = "A longitude in degrees. Must be between -180° and 180°.",
                required = true
        )
        public double lng;
    }


    @ApiModelProperty(
            notes = "An array containing the response for each point.",
            required = true
    )
    protected Location locations;


    @ApiModelProperty(
            notes = "(optional) the distance around the lat/lng locations in meters, default is 300.",
            required = true
    )
    protected int radius;

    // ====================================================
    // Getters & Setters
    // ====================================================


    public Location getLocations() {
        return locations;
    }

    public void setLocations(Location locations) {
        this.locations = locations;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}