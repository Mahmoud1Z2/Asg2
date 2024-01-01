package com.example.asg2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    private EditText txtF,txtL,txtPass,txtEmail,txtUser;
    private Button btnSignUp;

    public static ArrayList<User> aUsers = new ArrayList<>();
    public static ArrayList<User> aUsers2 = new ArrayList<>();
    public  static SharedPreferences prefs;

    public static SharedPreferences.Editor editor;

    public  static SharedPreferences prefs2;

    public static SharedPreferences.Editor editor2;
    public static final String DATA = "DATA";
    public static final String DATA2 = "DATA";
    @SuppressLint("MissingInflatedId")


    protected void onStop() {
        //if the user press on back button the data will store
        super.onStop();
        String firstName = String.valueOf(txtF.getText());
        String lastName = String.valueOf(txtL.getText());
        String password = String.valueOf(txtPass.getText());
        String Email = String.valueOf(txtUser.getText());
        String Username = String.valueOf(txtUser.getText());
        User user = new User(firstName,lastName,password,Email,Username);
        aUsers2.add(user);
        //store localy
        setupSP2();
        store2(aUsers2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtF = findViewById(R.id.txtF);
        txtL = findViewById(R.id.txtL);
        txtPass = findViewById(R.id.txtPass);
        txtEmail = findViewById(R.id.txtEmail);
        txtUser = findViewById(R.id.txtUser);
        btnSignUp = findViewById(R.id.btnSignUp);
        setupSP2();

        for (User user : load()) {
                txtF.setText(user.getFirstName());
                txtL.setText(user.getLastName());
                txtPass.setText(user.getPassword());
                txtEmail.setText(user.getEmail());
                txtUser.setText(user.getUserName());
        }
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickedSign(view);
            }
        });
    }

    private void onClickedSign(View view){
        String firstName = String.valueOf(txtF.getText());
        String lastName = String.valueOf(txtL.getText());
        String password = String.valueOf(txtPass.getText());
        String Email = String.valueOf(txtEmail.getText());
        String Username = String.valueOf(txtUser.getText());
        User user = new User(firstName,lastName,password,Email,Username);
        aUsers.add(user);
        setupSP();
        store(aUsers);

        Intent intent;
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    };

    public static void store(ArrayList<User> users){
        Gson gson = new Gson();
        String user = gson.toJson(aUsers);
        editor.putString(DATA,user);
        editor.commit();
    }
    private void setupSP(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    public static void store2(ArrayList<User> users){
        Gson gson = new Gson();
        String user = gson.toJson(aUsers2);
        editor2.putString(DATA2,user);
        editor2.commit();
    }
    private void setupSP2(){
        prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        editor2 = prefs2.edit();
    }

    private static ArrayList<User> load(){
        Gson gson = new Gson();
        String user = prefs2.getString(DATA2 ,"");
        if(!user.isEmpty()){
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(user,type);
        }
        return new ArrayList<>();
    };
}