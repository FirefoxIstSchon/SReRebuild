package designersfox.k.s_rerebuild.gui.fragment;

import designersfox.k.s_rerebuild.R;
import designersfox.k.s_rerebuild.model.Scale;
import designersfox.k.s_rerebuild.technical.PianoPref;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PianoFragment extends Fragment {

    public static boolean BWModeOn = false;
    public static boolean learningModeOn = true;

    GridLayout gridLayout;
    PianoListener pianoListener;
    MediaPlayer currentSound1, currentSound2, currentSound3;
    static Scale currentScale;
    static PianoPref pianoPref;
    static Random random;
    static ArrayList<int[]> savedColorpacks;
    static float randomVal1, randomVal2, randomVal3;
    static Toast dispNotePressed;

    float x1, y1, x2, y2, x3, y3;
    double sectionPtr1, sectionPtr2, sectionPtr3;

    final int touchSlop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();
    final int screenHeight
            = Resources.getSystem().getDisplayMetrics().heightPixels;
    final int screenWidth
            = Resources.getSystem().getDisplayMetrics().widthPixels;

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
        loadPrefs(savedInstanceState);
        return initView(inflater, container);
    }

    private void loadPrefs(Bundle bundle){
        pianoPref = PianoPref.getInstance();
        currentScale = pianoPref.currentScale;
        if(bundle == null){
            randomizeColors();
        }else{
            randomVal1 = bundle.getInt("rv1");
            randomVal2 = bundle.getInt("rv2");
            randomVal3 = bundle.getInt("rv3");
        }
    }

    private View initView(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_piano,container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                //onTouchEvent(motionEvent); //todo: 1-evalTouch vs gibi bisey 1,2,3 dondursun
                //todo; 2-evokeSynth(1,2,3) gibi bisey etkisini gostersin
                // ki replay de bu sekilde bunu kullanir

                evalTouch();
                evokeSynth();











                return true;
            }
        });
        gridLayout = view.findViewById(R.id.gridlayoutPiano);
        gridLayout.setBackgroundColor(Color.BLACK);
        return view;
    }

    private void evalTouch(){
        //todo
    }

    private void evokeSynth(){
        //todo
    }

    public static void randomizeColors(){
        random = new Random();
        randomVal1 = random.nextFloat();
        randomVal2 = random.nextFloat();
        randomVal3 = random.nextFloat();
    }

    public static void shuffleColors(){
        int i = random.nextInt(pianoPref.savedColorpacks.size());
        int[] colorPack = pianoPref.savedColorpacks.get(i);
        randomVal1 = colorPack[0];
        randomVal2 = colorPack[1];
        randomVal3 = colorPack[2];
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //todo: put rv1 2 3 to bundle
    }
}
