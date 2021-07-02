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

public class ActivityVerMeusPedidos extends AppCompatActivity {
    RecyclerView recyclerview_meuspedidos;
    meuspedidosAdapter meus_pedidos_adapter;
    FloatingActionButton buttonvoltarMenuMeusPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_meus_pedidos);

        setTitle("Procure seu pedido aqui...");

        buttonvoltarMenuMeusPedidos= findViewById(R.id.buttonvoltarMenuMeusPedidos);


        voltarMenu();

        recyclerview_meuspedidos= findViewById(R.id.recyclerview_meuspedidos);
        recyclerview_meuspedidos.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pedidos"), model.class)// traz os dados do banco referido
                        .build();

        meus_pedidos_adapter=new meuspedidosAdapter(options);
        recyclerview_meuspedidos.setAdapter(meus_pedidos_adapter);

    }



    @Override
    protected void onStart() {
        super.onStart();
        meus_pedidos_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        meus_pedidos_adapter.stopListening();
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pedidos").orderByChild("modelo").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        meus_pedidos_adapter=new meuspedidosAdapter(options);
        meus_pedidos_adapter.startListening();
        recyclerview_meuspedidos.setAdapter(meus_pedidos_adapter);

    }

    private void voltarMenu(){

        buttonvoltarMenuMeusPedidos.setOnClickListener(v -> {
            Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(telaMenu);
        });
    }

}