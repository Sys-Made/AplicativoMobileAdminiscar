package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import  android.view.View;

public class ActivityTelaEsqueciSenha extends AppCompatActivity {
    ImageView imgViewRodape2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_esqueci_senha);
        imgViewRodape2 = findViewById(R.id.imgViewRodape2);
        voltarlogin();
    }


    //voltar tela login
    public void voltarlogin(){
        imgViewRodape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLogin = new Intent(getApplicationContext(), ActivityTelaLogin.class);
                startActivity(telaLogin);
            }
        });
    }

}