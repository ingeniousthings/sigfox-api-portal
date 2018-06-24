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

import java.util.Arrays;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage margins.
 * ----------------------------------------------------------------------------------
 * Response Format :
 * {
 *     "data": [
 *         {
 *             "lat": 43.5,
 *             "lng": 1.5,
 *             "margins": [
 *                 42,
 *                 35,
 *                 33
 *             ]
 *         },
 *         {
 *             "lat": 43.4,
 *             "lng": 1.5,
 *             "locationCovered": true,
 *             "margins": [
 *                 30,
 *                 27,
 *                 24
 *             ]
 *         }
 *     ]
 * }
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMarginsList {

    @ApiModelProperty(
            notes = "An array containing the response for each point.",
            required = true
    )
    protected SigfoxApiCoverageMargins data;


    // ====================================================
    // Getters & Setters
    // ====================================================


    public SigfoxApiCoverageMargins getData() {
        return data;
    }

    public void setData(SigfoxApiCoverageMargins data) {
        this.data = data;
    }
}