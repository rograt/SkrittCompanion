package com.example.skrittcompanion.ViewModel;

import android.accounts.AuthenticatorException;
import android.app.Application;
import android.os.AsyncTask;

import com.example.skrittcompanion.Model.Repositories.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class SignUpViewModel extends AndroidViewModel {
    private UserRepository repository;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        repository=UserRepository.getInstance(application);
    }

    public Task<AuthResult> register(String email, String password){
       return repository.signUp(email, password);
    }

    public Task<AuthResult> login(String email, String password){
        return repository.login(email, password);
    }

    public void saveAuthKey(String authKey){
        repository.saveAuthKey(authKey);
    }

    public void pullAccountInfo() throws AuthenticatorException {
        repository.pullAccountInfo();
    }



}
