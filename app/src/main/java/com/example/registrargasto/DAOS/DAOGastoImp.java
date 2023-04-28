package com.example.registrargasto.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.registrargasto.Complements.OperacionesFechas;
import com.example.registrargasto.DAOS.IDAOS.IDAOGasto;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.GastoDTO;
import com.example.registrargasto.entidades.openHelperDb.DbHelper;
import com.example.registrargasto.querys.DBQueryGastos;
import com.example.registrargasto.view.activity.IActivity.IGastoActivityView;
import com.example.registrargasto.view.fragment.IFragment.IGastoFragmentView;

import java.util.ArrayList;

public class DAOGastoImp extends DbHelper implements IDAOGasto, IGastoActivityView, IGastoFragmentView {
    Context context;
    public DAOGastoImp(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public long registrarNuevoAdeudo(GastoDTO gastoDTO) {
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db =  dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre_gasto",gastoDTO.getNombre());
            values.put("fecha_registro",gastoDTO.getFechaRegistro());
            values.put("lugar",gastoDTO.getLugar());
            values.put("precio",gastoDTO.getPrecio());
            values.put("cantidad",gastoDTO.getCantidad());
            values.put("total",gastoDTO.getTotal());
            values.put("id_tipo",gastoDTO.getTipoGasto());
            id = db.insert(DBQueryGastos.TABLE_NAME_GASTOS,null,values);
        }catch (Exception ex){
            Toast.makeText(context.getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return id;
    }



    @Override
    public long registrarNuevoGastoAAdeudo(AdeudoDTO gastoDTO) {
        long id = 0;
        try{
            OperacionesFechas operacionesFechas=new OperacionesFechas();
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db =  dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre_gasto",gastoDTO.getNombreadeudo());
            values.put("fecha_registro", operacionesFechas.fechaActual());
            values.put("lugar",gastoDTO.getLugar());
            values.put("precio",gastoDTO.getPrecio());
            values.put("cantidad",gastoDTO.getCantidad());
            values.put("total",gastoDTO.getTotal());
            values.put("id_tipo",gastoDTO.getIdTipoGasto());
            id = db.insert(DBQueryGastos.TABLE_NAME_GASTOS,null,values);
        }catch (Exception ex){
            Toast.makeText(context.getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return id;
    }



    @Override
    public ArrayList<GastoDTO>  consultarGastos(){
        ArrayList<GastoDTO> listaGasto= new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        GastoDTO gastoDTO;
        Cursor cursorGasto;
        cursorGasto = db.rawQuery(DBQueryGastos.CONSULTAR_GASTOS, null);
        while(cursorGasto.moveToNext()){
            gastoDTO= new GastoDTO(
                    cursorGasto.getString(0),
                    cursorGasto.getString(1),
                    cursorGasto.getInt(2),
                    cursorGasto.getDouble(3)
            );
            listaGasto.add(gastoDTO);
        }
        return listaGasto;
    }


}
