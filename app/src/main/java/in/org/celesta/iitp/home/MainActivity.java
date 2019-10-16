package in.org.celesta.iitp.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.events.EventDetailsFragment;
import in.org.celesta.iitp.events.EventsRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements EventsRecyclerAdapter.OnEventSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.background_image_2);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_events_cat, R.id.nav_ongoing, R.id.nav_pronite, R.id.nav_workshops,
                R.id.nav_lectures, R.id.nav_gallery, R.id.nav_team, R.id.nav_sponsors,
                R.id.nav_maps, R.id.nav_about, R.id.nav_login, R.id.nav_account)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.background_animation);

//        getWindow().setBackgroundDrawable(drawable);
//        drawable.start();

    }

    private void handleIntent(Intent appLinkIntent) {
        String appLinkAction = appLinkIntent.getAction();
        String appLinkData = appLinkIntent.getDataString();

        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {

            if (appLinkData.contains("/notification/")) {
                String time = appLinkData.substring(appLinkData.lastIndexOf("/") + 1);

                Bundle bundle = new Bundle();
                bundle.putString("time", time);


            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
    public void onEventSelected(String id, int[] color) {
        Bundle b = new Bundle();
        b.putString("data", id);
        b.putIntArray("color", color);

        EventDetailsFragment fragment = new EventDetailsFragment();
        fragment.setArguments(b);
        fragment.setRetainInstance(true);
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }
}
