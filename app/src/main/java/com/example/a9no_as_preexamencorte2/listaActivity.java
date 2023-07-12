package com.example.a9no_as_preexamencorte2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class listaActivity extends AppCompatActivity {

    private DbManager db;

    private usuarioAdapter usuarioAdapter;

    private List<Usuario> listaUsuarios;

    private RecyclerView recyclerViewEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        db = new DbManager(this);
        recyclerViewEmpleados = findViewById(R.id.recyclerViewEmpleados);

        listaUsuarios = new ArrayList<>();
        usuarioAdapter = new usuarioAdapter(this, listaUsuarios);
        recyclerViewEmpleados.setAdapter(usuarioAdapter);
        recyclerViewEmpleados.setLayoutManager(new LinearLayoutManager(this));

        mostrarUsuarios();
    }

    private void mostrarUsuarios() {
        listaUsuarios.clear();
        listaUsuarios.addAll(db.obtenerUsuarios());
        usuarioAdapter.notifyDataSetChanged();
    }

    public void regresar(View view) {
        // Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        finish();
    }



}
