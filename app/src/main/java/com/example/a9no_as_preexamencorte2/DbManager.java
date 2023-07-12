package com.example.a9no_as_preexamencorte2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "preexamen.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_CORREO = "correo";
    private static final String COLUMN_CONTRASEÑA = "contraseña";

    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crea la tabla de usuarios en la base de datos
        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + TABLE_USUARIOS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_CORREO + " TEXT,"
                + COLUMN_CONTRASEÑA + " TEXT"
                + ")";
        db.execSQL(CREATE_USUARIOS_TABLE);

        // Verifica si el usuario "admin" ya existe en la tabla
        String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_USERNAME + " = 'admin'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            // No se encontró el usuario "admin", se agrega a la tabla
            db.execSQL("INSERT INTO " + TABLE_USUARIOS + " (" + COLUMN_USERNAME + ", " + COLUMN_CORREO + ", " + COLUMN_CONTRASEÑA + ") VALUES ('admin', 'admin@gmail.com', 'admin')");
        }

        // Cierra el cursor
        cursor.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Elimina la tabla de usuarios si existe y la vuelve a crear
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    public boolean validarInicioSesion(String correo, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta para buscar si existe una coincidencia de correo y contraseña en la tabla
        String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_CORREO + " = ? AND " + COLUMN_CONTRASEÑA + " = ?";
        String[] selectionArgs = {correo, password};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        boolean inicioSesionExitoso = false;

        if (cursor.getCount() > 0) {
            // Se encontró una coincidencia de correo y contraseña
            inicioSesionExitoso = true;
        }

        // Cierra el cursor y la base de datos
        cursor.close();
        db.close();

        return inicioSesionExitoso;
    }


    public void aggUsuario(String userName, String correo, String password) {
        // Agrega un nuevo usuario a la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userName);
        values.put(COLUMN_CORREO, correo);
        values.put(COLUMN_CONTRASEÑA, password);
        long resultado = db.insert(TABLE_USUARIOS, null, values);
        if (resultado == -1) {
            // Si el resultado es -1, hubo un error al insertar el usuario
            // Puedes manejar el error aquí
        }
        db.close();
    }

    public void eliminarUsuario(int idUsuario) {
        // Elimina un usuario de la base de datos dado su ID
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USUARIOS, COLUMN_ID + " = ?", new String[]{String.valueOf(idUsuario)});
        db.close();
    }


    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexUserName = cursor.getColumnIndex(COLUMN_USERNAME);
            int columnIndexCorreo = cursor.getColumnIndex(COLUMN_CORREO);
            int columnIndexPassword = cursor.getColumnIndex(COLUMN_CONTRASEÑA);

            do {
                Usuario usuario = new Usuario();
                if (columnIndexId != -1) {
                    usuario.setId(cursor.getInt(columnIndexId));
                }
                if (columnIndexUserName != -1) {
                    usuario.setUserName(cursor.getString(columnIndexUserName));
                }
                if (columnIndexCorreo != -1) {
                    usuario.setCorreo(cursor.getString(columnIndexCorreo));
                }
                if (columnIndexPassword != -1) {
                    usuario.setPassword(cursor.getString(columnIndexPassword));
                }

                listaUsuarios.add(usuario);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return listaUsuarios;
    }


}

