package com.example.aplicativomobileadminiscar.LOGIN;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplicativomobileadminiscar.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ActivityTelaEsqueciSenha extends AppCompatActivity {

    private Button buttonRecuperaSenha;
    private EditText editRecuperaSenha;
    private ImageView imgViewRodape2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_esqueci_senha);

        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonRecuperaSenha = findViewById(R.id.buttonRecuperaSenha);
        editRecuperaSenha = findViewById(R.id.editRecuperaSenha);
        imgViewRodape2 = findViewById(R.id.imgViewRodape2);

        recuperarSenha();
        voltarlogin();
    }

    //metodo para recuperar a senha
    private void recuperarSenha(){

        buttonRecuperaSenha.setOnClickListener(v -> {

        String email = editRecuperaSenha.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(getBaseContext(),"Insira o e-mail para poder recuperar sua senha",
                    Toast.LENGTH_LONG).show();
        }else{
            enviarEmail(email);
        }
        });

    }

    private void enviarEmail(String email){

        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnSuccessListener(aVoid ->
                Toast.makeText(getBaseContext(),"Enviamos uma mensagem para o seu e-mail com um link para você redefinir a senha",
                Toast.LENGTH_LONG).show()).addOnFailureListener(e -> Toast.makeText(getBaseContext(),"Enviamos uma mensagem para o seu e-mail com um link para você redefinir a senha",
                        Toast.LENGTH_LONG).show());

    }
    //voltar tela login
    private void voltarlogin(){
        imgViewRodape2.setOnClickListener(v -> {
            Intent telaLogin = new Intent(getApplicationContext(), ActivityTelaLogin.class);
            startActivity(telaLogin);
        });
    }
}