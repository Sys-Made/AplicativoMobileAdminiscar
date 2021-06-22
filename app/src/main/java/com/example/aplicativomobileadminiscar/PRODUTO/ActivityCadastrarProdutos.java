package com.example.aplicativomobileadminiscar.PRODUTO;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityCadastrarProdutos extends AppCompatActivity {

    RecyclerView recyclerview;
    produtoAdapter adapter;
    FloatingActionButton buttonAdicionar;
    FloatingActionButton buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtos);
        setTitle("Procure seu veículo aqui...");

        buttonMenu= findViewById(R.id.buttonMenu);

        // volta pra tela de menu
        voltarMenu();

        recyclerview= findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carros"), model.class)
                        .build();

        adapter=new produtoAdapter(options);
        recyclerview.setAdapter(adapter);

        buttonAdicionar= findViewById(R.id.buttonAdicionar);
        buttonAdicionar.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ActivityInserirProduto.class)));

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        assert usuarioAtual != null;
        if (usuarioAtual.toString().equals("admin@gmail.com")){
            buttonAdicionar.setVisibility(View.VISIBLE);
        }else{
            buttonAdicionar.setVisibility(View.VISIBLE);
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
        recyclerview.setAdapter(adapter);

    }

    private void voltarMenu(){

        buttonMenu.setOnClickListener(v -> {
            Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(telaMenu);
        });
    }
}