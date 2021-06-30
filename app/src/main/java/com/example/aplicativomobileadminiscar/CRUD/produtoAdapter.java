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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class produtoAdapter extends FirebaseRecyclerAdapter<model, produtoAdapter.myviewholderproduto> {



    public produtoAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholderproduto holder, final int position, @NonNull final model model)
    {// metodo que pega os dados do cardview e joga na tela de alteraçao do produto
        holder.modelo.setText(model.getModelo());
        holder.categoria.setText(model.getCategoria());
        holder.diaria.setText(model.getDiaria());
        holder.qtd_dias.setText(model.getQtd_dias());
        holder.pagamento.setText(model.getPagamento());
        holder.valor_total.setText(model.getValor_total());
        Glide.with(holder.img_carro.getContext()).load(model.getImagem()).into(holder.img_carro);



        // (inicio do metodo alterar produto)chama uma minitela dentro da activity ver produtos
        holder.alterar.setOnClickListener(view -> {
            final DialogPlus alterarItem=DialogPlus.newDialog(holder.img_carro.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_alterar_produto))
                    .setExpanded(true,1100)
                    .create();
            // aqui é a aparencia da tela alterar produto
            View myview=alterarItem.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_alter_imagem_produto);
            final EditText modelo=myview.findViewById(R.id.edit_alter_modelo_produto);
            final EditText categoria=myview.findViewById(R.id.edit_alter_cat_produto);
            final EditText diaria=myview.findViewById(R.id.edit_alter_diaria_produto);
            final EditText qtd_dias=myview.findViewById(R.id.edit_alter_qtd_dias_produto);
            final EditText pagamento=myview.findViewById(R.id.edit_alter_pagamento_produto);
            final EditText valor_total=myview.findViewById(R.id.edit_alter_vl_total_produto);
            Button alter_item=myview.findViewById(R.id.bt_alter_item_produto);// aciona o metodo que de fato altera o item

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            categoria.setText(model.getCategoria());
            diaria.setText(model.getDiaria());
            qtd_dias.setText(model.getQtd_dias());
            pagamento.setText(model.getPagamento());
            valor_total.setText(model.getValor_total());

            alterarItem.show();
            // fim da minitela com os campos trazidos do cardview


            // metodo do botao que altera o item
            alter_item.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("categoria",categoria.getText().toString());
                map.put("diaria",diaria.getText().toString());
                map.put("qtd_dias",qtd_dias.getText().toString());
                map.put("pagamento",pagamento.getText().toString());
                map.put("valor_total",valor_total.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("carros")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> alterarItem.dismiss())
                        .addOnFailureListener(e -> alterarItem.dismiss());
            });

        });// fim do metodo alterar produto



        // metodo adicionar no carrinho
        holder.add_carrinho.setOnClickListener(view ->{
            final DialogPlus adicionarItem=DialogPlus.newDialog(holder.img_carro.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_adicionar_carrinho))
                    .setExpanded(true,1100)
                    .create();
            View myview=adicionarItem.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_add_img_carrinho);
            final EditText modelo=myview.findViewById(R.id.edit_add_modelo_carrinho);
            final EditText categoria=myview.findViewById(R.id.edit_add_cat_carrinho);
            final EditText diaria=myview.findViewById(R.id.edit_add_diaria_carrinho);
            final EditText qtd_dias=myview.findViewById(R.id.edit_add_qtd_dias_carrinho);
            final EditText pagamento=myview.findViewById(R.id.edit_add_pagamento_carrinho);
            final EditText valor_total=myview.findViewById(R.id.edit_add_valor_total_carrinho);
            Button add_item=myview.findViewById(R.id.bt_add_item_carrinho);

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            categoria.setText(model.getCategoria());
            diaria.setText(model.getDiaria());
            qtd_dias.setText(model.getQtd_dias());
            pagamento.setText(model.getPagamento());
            valor_total.setText(model.getValor_total());


            adicionarItem.show();

            add_item.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("categoria",categoria.getText().toString());
                map.put("diaria",diaria.getText().toString());
                map.put("qtd_dias",qtd_dias.getText().toString());
                map.put("pagamento",pagamento.getText().toString());
                map.put("valor_total",valor_total.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("carrinho")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> adicionarItem.dismiss())
                        .addOnFailureListener(e -> adicionarItem.dismiss());
            });
        });

        // metodo para deletar o produto
        holder.deletar.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(holder.img_carro.getContext());
            builder.setTitle("DELETAR ITEM");
            builder.setMessage("Deseja deletar este item?");

            builder.setPositiveButton("Sim", (dialogInterface, i) -> FirebaseDatabase.getInstance().getReference().child("carros")
                    .child(Objects.requireNonNull(getRef(position).getKey())).removeValue());

            builder.setNegativeButton("Não", (dialogInterface, i) -> {

            });

            builder.show();
        });

    }

    @NonNull
    @Override// codigo do layout "catalogo_modelo"
    public myviewholderproduto onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_produto,parent,false);
        return new myviewholderproduto(view);
    }

    // codigo do layout "catalogo_produto"
    static class myviewholderproduto extends RecyclerView.ViewHolder
    {
        ImageView img_carro;
        ImageView alterar,deletar,add_carrinho;
        TextView modelo,categoria,diaria,qtd_dias,pagamento,valor_total;
        public myviewholderproduto(@NonNull View itemView) {
            super(itemView);
            img_carro = itemView.findViewById(R.id.img_produto);
            modelo = itemView.findViewById(R.id.txt_modelo_catalogo_produto);
            categoria = itemView.findViewById(R.id.txt_cat_catalogo_produto);
            diaria = itemView.findViewById(R.id.txt_diaria_catalogo_produto);
            qtd_dias = itemView.findViewById(R.id.txt_qtd_dias_catalogo_produto);
            pagamento = itemView.findViewById(R.id.txt_pagamento_catalogo_produto);
            valor_total = itemView.findViewById(R.id.txt_valor_total_catalogo_produto);

            alterar = itemView.findViewById(R.id.alterar_item_produto);
            deletar = itemView.findViewById(R.id.deletar_item_produto);
            add_carrinho = itemView.findViewById(R.id.add_item_produto);

            String usuarioAtual = (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail());
            assert usuarioAtual != null;// checando se o usuario atual nao é nulo
            if (usuarioAtual.equals("admin@gmail.com")) {
                alterar.setVisibility(View.VISIBLE);
                deletar.setVisibility(View.VISIBLE);
                add_carrinho.setVisibility(View.INVISIBLE);
            }else {
                add_carrinho.setVisibility(View.VISIBLE);
            }
        }
    }
}
