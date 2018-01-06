package designersfox.k.s_rerebuild.gui.fragment;

import designersfox.k.s_rerebuild.R;
import designersfox.k.s_rerebuild.model.Scale;
import designersfox.k.s_rerebuild.technical.PianoFragPref;

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

public class Piano extends Fragment {

    public static boolean BWModeOn = false;
    public static boolean learningModeOn = true;

    GridLayout gridLayout;
    PianoListener pianoListener;
    MediaPlayer[] currentSounds;
    static Scale currentScale;
    static int currentNoteRange, currentOctaveRange;
    static PianoFragPref pianoFragPref;
    static Random random;
    static ArrayList<int[]> savedColorpacks;
    static float randomVal1, randomVal2, randomVal3;
    static Toast notePressedDisplay;

    float[] xCoord, yCoord;
    double[] sectionPressed;
    int touchSlop, screenHeight, screenWidth;

    public interface PianoListener{
        void onPianoEventDetected(double[] sectionOfTouch);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadPrefs(savedInstanceState);
        return initView(inflater, container);
    }

    private void loadPrefs(Bundle bundle){
        currentSounds = new MediaPlayer[3];
        xCoord = new float[3];
        yCoord = new float[3];
        sectionPressed = new double[3];
        pianoFragPref = PianoFragPref.getInstance();
        savedColorpacks = pianoFragPref.savedColorpacks;
        currentScale = pianoFragPref.currentScale;
        currentNoteRange = currentScale.noteRange;
        currentOctaveRange = currentScale.octaveRange;
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
                evalTouch(motionEvent);
                XYtoSynth(xCoord, yCoord);
                return true;
            }
        });
        gridLayout = view.findViewById(R.id.gridlayoutPiano);
        gridLayout.setBackgroundColor(Color.BLACK);
        touchSlop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        return view;
    }

    private void evalTouch(MotionEvent event){        //todo: duzenlenicek to set xys
        int pointerCount = event.getPointerCount();
        int actionIndex = event.getActionIndex();

        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:


                break;
            case MotionEvent.ACTION_MOVE:
               /*if(Math.abs(event.getX() - previousTouchX) > touchSlop || Math.abs(event.getY() - previousTouchY) > touchSlop){ //todo: math-physical application check


                }*/
                break;
            case MotionEvent.ACTION_POINTER_DOWN:


                break;
            case MotionEvent.ACTION_POINTER_UP:


                break;
            case MotionEvent.ACTION_UP:
                gridLayout.setBackgroundColor(Color.BLACK);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(MediaPlayer mp : currentSounds){
                            try {
                                mp.reset();
                                mp.prepare();
                                mp.stop();
                                mp.release();
                            } catch (Throwable t) {t.printStackTrace();}
                        }
                    }
                }).start();
            default:
        }

    }

    private void XYtoSynth(float[] xCoord, float[] yCoord){
        for(int i = 0; i < currentSounds.length; i++){
            if(xCoord[i] == 0 && yCoord[i] == 0){
                sectionPressed[i] = 0;
            }
            int xratio = screenWidth/currentNoteRange;
            int yratio = screenHeight/currentOctaveRange;
            for(int j = 1; j <= currentNoteRange; j++){
                for(int k = 1; k <= currentOctaveRange; k++){
                    if(xCoord[i] <= xratio*j){
                        if(yCoord[i] <= yratio*k){
                            currentSounds[i] = currentScale.sounds.get((j-1) + (k-1)*currentNoteRange);
                            if(learningModeOn){
                                String notePlayed = currentScale.notesAsText.get(i);
                                if (notePressedDisplay!= null) {notePressedDisplay.cancel();}
                                notePressedDisplay = Toast.makeText(getActivity(), notePlayed, Toast.LENGTH_SHORT);
                                notePressedDisplay.show();
                            }
                            sectionPressed[i] = j + k*0.1;
                        }
                    }
                }
            }
        }
        pianoListener.onPianoEventDetected(sectionPressed);
        evokeSound();
        evokeVisual();
    }

    private void evokeSound(){
        for(MediaPlayer sound : currentSounds){
            if(sound != null){
                try {
                    sound.prepare(); //todo: burasi error vericekmi
                    sound.start();
                } catch (Throwable t) {t.printStackTrace();}
            }
        }
    }

    private void evokeVisual(){
        int r,g,b;
        if(sectionPressed[2] == 0){
            if(sectionPressed[1] == 0){
                //singleTouchSyn(tcv1);
                if(BWModeOn){
                    r = (int) sectionPressed[0]*8;
                    g = (int) sectionPressed[0]*8;
                    b = (int) sectionPressed[0]*8;
                }else{
                    r = (int) (sectionPressed[0]/(randomVal1))*8;
                    g = (int) (sectionPressed[1]/(randomVal2))*8;
                    b = (int) (sectionPressed[2]/(randomVal3))*8;
                }
            }else{
                if(BWModeOn){
                    r = (int) (sectionPressed[0]+ sectionPressed[1])/2*10;
                    g = (int) (sectionPressed[0]+ sectionPressed[1])/2*10;
                    b = (int) (sectionPressed[0]+ sectionPressed[1])/2*10;
                }else{
                    r = (int) ((sectionPressed[0]+ sectionPressed[1])/(randomVal1))*10;
                    g = (int) ((sectionPressed[0]+ sectionPressed[1])/(randomVal2))*10;
                    b = (int) ((sectionPressed[0]+ sectionPressed[1])/(randomVal3))*10;
                }
            }
        }else{
            if(BWModeOn){
                r = (int) (sectionPressed[0]+ sectionPressed[1]+ sectionPressed[2])/3*12;
                g = (int) (sectionPressed[0]+ sectionPressed[1]+ sectionPressed[2])/3*12;
                b = (int) (sectionPressed[0]+ sectionPressed[1]+ sectionPressed[2])/3*12;
            }else{
                r = (int) ((sectionPressed[0]+ sectionPressed[1]+ sectionPressed[2])/(randomVal1))*12;
                g = (int) ((sectionPressed[0]+ sectionPressed[1]+ sectionPressed[2])/(randomVal2))*12;
                b = (int) ((sectionPressed[0]+ sectionPressed[1]+ sectionPressed[2])/(randomVal3))*12;
            }
        }
        int customColor = Color.rgb(r,g,b);
        gridLayout.setBackgroundColor(customColor);
    }

    public void evokeReplay(double[] sectionPressed){
        for(int i = 0; i < xCoord.length; i++){ //todo: section to XY here
            xCoord[i] = ((long) sectionPressed[i]);
            yCoord[i] = (float) sectionPressed[i] - xCoord[i];
            XYtoSynth(xCoord, yCoord);
        }
    }

    public static void randomizeColors(){
        random = new Random();
        randomVal1 = random.nextFloat();
        randomVal2 = random.nextFloat();
        randomVal3 = random.nextFloat();
    }

    public static void shuffleColors(){
        int i = random.nextInt(pianoFragPref.savedColorpacks.size());
        int[] colorPack = pianoFragPref.savedColorpacks.get(i);
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
