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

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOfRange;

/**
 * Enable a more precise manipulation of the  {@link SigfoxMessage}'s data field
 *
 * @author Daniel Petisme
 */
public class SigfoxDataFragment {

    private static int NUMBER_OF_BITS_PER_CHAR = 4;

    private final String fragment;

    public SigfoxDataFragment(String fragment) {
        this.fragment = fragment;
    }

    public long toLong() {
        return Long.parseLong(fragment, 16);
    }

    public int toInt() {
        return parseInt(fragment, 16);
    }

    private boolean[] characterToBitArray(String character) {
        //TODO fix this ugly snippet, skip the String encoding
        String binaryString = format("%4s", Integer.toBinaryString(parseInt(character, 16))).replace(' ', '0');
        boolean[] bits = new boolean[NUMBER_OF_BITS_PER_CHAR];
        for (int i = 0; i < NUMBER_OF_BITS_PER_CHAR; i++) {
            bits[i] = binaryString.charAt(i) == '1';
        }
        return bits;
    }

    public boolean[] toBitArray() {
        boolean[] bits = new boolean[fragment.length() * NUMBER_OF_BITS_PER_CHAR];
        for (int i = 0; i < fragment.length(); i++) {
            boolean[] charBits = characterToBitArray(fragment.substring(i, i + 1));
            arraycopy(charBits, 0, bits, i * NUMBER_OF_BITS_PER_CHAR, NUMBER_OF_BITS_PER_CHAR);
        }
        return bits;
    }

    public boolean[] rangeOfBits(int src, int end) {
        return copyOfRange(toBitArray(), src, end);
    }

    public boolean[] firstBits(int nbBitsToKeep) {
        return rangeOfBits(0, nbBitsToKeep);
    }

    public boolean[] lastBits(int nbBitsToKeep) {
        int end = fragment.length() * NUMBER_OF_BITS_PER_CHAR;
        boolean[] bits = rangeOfBits(end - nbBitsToKeep, end);
        return bits;
    }
}
