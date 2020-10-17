package com.example.hack;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /**/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.domestic_list)
                    startActivity(new Intent(getApplicationContext(), ComplaintList.class));
                else if(id == R.id.student_list)
                    startActivity(new Intent(getApplicationContext(), StudentComplaintList.class));
                else if(id == R.id.workplace_list)
                    startActivity(new Intent(getApplicationContext(), WorkplaceComplaintList.class));
                else if(id == R.id.nav_home)
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                else if(id == R.id.nav_domestic)
                    startActivity(new Intent(getApplicationContext(), DomesticComplaint.class));
                else if(id == R.id.nav_student)
                    startActivity(new Intent(getApplicationContext(), StudentComplaint.class));
                else if(id == R.id.nav_corporate)
                    startActivity(new Intent(getApplicationContext(), WorkplaceComplaint.class));
                else if(id ==R.id.nav_logout)
                    logout();
                else if(id == R.id.nav_about)
                    startActivity(new Intent(getApplicationContext(), About.class));
                return true;
            }

            private void logout() {
                firebaseAuth.signOut(); //sign out of the current account and move to login page
                startActivity(new Intent(getApplicationContext(), LoginPage.class));
            }
        });
        displaySelectedScreen(R.id.nav_home);
    }
    /*private void hideItem()
    {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.domestic_list).setVisible(false);
        nav_Menu.findItem(R.id.student_list).setVisible(false);
        nav_Menu.findItem(R.id.workplace_list).setVisible(false);
    }*/

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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
        displaySelectedScreen(item.getItemId());
        return true;

    }
    public void displaySelectedScreen(int itemId)
    {
        Fragment fragment=null;
        switch(itemId)
        {
            case R.id.nav_home:
                fragment=new Home();
                break;
            case R.id.nav_corporate:
                fragment=new Corporate();
                break;
            case R.id.nav_student:
                fragment=new Student();
                break;
            case R.id.nav_domestic:
                fragment=new Domestic();
                break;
            case R.id.student_list:
                fragment = new StudentFragment();
                break;
            case R.id.domestic_list:
                fragment = new DomesticFragment();
                break;
        }

        if(fragment!=null)
        {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
