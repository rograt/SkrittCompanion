package com.example.skrittcompanion.Model.Repositories;

import android.accounts.AuthenticatorException;
import android.app.Application;
import android.os.AsyncTask;
import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.RemoteDataHandlers.AccountHandler;
import com.example.skrittcompanion.Model.RemoteDataHandlers.DailyHandler;
import com.example.skrittcompanion.Model.RemoteDataHandlers.TradingPostHandler;
import com.example.skrittcompanion.Model.Services.AccountService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private static UserRepository repo;
    private FirebaseAuth auth;
    private DatabaseReference fireDB;
    private AccountService service;
    private String authKey;
    private AsyncTask<Void,Void,Void> authTask;
    private AccountHandler remoteData;

    private UserRepository(Application application) {
        FirebaseApp.initializeApp(application);
        auth=FirebaseAuth.getInstance();
        fireDB= FirebaseDatabase.getInstance().getReference();
        remoteData=new AccountHandler();
    }

    public static UserRepository getInstance(Application application){
        if(repo!=null){
            return repo;
        }
        return repo=new UserRepository(application);
    }

    public Task<AuthResult> signUp(String email, String password){
        return  auth.createUserWithEmailAndPassword(email, password);
    }

    public void saveAuthKey(String authKey){
        fireDB.child("Users").child(auth.getCurrentUser().getUid()).setValue(authKey);
    }

    public Task<AuthResult> login(String email, String password){
        return  auth.signInWithEmailAndPassword(email, password);
    }

    public void logout(){
        auth.signOut();
    }

    public void pullAccountInfo() throws AuthenticatorException {

        if(auth.getCurrentUser()!=null){
            new LoginToFireBase().execute();

        } else throw new AuthenticatorException("AUTHENTICATION_REQUIRED");


    }

    private class LoginToFireBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            String key=fireDB.child("Users").child(auth.getCurrentUser().getUid()).getKey();
            DatabaseReference apiRef=fireDB.getDatabase().getReference("Users/"+key);

            apiRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    authKey=dataSnapshot.getValue(String.class);
                    remoteData.getAccountInfo(authKey);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

        });
            return null;
        }
    }

    public MutableLiveData<Integer> verifyKey(String authKey){
        return remoteData.validateToken(authKey);
    }

}
