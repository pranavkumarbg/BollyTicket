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
package com.phpnew_pranavkumar.farmerproject.json;

import com.phpnew_pranavkumar.farmerproject.bean.Product;
import com.phpnew_pranavkumar.farmerproject.utils.TagName;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonReader {

    public static List<Product> getHome(JSONObject jsonObject)
            throws JSONException {
        List<Product> products = new ArrayList<Product>();

        JSONArray jsonArray = jsonObject.getJSONArray(TagName.TAG_PRODUCTS);
        Product product;
        for (int i = 0; i < jsonArray.length(); i++) {
            product = new Product();
            JSONObject productObj = jsonArray.getJSONObject(i);
            product.setId(productObj.getInt(TagName.KEY_ID));
            product.setName(productObj.getString(TagName.KEY_NAME));
            product.setImageUrl(productObj.getString(TagName.KEY_IMAGE_URL));

            products.add(product);
        }
        return products;
    }
}
