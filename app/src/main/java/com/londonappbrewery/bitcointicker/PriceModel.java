package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

public class PriceModel {
    private static double mPrice;

    public PriceModel fromJson (JSONObject jsonObject)
    {

        PriceModel currentPrice = new PriceModel();
        try {
            mPrice = jsonObject.getDouble("ask");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return currentPrice;
    }

    public static double getPrice() {
        return mPrice;
    }

}
