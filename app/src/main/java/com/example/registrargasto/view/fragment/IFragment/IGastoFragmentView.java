package com.example.registrargasto.view.fragment.IFragment;

import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.GastoDTO;

import java.util.ArrayList;

public interface IGastoFragmentView {
    long registrarNuevoGastoAAdeudo(AdeudoDTO adeudoDTO);
}
