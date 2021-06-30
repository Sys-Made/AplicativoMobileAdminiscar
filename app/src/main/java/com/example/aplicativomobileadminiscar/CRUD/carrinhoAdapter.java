package com.example.aplicativomobileadminiscar.CRUD;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicativomobileadminiscar.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class carrinhoAdapter extends FirebaseRecyclerAdapter<model, carrinhoAdapter.myviewholdercarrinho> {

    public carrinhoAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholdercarrinho holder, final int position, @NonNull final model model)
    {// metodo que pega o conteudo do cardview e joga na tela de alteraçao do produto
        holder.modelo.setText(model.getModelo());
        holder.categoria.setText(model.getCategoria());
        holder.diaria.setText(model.getDiaria());
        holder.qtd_dias.setText(model.getQtd_dias());
        holder.pagamento.setText(model.getPagamento());
        holder.valor_total.setText(model.getValor_total());
        Glide.with(holder.imgCarrinho.getContext()).load(model.getImagem()).into(holder.imgCarrinho);

        // metodo para deletar o produto
        holder.deletarCarrinho.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(holder.imgCarrinho.getContext());
            builder.setTitle("DELETAR ITEM");
            builder.setMessage("Deseja deletar este item?");

            builder.setPositiveButton("Sim", (dialogInterface, i) -> FirebaseDatabase.getInstance().getReference().child("carrinho")
                    .child(Objects.requireNonNull(getRef(position).getKey())).removeValue());

            builder.setNegativeButton("Não", (dialogInterface, i) -> {

            });

            builder.show();
        });

    }

    @NonNull
    @Override// codigo do layout "catalogo_carrinho"
    public myviewholdercarrinho onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_carrinho,parent,false);
        return new myviewholdercarrinho(view);
    }

    // codigo do layout "catalogo_carrinho"
    static class myviewholdercarrinho extends RecyclerView.ViewHolder
    {
        ImageView imgCarrinho,deletarCarrinho;
        TextView modelo,categoria,diaria,qtd_dias,pagamento,valor_total;


        public myviewholdercarrinho(@NonNull View itemView) {
            super(itemView);
            imgCarrinho = itemView.findViewById(R.id.imgItemCarrinho);
            modelo = itemView.findViewById(R.id.txt_modelo_catalogo_carrinho);
            categoria = itemView.findViewById(R.id.txt_cat_catalogo_carrinho);
            diaria = itemView.findViewById(R.id.txt_diaria_catalogo_carrinho);
            qtd_dias = itemView.findViewById(R.id.txt_qtd_dias_catalogo_carrinho);
            pagamento = itemView.findViewById(R.id.txt_pagamento_catalogo_carrinho);
            valor_total = itemView.findViewById(R.id.txt_valor_total_catalogo_carrinho);


            deletarCarrinho = itemView.findViewById(R.id.deletar_item_carrinho);


        }

    }

}
