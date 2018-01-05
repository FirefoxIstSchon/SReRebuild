package designersfox.k.s_rerebuild.factory;

import designersfox.k.s_rerebuild.model.Scale;
import designersfox.k.s_rerebuild.technical.ScaleBuilder;

import android.app.Activity;
import java.util.ArrayList;

public class ScaleFactory {

    static volatile ScaleFactory scaleFactory;

    static ScaleBuilder scaleBuilder;

    private ScaleFactory(){
        scaleBuilder = ScaleBuilder.getInstance();
    }

    public static ScaleFactory getInstance(){
        if(scaleFactory == null){
            synchronized (ScaleFactory.class){
                scaleFactory = new ScaleFactory();
            }
        }
        return scaleFactory;
    }

    public Scale getScale(int key, int type, int octaves, Activity activity){
        Scale returnScale = new Scale(key, type, octaves);
        ArrayList<Integer> soundBuild = scaleBuilder.getSoundBuild(key, type, octaves);
        for(Integer sound : soundBuild){
            //todo: returnScale.sounds.add( system.path + String.valueOf(sound) );
        }
        returnScale.notesAsText = scaleBuilder.getNotesFromSoundBuild(soundBuild);
        return returnScale;
    }




}
