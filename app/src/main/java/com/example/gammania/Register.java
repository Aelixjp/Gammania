package com.example.gammania;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setSavedData();
    }

    private void setSavedData(){


        Bundle extras = getIntent().getExtras();

        EditText campoNombre = (EditText)findViewById(R.id.nameField);
        EditText campoEmail = (EditText)findViewById(R.id.email);
        EditText campoPassword = (EditText)findViewById(R.id.password);
        EditText campoPasswordConfirm = (EditText)findViewById(R.id.confirmPassword);
        CheckBox campoTerms = findViewById(R.id.conditions);

        String nombre = "";
        String email = "";
        String password = "";
        String passwordConfirm = "";
        Boolean terms = false;

        if(extras != null){
            nombre = extras.getString("nombre") != null ? extras.getString("nombre").trim() : campoNombre.getText().toString().trim();
            email = extras.getString("correo") != null ? extras.getString("correo").trim() : campoEmail.getText().toString().trim();
            password = extras.getString("password") != null ? extras.getString("password").trim() : campoPassword.getText().toString().trim();
            passwordConfirm = extras.getString("passwordConfirm") != null ?
                    extras.getString("passwordConfirm").trim() :
                    campoPasswordConfirm.getText().toString().trim();
            terms = extras.getBoolean("terms") ? Boolean.valueOf(extras.getBoolean("terms")) : Boolean.valueOf(campoTerms.isChecked());
        }else{

            nombre = campoNombre.getText().toString().trim();
            email = campoEmail.getText().toString().trim();
            password = campoPassword.getText().toString().trim();
            passwordConfirm = campoPassword.getText().toString().trim();

        }

        campoNombre.setText(nombre);
        campoEmail.setText(email);
        campoPassword.setText(password);
        campoPasswordConfirm.setText(passwordConfirm);
        campoTerms.setChecked(terms);


    }

    public String[] getInputsData(){

        EditText nombre = (EditText)findViewById(R.id.nameField);
        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
        EditText passwordConfim = (EditText)findViewById(R.id.confirmPassword);
        CheckBox terms = findViewById(R.id.conditions);

        String [] datos = {
                nombre.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                passwordConfim.getText().toString(),
                Boolean.valueOf(terms.isChecked()).toString()
        };

        return datos;
    }

    private EditText[] getFields(){

        EditText nombreCampo = (EditText)findViewById(R.id.nameField);
        EditText emailCampo = (EditText)findViewById(R.id.email);
        EditText passwordCampo = (EditText)findViewById(R.id.password);
        EditText passwordConfimCampo = (EditText)findViewById(R.id.confirmPassword);

        EditText[] fields = {nombreCampo, emailCampo, passwordCampo, passwordConfimCampo};
        return fields;

    }

    private CheckBox getCheckBox(){
        return findViewById(R.id.conditions);
    }

    private String[] getIntentData(){

        Bundle extras = getIntent().getExtras();
        EditText[] campos = getFields();
        EditText nombreCampo = campos[0];
        EditText emailCampo = campos[1];
        EditText passwordCampo = campos[2];
        EditText passwordConfirmCampo = campos[3];
        CheckBox termsCampo = getCheckBox();

        String nombre = "";
        String email = "";
        String password = "";
        String passwordConfirm = "";
        Boolean acceptedTerms = false;

        if(extras != null){
            nombre = extras.getString("nombre") != null ? extras.getString("nombre").trim() : nombreCampo.getText().toString().trim();
            email = extras.getString("correo") != null ? extras.getString("correo").trim() : emailCampo.getText().toString().trim();
            password = extras.getString("password") != null ?
                    extras.getString("password").trim() :
                    passwordCampo.getText().toString().trim();
            passwordConfirm = extras.getString("passwordConfirm") != null ?
                    extras.getString("passwordConfirm").trim() :
                    passwordConfirmCampo.getText().toString().trim();
            acceptedTerms = extras.getBoolean("terms") ? Boolean.valueOf(extras.getBoolean("terms")) : Boolean.valueOf(termsCampo.isChecked());
        }else{

            nombre = nombreCampo.getText().toString().trim();
            email = emailCampo.getText().toString().trim();
            password = passwordCampo.getText().toString().trim();
            passwordConfirm = passwordConfirmCampo.getText().toString().trim();

        }

        String[] intentData = {nombre, email, password, passwordConfirm, acceptedTerms.toString()};
        return intentData;

    }

    public void onClick(View view){

        Intent intent;
        String[] intentData = getIntentData();
        String nombre = intentData[0];
        String email = intentData[1];
        String password = intentData[2];
        String passwordConfirm = intentData[3];
        Boolean terms = Boolean.valueOf(intentData[4]);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Gammania");

        switch (view.getId()){

            case R.id.botonVolver:

                intent = new Intent(Register.this, MainActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("correo", email);
                intent.putExtra("password", password);
                intent.putExtra("passwordConfirm", passwordConfirm);
                intent.putExtra("terms", terms);
                startActivity(intent);
                break;

            case R.id.botonSiguiente:

                String[] datos = getInputsData();
                nombre = datos[0].trim();
                email = datos[1].trim();
                password = datos[2].trim();
                passwordConfirm = datos[3].trim();
                terms = Boolean.valueOf(datos[4]);

                if(!(nombre.equals("") || email.equals("") || password.equals("") || passwordConfirm.equals(""))){

                    if(password.equals(passwordConfirm)){

                        if(terms){

                            intent = new Intent(Register.this, ConfirmarDatos.class);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("correo", email);
                            intent.putExtra("password", password);
                            intent.putExtra("passwordConfirm", passwordConfirm);
                            intent.putExtra("terms", terms);
                            startActivity(intent);

                        }else{

                            dialogBuilder.setMessage("¡Debes aceptar los terminos y condiciones primero!");
                            dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    dialogInterface.cancel();

                                }
                            });

                            dialogBuilder.show();

                        }

                    }else{

                        dialogBuilder.setMessage("¡Las contraseñas no coinciden!");
                        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel();

                            }
                        });

                        dialogBuilder.show();

                    }

                }else{

                    dialogBuilder.setMessage("¡Todos los campos son necesarios!");
                    dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.cancel();

                        }
                    });

                    dialogBuilder.show();

                }

                break;
        }

    }

}
