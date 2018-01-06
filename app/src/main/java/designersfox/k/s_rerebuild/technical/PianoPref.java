package designersfox.k.s_rerebuild.technical;

import designersfox.k.s_rerebuild.model.Scale;

import java.util.ArrayList;

public class PianoPref {

    public ArrayList<int[]> savedColorpacks;
    public Scale currentScale;

    static volatile PianoPref pianoPref;

    private PianoPref(){}

    public static PianoPref getInstance(){
        if(pianoPref == null) pianoPref = new PianoPref();
        return pianoPref;
    }
}
