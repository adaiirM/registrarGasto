package com.example.registrargasto.querys;

public class DBQueryUsuario {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "gasperApp.db";
    public static final String TABLE_uSUARIO="CREATE TABLE \"usuario\" ( \"id_usuario\" INTEGER NOT NULL, \"nombre_us\" TEXT, \"apellido_us\" TEXT, \"correo_us\" TEXT, PRIMARY KEY(\"id_usuario\" AUTOINCREMENT) );";

    public static String ELIMINAR_TABLA_CLIENTES = "DROP TABLE usuario;";
    public static String TABLE_CLIENTES_NAME = "usuario";

}
