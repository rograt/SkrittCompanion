package com.example.skrittcompanion.View.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.accounts.AuthenticatorException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.Transaction;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.View.Fragments.LoginFragment;
import com.example.skrittcompanion.View.Fragments.RegisterFragment;
import com.example.skrittcompanion.ViewModel.SignUpViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {


    private SignUpViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportFragmentManager().beginTransaction().replace(R.id.startActivity, new LoginFragment()).commit();
        vm = ViewModelProviders.of(this).get(SignUpViewModel.class);
    }

    public void onLoginClicked(View v){

        EditText emailField=findViewById(R.id.regEmailField);
        EditText passwordField=findViewById(R.id.regPassField);
        final ConstraintLayout uiLayout = findViewById(R.id.uiLayout);
        if(!emailField.getText().toString().equals("") && !passwordField.getText().toString().equals("")){
            findViewById(R.id.regStatus).setVisibility(View.VISIBLE);
            uiLayout.setForeground(new ColorDrawable(0xCCFFFFFF));
            vm.login(emailField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (task.isSuccessful()) {
                        try {
                            vm.pullAccountInfo();
                            AccountSingleton.getInstance().observe(SignUpActivity.this, new Observer<Account>() {
                                @Override
                                public void onChanged(@Nullable final Account account) {
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            });
                        } catch (AuthenticatorException e) {
                            FirebaseAuth.getInstance().signOut();
                        }
                    } else{
                        uiLayout.setForeground(null);
                        findViewById(R.id.regStatus).setVisibility(View.INVISIBLE);
                        Toast.makeText(SignUpActivity.this, "Wrong login information.", Toast.LENGTH_SHORT).show();
                    }
                }});
        } else Toast.makeText(SignUpActivity.this,"Empty fields. Please put it relevant information." , Toast.LENGTH_LONG).show();
        }

    public void onRegisterClicked(View v){
        findViewById(R.id.regStatus).setVisibility(View.VISIBLE);
        EditText emailField=findViewById(R.id.regEmailField);
        EditText passwordField=findViewById(R.id.regPassField);
        final EditText authKeyField=findViewById(R.id.authField);
        if(!emailField.getText().toString().equals("") && !passwordField.getText().toString().equals("")){
            findViewById(R.id.regStatus).setVisibility(View.VISIBLE);
            final ConstraintLayout uiLayout = findViewById(R.id.uiLayout);
            uiLayout.setForeground(new ColorDrawable(0xCCFFFFFF));
            vm.register(emailField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    vm.saveAuthKey(authKeyField.getText().toString().trim());
                    try {
                        vm.pullAccountInfo();
                        AccountSingleton.getInstance().observe(SignUpActivity.this, new Observer<Account>() {
                            @Override
                            public void onChanged(@Nullable final Account account) {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                                findViewById(R.id.regStatus).setVisibility(View.INVISIBLE);
                                uiLayout.setForeground(null);
                            }
                        });
                    } catch (AuthenticatorException e) {
                        e.printStackTrace();
                    }
                    findViewById(R.id.regStatus).setVisibility(View.INVISIBLE);
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                } else Toast.makeText(SignUpActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
            }});
        } else Toast.makeText(SignUpActivity.this,"Empty fields. Please put it relevant information." , Toast.LENGTH_LONG).show();
    }

    public void onSwitchToRegClicked(View v){
        getSupportFragmentManager().beginTransaction().replace(R.id.startActivity, new RegisterFragment()).commit();

    }

}
