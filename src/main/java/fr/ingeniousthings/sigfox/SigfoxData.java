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
