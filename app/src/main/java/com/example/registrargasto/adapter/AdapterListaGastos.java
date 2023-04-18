package com.example.registrargasto.adapter;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registrargasto.R;
import com.example.registrargasto.entidades.GastoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterListaGastos extends RecyclerView.Adapter<AdapterListaGastos.ViewHolderDatosHome> implements Filterable {

    ArrayList<GastoDTO> mDataModelGasto;
    ArrayList<GastoDTO> buscado;

    public AdapterListaGastos(ArrayList<GastoDTO> dataModelGastos){
        this.mDataModelGasto = dataModelGastos;
        this.buscado=new ArrayList<>();
        buscado.addAll(mDataModelGasto);
    }

    private Filter usuariobuscado = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<GastoDTO> listaFiltrada = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                listaFiltrada.addAll(buscado);
            } else {
                String patronFiltrado = constraint.toString().toLowerCase().trim();

                for (GastoDTO usuarioDTO : buscado) {
                    if (usuarioDTO.getNombre().toLowerCase().contains(patronFiltrado)) {
                        listaFiltrada.add(usuarioDTO);
                    }

                }
            }

            FilterResults results = new FilterResults();
            results.values = listaFiltrada;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataModelGasto.clear();
            mDataModelGasto.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
    @Override
    public Filter getFilter() {
        return usuariobuscado;
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