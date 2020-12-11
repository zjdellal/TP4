package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp4.data.User;
import com.example.tp4.data.UserDataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnConnexion;
    private Button btnInscription;
    private UserDataSource dataSource;
    boolean isPassValid;
    boolean isEmailValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = new UserDataSource(this);
        dataSource.open();
        initVariable();
        setListener();
    }

    private void initVariable() {
        this.txtEmail = findViewById(R.id.txtEmail);
        this.txtPassword = findViewById(R.id.txtPassword);
        this.btnConnexion = findViewById(R.id.btnConnexion);
        this.btnConnexion.setEnabled(false);
        this.btnInscription = findViewById(R.id.btnInscription);
    }
    private void setListener(){
        this.btnConnexion.setOnClickListener(onBtnConnexionClicked());
        this.btnInscription.setOnClickListener(onBtnInscriptionClicked());

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
                btnConnexion.setEnabled(isEmailValid && isPassValid);
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
                    if(txtPassword.getText().toString().matches("[0-9a-zA-Z]{5,}")){
                        isPassValid = true;

                    }else{
                        txtPassword.setError("Mot de passe trop cout (longueur min 5)");
                        isPassValid = false;
                    }

                }else{
                    isPassValid = false;
                    txtPassword.setError("Mot de passe ne doit pas être vide!");
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                btnConnexion.setEnabled(isEmailValid && isPassValid);
            }
        });
    }

    private View.OnClickListener onBtnConnexionClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean exist = false;
                if(!txtEmail.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()){
                    List<User> users = dataSource.getAllUsers();
                    for (User u: users){
                        if(u.getEmail().equals(txtEmail.getText().toString())&& u.getPassword().equals(txtPassword.getText().toString())){
                            exist = true;
                            Toast.makeText (getApplicationContext (), "Connecté !"  , Toast.LENGTH_SHORT) .show ();
                            break;
                        }
                    }
                }

            }
        };
    }

    private View.OnClickListener onBtnInscriptionClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  =  new Intent(getApplicationContext(), InscriptionActivity.class);
                startActivity(intent);
            }
        };
    }


}