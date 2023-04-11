package com.example.registrargasto.view.fragment.IFragment;

import com.example.registrargasto.entidades.AdeudoDTO;

import java.util.ArrayList;

public interface IAdeudoFragmentView {
    ArrayList<AdeudoDTO> consultarAdeudo();
    ArrayList<AdeudoDTO> consultarAdeudoid();
    void eliminarid(long id);
    ArrayList<AdeudoDTO> consultarAdeudoDatos();
}
