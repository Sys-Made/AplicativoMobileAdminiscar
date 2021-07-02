package com.example.aplicativomobileadminiscar.CRUD;

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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class pedidoAdapter extends FirebaseRecyclerAdapter<model, pedidoAdapter.myviewholderpedido>{

    public pedidoAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final pedidoAdapter.myviewholderpedido holder, final int position, @NonNull final model model)
    {// metodo que pega os dados do cardview e joga na tela de alteraçao do produto
        holder.modelo.setText(model.getModelo());
        holder.categoria.setText(model.getCategoria());
        holder.diaria.setText(model.getDiaria());
        holder.qtd_dias.setText(model.getQtd_dias());
        holder.pagamento.setText(model.getPagamento());
        holder.valor_total.setText(model.getValor_total());
        Glide.with(holder.img_pedido.getContext()).load(model.getImagem()).into(holder.img_pedido);

        holder.alugar_pedido.setOnClickListener(view -> {
            final DialogPlus inserirPedido=DialogPlus.newDialog(holder.img_pedido.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_confirmar_pedido))
                    .setExpanded(true,1100)
                    .create();
            // aqui é a aparencia da tela confirmar pedido
            View myview=inserirPedido.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_add_imagem_pedido);
            final EditText modelo=myview.findViewById(R.id.edit_add_modelo_pedido);
            final EditText categoria=myview.findViewById(R.id.edit_add_cat_pedido);
            final EditText diaria=myview.findViewById(R.id.edit_add_diaria_pedido);
            final EditText qtd_dias=myview.findViewById(R.id.edit_add_qtd_dias_pedido);
            final EditText pagamento=myview.findViewById(R.id.edit_add_pagamento_pedido);
            final EditText valor_total=myview.findViewById(R.id.edit_add_valor_total_pedido);
            Button add_pedido=myview.findViewById(R.id.bt_add_item_pedido);// aciona o metodo que de fato altera o item

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            categoria.setText(model.getCategoria());
            diaria.setText(model.getDiaria());
            qtd_dias.setText(model.getQtd_dias());
            pagamento.setText(model.getPagamento());
            valor_total.setText(model.getValor_total());

            inserirPedido.show();
            // fim da minitela com os campos trazidos do cardview


            // metodo do botao que altera o item
            add_pedido.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("categoria",categoria.getText().toString());
                map.put("diaria",diaria.getText().toString());
                map.put("qtd_dias",qtd_dias.getText().toString());
                map.put("pagamento",pagamento.getText().toString());
                map.put("valor_total",valor_total.getText().toString());
                // gera um id independente do carrinho
                /*FirebaseDatabase.getInstance().getReference().child("pedidos").push()
                        .setValue(map)*/
                FirebaseDatabase.getInstance().getReference().child("pedidos") // gera um id atrelado ao carrinho
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> inserirPedido.dismiss())
                        .addOnFailureListener(e -> inserirPedido.dismiss());
            });

        });// fim do metodo  inserir pedido


    }

    @NonNull
    @Override// codigo do layout "catalogo_pedido"
    public pedidoAdapter.myviewholderpedido onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_pedido,parent,false);
        return new pedidoAdapter.myviewholderpedido(view);
    }

    // codigo do layout "catalogo_pedido"
    static class myviewholderpedido extends RecyclerView.ViewHolder
    {
        ImageView img_pedido;
        Button alugar_pedido;
        TextView modelo,categoria,diaria,qtd_dias,pagamento,valor_total;

        public myviewholderpedido(@NonNull View itemView) {
            super(itemView);
            img_pedido = itemView.findViewById(R.id.img_pedido);
            modelo = itemView.findViewById(R.id.txt_modelo_catalogo_pedido);
            categoria = itemView.findViewById(R.id.txt_cat_catalogo_pedido);
            diaria = itemView.findViewById(R.id.txt_diaria_catalogo_pedido);
            qtd_dias = itemView.findViewById(R.id.txt_qtd_dias_catalogo_pedido);
            pagamento = itemView.findViewById(R.id.txt_pagamento_catalogo_pedido);
            valor_total = itemView.findViewById(R.id.txt_valor_total_catalogo_pedido);

            alugar_pedido = itemView.findViewById(R.id.bt_alugar_pedido);



        }
    }
}
