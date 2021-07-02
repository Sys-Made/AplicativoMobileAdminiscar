package com.example.aplicativomobileadminiscar.LOGIN;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicativomobileadminiscar.ActivityTelaMenu;
import com.example.aplicativomobileadminiscar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityTelaPerfil extends AppCompatActivity {

    private TextView nomeUsuario, emailUsuario,telUsuario,cnhUsuario;
    private Button bt_deslogar;
    private Button bt_menu;
    private CircleImageView imgPerfilCliente, imgPerfilGerente;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        Objects.requireNonNull(getSupportActionBar()).hide();
        IniciarComponentes();

        voltarMenu();
        logoutPerfil();
    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.textNomeUsuario);
        emailUsuario = findViewById(R.id.textEmailUsuario);
        telUsuario = findViewById(R.id.textTelUsuario);
        cnhUsuario = findViewById(R.id.textCnhUsuario);
        bt_deslogar = findViewById(R.id.bt_deslogar);
        bt_menu = findViewById(R.id.bt_menu);
        imgPerfilCliente = findViewById(R.id.imgPerfilCliente);
        imgPerfilGerente = findViewById(R.id.imgPerfilGerente);
    }

    private void voltarMenu(){
        bt_menu.setOnClickListener(v -> {
            Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
            startActivity(telaMenu);
        });
    }

    private void logoutPerfil(){
        // sai da conta e volta pra tela de login
        bt_deslogar.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ActivityTelaPerfil.this, ActivityTelaLogin.class);
            Toast.makeText(ActivityTelaPerfil.this, "Usuário deslogado",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if ((documentSnapshot != null)){
                nomeUsuario.setText(documentSnapshot.getString("nome"));
                emailUsuario.setText(email);
                telUsuario.setText(documentSnapshot.getString("telefone"));
                cnhUsuario.setText(documentSnapshot.getString("cnh"));

                assert email != null;
                if (email.equals("admin@gmail.com")){
                    imgPerfilGerente.setVisibility(View.VISIBLE);
                }else{
                    nomeUsuario.setText(documentSnapshot.getString("nome"));
                    emailUsuario.setText(email);
                    imgPerfilCliente.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}