package com.example.voltassafety;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 2;
    SharedPreferences sharedPreferences;
    EditText editText_empid,editText_password;
    String name, password;
    ImageView eyeImageView;
    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";

    SharedPreferences sharedpreferences;
    private boolean isPasswordVisible;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.blue));
        checkPermissions();
       editText_empid = findViewById(R.id.employeecodetxt);
        editText_password = findViewById(R.id.passwordtxt);
        ImageView button_save = findViewById(R.id.Squre);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);



        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, editText_empid.getText().toString());
                editor.putString(KEY_EMAIL, editText_password.getText().toString());
                editor.apply();
                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(i);
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        String[] names = {"INDIA", "JAPAN", "AFRICA", "AMERICA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedName = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        sharedpreferences = getSharedPreferences("saveDAta",Context.MODE_PRIVATE);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_empid.getText().toString().equals("Admin")&&editText_password.getText().toString().equals(("12345"))){
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(i);
                } else if (editText_empid.getText().toString().equals("Thiru")&&editText_password.getText().toString().equals(("123456"))){
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(i);

                } else{
                    Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();
                }
                saveData();
            }
        });
        retriveData();
    }
    public void saveData(){
        name = editText_empid.getText().toString();
        password = editText_password.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("keyname",name);
        editor.putString("keypassword",password);
        editor.commit();
    }
    public void retriveData(){
        name = sharedpreferences.getString("keyname",null);
        password = sharedpreferences.getString("keypassword",null);
        editText_empid.setText(name);
        editText_password.setText(password);
    }
    private boolean checkPermissions() {
        try {

            int readexternal = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readinternal = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            //int callPermition = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
            int location = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            //int phonereadpermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);


            List<String> permissionString = new ArrayList<String>();

            if (readexternal != PackageManager.PERMISSION_GRANTED) {
                permissionString.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (readinternal != PackageManager.PERMISSION_GRANTED) {
                permissionString.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            //if (callPermition != PackageManager.PERMISSION_GRANTED) {
               // permissionString.add(android.Manifest.permission.CALL_PHONE);
           // }

           // if (phonereadpermission != PackageManager.PERMISSION_GRANTED) {
               // permissionString.add(android.Manifest.permission.READ_PHONE_STATE);
           // }
            if (location != PackageManager.PERMISSION_GRANTED) {
                permissionString.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            }


            if (!permissionString.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissionString.toArray(new String[permissionString.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            Log.d("Exception in Login", "" + e.getMessage());
            return false;
        }
        return true;
    }
    private boolean validateUserName() {
        try {

            if (editText_empid.getText().toString().length() == 0) {

                String errorString = "Please Enter User Name";

                editText_empid.setError(errorString);

                editText_empid.requestFocus();
                return false;
            }
            if (editText_password.getText().toString().length() == 0) {

                String errorString = "Please Enter Password";

                editText_password.setError(errorString);

               editText_password.requestFocus();
                return false;
            }
            if(SHARED_PREF_NAME.getClass().toString().equalsIgnoreCase("--Select Domain--")){

                showAlert("Please select Domain");
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            Log.d("Exception in Login", "" + e.getMessage());
        }
        return true;
    }

    private void showAlert(String pleaseSelectDomain) {
    }


}