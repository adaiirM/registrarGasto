package com.example.registrargasto.DAOS;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.registrargasto.DAOS.IDAOS.IDAOTipoGasto;
import com.example.registrargasto.entidades.TipoGastoDTO;
import com.example.registrargasto.openHelperDb.DbHelper;
import com.example.registrargasto.querys.DBQueryTipoGasto;

import java.util.ArrayList;

public class DAOTipoGastoImp extends DbHelper implements IDAOTipoGasto {
    Context context;
    public DAOTipoGastoImp(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public ArrayList<TipoGastoDTO> consultarTipoGasto(){
        ArrayList<TipoGastoDTO> listatipoGasto= new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        TipoGastoDTO tipoGastoDTO;
        Cursor cursorTipoGasto;
        cursorTipoGasto = db.rawQuery(DBQueryTipoGasto.CONSULTARTIPOGASTO, null);

        while(cursorTipoGasto.moveToNext()){
            tipoGastoDTO= new TipoGastoDTO(
                    cursorTipoGasto.getInt(0),
                    cursorTipoGasto.getString(1)
            );
            listatipoGasto.add(tipoGastoDTO);
        }
        return listatipoGasto;
    }

}
