package com.example.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.tp4.data.User;
import com.example.tp4.data.UserDataSource;

import java.util.List;

public class InscriptionActivity  extends AppCompatActivity {
    private EditText txtLogin, txtNomComplet, txtEmail, txtPassword, txtAdresse, txtTelephone ;
    private Button btn_inscrire;
    private UserDataSource datasource;
    boolean isEmailValid, isPassValid, isNomValid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.datasource =  new UserDataSource(this);
        datasource.open();
        initVariables();
        setListeners();
    }

    private void initVariables(){
        this.txtNomComplet = findViewById(R.id.txtNomComplet);
        this.txtLogin = findViewById(R.id.txtLogin);
        this.txtEmail = findViewById(R.id.txtEmail);
        this.txtPassword =  findViewById(R.id.txtMotDePasse);
        this.txtAdresse = findViewById(R.id.txtAdresse);
        this.txtTelephone = findViewById(R.id.txtTelephone);
        this.btn_inscrire = findViewById(R.id.btn_inscrire);
        btn_inscrire.setEnabled(false);
    }

    private void setListeners(){
        //listener button inscription
        this.btn_inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtLogin.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()){
                    User user =  new User();
                    user.setNomComplet(txtNomComplet.getText().toString());
                    user.setLogin(txtLogin.getText().toString());
                    user.setEmail(txtEmail.getText().toString());
                    user.setPassword(txtPassword.getText().toString());
                    user.setAdresse(txtAdresse.getText().toString());
                    user.setTelephone(txtTelephone.getText().toString());
                    //lancement de requete vers la bd afin d'enregistrer le user
                    datasource.createUser(user);
                    List<User> users =  datasource.getAllUsers();
                    Toast.makeText (getApplicationContext (), "utilisateur enregitré: total"+ users.size()  , Toast.LENGTH_SHORT) .show ();
                    Intent intent  =  new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        //validation email
        this.txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("char", charSequence.toString());

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!txtEmail.getText().toString().isEmpty()){
                    if(txtEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){

                        isEmailValid = true;
                    }else{
                        isEmailValid = false;
                        txtEmail.setError("Email non valid");

                    }
                }else{
                    isEmailValid = false;
                    txtEmail.setError("Email ne doit pas être vide!");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("editable", editable.toString());
                btn_inscrire.setEnabled(isEmailValid && isPassValid && isNomValid);
            }
        });
        //validation password
        this.txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!txtPassword.getText().toString().isEmpty()){
                    if(txtPassword.getText().toString().matches("[0-9a-zA-Z]{6,}")){
                        isPassValid = true;

                    }else{
                        txtPassword.setError("Mot de passe trop cout (longueur min 5) et doit contenit mn, maj, au moins un chiffre");
                        isPassValid = false;
                    }

                }else{
                    isPassValid = false;
                    txtPassword.setError("Mot de passe ne doit pas être vide!");
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                btn_inscrire.setEnabled(isEmailValid && isPassValid && isNomValid);
            }
        });

        //validation nom
        this.txtNomComplet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtNomComplet.getText().toString().matches("[a-z a-z]{5,}")){
                    isNomValid = true;
                }else{
                    txtNomComplet.setError("Nom trop court ou invalid(min 5 charectères alphabétique)");
                    isNomValid = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            btn_inscrire.setEnabled(isNomValid && isEmailValid && isPassValid);
            }
        });
    }
}
