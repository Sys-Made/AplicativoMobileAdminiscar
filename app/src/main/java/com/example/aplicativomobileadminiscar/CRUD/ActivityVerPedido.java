package com.example.aplicativomobileadminiscar.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicativomobileadminiscar.ActivityTelaMenu;
import com.example.aplicativomobileadminiscar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ActivityVerPedido extends AppCompatActivity {
    RecyclerView recyclerview_pedido;
    pedidoAdapter pedido_adapter;
    FloatingActionButton buttonVoltarMenuPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido);


        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonVoltarMenuPedido= findViewById(R.id.buttonVoltarMenuPedido);

        recyclerview_pedido= findViewById(R.id.recyclerview_pedido);
        recyclerview_pedido.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carrinho"), model.class)// traz os dados do banco referido
                        .build();

        pedido_adapter=new pedidoAdapter(options);
        recyclerview_pedido.setAdapter(pedido_adapter);

        voltarTelaMenuPedido();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pedido_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pedido_adapter.stopListening();
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carrinho").orderByChild("modelo").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        pedido_adapter=new pedidoAdapter(options);
        pedido_adapter.startListening();
        recyclerview_pedido.setAdapter(pedido_adapter);

    }

    private void voltarTelaMenuPedido(){

        buttonVoltarMenuPedido.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(intent);
        });
    }
}