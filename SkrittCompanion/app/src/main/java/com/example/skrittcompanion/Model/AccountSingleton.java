package com.example.skrittcompanion.Model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class AccountSingleton {

    private static MutableLiveData<Account> account;


    public static MutableLiveData<Account> getInstance() {
        if(account==null){
            return account=new MutableLiveData<>();
        }
        return account;
    }

    public static void CreateAccount(Account accountToSend){
        if(account.getValue()==null){
            account.postValue(accountToSend);
        }
        else throw new IllegalAccessError();
    }

    public static void DestroyAccount(){
        account.postValue(null);
    }

}
