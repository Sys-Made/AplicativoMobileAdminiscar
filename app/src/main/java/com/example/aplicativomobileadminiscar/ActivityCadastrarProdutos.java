package com.example.aplicativomobileadminiscar;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityCadastrarProdutos extends AppCompatActivity {

    RecyclerView recyclerview;
    myadapter adapter;
    FloatingActionButton bt_add;
    FloatingActionButton buttonMenu;

=======
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityCadastrarProdutos extends AppCompatActivity {

>>>>>>> 452c23c9cad88b28416f2975e0a89a7533484b24
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtos);
<<<<<<< HEAD
        setTitle("Procure seu veículo aqui...");

        buttonMenu= findViewById(R.id.buttonMenu);

        // volta pra tela de menu
        voltarMenu();

        recyclerview= findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), model.class)
                        .build();

        adapter=new myadapter(options);
        recyclerview.setAdapter(adapter);

        bt_add= findViewById(R.id.bt_add);
        bt_add.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),adddata.class)));

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        assert usuarioAtual != null;
        if (usuarioAtual.toString().equals("admin@gmail.com")){
            bt_add.setVisibility(View.VISIBLE);
        }else{
            bt_add.setVisibility(View.VISIBLE);
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
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("course").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        adapter=new myadapter(options);
        adapter.startListening();
        recyclerview.setAdapter(adapter);

    }

    private void voltarMenu(){

        buttonMenu.setOnClickListener(v -> {
            Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(telaMenu);
        });
=======
>>>>>>> 452c23c9cad88b28416f2975e0a89a7533484b24
    }
}