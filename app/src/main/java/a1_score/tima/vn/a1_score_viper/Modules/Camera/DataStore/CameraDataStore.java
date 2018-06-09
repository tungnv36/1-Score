package a1_score.tima.vn.a1_score_viper.Modules.Camera.DataStore;

import android.app.Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;

public class CameraDataStore implements CameraInterface.DataStore {

    @Override
    public boolean saveImage(Activity activity, byte[] data) {
        File pictureFile = Commons.getOutputMediaFile();
        if (pictureFile == null) {
            return false;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

}
