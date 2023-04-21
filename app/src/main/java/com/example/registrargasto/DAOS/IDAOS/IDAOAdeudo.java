package com.example.registrargasto.DAOS.IDAOS;

import com.example.registrargasto.entidades.AdeudoDTO;

import java.util.ArrayList;

public interface IDAOAdeudo {
    long registarNuevoAdeudo(AdeudoDTO adeudoDTO);
    ArrayList<AdeudoDTO> consultarAdeudo();
}
