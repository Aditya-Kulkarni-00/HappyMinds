package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InstituteDashboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_dashboard);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.InstituteDashboardBottomNav);
        loadFragment(new InstituteHomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.bottomNavHome : fragment = new InstituteHomeFragment(); break;
                case R.id.bottomNavData: fragment = new InstituteDataFragment(getApplicationContext() , InstituteDashboardActivity.this);break;
                case R.id.bottomNavProfile: fragment = new InstituteProfileFragment(); break;
                case R.id.bottomNavStats: fragment = new InstituteStatsFragment();break;
                case R.id.bottomNavUpload: fragment = new InstituteUploadFragment(this);break;
            }

            if(fragment !=null){
                loadFragment(fragment);
            }

            return true;
        });
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.InstituteDashboardRelativeLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(1);
    }
}