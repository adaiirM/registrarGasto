package com.example.registrargasto.querys;

public class DBQueryTipoGasto {
    public static final String TABLE_TIPO_GASTO ="CREATE TABLE \"tipo_gasto\" ( \"id_tipo\" INTEGER NOT NULL UNIQUE, \"nombre_gasto\" TEXT NOT NULL, PRIMARY KEY(\"id_tipo\" AUTOINCREMENT) );";
    public static String ELIMINAR_TABLA_TIPO_GASTO= "DROP TABLE tipo_gasto;";
    public static String TABLE_NAME_ADEUDOS = "tipo_gasto";
    public static String UPDATE_AT = "DATETIME('now');";
    public static String INICIALVALOR1="insert into tipo_gasto VALUES (1,\"Producto\");";
    public static String INICIALVALOR2="insert into tipo_gasto VALUES (2,\"Servicio\");";
public  static String CONSULTARTIPOGASTO="SELECT id_tipo, nombre_gasto FROM tipo_gasto ORDER BY id_tipo ASC;";
}
