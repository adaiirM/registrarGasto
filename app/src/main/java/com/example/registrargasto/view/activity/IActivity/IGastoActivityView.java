package com.example.registrargasto.view.activity.IActivity;

import com.example.registrargasto.entidades.GastoDTO;

import java.util.ArrayList;

public interface IGastoActivityView {
    ArrayList<GastoDTO> consultarGastos();
}
