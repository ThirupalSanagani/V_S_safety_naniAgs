
package com.example.voltassafety;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class DashboardActivity extends AppCompatActivity {
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setStatusBarColor(ContextCompat.getColor(DashboardActivity.this,R.color.blue));

        TextView welcomeText = findViewById(R.id.welcome_txt);
        TextView nameText = findViewById(R.id.name_txt);
        TextView vsafetyText = findViewById(R.id.vsafety_txt);
        ImageView logoutButton = findViewById(R.id.logout_btn);
        ImageView hazardImage = findViewById(R.id.hazard_img);
        ImageView nearmissImage = findViewById(R.id.nearmiss_img);

        vsafetyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        welcomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        hazardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle hazard CardView click
            }
        });
        nearmissImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle near miss CardView click
            }
        });
        builder = new AlertDialog.Builder(this);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("ALERT!!")
                        .setMessage("DO YO WANT TO LOGOUT")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                            }
                        })
                        .show();
            }
        });
    }

}
