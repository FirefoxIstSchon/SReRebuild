package designersfox.k.s_rerebuild.factory;

import designersfox.k.s_rerebuild.model.Scale;
import designersfox.k.s_rerebuild.technical.ScaleBuilder;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import java.util.ArrayList;

public class ScaleFactory {

    static volatile ScaleFactory scaleFactory;

    static ScaleBuilder scaleBuilder;

    MediaPlayer currentSound;
    String sdPath;

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
        currentSound = new MediaPlayer();
        for(Integer sound : soundBuild){
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String currentSoundPath = sdPath + "/msounds/p" + sound + ".mp3";
            try {
                currentSound.setDataSource(currentSoundPath); //todo: error verirse burada MediaPlayer.create gerekebilir
            } catch (Throwable t) {t.printStackTrace();}
            returnScale.sounds.add(currentSound); //todo: her yerde ayni ses cikarsa bu obj pass'inden
        }
        returnScale.notesAsText = scaleBuilder.getNotesFromSoundBuild(soundBuild);
        currentSound.release(); //todo: error veriyor mu?
        return returnScale;
    }

    /*mp.prepare();
                mp.start();*/

    //avoid exceptions
     /* try {
        mp.reset();
        mp.prepare();
        mp.stop();
        mp.release();
        mp=null;
    }
  catch (Exception e)
    {
        e.printStackTrace();
    }*/


}
