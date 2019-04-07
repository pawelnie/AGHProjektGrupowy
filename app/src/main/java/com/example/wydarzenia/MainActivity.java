package com.example.wydarzenia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wydarzenia.adapter.RetrofitRecyclerAdapterForMainActivity;
import com.example.wydarzenia.model.Event;
import com.example.wydarzenia.model.User;
import com.example.wydarzenia.network.GetDataService;
import com.example.wydarzenia.network.RetrofitClientInstance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RetrofitRecyclerAdapterForMainActivity adapter;
    private RecyclerView recyclerView;
    private TextView navEmail, navUsername;
    private FirebaseAuth mAuth;
    private String userFireID;
    private final String FCM = "FCM";

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int userID;
    /**To be used to store all user data that is already available via getUserID method**/
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your text", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**Get Firebase ID**/
        mAuth = FirebaseAuth.getInstance();
        userFireID = mAuth.getInstance().getCurrentUser().getUid();

        /**This retrofit method  requests User data including his id.
         * Then calls another retrofit method providing it with user.id to. Second method pulls all users events**/
        getUserID();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        /**Get Device ID**/
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(FCM, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = "Token: " + token;
                        Log.d(FCM, "onComplete: " + msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void getUserID() {

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service1 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        /*Getting all User data*/
        Call<List<User>> userCall = service1.getUser(userFireID);
        userCall.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> userCall, Response<List<User>> response1) {
                user = response1.body().get(0);
                setUserID(response1.body().get(0).getId());
                getUsersEvents(userID);
                setNav();

            }
            @Override
            public void onFailure(Call<List<User>> userCall, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(MainActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });

    }
    public void getUsersEvents(int userID) {
        GetDataService service2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Event>> call = service2.getUserEvents(userID);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response2) {
                generateDataList(response2.body());
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(MainActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }



    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Event> eventList) {
        recyclerView = findViewById(R.id.recycler_events);
        adapter = new RetrofitRecyclerAdapterForMainActivity(this, eventList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
//        return true;
//    }

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

        if (id == R.id.nav_main_activity) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_all_events) {
            Intent intent = new Intent(getApplicationContext(), AllEventsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_map) {
            //unused menu item

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
            //intent.putExtra("User", user);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNav(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.navUsernameTV);
        navEmail = headerView.findViewById(R.id.navEmailTV);

        navUsername.setText(user.getUsername());
        navEmail.setText(user.getEmail());
    }

}
