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


package fr.ingeniousthings.sigfox;

/**
 * Creates data fragments from the {@link SigfoxMessage}'s data field
 *
 * @author Daniel Petisme
 */
public class SigfoxData {

    private final String data;

    public SigfoxData(String data) {
        this.data = data;

    }
    public SigfoxDataFragment getBytes(int start, int end) {
        //1 char = 4 bits, 2chars = 1 byte
        return new SigfoxDataFragment(data.substring(start * 2, end * 2 + 2));
    }

    public SigfoxDataFragment getByte(int index) {
        return getBytes(index, index);
    }
}
