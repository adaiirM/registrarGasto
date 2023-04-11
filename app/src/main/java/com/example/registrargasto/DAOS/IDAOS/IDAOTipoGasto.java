package com.example.registrargasto.DAOS.IDAOS;

import com.example.registrargasto.entidades.TipoGastoDTO;

import java.util.ArrayList;

public interface IDAOTipoGasto {
    ArrayList<TipoGastoDTO> consultarTipoGasto();
}
