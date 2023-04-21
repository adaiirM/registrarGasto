package com.example.registrargasto.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.registrargasto.DAOS.IDAOS.IDAOAdeudo;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.openHelperDb.DbHelper;
import com.example.registrargasto.querys.DBQueryAdeudo;
import com.example.registrargasto.view.fragment.IFragment.IAdeudoFragmentView;

import java.util.ArrayList;

public class DAOAdeudoImp extends DbHelper implements IDAOAdeudo,IAdeudoFragmentView {
    Context context;
    public DAOAdeudoImp(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public long registarNuevoAdeudo(AdeudoDTO adeudoDTO) {
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db =  dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre_adeudo",adeudoDTO.getNombreadeudo());
            values.put("lugar",adeudoDTO.getLugar());
            values.put("precio",adeudoDTO.getPrecio());
            values.put("cantidad",adeudoDTO.getCantidad());
            values.put("monto",adeudoDTO.getTotal());
            values.put("fecha_limite",adeudoDTO.getFechaLimite());
            values.put("id_tipo",adeudoDTO.getIdTipoGasto());

            id = db.insert(DBQueryAdeudo.TABLE_NAME_ADEUDOS,null,values);
        }catch (Exception ex){
            Toast.makeText(context.getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return id;
    }



    @Override
    public ArrayList<AdeudoDTO> consultarAdeudo(){
        ArrayList<AdeudoDTO> listaAdeudo= new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        AdeudoDTO adeudoDTO = null;
        Cursor cursorAdeudo;
        cursorAdeudo = db.rawQuery(DBQueryAdeudo.CONSULTAR_ADEUDO, null);
        while(cursorAdeudo.moveToNext()){
            adeudoDTO=  new AdeudoDTO(

                    cursorAdeudo.getString(0),
                    cursorAdeudo.getDouble(1),
                    cursorAdeudo.getString(2)
            );
            listaAdeudo.add(adeudoDTO);
        }
        return listaAdeudo;
    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudoDatos(){
        ArrayList<AdeudoDTO> listaAdeudo= new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        AdeudoDTO adeudoDTO = null;
        Cursor cursorAdeudo;
        cursorAdeudo = db.rawQuery(DBQueryAdeudo.CONSULTAR_ADEUDO_datos, null);
        while(cursorAdeudo.moveToNext()){
            adeudoDTO=  new AdeudoDTO(

                    cursorAdeudo.getString(0),
                    cursorAdeudo.getString(1),
                    cursorAdeudo.getDouble(2),
                    cursorAdeudo.getInt(3),
                    cursorAdeudo.getDouble(4),
                    cursorAdeudo.getString(5),
                    cursorAdeudo.getLong(6)
            );
            listaAdeudo.add(adeudoDTO);
        }
        return listaAdeudo;
    }

   @Override
    public ArrayList<AdeudoDTO> consultarAdeudoid(){
        ArrayList<AdeudoDTO> listaAdeudo= new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        AdeudoDTO adeudoDTO = null;
        Cursor cursorAdeudo;
        cursorAdeudo = db.rawQuery(DBQueryAdeudo.CONSULTAR_ADEUDO_ID, null);
        while(cursorAdeudo.moveToNext()){
            adeudoDTO=  new AdeudoDTO(

                    cursorAdeudo.getLong(0)
            );
            listaAdeudo.add(adeudoDTO);
        }
        return listaAdeudo;
    }

    @Override
    public void eliminarid(long id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        AdeudoDTO adeudoDTO = null;
        String idAd[]= {String.valueOf(id)};
        String query="id_adeudo="+id;

        db.delete(DBQueryAdeudo.TABLE_NAME_ADEUDOS,query,null);
    }


}
