package a1_score.tima.vn.a1_score_viper.Modules.Camera.View;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Presenter.CameraPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, CameraInterface.View {

    @BindView(R.id.CameraView)
    SurfaceView cameraView;
    @BindView(R.id.TransparentView)
    SurfaceView transparentView;
    @BindView(R.id.ibTakePhoto)
    ImageButton ibTakePhoto;

    private Camera mCamera;
    private SurfaceHolder mHolder, mHolderTransparent;
    private int mType;
    private int mImageType;

    private CameraInterface.Presenter mPresenter;

    private ProgressDialog mProgressBar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mPresenter = new CameraPresenter(this);

        initView();

        //type: Xác định loại khung ảnh sẽ vẽ lên camera
        //type = 1: khung chữ nhật ngang dùng cho các loại CMND, bằng lái, ...
        //type = 2: Không có khung, dùng để chụp hợp đồng, giấy tờ dạng A4
        //type = 3: Khung vuông chụp chân dung

        //imageType: Xác định image view nào sẽ hiển thị ảnh vừa chụp
        mType = getIntent().getIntExtra(getString(R.string.type), 0);
        mImageType = getIntent().getIntExtra(getString(R.string.image_type), 0);

        ibTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initProgress();
                mCamera.takePicture(null, null, mPicture);
            }
        });

        final Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                ibTakePhoto.setEnabled(true);
            }
        };

        //auto focus khi chạm vào màn hình
        cameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibTakePhoto.setEnabled(false);
                mCamera.autoFocus(autoFocusCallback);
            }
        });
    }

    private void initProgress() {
        mProgressBar = new ProgressDialog(this);
        mProgressBar.setCancelable(true);
        mProgressBar.setMessage(getString(R.string.take_photo_progress));
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);
        mProgressBar.show();
    }

    private void initView() {
        mHolder = cameraView.getHolder();
        mHolder.addCallback((SurfaceHolder.Callback) this);

        cameraView.setSecure(true);

        mHolderTransparent = transparentView.getHolder();
        mHolderTransparent.addCallback((SurfaceHolder.Callback) this);
        mHolderTransparent.setFormat(PixelFormat.TRANSLUCENT);

        transparentView.setZOrderMediaOverlay(true);
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            mPresenter.saveImage(data);
        }
    };

    /**
     * Vẽ khung lên camera
     */
    private void Draw() {
        List<Integer> lstCameraSize = Commons.getCameraSize(this, mType);
        if(lstCameraSize != null) {
            Canvas canvas = mHolderTransparent.lockCanvas(null);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(3);

            Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(Color.parseColor("#99000000"));

            Rect rec = new Rect(lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));//left top right bototm
            canvas.drawRect(rec, paint);

            Rect rec2 = new Rect(0, 0, Commons.getDisplayMetrics(this).widthPixels, lstCameraSize.get(1));
            canvas.drawRect(rec2, paint2);

            Rect rec3 = new Rect(0, lstCameraSize.get(3), Commons.getDisplayMetrics(this).widthPixels, Commons.getDisplayMetrics(this).heightPixels);
            canvas.drawRect(rec3, paint2);

            Rect rec4 = new Rect(0, lstCameraSize.get(1), lstCameraSize.get(0), lstCameraSize.get(3));
            canvas.drawRect(rec4, paint2);

            Rect rec5 = new Rect(lstCameraSize.get(2), lstCameraSize.get(1), Commons.getDisplayMetrics(this).widthPixels, lstCameraSize.get(3));
            canvas.drawRect(rec5, paint2);

            mHolderTransparent.unlockCanvasAndPost(canvas);
        }
    }

    public void refreshCamera() {
        if (mHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open(mType==3? Camera.CameraInfo.CAMERA_FACING_FRONT: Camera.CameraInfo.CAMERA_FACING_BACK);
            synchronized (holder) {
                Draw();
            }
        } catch (Exception e) {
            return;
        }
        Camera.Parameters param;
        param = mCamera.getParameters();
//        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        if (display.getRotation() == Surface.ROTATION_0) {
            mCamera.setDisplayOrientation(90);
        }
        mCamera.setParameters(param);
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
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
        mCamera.release();
    }

    @Override
    public void saveImageSuccess() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(getString(R.string.result), Environment.getExternalStorageDirectory() + getString(R.string.image_name));
        if(mType != 0) {
            returnIntent.putExtra(getString(R.string.type), mType);
        }
        if(mImageType != 0) {
            returnIntent.putExtra(getString(R.string.image_type), mImageType);
        }
        setResult(Commons.TAKE_PHOTO_REQUEST_CODE, returnIntent);
        mProgressBar.dismiss();
        finish();
    }

    @Override
    public void saveImageFailed() {
        Toast.makeText(this, getString(R.string.take_photo_failed), Toast.LENGTH_LONG).show();
        mProgressBar.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }
}
