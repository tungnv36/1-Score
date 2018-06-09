package a1_score.tima.vn.a1_score_viper.Modules.Camera.View;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.CameraType;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Presenter.CameraPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, CameraInterface.View {

    Camera camera;
    @BindView(R.id.CameraView)
    SurfaceView cameraView;
    @BindView(R.id.TransparentView)
    SurfaceView transparentView;
    @BindView(R.id.ibTakePhoto)
    ImageButton ibTakePhoto;

    private SurfaceHolder holder, holderTransparent;
    private int type;
    private int imageType;

    private CameraInterface.Presenter presenter;

    private ProgressDialog progressBar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        presenter = new CameraPresenter(this);

        initView();

        type = getIntent().getIntExtra(getString(R.string.type), 0);
        imageType = getIntent().getIntExtra(getString(R.string.image_type), 0);

        ibTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initProgress();
                camera.takePicture(null, null, mPicture);
            }
        });

        final Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                ibTakePhoto.setEnabled(true);
            }
        };

        cameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibTakePhoto.setEnabled(false);
                camera.autoFocus(autoFocusCallback);
            }
        });
    }

    private void initProgress() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage(getString(R.string.take_photo_progress));
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
    }

    private void initView() {
        holder = cameraView.getHolder();
        holder.addCallback((SurfaceHolder.Callback) this);

        cameraView.setSecure(true);

        holderTransparent = transparentView.getHolder();
        holderTransparent.addCallback((SurfaceHolder.Callback) this);
        holderTransparent.setFormat(PixelFormat.TRANSLUCENT);

        transparentView.setZOrderMediaOverlay(true);
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            presenter.saveImage(data);
        }
    };

    /**
     * Vẽ khung lên camera
     */
    private void Draw() {
        List<Integer> lstCameraSize = Commons.getCameraSize(this, type);
        if(lstCameraSize != null) {
            Canvas canvas = holderTransparent.lockCanvas(null);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(3);

            Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(Color.parseColor("#99000000"));

            Rect rec = new Rect(lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));//left top right bototm
            canvas.drawRect(rec, paint);

            Rect rec2 = new Rect(0, 0, lstCameraSize.get(2), lstCameraSize.get(1));
            canvas.drawRect(rec2, paint2);

            Rect rec3 = new Rect(0, lstCameraSize.get(3), lstCameraSize.get(2), Commons.getDisplayMetrics(this).heightPixels);
            canvas.drawRect(rec3, paint2);

            holderTransparent.unlockCanvasAndPost(canvas);
        }
    }

    public void refreshCamera() {
        if (holder.getSurface() == null) {
            return;
        }
        try {
            camera.stopPreview();
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            synchronized (holder) {
                Draw();
            }
            camera = Camera.open();
        } catch (Exception e) {
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();
//        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        if (display.getRotation() == Surface.ROTATION_0) {
            camera.setDisplayOrientation(90);
        }
        camera.setParameters(param);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.release();
    }

    @Override
    public void saveImageSuccess() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(getString(R.string.result), Environment.getExternalStorageDirectory() + getString(R.string.image_name));
        if(type != 0) {
            returnIntent.putExtra(getString(R.string.type), type);
        }
        if(imageType != 0) {
            returnIntent.putExtra(getString(R.string.image_type), imageType);
        }
        setResult(Commons.TAKE_PHOTO_REQUEST_CODE, returnIntent);
        progressBar.dismiss();
        finish();
    }

    @Override
    public void saveImageFailed() {
        Toast.makeText(this, getString(R.string.take_photo_failed), Toast.LENGTH_LONG).show();
        progressBar.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }
}
