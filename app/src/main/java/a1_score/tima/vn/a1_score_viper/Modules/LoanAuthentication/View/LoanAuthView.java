package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.View;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Common.Media.CameraHelper;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface.LoanAuthInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Presenter.LoanAuthPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanAuthView extends AppCompatActivity implements LoanAuthInterface.View {
    @BindView(R.id.surface_view)
    TextureView surfaceView;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.tvNumberAuth)
    TextView tvNumberAuth;
    @BindView(R.id.ibRecord)
    ImageButton ibRecord;
    @BindView(R.id.ibCancel)
    ImageButton ibCancel;
    @BindView(R.id.ibReRecord)
    ImageButton ibReRecord;//, View.OnClickListener, SurfaceHolder.Callback

    private LoanAuthInterface.Presenter mPresenter;

    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private File mOutputFile;

    private boolean isRecording = false;
    private int recording = 0;
    private static final String TAG = "Recorder";
    private int numberAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        mPresenter = new LoanAuthPresenter(this);

        Random random = new Random();
        numberAuth = random.nextInt(999999 - 100000) + 100000;
        tvNumberAuth.setText(String.valueOf(numberAuth));
    }

    public void onCancelRecord(View view) {
        this.finish();
    }


    public void onCaptureClick(View view) {
        if (recording == 1) {//isRecording
            // BEGIN_INCLUDE(stop_release_media_recorder)

            // stop recording and release camera
            try {
                mMediaRecorder.stop();  // stop the recording
            } catch (RuntimeException e) {
                mOutputFile.delete();
            }
            releaseMediaRecorder(); // release the MediaRecorder object
            mCamera.lock();         // take camera access back from MediaRecorder

            // inform the user that recording has stopped
            setCaptureButtonText(R.mipmap.ic_done);
//            isRecording = false;
            recording = 2;
            releaseCamera();
            // END_INCLUDE(stop_release_media_recorder)

        } else if (recording == 0) {
            new MediaPrepareTask().execute(null, null, null);
        } else {
            setCaptureButtonText(R.mipmap.ic_record);
            recording = 0;
            mPresenter.uploadVideo(String.valueOf(numberAuth));
        }
    }

    public void onReload(View view) {
        try {
            mMediaRecorder.stop();
        } catch (RuntimeException e) {
            mOutputFile.delete();
        }
        releaseMediaRecorder();
        mCamera.lock();

        recording = 0;
        setCaptureButtonText(R.mipmap.ic_record);
        releaseCamera();
    }

    private void setCaptureButtonText(int res) {
        ibRecord.setImageResource(res);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // if we are using MediaRecorder, release it first
        releaseMediaRecorder();
        // release the camera immediately on pause event
        releaseCamera();
    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            // clear recorder configuration
            mMediaRecorder.reset();
            // release the recorder object
            mMediaRecorder.release();
            mMediaRecorder = null;
            // Lock camera for later use i.e taking it back from MediaRecorder.
            // MediaRecorder doesn't need it anymore and we will release it if the activity pauses.
            mCamera.lock();
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            // release the camera for other applications
            mCamera.release();
            mCamera = null;
        }
    }

    private boolean prepareVideoRecorder() {

        // BEGIN_INCLUDE (configure_preview)
        mCamera = CameraHelper.getDefaultCameraInstance(Camera.CameraInfo.CAMERA_FACING_FRONT);

        // We need to make sure that our preview and recording video size are supported by the
        // camera. Query camera to find all the sizes and choose the optimal size given the
        // dimensions of our preview surface.
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> mSupportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> mSupportedVideoSizes = parameters.getSupportedVideoSizes();
        Camera.Size optimalSize = CameraHelper.getOptimalVideoSize(mSupportedVideoSizes,
                mSupportedPreviewSizes, surfaceView.getWidth(), surfaceView.getHeight());

        // Use the same size for recording profile.
        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        profile.videoFrameWidth = optimalSize.width;
        profile.videoFrameHeight = optimalSize.height;

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        if (display.getRotation() == Surface.ROTATION_0) {
            mCamera.setDisplayOrientation(90);
        }

        // likewise for the camera object itself.
        parameters.setPreviewSize(profile.videoFrameWidth, profile.videoFrameHeight);
        mCamera.setParameters(parameters);
        try {
            // Requires API level 11+, For backward compatibility use {@link setPreviewDisplay}
            // with {@link SurfaceView}
            mCamera.setPreviewTexture(surfaceView.getSurfaceTexture());
        } catch (IOException e) {
            Log.e(TAG, "Surface texture is unavailable or unsuitable" + e.getMessage());
            return false;
        }
        // END_INCLUDE (configure_preview)


        // BEGIN_INCLUDE (configure_media_recorder)
        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Step 2: Set sources
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mMediaRecorder.setProfile(profile);

        // Step 4: Set output file
        mOutputFile = CameraHelper.getOutputMediaFile(CameraHelper.MEDIA_TYPE_VIDEO);
        if (mOutputFile == null) {
            return false;
        }
        mMediaRecorder.setOutputFile(mOutputFile.getPath());
        // END_INCLUDE (configure_media_recorder)

        // Step 5: Prepare configured MediaRecorder
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    @Override
    public void uploadVideoSuccess(String msg) {
        String username = getIntent().getStringExtra("Username");
        long value = getIntent().getLongExtra("Value", 0);
        int duration = getIntent().getIntExtra("Duration", 0);
        int packageId = getIntent().getIntExtra("PackageId", 0);
        int paymentMethodId = getIntent().getIntExtra("PaymentMethodId", 0);
        int purposeId = getIntent().getIntExtra("PurposeId", 0);
        mPresenter.registerLoanCredit(username, value, duration, packageId, paymentMethodId, purposeId);
    }

    @Override
    public void uploadVideoFail(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void registerLoanCreditSuccess(String msg) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), msg, new DialogUtils.OnClickListener() {
            @Override
            public void onClickSuccess() {
                LoanAuthView.this.finish();
            }

            @Override
            public void onClickSuccess2() {
                LoanAuthView.this.finish();
            }
        });
    }

    @Override
    public void registerLoanCreditFail(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    /**
     * Asynchronous task for preparing the {@link MediaRecorder} since it's a long blocking
     * operation.
     */
    class MediaPrepareTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // initialize video camera
            if (prepareVideoRecorder()) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can start recording
                mMediaRecorder.start();

//                isRecording = true;
                recording = 1;
            } else {
                // prepare didn't work, release the camera
                releaseMediaRecorder();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                LoanAuthView.this.finish();
            }
            // inform the user that recording has started
            setCaptureButtonText(R.mipmap.ic_stop);//stop

        }
    }

}
