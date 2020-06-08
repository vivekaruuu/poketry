package com.example.pokedexx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pokedexx.adapters.FragmentAdapter;
import com.example.pokedexx.itemView.ItemFragment;
import com.example.pokedexx.locationView.LocationFragment;
import com.example.pokedexx.pokemonView.RecyclerFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity   {



    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actual);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager=findViewById(R.id.fragmentContainer);
        setUpViewPager(mViewPager);
        NavigationView navigationView=findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pokemonList:setViewPage(0); break;
                    case R.id.itemsList:setViewPage(1); break;
                    case R.id.locationList:setViewPage(2);break;
                }
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new RecyclerFragment()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });

        drawerLayout=findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setUpViewPager(ViewPager viewPager){
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new RecyclerFragment(),"RecyclerView");
        adapter.addFragment(new ItemFragment(),"itemFragment");
        adapter.addFragment(new LocationFragment(),"locationFragment");
        viewPager.setAdapter(adapter);
    }
    public void setViewPage(int FragmentNumber){
        mViewPager.setCurrentItem(FragmentNumber);
    }


}


