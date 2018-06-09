package a1_score.tima.vn.a1_score_viper.Modules.Camera.DataStore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraActivity;

public class CameraDataStore implements CameraInterface.DataStore {

    @Override
    public boolean saveImage(Activity activity, byte[] data) {

        File pictureFile = Commons.getOutputMediaFile();
        if (pictureFile == null) {
            return false;
        }
        try {
//                Bitmap storedBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, null);
//                Bitmap bmp = rotateImage(storedBitmap, 90);
//                List<Integer> lstCameraSize = Commons.getCropSize(CameraActivity.this, type, bmp);
//                Bitmap cropBmp = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));

            FileOutputStream fos = new FileOutputStream(pictureFile);
//                cropBmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
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
