package com.example.skrittcompanion.View.Activities;

import android.accounts.AuthenticatorException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.ViewModel.SignUpViewModel;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SignUpViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProviders.of(this).get(SignUpViewModel.class);
        setContentView(R.layout.activity_settings);
        prefs=getSharedPreferences(MainActivity.PREF_NAME,MODE_PRIVATE);
        ((Switch)findViewById(R.id.dataSaverStatus)).setChecked(prefs.getBoolean("dataSaver",false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.done) {
            startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEnableDataSaver(View view){
        SharedPreferences.Editor editor=getSharedPreferences(MainActivity.PREF_NAME,MODE_PRIVATE).edit();
        editor.putBoolean("dataSaver",((Switch)findViewById(R.id.dataSaverStatus)).isChecked());
        editor.apply();
    }

    public void onSaveKeyClicked(View view){
        final EditText authKeyField = findViewById(R.id.newApiField);
        final String oldAuthKey=AccountSingleton.getInstance().getValue().getProvidedAPIKey();
        final ConstraintLayout uiLayout=findViewById(R.id.settingsUILayout);
        uiLayout.setForeground(new ColorDrawable(0xCCFFFFFF));
        findViewById(R.id.verificationBar).setVisibility(View.VISIBLE);
        vm.verifyAuth(authKeyField.getText().toString().trim()).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer validityStatusCode) {
                if (validityStatusCode == 200) {
                    try {
                        // move to VM?
                        AccountSingleton.DestroyAccount();
                        vm.saveAuthKey(authKeyField.getText().toString().trim());
                        vm.pullAccountInfo();
                    } catch (AuthenticatorException e) {
                        e.printStackTrace();
                    }
                        AccountSingleton.getInstance().observe(SettingsActivity.this, new Observer<Account>() {
                        @Override
                        public void onChanged(@Nullable final Account account) {
                            if(account!=null&&account.getName()!=null&& !account.getProvidedAPIKey().equals(oldAuthKey)){
                                uiLayout.setForeground(null);
                                findViewById(R.id.verificationBar).setVisibility(View.GONE);
                            }
                        }
                    });
                } else if (validityStatusCode == 400) {
                    uiLayout.setForeground(null);
                    findViewById(R.id.verificationBar).setVisibility(View.GONE);
                    Toast.makeText(SettingsActivity.this, "Incorrect API Key, make sure that all 10 permissions are enabled and try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
