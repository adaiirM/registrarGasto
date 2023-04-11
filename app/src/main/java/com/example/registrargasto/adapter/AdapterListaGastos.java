package com.example.registrargasto.adapter;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registrargasto.R;
import com.example.registrargasto.entidades.GastoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterListaGastos extends RecyclerView.Adapter<AdapterListaGastos.ViewHolderDatosHome> {

    ArrayList<GastoDTO> mDataModelGasto;
    ArrayList<GastoDTO> buscado;

    public AdapterListaGastos(ArrayList<GastoDTO> dataModelGastos){
        this.mDataModelGasto = dataModelGastos;
        this.buscado=new ArrayList<>();
        buscado.addAll(mDataModelGasto);
    }

    public void  filtrado(String buscar){
        int longitud= buscar.length();
        if(longitud==0){
            mDataModelGasto.clear();
            mDataModelGasto.addAll(buscado);

        }else {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
                List<GastoDTO> lista= mDataModelGasto.stream().filter(i -> i.getNombre().toLowerCase().contains(buscar.toLowerCase())).collect(Collectors.toList());
                mDataModelGasto.clear();
                mDataModelGasto.addAll(lista);
            }else {
                for (GastoDTO gasto:buscado) {
                    if(gasto.getNombre().toLowerCase().contains(buscar.toLowerCase())){
                        mDataModelGasto.add(gasto);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolderDatosHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_gastos,null,false);
        return  new ViewHolderDatosHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatosHome holder, int position) {
        String total= String.valueOf(mDataModelGasto.get(position).getTotal());

        //Establece la comunicación entre el adaptador y ViewHolderDatosHome
        holder.mtxtTitulo.setText(mDataModelGasto.get(position).getNombre());
        holder.mtxtFecha.setText(mDataModelGasto.get(position).getFechaRegistro());
        holder.mCantidad.setText(String.valueOf(mDataModelGasto.get(position).getCantidad()));
        holder.mMonto.setText(String.valueOf(mDataModelGasto.get(position).getTotal()));

    }

    @Override
    public int getItemCount() {
        return mDataModelGasto.size();
    }

    public class ViewHolderDatosHome extends RecyclerView.ViewHolder {
        TextView mtxtTitulo;
        TextView mtxtFecha;
        TextView mCantidad;
        TextView mMonto;
        ImageView imageView1;
        ImageView imageView2;

        public ViewHolderDatosHome(@NonNull View itemView) {
            super(itemView);
            inicializaComponentes(itemView);
        }


        public void inicializaComponentes(View itemView){
            mtxtTitulo=itemView.findViewById(R.id.txtNombreGasto);
            mtxtFecha=itemView.findViewById(R.id.mosFecha);
            mCantidad=itemView.findViewById(R.id.mosCantidad);
            mMonto=itemView.findViewById(R.id.mosMonto);
            imageView1=itemView.findViewById(R.id.imageNota);
        }


    }
}