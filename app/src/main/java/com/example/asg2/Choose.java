package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    private Button btnEx,btnCo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btnEx = findViewById(R.id.btnEx);
        btnCo = findViewById(R.id.btnCo);
        //go to exhange activity
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedEx(view);
            }
        });
        //go to oil price activity
        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedOil(view);
            }
        });
    }

    private void onClickedEx(View view){
        Intent intent;
        intent = new Intent(this,Exchange.class);
        startActivity(intent);
    };
    private void onClickedOil(View view){
        Intent intent;
        intent = new Intent(this,Oil.class);
        startActivity(intent);
    };
}