package com.example.aplicativomobileadminiscar.CRUD;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicativomobileadminiscar.ActivityTelaMenu;
import com.example.aplicativomobileadminiscar.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ActivityVerCarrinho extends AppCompatActivity {

    RecyclerView recyclerview_carrinho;
    carrinhoAdapter carrinho_adapter;
    Button bt_chama_ver_pedido;
    FloatingActionButton bt_ir_tela_meus_pedidos,buttonMenuCarrinho,buttonVoltarCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carrinho);

        Objects.requireNonNull(getSupportActionBar()).hide();
        // setTitle("Procure seu item aqui...");

        buttonMenuCarrinho= findViewById(R.id.buttonMenuCarrinho);
        bt_ir_tela_meus_pedidos= findViewById(R.id.bt_ir_tela_meus_pedidos);
        buttonVoltarCarros= findViewById(R.id.buttonVoltarCarros);
        bt_chama_ver_pedido= findViewById(R.id.bt_chama_ver_pedido);


        irTelaMeusPedidos();
        voltarMenu();
        voltarCarros();
        irTelaPedido();

        recyclerview_carrinho= findViewById(R.id.recyclerview_carrinho);
        recyclerview_carrinho.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("carrinho"), model.class)// traz os dados do banco referido
                        .build();

        carrinho_adapter=new carrinhoAdapter(options);
        recyclerview_carrinho.setAdapter(carrinho_adapter);

    }



    @Override
    protected void onStart() {
        super.onStart();
        carrinho_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        carrinho_adapter.stopListening();
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

        carrinho_adapter=new carrinhoAdapter(options);
        carrinho_adapter.startListening();
        recyclerview_carrinho.setAdapter(carrinho_adapter);

    }

    private void voltarMenu(){

        buttonMenuCarrinho.setOnClickListener(v -> {
            Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(telaMenu);
        });
    }

    private void voltarCarros(){

        buttonVoltarCarros.setOnClickListener(v -> {
            Intent telaVoltarCarros = new Intent(getApplicationContext(), ActivityVerProdutos.class);
            startActivity(telaVoltarCarros);
        });
    }

    private void irTelaPedido(){

        bt_chama_ver_pedido.setOnClickListener(v -> {
            Intent telaPedidos = new Intent(getApplicationContext(), ActivityVerPedido.class);
            startActivity(telaPedidos);
        });
    }
    private void irTelaMeusPedidos(){

        bt_ir_tela_meus_pedidos.setOnClickListener(v -> {
            Intent telaMeusPedidos = new Intent(getApplicationContext(), ActivityVerMeusPedidos.class);
            startActivity(telaMeusPedidos);
        });
    }
}