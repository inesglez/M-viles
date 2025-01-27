package com.example.cineparte2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos y versión
    private static final String DATABASE_NAME = "Cine.db";
    private static final int DATABASE_VERSION = 2;

    // Tablas
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_PELICULAS = "peliculas";

    // Crear tabla de usuarios
    private static final String CREATE_TABLE_USUARIOS =
            "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "clave TEXT, " +
                    "rol TEXT)";

    // Crear tabla de películas
    private static final String CREATE_TABLE_PELICULAS =
            "CREATE TABLE " + TABLE_PELICULAS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT, " +
                    "sinopsis TEXT, " +
                    "genero TEXT, " +
                    "duracion INTEGER, " +
                    "puntuacion REAL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear las tablas
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_PELICULAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas existentes si hay cambios en la estructura
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PELICULAS);
        onCreate(db);
    }

    // Método para insertar un usuario en la tabla "usuarios"
    public boolean insertarUsuario(String nombre, String clave, String rol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("clave", clave);
        values.put("rol", rol);

        long result = db.insert(TABLE_USUARIOS, null, values);
        db.close();
        return result != -1; // Retorna true si se insertó correctamente
    }

    // Método para verificar las credenciales del usuario
    public Cursor verificarCredenciales(String nombre, String clave) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USUARIOS +
                " WHERE nombre=? AND clave=?", new String[]{nombre, clave});
    }
    public boolean insertarPelicula(String titulo, String sinopsis, String genero, int duracion, float puntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("sinopsis", sinopsis);
        values.put("genero", genero);
        values.put("duracion", duracion);
        values.put("puntuacion", puntuacion);

        long result = db.insert(TABLE_PELICULAS, null, values);
        db.close();
        return result != -1;  // Retorna true si la película se insertó correctamente
    }

}
