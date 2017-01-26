package com.cyberlex404.maxbusapp1;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.cyberlex404.maxbusapp1.api.UmoriliApi;
import com.cyberlex404.maxbusapp1.fragments.BusFragment;
import com.cyberlex404.maxbusapp1.fragments.FligthsFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BusFragment busFragment;
    FligthsFragment fligthsFragment;
    List<TicketsModel> tickets;
    List<PostModel> post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getTiketsByFligth();
                getPost();
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        busFragment = new BusFragment();
        fligthsFragment = new FligthsFragment();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (id == R.id.nav_flights) {

            fragmentTransaction.replace(R.id.content_drawer, fligthsFragment);

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onActionSettings(MenuItem item) {
        Intent intent  = new Intent(this, SettingsActivity.class);

        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    private void getTiketsByFligth() {
        int id = 6;
        tickets = new ArrayList<>();
        HttpApiClient.getApi().getTickets(id).enqueue(new Callback<List<TicketsModel>>() {
            @Override
            public void onResponse(Call<List<TicketsModel>> call, Response<List<TicketsModel>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                if(response.body() != null){
                    tickets.addAll(response.body());
                    String p = tickets.get(1).getTelephone();
                    Toast.makeText(DrawerActivity.this, p, Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<List<TicketsModel>> call, Throwable t) {
                Toast.makeText(DrawerActivity.this, "Ошибка TicketsModel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPost() {
        post = new ArrayList<>();
        //Response response = HttpApiClient.getApi().getData("bash", 50).execute();
        UmoriliApi service = HttpApiClient.getExApi();
        Call<List<PostModel>> call = service.getData("bash", 50);

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                post.addAll(response.body());
                String p = post.get(1).getLink();
                Toast.makeText(DrawerActivity.this, p, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(DrawerActivity.this, "Ошибка Post", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
