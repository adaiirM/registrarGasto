package com.example.registrargasto.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.registrargasto.DAOS.IDAOS.IDAOPresupuesto;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.openHelperDb.DbHelper;
import com.example.registrargasto.querys.DBQueryPresupuesto;
import com.example.registrargasto.view.activity.IActivity.IPresupuestoactivityGasto;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;

public class DAOPresupuestoIm extends DbHelper implements IDAOPresupuesto, IPresupuestoFragmentView, IPresupuestoactivityGasto {

    Context context;
    public DAOPresupuestoIm(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public long registarPresupuesto(PresupuestoDTO presupuestoDTO) {
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db =  dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("cantiadad",presupuestoDTO.getCantidad());
            values.put("fecha_ini",presupuestoDTO.getFechaIni());
            values.put("fecha_fin",presupuestoDTO.getGetFechaFin());
            id=db.update(DBQueryPresupuesto.TABLE_NAME_ADEUDOS, values, "id_presupuesto=1",null);
        }catch (Exception ex){
            Toast.makeText(context.getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return id;
    }

    @Override
    public void restarPresupuesto(double gasto) {
        
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db =  dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] id=new String[]{"1"};
            PresupuestoDTO presupuestoDTO=consultarPresupuesto();
            double total=presupuestoDTO.getCantidad()-gasto;
            values.put("cantiadad",total);
            db.update(DBQueryPresupuesto.TABLE_NAME_ADEUDOS, values, "id_presupuesto=1",null);
        }catch (Exception ex){
            Toast.makeText(context.getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public PresupuestoDTO consultarPresupuesto(){
        PresupuestoDTO presupuestoDTO = null;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursorPresupuesto;
        cursorPresupuesto = db.rawQuery(DBQueryPresupuesto.CONSULTAR_MONTOPRES, null);
        while (cursorPresupuesto.moveToNext()){
            presupuestoDTO=  new PresupuestoDTO(
                    cursorPresupuesto.getDouble(0)
            );
        }
       
        return  presupuestoDTO;
    }


    //*********************

    @Override
    public PresupuestoDTO consultarPresupuestoid(){
        PresupuestoDTO presupuestoDTOS = null;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursorPresupuesto;
        cursorPresupuesto = db.rawQuery(DBQueryPresupuesto.CONSULTAR_MONTOPRES_ID, null);
        while (cursorPresupuesto.moveToNext()){
            presupuestoDTOS= new PresupuestoDTO(cursorPresupuesto.getDouble(0));
        }
        
        return presupuestoDTOS;
    }
    //******************

    @Override
    public PresupuestoDTO consultarPresupuestoDatos(){
        PresupuestoDTO presupuestoDTO = null;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursorPresupuesto;
        cursorPresupuesto = db.rawQuery(DBQueryPresupuesto.CONSULTAR_DAT_MONTOPRES, null);
        while (cursorPresupuesto.moveToNext()){
            presupuestoDTO=  new PresupuestoDTO(
                    cursorPresupuesto.getDouble(0),
                    cursorPresupuesto.getString(1),
                    cursorPresupuesto.getString(2)
            );
        }


        return presupuestoDTO;
    }
}
