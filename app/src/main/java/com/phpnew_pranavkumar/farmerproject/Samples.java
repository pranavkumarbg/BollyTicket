/*
 * Copyright (C) 2015 Pranavkumar Gorawar
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
package com.phpnew_pranavkumar.farmerproject;

import java.util.Locale;

class Samples {

    public static class Sample {

        public final String name;
        public final String contentId;
        public final String uri;
        public final int type;

        public Sample(String name, String uri, int type) {
            this(name, name.toLowerCase(Locale.US).replaceAll("\\s", ""), uri, type);
        }

        public Sample(String name, String contentId, String uri, int type) {
            this.name = name;
            this.contentId = contentId;
            this.uri = uri;
            this.type = type;
        }

    }

    private Samples() {
    }

}
