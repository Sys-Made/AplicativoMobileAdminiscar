package com.example.aplicativomobileadminiscar.CRUD;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicativomobileadminiscar.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class meuspedidosAdapter extends FirebaseRecyclerAdapter<model, meuspedidosAdapter.myviewholdermeuspedidos>{

    public meuspedidosAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final meuspedidosAdapter.myviewholdermeuspedidos holder, final int position, @NonNull final model model)
    {// metodo que pega os dados do cardview e joga na tela meus pedidos
        holder.modelo.setText(model.getModelo());
        holder.categoria.setText(model.getCategoria());
        holder.diaria.setText(model.getDiaria());
        holder.qtd_dias.setText(model.getQtd_dias());
        holder.pagamento.setText(model.getPagamento());
        holder.valor_total.setText(model.getValor_total());
        holder.status.setText(model.getStatus());
        Glide.with(holder.img_meuspedidos.getContext()).load(model.getImagem()).into(holder.img_meuspedidos);


    }

    @NonNull
    @Override// codigo do layout "catalogo_meuspedidos"
    public meuspedidosAdapter.myviewholdermeuspedidos onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_meus_pedidos,parent,false);
        return new meuspedidosAdapter.myviewholdermeuspedidos(view);
    }

    // codigo do layout "catalogo_meuspedidos"
    static class myviewholdermeuspedidos extends RecyclerView.ViewHolder
    {
        ImageView img_meuspedidos;
        EditText modelo,categoria,diaria,qtd_dias,pagamento,valor_total,status;
        public myviewholdermeuspedidos(@NonNull View itemView) {
            super(itemView);
            img_meuspedidos = itemView.findViewById(R.id.img_meuspedidos);
            modelo = itemView.findViewById(R.id.edit_modelo_catalogo_meuspedidos);
            categoria = itemView.findViewById(R.id.edit_cat_catalogo_meuspedidos);
            diaria = itemView.findViewById(R.id.edit_diaria_catalogo_meuspedidos);
            qtd_dias = itemView.findViewById(R.id.edit_qtd_dias_catalogo_meuspedidos);
            pagamento = itemView.findViewById(R.id.edit_pagamento_catalogo_meuspedidos);
            valor_total = itemView.findViewById(R.id.edit_valor_total_catalogo_meuspedidos);
            status = itemView.findViewById(R.id.edit_status_catalogo_meuspedidos);
        }
    }
}
