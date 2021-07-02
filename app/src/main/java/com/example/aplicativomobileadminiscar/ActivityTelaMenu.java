package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplicativomobileadminiscar.CRUD.ActivityVerCarrinho;
import com.example.aplicativomobileadminiscar.CRUD.ActivityVerMeusPedidos;
import com.example.aplicativomobileadminiscar.CRUD.ActivityVerPedido;
import com.example.aplicativomobileadminiscar.CRUD.ActivityVerProdutos;
import com.example.aplicativomobileadminiscar.LOGIN.ActivityTelaLogin;
import com.example.aplicativomobileadminiscar.LOGIN.ActivityTelaPerfil;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class ActivityTelaMenu extends AppCompatActivity {
    Button buttonMapa;
    Button buttonLogout;
    Button buttonMinhaConta;
    Button buttonCarros;
    Button buttonCarrinho;
    Button buttonPedidos;
    Button buttonQuemSomos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonMapa = findViewById(R.id.buttonMapa);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonMinhaConta = findViewById(R.id.buttonMinhaConta);
        buttonCarros = findViewById(R.id.buttonCarros);
        buttonCarrinho = findViewById(R.id.buttonCarrinho);
        buttonPedidos = findViewById(R.id.buttonPedidos);
        buttonQuemSomos = findViewById(R.id.buttonQuemSomos);
        abrirMapa();
        abrirMeusPedidos();
        abrirMinhaConta();
        abrirCarros();
        abrirCarrinho();
        logoutMenu();
        abrirQuemSomos();
    }

    private void logoutMenu(){
        // sai da conta e volta pra tela de login
        buttonLogout.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ActivityTelaMenu.this, ActivityTelaLogin.class);
            Toast.makeText(ActivityTelaMenu.this, "Usuário deslogado",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });
    }

    //Metodo que direciona o usuario para a tela de Mapa
    public void abrirQuemSomos(){
        buttonQuemSomos.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ActivityQuemSomos.class);
            startActivity(intent);
        });
    }

    public void abrirMapa(){
        buttonMapa.setOnClickListener(v -> {
            Intent telaMapa = new Intent(getApplicationContext(), ActivityMapa.class);
            startActivity(telaMapa);
        });
    }

    public void abrirCarros(){
        buttonCarros.setOnClickListener(v -> {
            Intent telaCarros = new Intent(getApplicationContext(), ActivityVerProdutos.class);
            startActivity(telaCarros);
        });
    }

    public void abrirCarrinho(){
        buttonCarrinho.setOnClickListener(v -> {
            Intent telaCarrinho = new Intent(getApplicationContext(), ActivityVerCarrinho.class);
            startActivity(telaCarrinho);
        });
    }

    //Metodo que direciona o usuario para a tela minha conta
    public void abrirMinhaConta(){
        buttonMinhaConta.setOnClickListener(v -> {
            Intent telaMinhaConta = new Intent(getApplicationContext(), ActivityTelaPerfil.class);
            startActivity(telaMinhaConta);
        });
    }

    public void abrirMeusPedidos(){
        buttonPedidos.setOnClickListener(v -> {
            Intent telaMeusPedidos = new Intent(getApplicationContext(), ActivityVerMeusPedidos.class);
            startActivity(telaMeusPedidos);
        });
    }

}
