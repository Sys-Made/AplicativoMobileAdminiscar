 package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicativomobileadminiscar.database.DtoLogin;

public class ActivityTelaLogin extends AppCompatActivity {
    Button buttonEntrar;//Declarando objeto botao
    Button buttonCancelar;
    Button buttonFacebook;
    EditText txtUsuario;
    EditText txtSenha;
    TextView txtEsqueci;

    //Usuario administrador.
    DtoLogin dtoLogin = new DtoLogin("administrador","admin","1234");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonEntrar = findViewById(R.id.buttonEntrar);//Atribuindo do objeto para a classe
        buttonCancelar = findViewById(R.id.buttonCancelar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);
        txtEsqueci = findViewById(R.id.txtEsqueci);
        buttonFacebook = findViewById(R.id.buttonFacebook);
        logarface();
        esquecisenha();


        buttonEntrar.setOnClickListener(view -> {
            String usuario = txtUsuario.getText().toString();
            String senha =  txtSenha.getText().toString();
            logar(usuario,senha);

        });
        buttonCancelar.setOnClickListener(view -> {
            // Limpar os campos de texto.
            txtUsuario.setText("");
            txtSenha.setText("");
        });

    }
    private  void logar(String usuario, String senha){
        // DtoLogin dtoLogin = dao.
        if(dtoLogin.autenticar(usuario,senha)){
            //Ir para a tela de menu.
            Intent telaMenu = new Intent(getApplicationContext(),ActivityTelaMenu.class);
            startActivity(telaMenu);
        }
        else{
            Toast.makeText(this, "Cê é bobo ou quer 1 real?", Toast.LENGTH_SHORT).show();
        }
    }

    //ir para tela de login do facebook
    public void logarface(){
        buttonFacebook.setOnClickListener(v -> {
            Intent telaFacebook = new Intent(getApplicationContext(), ActivityLoginFacebook.class);
            startActivity(telaFacebook);
        });
    }

    //vai para a tela de recuperação de senha.
    public void esquecisenha(){
        txtEsqueci.setOnClickListener(v -> {
            Intent telaEsqueciSenha = new Intent(getApplicationContext(), ActivityTelaEsqueciSenha.class);
            startActivity(telaEsqueciSenha);
        });
        TextView esqueci = (TextView) findViewById(R.id.txtEsqueci);
        esqueci.setPaintFlags(esqueci.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}