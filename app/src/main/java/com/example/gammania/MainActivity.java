package com.example.gammania;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private String[] data;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        data = getData();
    }

    public String[] getData(){

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            String nombre = extras.getString("nombre") != null ? extras.getString("nombre").trim() : "";
            String email = extras.getString("correo") != null ? extras.getString("correo").trim() : "";
            String password = extras.getString("password") != null ? extras.getString("password").trim() : "";
            String passwordConfirm = extras.getString("passwordConfirm") != null ? extras.getString("passwordConfirm").trim() : "";

            String[] datos = {nombre, email, password, passwordConfirm};
            return datos;

        }else{

            String[] datos = {"", "", "", ""};
            return datos;

        }

    }

    public void onClick(View view){

        String nombre = data[0];
        String email = data[1];
        String password = data[2];
        String passwordConfirm = data[3];

        Intent intent = new Intent(MainActivity.this, Register.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("correo", email);
        intent.putExtra("password", password);
        intent.putExtra("passwordConfirm", passwordConfirm);
        startActivity(intent);
    }

}
