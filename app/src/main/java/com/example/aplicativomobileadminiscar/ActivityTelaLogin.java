package com.example.aplicativomobileadminiscar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;

 public class ActivityTelaLogin extends AppCompatActivity {
    Button buttonEntrar;//Declarando objeto botao
    Button buttonCancelar;
    Button buttonFacebook;
    EditText edit_email_login;
    EditText edit_senha_login;
    TextView txtEsqueciSenha;
    Button buttonChamaTelaConta;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonEntrar = findViewById(R.id.buttonEntrar);//Atribuindo objeto para a classe
        buttonCancelar = findViewById(R.id.buttonCancelar);
        edit_email_login = findViewById(R.id.edit_email_login);
        edit_senha_login = findViewById(R.id.edit_senha_login);
        txtEsqueciSenha = findViewById(R.id.txtEsqueciSenha);
        buttonFacebook = findViewById(R.id.buttonFacebook);
        buttonChamaTelaConta = findViewById(R.id.buttonChamaTelaConta);
        progressBar = findViewById(R.id.progressBar);

        entrarLogin();
        limparCampos();
        logarFace();
        esqueciSenha();
        criarConta();
    }

     private void entrarLogin(){

         buttonEntrar.setOnClickListener(v -> {
             // entra com o login
             String email = edit_email_login.getText().toString();
             String senha = edit_senha_login.getText().toString();


             if (email.isEmpty() || senha.isEmpty()){
                 Toast.makeText(getBaseContext(),"Preencha todos os campos",
                         Toast.LENGTH_LONG).show();
             }

             else{
                 AutenticarUsuario();
             }
         });
     }



    private void limparCampos(){

        buttonCancelar.setOnClickListener(view -> {
            // Limpar os campos de texto.
            edit_email_login.setText("");
            edit_senha_login.setText("");
        });
    }

    private void AutenticarUsuario(){

        String email = edit_email_login.getText().toString();
        String senha = edit_senha_login.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {

             if (email.equals("admin@gmail.com") && senha.equals("12345678")){
                 progressBar.setVisibility(View.VISIBLE);

                 new Handler(Looper.getMainLooper()).postDelayed(this::TelaProdutos, 3000);

            }

            else if (task.isSuccessful()){
                progressBar.setVisibility(View.VISIBLE);

                new Handler(Looper.getMainLooper()).postDelayed(this::TelaMenu, 3000);
            }else{


                try{
                    throw Objects.requireNonNull(task.getException());
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),"Ocorreu um erro ao fazer o login",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
}

     @Override
     protected void onStart() {
         super.onStart();
         FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
             if (usuarioAtual != null) {
                 TelaMenu();
             }
     }


     private void TelaProdutos(){
         Intent intent = new Intent(ActivityTelaLogin.this,ActivityCadastrarProdutos.class);
         startActivity(intent);
         finish();
         Toast.makeText(getBaseContext(),"Bem vindo patrão!",
                 Toast.LENGTH_LONG).show();
     }

     private void TelaMenu(){
        Intent intent = new Intent(ActivityTelaLogin.this,ActivityTelaMenu.class);
        startActivity(intent);
        finish();
         Toast.makeText(getBaseContext(),"Seja bem vindo!",
                 Toast.LENGTH_LONG).show();
    }

    //ir para tela de login do facebook
    public void logarFace(){
        buttonFacebook.setOnClickListener(v -> {
            Intent telaFacebook = new Intent(getApplicationContext(), ActivityLoginFacebook.class);
            startActivity(telaFacebook);
        });

    }

    //vai para a tela de recuperação de senha.
    public void esqueciSenha(){
        txtEsqueciSenha.setOnClickListener(v -> {
            Intent telaEsqueciSenha = new Intent(getApplicationContext(), ActivityTelaEsqueciSenha.class);
            startActivity(telaEsqueciSenha);
        });
        TextView esquecisenha = findViewById(R.id.txtEsqueciSenha);
        esquecisenha.setPaintFlags(esquecisenha.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

     //ir para tela de cadastro de conta
     public void criarConta(){
         buttonChamaTelaConta.setOnClickListener(v -> {
             Intent telaCriarConta = new Intent(getApplicationContext(), ActivityCriarConta.class);
             startActivity(telaCriarConta);
         });
     }
}