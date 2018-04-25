package brunel.csgroup18.squadlink;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    static String value="",value1="",id="";//Variable Description
    static int indexx=0;//Variable Description
    static  String item="";//Variable Description
    static String startdate="", enddate="", name="";//Variable Description
    private ProgressDialog progress;//ProgressDialog for Async Task (Background Network related Processes)
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    static ListView lv;
    TextView nav;
    static String[] sessions;
    static  String result="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Using our own action bar as default was disabled when creating nav drawer
        mToolbar = (Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.closed);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Starts the overview fragment as default(overview fragment is at index 0 of menu list)
        onNavigationItemSelected(navigationView.getMenu().getItem(0));


        lv = (ListView) findViewById(R.id.listview);//Initialising ListView
        try {
            lv.setAdapter(SignInActivity.adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    item = ((TextView) view).getText().toString();
                    String[] result = item.split("\n", 3); // Delimit the string upto 2nd line
                    result[0]=result[0].substring(result[0].indexOf(":")+1, result[0].length());//get first line
                    result[0]=result[0].trim();//trim the first line

                    result[1]=result[1].substring(result[1].indexOf(":")+1, result[1].length());//get first line
                    result[1]=result[1].trim();//trim the first line

                    result[2]=result[2].substring(result[2].indexOf(":")+1, result[2].length());//get first line
                    result[2]=result[2].trim();//trim the first line

                    name=result[0]+"";//store it in name
                    startdate=result[1]+"";
                    enddate=result[2]+"";
                    indexx = position;//store the position in indexx


                    try {
                        FirebaseDatabase database_b1 = FirebaseDatabase.getInstance();
                        String a=SignInActivity.UserEmail.getText()+"";
                        String usernamee=SignInActivity.getUserName(a+"");
                        final DatabaseReference myRefb1 = database_b1.getReference("Meals").child(usernamee+"").child(result[0]+"");

                        myRefb1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try {
                                    value="";
                                    value = dataSnapshot.getValue().toString();
                                    System.out.println("************************************/////***"+value+"");
                                } catch (Exception dfd) {
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });
                    } catch (Exception df) {}



                }
            });
        }catch (Exception ds){}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Handles the event of when someone clicks a menu item. Starts the relevant fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()){

            case(R.id.nav_overview):{
                OverviewFragment overviewFragment = new OverviewFragment();
                ft.replace(R.id.fragment_container, overviewFragment);
                ft.commit();
                mToolbar.setTitle("Overview");
            }
            break;
            case(R.id.nav_my_matches):{
                MatchesFragment matchesFragment = new MatchesFragment();
                ft.replace(R.id.fragment_container, matchesFragment);
                ft.commit();
                mToolbar.setTitle("My Matches");
            }

            break;
            case(R.id.nav_training_plans):{
               Intent a=new Intent(this, TrainingPlansActivity.class);
               startActivity(a);
            }

            break;
            case(R.id.nav_nutrition_plans):{
                Intent a=new Intent(this, WorkoutActivity.class);
                startActivity(a);
            }

            break;
            case(R.id.nav_profile):{
                Intent a=new Intent(this,ProfileActivity.class);
                startActivity(a);
            }
            break;
            case(R.id.nav_events_finder):{
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }

            break;
            case(R.id.nav_settings):{
                SettingsFragment settingsFragment = new SettingsFragment();
                ft.replace(R.id.fragment_container, settingsFragment);
                ft.commit();
                mToolbar.setTitle("Settings");
            }

            break;

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
