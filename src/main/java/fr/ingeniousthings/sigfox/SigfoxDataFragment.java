/*
 * Copyright 2016 Ingeniousthings
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
