package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity{

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCUSD";

    // Member Variables:
    TextView mPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Bitcoin", "" + adapterView.getItemAtPosition(i));
                String country = (String) adapterView.getItemAtPosition(i);
                letsDoSomeNetworking(BASE_URL);
                updateUI(country);



            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("Bitcoin", " nothing selected");
            }
        });
    }


    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("BitCoinTracker", "JSON: " + response.toString());
                PriceModel newPrice =new PriceModel();
                newPrice.fromJson(response);
                
                
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("BitCoinTracker", "Request fail! Status code: " + statusCode);
                Log.d("BitCoinTracker", "Fail response: " + response);
                Log.e("ERROR", e.toString());
            }
        });


    }
    private void updateUI(String cou) {

        PriceModel newPrice = null;
        double price = newPrice.getPrice();
        if (cou=="AUD")
        {
            price = price*1.69;
            mPriceTextView.setText((int) price);
            Log.d("bitcoin", "updateUI: AUD UI UPDATING");
        }
        else if (cou=="BRL")
        {
            price=price*5.08;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="CAD")
        {
            price=price*1.45;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="CNY")
        {
            price=price*7.06;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="EUR")
        {
            price=price*0.93;
            mPriceTextView.setText((int) price);
        }
        else if (cou=="GBP")
        {
            price=price*0.85;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="HKD")
        {
            price=price*7.75;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="JPY")
        {
            price=price*111.58;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="PLN")
        {
            price=price*4.28;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="RUB")
        {
            price=price*78.56;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="SEK")
        {
            price=price*10.21;
            mPriceTextView.setText((int) price);

        }
        else if (cou=="ZAR")
        {
            price=price*17.51;
            mPriceTextView.setText((int) price);

        }

    }


}
