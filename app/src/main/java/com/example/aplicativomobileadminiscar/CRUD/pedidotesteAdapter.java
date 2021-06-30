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

public class pedidotesteAdapter extends FirebaseRecyclerAdapter<model, pedidotesteAdapter.myviewholderpedidoteste>{

    public pedidotesteAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final pedidotesteAdapter.myviewholderpedidoteste holder, final int position, @NonNull final model model)
    {// metodo que pega os dados do cardview e joga na tela de alteraçao do produto
        holder.modelo.setText(model.getModelo());
        holder.categoria.setText(model.getCategoria());
        holder.diaria.setText(model.getDiaria());
        holder.qtd_dias.setText(model.getQtd_dias());
        holder.pagamento.setText(model.getPagamento());
        holder.valor_total.setText(model.getValor_total());
        Glide.with(holder.img_pedidoteste.getContext()).load(model.getImagem()).into(holder.img_pedidoteste);

        holder.alugar_pedidoteste.setOnClickListener(view -> {
            final DialogPlus alterarItemteste=DialogPlus.newDialog(holder.img_pedidoteste.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_confirmar_pedidoteste))
                    .setExpanded(true,1100)
                    .create();
            // aqui é a aparencia da tela alterar produto
            View myview=alterarItemteste.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_alter_imagem_pedidoteste);
            final EditText modelo=myview.findViewById(R.id.edit_alter_modelo_pedidoteste);
            final EditText categoria=myview.findViewById(R.id.edit_alter_cat_pedidoteste);
            final EditText diaria=myview.findViewById(R.id.edit_alter_diaria_pedidoteste);
            final EditText qtd_dias=myview.findViewById(R.id.didoteste);
            final EditText pagamento=myview.findViewById(R.id.edit_alter_pagamento_pedidoteste);
            final EditText valor_total=myview.findViewById(R.id.edit_alter_vl_total_pedidoteste);
            Button alter_itemteste=myview.findViewById(R.id.bt_alter_item_pedidoteste);// aciona o metodo que de fato altera o item

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            categoria.setText(model.getCategoria());
            diaria.setText(model.getDiaria());
            qtd_dias.setText(model.getQtd_dias());
            pagamento.setText(model.getPagamento());
            valor_total.setText(model.getValor_total());

            alterarItemteste.show();
            // fim da minitela com os campos trazidos do cardview


            // metodo do botao que altera o item
            alter_itemteste.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("categoria",categoria.getText().toString());
                map.put("diaria",diaria.getText().toString());
                map.put("qtd_dias",qtd_dias.getText().toString());
                map.put("pagamento",pagamento.getText().toString());
                map.put("valor_total",valor_total.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("pedidos")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> alterarItemteste.dismiss())
                        .addOnFailureListener(e -> alterarItemteste.dismiss());
            });

        });// fim do metodo alterar produto


    }

    @NonNull
    @Override// codigo do layout "catalogo_pedidoteste"
    public pedidotesteAdapter.myviewholderpedidoteste onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_pedido_teste,parent,false);
        return new pedidotesteAdapter.myviewholderpedidoteste(view);
    }

    // codigo do layout "catalogo_pedidoteste"
    static class myviewholderpedidoteste extends RecyclerView.ViewHolder
    {
        ImageView img_pedidoteste;
        Button alugar_pedidoteste;
        TextView modelo,categoria,diaria,qtd_dias,pagamento,valor_total;

        public myviewholderpedidoteste(@NonNull View itemView) {
            super(itemView);
            img_pedidoteste = itemView.findViewById(R.id.img_pedidoteste);
            modelo = itemView.findViewById(R.id.txt_modelo_catalogo_pedidoteste);
            categoria = itemView.findViewById(R.id.txt_cat_catalogo_pedidoteste);
            diaria = itemView.findViewById(R.id.txt_diaria_catalogo_pedidoteste);
            qtd_dias = itemView.findViewById(R.id.txt_qtd_dias_catalogo_pedidoteste);
            pagamento = itemView.findViewById(R.id.txt_pagamento_catalogo_pedidoteste);
            valor_total = itemView.findViewById(R.id.txt_valor_total_catalogo_pedidoteste);

            alugar_pedidoteste = itemView.findViewById(R.id.bt_alugar_pedidoteste);



        }
    }
}
