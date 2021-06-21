package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.aplicativomobileadminiscar.LOGIN.ActivityTelaLogin;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityTelaSplash extends AppCompatActivity {
    private static final int TEMPO_TELA_ABERTA = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        getSupportActionBar().hide();

        inicializarTelaLogin();
    }

    private void inicializarTelaLogin(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent telaLogin = new Intent(getApplicationContext(), ActivityTelaLogin.class);
                startActivity(telaLogin);
            }
        },TEMPO_TELA_ABERTA);
    }
}