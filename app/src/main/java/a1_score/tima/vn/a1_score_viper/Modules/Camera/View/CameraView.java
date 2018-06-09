package a1_score.tima.vn.a1_score_viper.Modules.Camera.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import a1_score.tima.vn.a1_score_viper.Common.CameraPreview;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraView extends AppCompatActivity {

    @BindView(R.id.camera_preview)
    FrameLayout cameraPreview;
    @BindView(R.id.button_capture)
    Button buttonCapture;
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    SurfaceView cameraView,transparentView;
    SurfaceHolder holder,holderTransparent;
    int  deviceHeight,deviceWidth;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_1);
        ButterKnife.bind(this);

        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        cameraPreview.addView(mCameraPreview);

        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
        }
        return camera;
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            try {
                Bitmap storedBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, null);
                Bitmap bmp = rotateImage(storedBitmap, 90);
//
                FileOutputStream fos = new FileOutputStream(pictureFile);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                fos.write(data);
                fos.close();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", Environment.getExternalStorageDirectory() + "/IMG.jpg");
                setResult(Commons.TAKE_PHOTO_REQUEST_CODE, returnIntent);
                finish();
            } catch (FileNotFoundException e) {
                Toast.makeText(CameraView.this, e.getMessage(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(CameraView.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        return rotatedImg;
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(String.valueOf(Environment.getExternalStorageDirectory()));
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG.jpg");

        return mediaFile;
    }
}
