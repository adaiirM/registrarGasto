package com.example.registrargasto.DAOS.IDAOS;

import com.example.registrargasto.entidades.GastoDTO;

import java.util.ArrayList;

public interface IDAOGasto {
    long registrarNuevoAdeudo(GastoDTO gastoDTO);
}
