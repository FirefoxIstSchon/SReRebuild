package designersfox.k.s_rerebuild.technical;

import designersfox.k.s_rerebuild.model.Scale;

import java.util.ArrayList;

public class PianoFragPref {

    public static ArrayList<int[]> savedColorpacks;
    public static Scale currentScale;

    static volatile PianoFragPref pianoFragPref;

    private PianoFragPref(){}

    public static PianoFragPref getInstance(){
        if(pianoFragPref == null) pianoFragPref = new PianoFragPref();
        return pianoFragPref;
    }
}
