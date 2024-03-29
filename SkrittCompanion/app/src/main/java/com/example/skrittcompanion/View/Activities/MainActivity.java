package com.example.skrittcompanion.View.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.skrittcompanion.View.Fragments.DailiesTabbedFragment;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.View.Fragments.BossFragment;
import com.example.skrittcompanion.View.Fragments.OverviewFragment;
import com.example.skrittcompanion.View.Fragments.TradingPostFragment;
import com.example.skrittcompanion.View.Fragments.WalletFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.skrittcompanion.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String PREF_NAME="settings_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.overview);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new OverviewFragment()).commit();
        TextView accountName=findViewById(R.id.accountName);

        try{
            accountName.setText(AccountSingleton.getInstance().getValue().getName());
        }catch (NullPointerException e){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,SignUpActivity.class));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            AccountSingleton.DestroyAccount();
            startActivity(new Intent(this,SignUpActivity.class));
        }
        else if(id==R.id.settings){
            startActivity(new Intent(this,SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //FIXME replace if with switch
        if (id == R.id.boss_timer) {
            setTitle("World Boss Timer");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new BossFragment()).commit();
        }
        else if (id==R.id.wallet){
            setTitle("Wallet");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new WalletFragment()).commit();
        }
        else if (id==R.id.daily_menu_item){
            setTitle("Dailies");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new DailiesTabbedFragment()).commit();
        }
        else if(id==R.id.trading_post_sell_menu_item){
            setTitle("Trading Post Sell");
            Bundle bundle=new Bundle();
            bundle.putBoolean("saleStat",true );
            TradingPostFragment fragment=new TradingPostFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }
        else if(id==R.id.trading_post_buy_menu_item){
            setTitle("Trading Post Buy");
            Bundle bundle=new Bundle();
            bundle.putBoolean("saleStat",false);
            TradingPostFragment fragment=new TradingPostFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }
        else if(id==R.id.overview){
            setTitle("Overview");
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new OverviewFragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
