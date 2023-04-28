package com.example.registrargasto.entidades.openHelperDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.registrargasto.querys.DBQueryAdeudo;
import com.example.registrargasto.querys.DBQueryGastos;
import com.example.registrargasto.querys.DBQueryPresupuesto;
import com.example.registrargasto.querys.DBQueryTipoGasto;
import com.example.registrargasto.querys.DBQueryUsuario;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, DBQueryUsuario.DATABASE_NAME, null, DBQueryUsuario.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBQueryGastos.TABLE_GASTO);
        sqLiteDatabase.execSQL(DBQueryUsuario.TABLE_uSUARIO);
        sqLiteDatabase.execSQL(DBQueryTipoGasto.TABLE_TIPO_GASTO);
        sqLiteDatabase.execSQL(DBQueryTipoGasto.INICIALVALOR1);
        sqLiteDatabase.execSQL(DBQueryTipoGasto.INICIALVALOR2);
        sqLiteDatabase.execSQL(DBQueryAdeudo.TABLE_ADEUDOS);
        sqLiteDatabase.execSQL(DBQueryPresupuesto.TABLE_PRESUPUESTO);
        sqLiteDatabase.execSQL(DBQueryPresupuesto.ESTABLECER_PRESUPUESTO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBQueryGastos.ELIMINAR_TABLA_GASTOS);
        sqLiteDatabase.execSQL(DBQueryUsuario.ELIMINAR_TABLA_CLIENTES);
        sqLiteDatabase.execSQL(DBQueryTipoGasto.ELIMINAR_TABLA_TIPO_GASTO);
        sqLiteDatabase.execSQL(DBQueryAdeudo.ELIMINAR_TABLA_ADEUDOS);
        sqLiteDatabase.execSQL(DBQueryPresupuesto.ELIMINAR_TABLA_PRESUPUESTO);
    }
}
