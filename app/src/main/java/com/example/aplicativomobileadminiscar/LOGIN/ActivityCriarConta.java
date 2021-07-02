package com.example.aplicativomobileadminiscar.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicativomobileadminiscar.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActivityCriarConta extends AppCompatActivity {
    private EditText edit_nome,edit_cnh, edit_telefone,edit_email, edit_senha;
    private Button buttonCadastrarConta;
    private Button buttonVoltarLogin;
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        Objects.requireNonNull(getSupportActionBar()).hide();
        IniciarComponentes();
        cadastrarConta();
        voltarTelaLogin();
    }
    private void IniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        edit_cnh = findViewById(R.id.edit_cnh);
        edit_telefone = findViewById(R.id.edit_telefone);
        buttonCadastrarConta = findViewById(R.id.buttonCadastrarConta);
        buttonVoltarLogin = findViewById(R.id.buttonVoltarLogin);
    }

    private void voltarTelaLogin(){

        buttonVoltarLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityCriarConta.this, ActivityTelaLogin.class);
            startActivity(intent);
            finish();
        });
    }

    private void cadastrarConta(){
        buttonCadastrarConta.setOnClickListener(v -> {
            String nome = edit_nome.getText().toString();
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();
            String cnh = edit_cnh.getText().toString();
            String telefone = edit_telefone.getText().toString();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cnh.isEmpty() || telefone.isEmpty()){
                Toast.makeText(getBaseContext(),"Preencha todos os campos",
                        Toast.LENGTH_LONG).show();
            }else{
                CadastrarUsuario();
            }
        });
    }
    // salva os dados no firebaseauth
    private void CadastrarUsuario(){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()){

                SalvarDadosUsuario();

                Toast.makeText(getBaseContext(),"Cadastro realizado com sucesso",
                        Toast.LENGTH_LONG).show();
            }else{

                try{
                    throw Objects.requireNonNull(task.getException());

                }catch (FirebaseAuthWeakPasswordException e) {
                    Toast.makeText(getBaseContext(),"Digite uma senha com no mínimo 6 caracteres",
                            Toast.LENGTH_LONG).show();
                }catch (FirebaseAuthUserCollisionException e) {
                    Toast.makeText(getBaseContext(),"Esta conta já foi cadastrada",
                            Toast.LENGTH_LONG).show();
                }catch (FirebaseAuthInvalidCredentialsException e){
                    Toast.makeText(getBaseContext(),"E-mail inválido",
                            Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),"Erro ao cadastrar usuário",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    // salva os dados no firestore
    private void SalvarDadosUsuario() {
        String nome = edit_nome.getText().toString();
        String cnh = edit_cnh.getText().toString();
        String telefone = edit_telefone.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        usuarios.put("cnh", cnh);
        usuarios.put("telefone", telefone);

        usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Log.d("db", "Sucesso ao Salvar os dados");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Erro ao salvar os dados" + e.toString());
            }
        });
        FirebaseAuth.getInstance().signOut();
    }
}