package designersfox.k.s_rerebuild.technical;

import android.util.Log;

import java.util.ArrayList;

public class ScaleBuilder {

    static volatile ScaleBuilder scaleBuilder;

    private ScaleBuilder(){}

    public static ScaleBuilder getInstance(){
        if(scaleBuilder == null){
            synchronized (ScaleBuilder.class){
                scaleBuilder = new ScaleBuilder();
            }
        }
        return scaleBuilder;
    }

    public ArrayList<Integer> getSoundBuild(int key, int type, int octaves){
        switch (type){
            case 0: return evalIonian(key, octaves);
            case 1: return evalDorian(key, octaves);
            case 2: return evalPhrygian(key, octaves);
            case 3: return evalLydian(key, octaves);
            case 4: return evalMixolydian(key, octaves);
            case 5: return evalAeolian(key, octaves);
            case 6: return evalLocrian(key, octaves);
            case 7: return evalMinorPentatonic(key, octaves);
            case 8: return evalMajorPentatonic(key, octaves);
            default:
                Log.v("ScaleBuilder", "Unknown type" );
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getNotesFromSoundBuild(ArrayList<Integer> soundBuild){
        ArrayList<String> notes = new ArrayList<>();
        for(Integer sound : soundBuild){
            switch (sound % 12){
                case 0: notes.add("C"); break;
                case 1: notes.add("C#"); break;
                case 2: notes.add("D"); break;
                case 3: notes.add("D#"); break;
                case 4: notes.add("E"); break;
                case 5: notes.add("F"); break;
                case 6: notes.add("F#"); break;
                case 7: notes.add("G"); break;
                case 8: notes.add("G#"); break;
                case 9: notes.add("A"); break;
                case 10: notes.add("A#"); break;
                case 11: notes.add("B"); break;
                default: Log.v("ScaleBuilder", "Unknown key");
            }
        }
        return notes;
    }

    private ArrayList<Integer> evalIonian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 2);
            scaleBuild.add(currentOctaveCenter + 4);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 7);
            scaleBuild.add(currentOctaveCenter + 9);
            scaleBuild.add(currentOctaveCenter + 11);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalDorian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 2);
            scaleBuild.add(currentOctaveCenter + 3);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 7);
            scaleBuild.add(currentOctaveCenter + 9);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalPhrygian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 1);
            scaleBuild.add(currentOctaveCenter + 3);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 7);
            scaleBuild.add(currentOctaveCenter + 8);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalLydian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 2);
            scaleBuild.add(currentOctaveCenter + 4);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 8);
            scaleBuild.add(currentOctaveCenter + 9);
            scaleBuild.add(currentOctaveCenter + 11);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalMixolydian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 2);
            scaleBuild.add(currentOctaveCenter + 4);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 7);
            scaleBuild.add(currentOctaveCenter + 9);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalAeolian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 2);
            scaleBuild.add(currentOctaveCenter + 3);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 7);
            scaleBuild.add(currentOctaveCenter + 8);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalLocrian(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 1);
            scaleBuild.add(currentOctaveCenter + 3);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 6);
            scaleBuild.add(currentOctaveCenter + 8);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalMinorPentatonic(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 3);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 6);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
    private ArrayList<Integer> evalMajorPentatonic(int key, int octaves){
        ArrayList<Integer> scaleBuild = new ArrayList<>();
        int currentOctaveCenter = key;
        for (int i = 0; i < octaves; i++){
            scaleBuild.add(currentOctaveCenter);
            scaleBuild.add(currentOctaveCenter + 4);
            scaleBuild.add(currentOctaveCenter + 5);
            scaleBuild.add(currentOctaveCenter + 6);
            scaleBuild.add(currentOctaveCenter + 10);
            currentOctaveCenter = currentOctaveCenter + 12;
        }
        return scaleBuild;
    }
}
