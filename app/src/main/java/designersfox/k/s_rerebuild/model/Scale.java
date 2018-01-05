package designersfox.k.s_rerebuild.model;

import android.media.MediaPlayer;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;

public class Scale implements Serializable {

    public int key; // c1-b1 : 0-11
    public int type; //ion-loc : 0-6 ; p-min : 7 ; p-maj : 8
    public int noteRange; //0-6 : 7 ; 7-8 : 5
    public int octaveRange; //min 3
    public ArrayList<String> notesAsText = new ArrayList<>();
    public ArrayList<MediaPlayer> sounds = new ArrayList<>();
    public String name;
    public String description;

    public Scale(int key, int type, int octaveRange){
        this.key = key;
        this.type = type;
        this.octaveRange = octaveRange;
        switch (type){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: this.noteRange = 7; break;
            case 7:
            case 8: this.noteRange = 5; break;
            default:
                Log.v("Scale", "Unknown type");
        }
        name = key + " " + type;
        description = type + " scale," +
                " containing " + noteRange + " notes," +
                " ranging over " + octaveRange + " octaves.";
    }

}
