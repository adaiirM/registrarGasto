package com.example.registrargasto.querys;

public class DBQueryGastos {
    public static final String TABLE_GASTO="CREATE TABLE \"gasto\" (\n" +
            "\t\"id_gasto\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"nombre_gasto\"\tTEXT NOT NULL,\n" +
            "\t\"fecha_registro\"\tTEXT NOT NULL,\n" +
            "\t\"lugar\"\tTEXT NOT NULL,\n" +
            "\t\"precio\"\tNUMERIC,\n" +
            "\t\"cantidad\"\tINTEGER,\n" +
            "\t\"total\"\tNUMERIC,\n" +
            "\t\"id_tipo\"\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(\"id_tipo\") REFERENCES \"tipo_gasto\"(\"id_tipo\"),\n" +
            "\tPRIMARY KEY(\"id_gasto\" AUTOINCREMENT)\n" +
            ");";
    public static String ELIMINAR_TABLA_GASTOS = "DROP TABLE gasto;";
    public static String TABLE_NAME_GASTOS = "gasto";
    public static String UPDATE_AT = "DATETIME('now');";
    public static String CONSULTAR_GASTOS="select nombre_gasto,fecha_registro,cantidad, total from gasto;";

}
