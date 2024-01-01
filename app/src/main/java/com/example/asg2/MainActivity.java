package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText txtU,txtP;
    private Button btnLogin,btnSign;
    private CheckBox checkBox;

    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";

    private boolean flag = false;
    public  static SharedPreferences prefs3;
    public static SharedPreferences.Editor editor3;

    public  static SharedPreferences prefs5;
    public static SharedPreferences.Editor editor5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtU = findViewById(R.id.txtU);
        txtP = findViewById(R.id.txtP);
        btnLogin = findViewById(R.id.btnLogin);
        btnSign = findViewById(R.id.btnSign);
        checkBox = findViewById(R.id.checkBox);

        //for the remember checkbox
        prefs5= PreferenceManager.getDefaultSharedPreferences(this);
        editor5 = prefs5.edit();
        checkPrefs();

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedSignUp(view);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedLogin(view);
            }
        });

    }

    private void onClickedSignUp(View view){
        Intent intent;
        intent = new Intent(this,SignUp.class);
        startActivity(intent);


    };
    private void onClickedLogin(View view){

        if(checkBox.isChecked()){
            if(!flag) {
                editor5.putString(NAME, String.valueOf(txtU.getText()));
                editor5.putString(PASS, String.valueOf(txtP.getText()));
                editor5.putBoolean(FLAG, true);
                editor5.commit();
            }
            //if i change account i want to remember
        }else if(!checkBox.isChecked() && flag){
            editor5.remove(NAME);
            editor5.remove(PASS);
            editor5.putBoolean(FLAG,false);
            editor5.commit();
        }
        //check if the acount exist
        SignUp.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SignUp.editor = SignUp.prefs.edit();
            for (User user : load()) {
                if(user.getUserName().equals(String.valueOf(txtU.getText())) && user.getPassword().equals(String.valueOf(txtP.getText())) ){
                    Intent intent;
                    intent = new Intent(this,Choose.class);
                    startActivity(intent);
            }
        };
    }

    private static ArrayList<User> load(){
        Gson gson = new Gson();
        String user = SignUp.prefs.getString(SignUp.DATA ,"");
        if(!user.isEmpty()){
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(user,type);

        }
        return new ArrayList<>();
    };

    private void checkPrefs() {
        flag = prefs5.getBoolean(FLAG, false);

        if(flag){
            String name = prefs5.getString(NAME, "");
            String password = prefs5.getString(PASS, "");
            txtU.setText(name);
            txtP.setText(password);
            checkBox.setChecked(true);
        }
    }

}