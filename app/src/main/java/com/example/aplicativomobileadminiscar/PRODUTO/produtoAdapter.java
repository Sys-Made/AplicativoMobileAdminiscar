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
    {// metodo alterar produto
        holder.modelo.setText(model.getModelo());
        holder.combustivel.setText(model.getCombustivel());
        holder.diaria.setText(model.getDiaria());
        Glide.with(holder.carro.getContext()).load(model.getImagem()).into(holder.carro);

        holder.alterar.setOnClickListener(view -> {
            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.carro.getContext())
                    .setContentHolder(new ViewHolder(R.layout.activity_alterar_produto))
                    .setExpanded(true,1100)
                    .create();

            View myview=dialogPlus.getHolderView();
            final EditText imagem=myview.findViewById(R.id.edit_alter_img);
            final EditText modelo=myview.findViewById(R.id.edit_alter_modelo);
            final EditText combustivel=myview.findViewById(R.id.edit_alter_comb);
            final EditText diaria=myview.findViewById(R.id.edit_alter_diaria);
            Button alter_item=myview.findViewById(R.id.bt_alter_item);

            imagem.setText(model.getImagem());
            modelo.setText(model.getModelo());
            combustivel.setText(model.getCombustivel());
            diaria.setText(model.getDiaria());

            dialogPlus.show();

            alter_item.setOnClickListener(view1 -> {
                Map<String,Object> map=new HashMap<>();
                map.put("imagem",imagem.getText().toString());
                map.put("modelo",modelo.getText().toString());
                map.put("combustivel",combustivel.getText().toString());
                map.put("diaria",diaria.getText().toString());


                FirebaseDatabase.getInstance().getReference().child("carros")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(aVoid -> dialogPlus.dismiss())
                        .addOnFailureListener(e -> dialogPlus.dismiss());


            });


        });


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

    }// fim do metodo alterar produto

    @NonNull
    @Override// codigo do layout "catalogo_modelo"
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogo_modelo,parent,false);
        return new myviewholder(view);
    }

    // codigo do layout "catalogo_modelo"
    static class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView carro;
        ImageView alterar,deletar;
        TextView modelo,combustivel,diaria;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            carro= itemView.findViewById(R.id.img_carro);
            modelo= itemView.findViewById(R.id.txt_modelo);
            combustivel= itemView.findViewById(R.id.txt_combustivel);
            diaria= itemView.findViewById(R.id.txt_diaria);

            alterar= itemView.findViewById(R.id.alterar_item);
            deletar= itemView.findViewById(R.id.deletar_item);
        }
    }
}
