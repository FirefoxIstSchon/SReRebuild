package designersfox.k.s_rerebuild.gui.activity;

import designersfox.k.s_rerebuild.R;
import designersfox.k.s_rerebuild.factory.ScaleFactory;
import designersfox.k.s_rerebuild.gui.fragment.Piano;
import designersfox.k.s_rerebuild.gui.fragment.Preference;
import designersfox.k.s_rerebuild.technical.PianoFragPref;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CreativeActivity extends Activity implements Piano.PianoListener {

    FrameLayout frameLayout;
    Button buttonStartStop;
    Button buttonLearningModeOn;
    Button buttonMyRecordings;
    Button buttonReRandomizeColors;
    Button buttonILikeThisConfig;
    Button buttonMyConfigs;
    Button buttonToggleGrayScale;
    LinearLayout drawer;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    private static ScaleFactory scaleFactory;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative);
        referenceDrawerViews();
        setDrawerViews();
        initPianoFragmentPrefs();
        callPianoFragment();
        setButtonView();
    }

    @Override
    public void onPianoEventDetected(double[] sectionOfTouch) {
        //todo: objeye save etme isi
    }

    private void callPianoFragment() {
        Fragment fragment = new Piano();
        frameLayout = findViewById(R.id.frameLayout);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment, "visible_fragment");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void initPianoFragmentPrefs(){
        if(PianoFragPref.currentScale == null){
            scaleFactory = ScaleFactory.getInstance();
            PianoFragPref.currentScale = scaleFactory.getScale(2,1,2,this);
            //default scale: d-dorian 2 octaves
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void referenceDrawerViews(){
        drawer = findViewById(R.id.drawer);
        drawerLayout = findViewById(R.id.drawerLayout);
    }

    private void setDrawerViews(){
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getActionBar().hide();
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("Creative Activity");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }else{
            switch (item.getItemId()){
                case R.id.action_settings:
                    //startActivity(new Intent(this, SettingsActivity.class)); //todo: bu menu itemini profille degistir, yana bi kac sey vs
                    break;
                case R.id.action_changecolor:
                    Piano.shuffleColors();
                    break;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setButtonView(){
        referenceButtonViews();

        if(isRecording){
            buttonStartStop.setText(R.string.stopRecord);
        }else{
            buttonStartStop.setText(R.string.startRecord);
        }

        setButtonViewOnClickListeners();
    }

    private void referenceButtonViews(){
        buttonStartStop = findViewById(R.id.buttonRecord);
        buttonLearningModeOn = findViewById(R.id.buttonLearningModeOn);
        buttonMyRecordings = findViewById(R.id.buttonMyRecordings);
        buttonReRandomizeColors = findViewById(R.id.buttonMyProfile);
        buttonILikeThisConfig = findViewById(R.id.buttonILikeThisConfig);
        buttonMyConfigs = findViewById(R.id.buttonMyConfigs);
        buttonToggleGrayScale = findViewById(R.id.buttonToggleGrayScale);
    }

    private void setButtonViewOnClickListeners(){

        buttonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRecording){ // stop recording
                    drawerLayout.closeDrawer(drawer);

                     //todo:

                    isRecording= false;
                    buttonStartStop.setText(R.string.startRecord);

                }else{ // start recording
                    drawerLayout.closeDrawer(drawer);

                     //todo:

                    isRecording= true;
                    buttonStartStop.setText(R.string.stopRecord);
                }
            }
        });

        buttonLearningModeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
                Piano.learningModeOn = !Piano.learningModeOn;
                callPianoFragment(); //todo: is dis necessary?
            }});

        buttonMyRecordings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //fragmentTransaction.replace(R.id.frameLayout, new ()); //todo: replay yerine hangi fragment
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }});

        buttonReRandomizeColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
                Piano.randomizeColors();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Piano());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }});

        buttonILikeThisConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
                //todo: save config here to prefs.
                Toast.makeText(CreativeActivity.this, "Config Saved", Toast.LENGTH_SHORT).show();

            }});

        buttonMyConfigs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Preference());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }});

        buttonToggleGrayScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
                Toast.makeText(CreativeActivity.this, "GrayScale toggled.", Toast.LENGTH_SHORT).show();
                Piano.BWModeOn = !Piano.BWModeOn;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Piano());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(this, MainActivity.class)); //todo: backpresste nereye don
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawer);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


}
