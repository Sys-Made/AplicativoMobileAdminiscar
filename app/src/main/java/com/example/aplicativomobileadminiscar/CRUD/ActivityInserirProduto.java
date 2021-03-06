package com.example.aplicativomobileadminiscar.CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicativomobileadminiscar.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActivityInserirProduto extends AppCompatActivity
{
    EditText modelo,categoria,diaria,imagem;
    Button bt_cadastrar,bt_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_produto);

        modelo= findViewById(R.id.edit_modelo);
        categoria= findViewById(R.id.edit_categoria);
        diaria= findViewById(R.id.edit_diaria);
        imagem= findViewById(R.id.edit_imagem);

        bt_voltar= findViewById(R.id.bt_voltar);

        bt_voltar.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ActivityVerProdutos.class));
            finish();
        });

        bt_cadastrar= findViewById(R.id.bt_cadastrar);
        bt_cadastrar.setOnClickListener(view -> metodoCadastrar());
    }



    private void metodoCadastrar()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("modelo",modelo.getText().toString());
        map.put("categoria",categoria.getText().toString());
        map.put("diaria",diaria.getText().toString());
        map.put("imagem",imagem.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("carros").push()
                .setValue(map)
                .addOnSuccessListener(aVoid -> {
                    modelo.setText("");
                    categoria.setText("");
                    diaria.setText("");
                    imagem.setText("");
                    Toast.makeText(getApplicationContext(),"Cadastrado com sucesso!",Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(),"Falha ao cadastrar!",Toast.LENGTH_LONG).show());

    }

}