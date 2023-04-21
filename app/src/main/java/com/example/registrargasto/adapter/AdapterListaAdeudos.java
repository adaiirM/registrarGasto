package com.example.registrargasto.adapter;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.registrargasto.R;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.GastoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterListaAdeudos extends RecyclerView.Adapter<AdapterListaAdeudos.ViewHolderDatosAdeudos>  {
    public ArrayList<AdeudoDTO> getListaAdeudo() {
        return listaAdeudo;
    }

    ArrayList<AdeudoDTO> listaAdeudo;

    public AdapterListaAdeudos(ArrayList<AdeudoDTO> adeudoDTOS){
        this.listaAdeudo = adeudoDTOS;
    }


    public void getElemet(int position){
        listaAdeudo.get(position);
    }


    @NonNull
    @Override
    public ViewHolderDatosAdeudos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_adeudos,null,false);
        return new ViewHolderDatosAdeudos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatosAdeudos holder, int position) {

        holder.totalPago.setText(String.valueOf(listaAdeudo.get(position).getTotal()));
        holder.fechaLimite.setText(listaAdeudo.get(position).getFechaLimite());
        holder.nombrePago.setText(listaAdeudo.get(position).getNombreadeudo());

    }

    @Override
    public int getItemCount() {
        return listaAdeudo.size();

    }


    public class ViewHolderDatosAdeudos extends RecyclerView.ViewHolder {
        TextView fechaLimite;
        TextView nombrePago;
        TextView totalPago;

        public ViewHolderDatosAdeudos(@NonNull View itemView) {
            super(itemView);
            inicializarComponentes(itemView);
        }

        public void inicializarComponentes(View view){
            fechaLimite=itemView.findViewById(R.id.textmostarFechaLimiteAd);
            nombrePago=itemView.findViewById(R.id.textMostarNombrePagoAd);
            totalPago=itemView.findViewById(R.id.mostrarTotalAd);
        }

    }

}
