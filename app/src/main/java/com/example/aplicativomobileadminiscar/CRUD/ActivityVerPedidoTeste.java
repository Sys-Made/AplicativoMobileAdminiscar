package com.example.aplicativomobileadminiscar.CRUD;

import androidx.appcompat.app.AppCompatActivity;
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
import android.os.Bundle;
import java.util.Objects;
import com.example.aplicativomobileadminiscar.R;

public class ActivityVerPedidoTeste extends AppCompatActivity {
    RecyclerView recyclerview_pedidoteste;
    pedidotesteAdapter pedido_teste_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido_teste);


        Objects.requireNonNull(getSupportActionBar()).hide();


        recyclerview_pedidoteste= findViewById(R.id.recyclerview_pedidoteste);
        recyclerview_pedidoteste.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carrinho"), model.class)// traz os dados do banco referido
                        .build();

        pedido_teste_adapter=new pedidotesteAdapter(options);
        recyclerview_pedidoteste.setAdapter(pedido_teste_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pedido_teste_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pedido_teste_adapter.stopListening();
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

        pedido_teste_adapter=new pedidotesteAdapter(options);
        pedido_teste_adapter.startListening();
        recyclerview_pedidoteste.setAdapter(pedido_teste_adapter);

    }
}