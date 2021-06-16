package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ActivityTelaMenu extends AppCompatActivity {
    Button buttonMapa;
    Button buttonLogout;
    Button buttonMinhaConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonMapa = findViewById(R.id.buttonMapa);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonMinhaConta = findViewById(R.id.buttonMinhaConta);

        abrirMapa();
        abrirMinhaConta();
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

}
