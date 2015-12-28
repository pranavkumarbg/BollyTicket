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
package com.phpnew_pranavkumar.farmerproject.adapter;

import android.graphics.Bitmap;
import android.support.v4.util.Pools;
import android.support.v7.graphics.Palette;

import com.squareup.picasso.Transformation;

import java.util.Map;
import java.util.WeakHashMap;

public class PaletteTransformation implements Transformation {
    private static final PaletteTransformation INSTANCE = new PaletteTransformation();
    private static final Map<Bitmap, Palette> CACHE = new WeakHashMap<>();
    private Palette palette;
    private static final Pools.Pool<PaletteTransformation> POOL = new Pools.SynchronizedPool<>(5);
    @Override
    public String key() {
        return "";
    }

    public static Palette getPalette(Bitmap bitmap)
    {
        return CACHE.get(bitmap);
    }


//    public Palette getPalette() {
//        if (palette == null) {
//            throw new IllegalStateException("Transformation was not run.");
//        }
//        return palette;
//    }

//    @Override public Bitmap transform(Bitmap source) {
//        if (palette != null) {
//            throw new IllegalStateException("Instances may only be used once.");
//        }
//        palette = Palette.generate(source);
//        return source;
//    }



    public static PaletteTransformation getInstance() {
        PaletteTransformation instance = POOL.acquire();
        return instance != null ? instance : new PaletteTransformation();
    }


    public static PaletteTransformation instance() {
        return INSTANCE;
    }


    public Palette extractPaletteAndRelease() {
        Palette palette = this.palette;
        if (palette == null) {
            throw new IllegalStateException("Transformation was not run.");
        }
        this.palette = null;
        POOL.release(this);
        return palette;
    }

    private PaletteTransformation() {}

    @Override public Bitmap transform(Bitmap source) {
        Palette palette = Palette.generate(source);
        CACHE.put(source, palette);
        return source;
    }
}

