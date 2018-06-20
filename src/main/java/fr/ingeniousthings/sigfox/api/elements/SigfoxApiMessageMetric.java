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

import java.io.IOException;
import java.lang.Override;
import java.lang.String;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to DeviceInit / Message metric.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "lastDay": 47,
 *      "lastWeek": 276,
 *      "lastMonth": 784
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiMessageMetric {

    protected int lastDay;
    protected int lastWeek;
    protected int lastMonth;

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public int getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(int lastWeek) {
        this.lastWeek = lastWeek;
    }

    public int getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(int lastMonth) {
        this.lastMonth = lastMonth;
    }

    @Override
    public String toString() {
        return "SigfoxApiMessageMetric{" +
                "lastDay=" + lastDay +
                ", lastWeek=" + lastWeek +
                ", lastMonth=" + lastMonth +
                '}';
    }
}