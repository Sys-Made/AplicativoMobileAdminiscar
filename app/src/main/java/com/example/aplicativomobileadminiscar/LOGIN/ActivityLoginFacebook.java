package com.example.aplicativomobileadminiscar.LOGIN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.RequestOptions;
import com.example.aplicativomobileadminiscar.ActivityTelaMenu;
import com.example.aplicativomobileadminiscar.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityLoginFacebook extends AppCompatActivity {

    LoginButton login_button;
    CircleImageView profile_pic;
    TextView profile_name;
    ImageView imgViewRodape3;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_facebook);

        getSupportActionBar().hide();

        login_button = findViewById(R.id.login_button);
        profile_name = findViewById(R.id.profile_name);
        profile_pic = findViewById(R.id.profile_pic);
        imgViewRodape3 = findViewById(R.id.imgViewRodape3);

        voltarlogin();

        callbackManager = CallbackManager.Factory.create();
        login_button.setPermissions(Arrays.asList("email","public_profile"));

        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            if(currentAccessToken==null)
            {
                profile_name.setText("");
                profile_pic.setVisibility(View.INVISIBLE);
                Toast.makeText(ActivityLoginFacebook.this, "Usuário deslogado",Toast.LENGTH_LONG).show();
            }
            else
                loadUserProfile(currentAccessToken);
        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, (object, response) -> {
            try {
                String first_name = object.getString("first_name");
                String last_name = object.getString("last_name");
                String id = object.getString("id");

                profile_name.setText(first_name +" "+last_name);
                profile_pic.setVisibility(View.VISIBLE);
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.dontAnimate();
                Intent telaMenu = new Intent(ActivityLoginFacebook.this, ActivityTelaMenu.class);
                startActivity(telaMenu);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    //voltar tela login
    private void voltarlogin(){
        imgViewRodape3.setOnClickListener(v -> {
            Intent telaLogin = new Intent(getApplicationContext(), ActivityTelaLogin.class);
            startActivity(telaLogin);
        });
    }
}