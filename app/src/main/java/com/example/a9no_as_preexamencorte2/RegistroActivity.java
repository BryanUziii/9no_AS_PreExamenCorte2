package com.example.a9no_as_preexamencorte2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private DbManager db;
    private EditText txtUserName;
    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        db = new DbManager(getApplicationContext());
        db.onConfigure(db.getWritableDatabase());
        db.onOpen(db.getWritableDatabase());

        txtUserName = findViewById(R.id.txtUserName);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);

        Button btnRegistrarUsuario = findViewById(R.id.btnRegistrarse);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUserName.getText().toString().trim();
                String correo = txtCorreo.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String password2 = txtPassword2.getText().toString().trim();

                if (validarCampos()){
                    boolean registroExitoso = db.validarNuevoRegistro(correo);

                    if (registroExitoso) {
                        // Inicio de sesión fallido, mostrar mensaje o realizar acciones adicionales
                        Toast.makeText(getApplicationContext(), "Ese correo ya esta registrado.", Toast.LENGTH_SHORT).show();
                        // ... realizar acciones adicionales ...

                    } else {
                        // Inicio de sesión exitoso, mostrar mensaje o realizar acciones adicionales


                        db.aggUsuario(userName, correo, password);

                        Toast.makeText(getApplicationContext(), "Usuario registrado con exito!.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                } else {

                }
            }
        });



    }

    public boolean validarCampos(){
        boolean validacionExitoso = false;

        txtUserName = findViewById(R.id.txtUserName);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);

        String userName = txtUserName.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String password2 = txtPassword2.getText().toString().trim();

        if (userName.isEmpty() || correo.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            // Validación: al menos uno de los campos está vacío
            Toast.makeText(getApplicationContext(), "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Todos los campos están llenos
            if (password.equals(password2)) {
                // Validación: las contraseñas coinciden
                    validacionExitoso = true;
                // Realiza las acciones necesarias cuando las contraseñas coinciden
            } else {
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
        return validacionExitoso;
    }

    public void regresar(View view) {
        // Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}