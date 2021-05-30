package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ActivityTelaMenu extends AppCompatActivity {
    Button buttonMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        buttonMapa = findViewById(R.id.buttonMapa);
        abrirMapa();


    }

    //Metodo que direciona o usuario para a tela de Mapa
    public void abrirMapa(){
        buttonMapa.setOnClickListener(v -> {
            Intent telaMapa = new Intent(getApplicationContext(), ActivityMapa.class);
            startActivity(telaMapa);
        });
    }



}
