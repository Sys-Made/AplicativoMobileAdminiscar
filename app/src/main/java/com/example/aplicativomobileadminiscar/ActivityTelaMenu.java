package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;
=======

import com.google.firebase.auth.FirebaseAuth;

>>>>>>> 452c23c9cad88b28416f2975e0a89a7533484b24
import java.util.Objects;

public class ActivityTelaMenu extends AppCompatActivity {
    Button buttonMapa;
    Button buttonLogout;
    Button buttonMinhaConta;
<<<<<<< HEAD
    Button buttonCarros;
=======

>>>>>>> 452c23c9cad88b28416f2975e0a89a7533484b24
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonMapa = findViewById(R.id.buttonMapa);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonMinhaConta = findViewById(R.id.buttonMinhaConta);
<<<<<<< HEAD
        buttonCarros = findViewById(R.id.buttonCarros);

        abrirMapa();
        abrirMinhaConta();
        abrirCarros();
=======

        abrirMapa();
        abrirMinhaConta();
>>>>>>> 452c23c9cad88b28416f2975e0a89a7533484b24
        logoutMenu();
    }

    private void logoutMenu(){
        // sai da conta e volta pra tela de login
        buttonLogout.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ActivityTelaMenu.this,ActivityTelaLogin.class);
            Toast.makeText(ActivityTelaMenu.this, "Usuário deslogado",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });
    }

    //Metodo que direciona o usuario para a tela de Mapa
    public void abrirMapa(){
        buttonMapa.setOnClickListener(v -> {
            Intent telaMapa = new Intent(getApplicationContext(), ActivityMapa.class);
            startActivity(telaMapa);
        });
    }
    //Metodo que direciona o usuario para a tela minha conta
    public void abrirMinhaConta(){
        buttonMinhaConta.setOnClickListener(v -> {
            Intent telaMinhaConta = new Intent(getApplicationContext(), ActivityTelaPerfil.class);
            startActivity(telaMinhaConta);
        });
    }

    public void abrirCarros(){
        buttonCarros.setOnClickListener(v -> {
            Intent telaCarros = new Intent(getApplicationContext(), ActivityCadastrarProdutos.class);
            startActivity(telaCarros);
        });
    }

    //Metodo que direciona o usuario para a tela minha conta
    public void abrirMinhaConta(){
        buttonMinhaConta.setOnClickListener(v -> {
            Intent telaMinhaConta = new Intent(getApplicationContext(), ActivityTelaPerfil.class);
            startActivity(telaMinhaConta);
        });
    }

}
