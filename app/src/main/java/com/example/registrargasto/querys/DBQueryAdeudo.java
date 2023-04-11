package com.example.registrargasto.querys;

public class DBQueryAdeudo {
    public static final String TABLE_ADEUDOS ="CREATE TABLE \"adeudo\" ( \"id_adeudo\" INTEGER NOT NULL UNIQUE, \"nombre_adeudo\" TEXT,\"lugar\" TEXT,\"precio\" NUMERIC,\"cantidad\" INTEGER, \"monto\" NUMERIC, \"fecha_limite\" TEXT, \"id_tipo\" INTEGER,FOREIGN KEY(\"id_tipo\") REFERENCES \"tipo_gasto\"(\"id_tipo\"), PRIMARY KEY(\"id_adeudo\" AUTOINCREMENT));";
    public static String ELIMINAR_TABLA_ADEUDOS = "DROP TABLE adeudo;";
    public static String TABLE_NAME_ADEUDOS = "adeudo";
    public static String UPDATE_AT = "DATETIME('now');";
    public static String CONSULTAR_ADEUDO="SELECT nombre_adeudo,monto, fecha_limite FROM adeudo;";
    public static String CONSULTAR_ID_ADEUDO="SELECT id_adeudo FROM adeudo;";
    public static String ELIMINAR_ID="delete from adeudo where id_adeudo=?;";
    public static String CONSULTAR_ADEUDO_ID="select id_adeudo from adeudo;";
    public static String CONSULTAR_ADEUDO_datos="select nombre_adeudo, lugar, precio,cantidad,monto,fecha_limite,id_tipo from adeudo;";


}
