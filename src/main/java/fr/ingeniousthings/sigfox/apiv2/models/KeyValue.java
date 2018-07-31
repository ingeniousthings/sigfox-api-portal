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
package fr.ingeniousthings.sigfox.apiv2.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KeyValue {
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
