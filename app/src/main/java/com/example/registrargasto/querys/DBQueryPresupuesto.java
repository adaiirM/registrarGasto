package com.example.registrargasto.querys;

public class DBQueryPresupuesto {
    public static final String TABLE_PRESUPUESTO ="CREATE TABLE \"presupuesto\" ( \"id_presupuesto\" INTEGER NOT NULL UNIQUE, \"cantiadad\" NUMERIC, \"fecha_ini\" TEXT, \"fecha_fin\" TEXT, PRIMARY KEY(\"id_presupuesto\" AUTOINCREMENT) );";
    public static String ELIMINAR_TABLA_PRESUPUESTO = "DROP TABLE presupuesto;";
    public static String TABLE_NAME_ADEUDOS = "presupuesto";
    public static String UPDATE_AT = "DATETIME('now');";
    public static String CONSULTAR_MONTOPRES="select cantiadad from presupuesto where id_presupuesto=1;";
    public static String CONSULTAR_MONTOPRES_ID="select cantiadad from presupuesto where id_presupuesto=1;";
    public static  String ESTABLECER_PRESUPUESTO=" insert into presupuesto values(1,0.0,null,null);";
    public static String CONSULTAR_DAT_MONTOPRES="select cantiadad,fecha_ini,fecha_fin from presupuesto where id_presupuesto=1;";
    public static String ACTUALIZA_PRESUPUESTO="update presupuesto set cantiadad=? where id_presupuesto=1;";
    public static String UPDATE_PRESUPUESTO="UPDATE presupuesto set cantiadad=1000,fecha_ini=?,fecha_fin=? WHERE id_presupuesto=1;";

}
