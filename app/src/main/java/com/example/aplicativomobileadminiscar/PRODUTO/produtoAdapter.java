package com.example.aplicativomobileadminiscar.PRODUTO;

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


public class produtoAdapter extends FirebaseRecyclerAdapter<model, produtoAdapter.myviewholder>
{
    public produtoAdapter(@NonNull FirebaseRecyclerOptions<model> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {// metodo que pega o conteudo do cardview e joga na tela de alteraçao do produto
        holder.modelo.setText(model.getModelo());
        holder.combustivel.setText(model.getCombustivel());
        holder.diaria.setText(model.getDiaria());
        Glide.with(holder.carro.getContext()).load(model.getImagem()).into(holder.carro);



        // (inicio do metodo alterar produto)chama uma minitela dentro da activity ver produtos
        holder.alterar.setOnClickListener(view -> {
            final DialogPlus alterarItem=DialogPlus.newDialog(holder.carro.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_alterar_produto))
                    .setExpanded(true,1100)
                    .create();
            // aqui é a aparencia da tela alterar produto
            View myview=alterarItem.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_alter_img_produto);
            final EditText modelo=myview.findViewById(R.id.edit_alter_modelo_produto);
            final EditText combustivel=myview.findViewById(R.id.edit_alter_comb_produto);
            final EditText diaria=myview.findViewById(R.id.edit_alter_diaria_produto);
            Button alter_item=myview.findViewById(R.id.bt_alter_item_produto);// aciona o metodo que de fato altera o item

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            combustivel.setText(model.getCombustivel());
            diaria.setText(model.getDiaria());

            alterarItem.show();
            // fim da minitela com os campos trazidos do cardview


            // metodo do botao que altera o item
            alter_item.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("combustivel",combustivel.getText().toString());
                map.put("diaria",diaria.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("carros")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> alterarItem.dismiss())
                        .addOnFailureListener(e -> alterarItem.dismiss());
            });

        });// fim do metodo alterar produto




        holder.add_carrinho.setOnClickListener(view ->{
            final DialogPlus adicionarItem=DialogPlus.newDialog(holder.carro.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_adicionar_carrinho))
                    .setExpanded(true,1100)
                    .create();
            View myview=adicionarItem.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_add_img_carrinho);
            final EditText modelo=myview.findViewById(R.id.edit_add_modelo_carrinho);
            final EditText combustivel=myview.findViewById(R.id.edit_add_comb_carrinho);
            final EditText diaria=myview.findViewById(R.id.edit_add_diaria_carrinho);
            Button add_item=myview.findViewById(R.id.bt_add_item_carrinho);

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            combustivel.setText(model.getCombustivel());
            diaria.setText(model.getDiaria());

            adicionarItem.show();

            add_item.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("combustivel",combustivel.getText().toString());
                map.put("diaria",diaria.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("carrinho")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> adicionarItem.dismiss())
                        .addOnFailureListener(e -> adicionarItem.dismiss());
            });
        });

        // metodo para deletar o produto
        holder.deletar.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(holder.carro.getContext());
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
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_produto,parent,false);
        return new myviewholder(view);
    }

    // codigo do layout "catalogo_modelo"
    static class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView carro;
        ImageView alterar,deletar,add_carrinho;
        TextView modelo,combustivel,diaria;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            carro = itemView.findViewById(R.id.img_produto);
            modelo = itemView.findViewById(R.id.txt_modelo_produto);
            combustivel = itemView.findViewById(R.id.txt_comb_produto);
            diaria = itemView.findViewById(R.id.txt_diaria_produto);

            alterar = itemView.findViewById(R.id.alterar_item_produto);
            deletar = itemView.findViewById(R.id.deletar_item_produto);
            add_carrinho = itemView.findViewById(R.id.add_item_carrinho);

            String usuarioAtual = (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail());
            assert usuarioAtual != null;// estou dizendo para o sistema que o usuario nao é nulo
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
