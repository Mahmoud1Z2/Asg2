package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;



public class Exchange extends AppCompatActivity {

    private Spinner spnF,spnTo;
    private Button btnSelect;
    private TextView txtResult;

    private RequestQueue queue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        queue = Volley.newRequestQueue(this);
        spnF = findViewById(R.id.spnF);
        spnTo = findViewById(R.id.spnTo);
        btnSelect = findViewById(R.id.btnSelect);
        txtResult = findViewById(R.id.txtResult);


        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_OnClick(view);
            }
        });
    }
    public void btn_OnClick(View view) {

        String fullUrl = "https://v6.exchangerate-api.com/v6/65d0baf66b8c4ad6e718905b/latest/USD";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, fullUrl,
                null, new Response.Listener<JSONObject>()  {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //get conversion_rates data
                    JSONObject conversionRates = response.getJSONObject("conversion_rates");
                    //get the from currency
                    String s = spnF.getSelectedItem().toString();
                    //get the to currency
                    String s2 = spnTo.getSelectedItem().toString();

                    //get the rates
                    double rateFrom = conversionRates.optDouble(s, 0.0);
                    double rateTo = conversionRates.optDouble(s2, 0.0);


                    double amount = 100.0;
                    double convertedValue = amount * rateTo / rateFrom;

                    txtResult.setText("100 "+ s +" = " +convertedValue+ " "+s2);

                } catch (JSONException exception) {
                    Log.d("volley_error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);

    }
}