package designersfox.k.s_rerebuild.technical;

import designersfox.k.s_rerebuild.model.Scale;

import java.util.ArrayList;

public class UserPref {

    public ArrayList<int[]> savedColorpacks;
    public Scale currentScale;

    static volatile UserPref userPref;

    private UserPref(){}

    public static UserPref getInstance(){
        if(userPref == null) userPref = new UserPref();
        return userPref;
    }
}
