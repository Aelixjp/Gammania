package com.example.gammania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmarDatos extends AppCompatActivity {

    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);
        data = getData();
        setData();
    }

    private void setData(){

        TextView nombreComp = findViewById(R.id.nombreComp);
        TextView correoComp = findViewById(R.id.correoComp);
        TextView passwordComp = findViewById(R.id.passwordComp);

        nombreComp.setText("Nombre: " + data[0]);
        correoComp.setText("Correo: " + data[1]);
        passwordComp.setText("Contraseña: " + data[2]);

    }

    private String[] getData(){

        Bundle extras = getIntent().getExtras();

        String nombre = extras != null ? extras.getString("nombre") : "";
        String correo = extras != null ? extras.getString("correo") : "";
        String password = extras != null ? extras.getString("password") : "";
        String passwordConfirm = extras != null ? extras.getString("passwordConfirm") : "";

        String[] datos = {nombre, correo, password, passwordConfirm};
        return datos;
    }

    public void onClick(View view){

        Intent intent;
        String nombre = data[0];
        String correo = data[1];
        String password = data[2];
        String passwordConfirm = data[3];

        switch (view.getId()){
            case R.id.volverConfirmacion:
                intent = new Intent(ConfirmarDatos.this, Register.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("correo", correo);
                intent.putExtra("password", password);
                intent.putExtra("passwordConfirm", passwordConfirm);
                startActivity(intent);
                break;

            case R.id.confirmarYRegistrar:
                intent = new Intent(ConfirmarDatos.this, Gammania.class);
                startActivity(intent);
                break;
        }

    }

}
