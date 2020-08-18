package com.example.pokedexx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pokedexx.adapters.FragmentAdapter;
import com.example.pokedexx.itemView.ItemFragment;
import com.example.pokedexx.locationView.LocationFragment;
import com.example.pokedexx.locationView.LocationPokeFragment;
import com.example.pokedexx.pokemonView.RecyclerFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity   {



    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    public ViewPager mViewPager;
    public LocationPokeFragment locationPokeFragment;
    LocationFragment locationFragment;
    public bioFragment bioFragment;

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
                    case R.id.pokemonList: mViewPager.getAdapter().notifyDataSetChanged();setViewPage(0); break;
                    case R.id.itemsList: mViewPager.getAdapter().notifyDataSetChanged();setViewPage(2); break;
                    case R.id.locationList: {
                        Bundle bundle = new Bundle();
                        bundle.putString("selected", "1");
                        locationFragment.setArguments(bundle);
                        mViewPager.getAdapter().notifyDataSetChanged();
                        setViewPage(3);break;
                    }
                    case R.id.regionList:{
                        Bundle bundle = new Bundle();
                        bundle.putString("selected", "2");
                        locationFragment.setArguments(bundle);
                        mViewPager.getAdapter().notifyDataSetChanged();

                        setViewPage(3);break;
                    }
                    case R.id.typeList: {
                        Bundle bundle = new Bundle();
                        bundle.putString("selected", "3");
                        locationFragment.setArguments(bundle);
                        mViewPager.getAdapter().notifyDataSetChanged();
                        setViewPage(3);

                        break;
                    }
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
        locationFragment=new LocationFragment();
        bioFragment=new bioFragment();
        locationPokeFragment=new LocationPokeFragment();
        adapter.addFragment(new RecyclerFragment(),"RecyclerView");
        adapter.addFragment( bioFragment,"poke");
        adapter.addFragment(new ItemFragment(),"itemFragment");
        adapter.addFragment(locationFragment,"locationFragment");
        adapter.addFragment(locationPokeFragment,"pokemon");
        viewPager.setAdapter(adapter);
    }

    public void setViewPage(int FragmentNumber){

        mViewPager.setCurrentItem(FragmentNumber);
    }



}


