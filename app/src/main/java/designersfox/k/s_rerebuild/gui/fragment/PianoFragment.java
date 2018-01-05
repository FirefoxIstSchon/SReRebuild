package designersfox.k.s_rerebuild.gui.fragment;

import designersfox.k.s_rerebuild.R;
import designersfox.k.s_rerebuild.model.Scale;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Random;

public class PianoFragment extends Fragment {

    public static Scale currentScale;
    GridLayout gridLayout;
    static Toast toast;
    static MediaPlayer currentSound1;
    static MediaPlayer currentSound2;
    static MediaPlayer currentSound3;
    static Random random;
    static boolean learningModeOn = true;
    static boolean randomized = false;
    static boolean BWModeOn = false;
    float x1, y1, x2, y2, x3, y3;
    double touchCoordinateValue1;
    double touchCoordinateValue2;
    double touchCoordinateValue3;
    static float randomValue1;
    static float randomValue2;
    static float randomValue3;
    final int screenHeight
            = Resources.getSystem().getDisplayMetrics().heightPixels;
    final int screenWidth
            = Resources.getSystem().getDisplayMetrics().widthPixels;

    PianoListener pianoListener;

    public interface PianoListener{
        void onPianoEventDetected(double section1, double section2, double section3);
        //todo: activity bu bilgi ile save edicek
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            pianoListener = (PianoListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    //todo : replay piano'ya s1 s2 s3 gonderip ses cikartabilsin

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piano,container, false);
        gridLayout = view.findViewById(R.id.gridLayoutFragment);
        gridLayout.setBackgroundColor(Color.BLACK);
        if(!randomized){randomizeColors();}
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                //onTouchEvent(motionEvent); //todo: 1-evalTouch vs gibi bisey 1,2,3 dondursun
                //todo; 2-evokeSynth(1,2,3) gibi bisey etkisini gostersin
                // ki replay de bu sekilde bunu kullanir





                return true;
            }
        });
        return view;
    }

    public static void randomizeColors(){
        random = new Random();
        randomValue1 = random.nextFloat();
        randomValue2 = random.nextFloat();
        randomValue3 = random.nextFloat();
        randomized = true;
    }

    /*public static void shuffleSavedColors(){ ////todo: config olayina el at
        int i = random.nextInt(ConfigObject.myConfigs.size());
        randomValue1 = ConfigObject.myConfigs.get(i).getRandomValue1();
        randomValue2 = ConfigObject.myConfigs.get(i).getRandomValue2();
        randomValue3 = ConfigObject.myConfigs.get(i).getRandomValue3();
    }*/





}
