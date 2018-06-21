package a1_score.tima.vn.a1_score_viper.Common;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import a1_score.tima.vn.a1_score_viper.R;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class Commons {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int MY_CAMERA_REQUEST_CODE = 100;
    public static final int TAKE_PHOTO_REQUEST_CODE = 2;

    public static Typeface setFont(Context context, String fontPath) {
        return Typeface.createFromAsset(context.getAssets(), fontPath);
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    public static void setVerticalRecyclerView(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkPermissionCamera(final Activity activity) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkPermission2(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission((Activity) context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static String formatMonthYear(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    public static void setupSizeLogo(Activity activity, ImageView ivLogo, CircularSeekBar sbLevel) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ViewGroup.LayoutParams paramsLogo = ivLogo.getLayoutParams();
        paramsLogo.width = (int) (displayMetrics.widthPixels / 2.5);
        paramsLogo.height = (int) (displayMetrics.widthPixels / 2.5);
        ivLogo.setLayoutParams(paramsLogo);

        ViewGroup.MarginLayoutParams marginLayoutParamsLogo = (ViewGroup.MarginLayoutParams) ivLogo.getLayoutParams();
        marginLayoutParamsLogo.setMargins(0, 0, 0, -(int) (displayMetrics.widthPixels / 12.5));
        ivLogo.requestLayout();

        ViewGroup.LayoutParams paramsProgress = sbLevel.getLayoutParams();
        paramsProgress.width = (int) (displayMetrics.widthPixels / 2);
        paramsProgress.height = (int) (displayMetrics.widthPixels / 2);
        sbLevel.setLayoutParams(paramsProgress);

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) sbLevel.getLayoutParams();
        marginLayoutParams.setMargins(0, 0, 0, -(int) (displayMetrics.widthPixels / 8));
        sbLevel.requestLayout();
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static List<Integer> getCameraSize(Activity activity, int cameraType) {
        List<Integer> lstCameraType = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        switch (cameraType) {
            case 1://CARD
                int rectLeft = 0;
                int rectTop = displayMetrics.heightPixels / 2 - (3 * displayMetrics.widthPixels / 10) - 50;
                int rectRight = displayMetrics.widthPixels;
                int rectBottom = displayMetrics.heightPixels / 2 + (3 * displayMetrics.widthPixels / 10);
                lstCameraType.add(rectLeft);
                lstCameraType.add(rectTop);
                lstCameraType.add(rectRight);
                lstCameraType.add(rectBottom);
                return lstCameraType;
            case 2://PAPER
                return null;
            default:
                return null;
        }
    }

    public static List<Integer> getCropSize(Activity activity, int cameraType, Bitmap bmp) {
        List<Integer> lstCameraType = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        switch (cameraType) {
            case 1://CARD
                int x = 0;
                int y = bmp.getHeight() / 2 - (3 * bmp.getWidth() / 10) - 50;
                int w = bmp.getWidth();
                int h = 50 + 3 * bmp.getWidth() / 5;
                lstCameraType.add(x);
                lstCameraType.add(y);
                lstCameraType.add(w);
                lstCameraType.add(h);
                return lstCameraType;
            case 2://PAPER
                return null;
            default:
                return null;
        }
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        return rotatedImg;
    }

    public static File getOutputMediaFile() {
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

    public static String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return "data:image/jpeg;base64," + Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }

    public static String getToday(){
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(presentTime_Date);
    }

}
