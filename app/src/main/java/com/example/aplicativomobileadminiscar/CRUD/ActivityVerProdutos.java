package com.example.aplicativomobileadminiscar.CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicativomobileadminiscar.ActivityTelaMenu;
import com.example.aplicativomobileadminiscar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class ActivityVerProdutos extends AppCompatActivity {

    RecyclerView recyclerview_produto;
    produtoAdapter adapter;// faz a integraçao desta activity com o catalogo_produto + inserir_produto + alterar_produto + menu_buscar + model
    FloatingActionButton buttonAdicionar, buttonIrCarrinho,buttonMenuProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_produtos);
        setTitle("Procure seu veículo aqui...");

        buttonMenuProduto= findViewById(R.id.buttonVoltarMenu);
        buttonIrCarrinho= findViewById(R.id.buttonIrCarrinho);

        irCarrinho();
        voltarMenu();

        recyclerview_produto= findViewById(R.id.recyclerview_produto);
        recyclerview_produto.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carros"), model.class)// traz os dados do banco referido
                        .build();

        adapter=new produtoAdapter(options);
        recyclerview_produto.setAdapter(adapter);

        buttonAdicionar= findViewById(R.id.buttonAdicionarProduto);
        buttonAdicionar.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ActivityInserirProduto.class)));

        String usuarioAtual = (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail());
        assert usuarioAtual != null;// estou dizendo para o sistema que o usuario nao é nulo
        if (usuarioAtual.equals("admin@gmail.com")){
            buttonAdicionar.setVisibility(View.VISIBLE);
        }else{
            buttonIrCarrinho.setVisibility(View.VISIBLE);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_buscar,menu);

        MenuItem item=menu.findItem(R.id.buscar);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                metodoBuscar(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                metodoBuscar(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void metodoBuscar(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carros").orderByChild("modelo").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        adapter=new produtoAdapter(options);
        adapter.startListening();
        recyclerview_produto.setAdapter(adapter);

    }

    private void voltarMenu(){

        buttonMenuProduto.setOnClickListener(v -> {
            Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(telaMenu);
        });
    }

    private void irCarrinho(){

        buttonIrCarrinho.setOnClickListener(v -> {
            Intent telaCarrinho = new Intent(getApplicationContext(), ActivityVerCarrinho.class);
            startActivity(telaCarrinho);
        });
    }
}