package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Oil extends AppCompatActivity {
    private Spinner spinner;
    private Button btnFind;
    private TextView txtRes;

    private RequestQueue queue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil);
        spinner = findViewById(R.id.spinner);
        btnFind = findViewById(R.id.btnFind);
        txtRes = findViewById(R.id.txtRes);

        queue = Volley.newRequestQueue(this);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_OnClick(view);
            }
        });
    }

    public void btn_OnClick(View view) {

        String fullUrl = "https://www.alphavantage.co/query?function=BRENT&interval=monthly&apikey=demo";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, fullUrl,
                null, new Response.Listener<JSONObject>()  {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray data = null;
                try {
                    data = response.getJSONArray("data");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String selectedDate = spinner.getSelectedItem().toString();

                // Iterate through the array to find the corresponding value for the selected date
                for (int i = 0; i < data.length(); i++) {
                    JSONObject dataObject = null;
                    try {
                        dataObject = data.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    String date = null;
                    try {
                        date = dataObject.getString("date");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (date.equals(selectedDate)) {
                        double value = 0;
                        try {
                            value = dataObject.getDouble("value");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        txtRes.setText(String.valueOf(value));
                        break;  // Break the loop once the value is found
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);
}}