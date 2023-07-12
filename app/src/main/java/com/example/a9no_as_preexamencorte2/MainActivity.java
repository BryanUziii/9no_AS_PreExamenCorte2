package com.example.a9no_as_preexamencorte2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbManager db;

    private EditText txtCorreo;
    private EditText txtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbManager(getApplicationContext());
        db.onConfigure(db.getWritableDatabase());
        db.onOpen(db.getWritableDatabase());

        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);

        Button btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtCorreo.getText().toString();
                String password = txtPassword.getText().toString();


                boolean inicioSesionExitoso = db.validarInicioSesion(correo, password);

                if (inicioSesionExitoso) {
                    // Inicio de sesión exitoso, mostrar mensaje o realizar acciones adicionales
                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, listaActivity.class); // Corrección aquí
                    startActivity(intent);
                    // ... realizar acciones adicionales ...
                } else {
                    // Inicio de sesión fallido, mostrar mensaje o realizar acciones adicionales
                    Toast.makeText(getApplicationContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    // ... realizar acciones adicionales ...
                }
            }
        });

        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class); // Corrección aquí
                startActivity(intent);
            }
        });

    }




    public void registrar(){

    }

    public void ingresar(){
        txtCorreo = findViewById(R.id.txtCorreo); // Reemplaza "txtCorreo" con el ID correcto de tu EditText en el archivo XML
        txtPassword = findViewById(R.id.txtPassword); // Reemplaza "txtPassword" con el ID correcto de tu EditText en el archivo XML



    }

    public void salir(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de querer salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acciones a realizar si se selecciona "Sí"
                finishAffinity(); // Cierra todas las actividades y sale de la aplicación
                System.exit(0); // Cierra la aplicación
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acciones a realizar si se selecciona "No"
                dialog.dismiss(); // Cierra el diálogo sin realizar ninguna acción adicional
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}