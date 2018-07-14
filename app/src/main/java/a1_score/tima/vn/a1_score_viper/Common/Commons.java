package a1_score.tima.vn.a1_score_viper.Common;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
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
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import a1_score.tima.vn.a1_score_viper.Common.Enums.ImageType;
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
        int rectLeft = 0;
        int rectTop = 0;
        int rectRight = 0;
        int rectBottom = 0;
        List<Integer> lstCameraType = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        switch (cameraType) {
            case 1://CARD
                rectLeft = 0;
                rectTop = displayMetrics.heightPixels / 2 - (3 * displayMetrics.widthPixels / 10) - 50;
                rectRight = displayMetrics.widthPixels;
                rectBottom = displayMetrics.heightPixels / 2 + (3 * displayMetrics.widthPixels / 10);
                lstCameraType.add(rectLeft);
                lstCameraType.add(rectTop);
                lstCameraType.add(rectRight);
                lstCameraType.add(rectBottom);
                return lstCameraType;
            case 2://PAPER
                return null;
            case 3://AVATAR
                rectLeft = 50;
                rectTop = displayMetrics.heightPixels / 7;
                rectRight = displayMetrics.widthPixels - 50;
                rectBottom = displayMetrics.heightPixels / 7 + displayMetrics.widthPixels - 50;
                lstCameraType.add(rectLeft);
                lstCameraType.add(rectTop);
                lstCameraType.add(rectRight);
                lstCameraType.add(rectBottom);
                return lstCameraType;
            default:
                return null;
        }
    }

    public static List<Integer> getCropSize(Activity activity, int cameraType, Bitmap bmp) {
        int x;
        int y;
        int w;
        int h;
        List<Integer> lstCameraType = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        switch (cameraType) {
            case 1://CARD
                x = 0;
                y = bmp.getHeight() / 2 - (3 * bmp.getWidth() / 10) - 50;
                w = bmp.getWidth();
                h = 50 + 3 * bmp.getWidth() / 5;
                lstCameraType.add(x);
                lstCameraType.add(y);
                lstCameraType.add(w);
                lstCameraType.add(h);
                return lstCameraType;
            case 2://PAPER
                return null;
            case 3://AVATAR
                x = 50;
                y = bmp.getHeight() / 7;
                w = bmp.getWidth() - 50;
                h = w;//bmp.getHeight() / 7 + bmp.getWidth() - 50;
                lstCameraType.add(x);
                lstCameraType.add(y);
                lstCameraType.add(w);
                lstCameraType.add(h);
                return lstCameraType;
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return "data:image/jpeg;base64," + Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }

    public static String getToday(){
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(presentTime_Date);
    }

    public static Boolean isTablet(Context context)
    {
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        double wInches = displayMetrics.widthPixels / (double)displayMetrics.densityDpi;
        double hInches = displayMetrics.heightPixels / (double)displayMetrics.densityDpi;

        double screenDiagonal = Math.sqrt(Math.pow(wInches, 2) + Math.pow(hInches, 2));
        return (screenDiagonal >= 7.0);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            return null;
        }
    }

    public static String getImageType(ImageType imageType) {
        switch (imageType) {
            case CMND_FRONT:
                return "CMND_FRONT";
            case CMND_BACK:
                return "CMND_BACK";
            case BANK_CARD:
                return "ATM_CARD";
            case CV:
                return "CV";
            case CONTRACT:
                return "CONTRACT";
            case SALARY_BOARD:
                return "SALARY_BOARD";
            default:
                return "";
        }
    }

    public static String changePhone(String phone) {
        if(phone.contains("+")) {
            return phone;
        }
        return String.format("+84%s",phone.substring(1));
    }

    public static String changePhone0(String phone) {
        if(phone == null) {
            return "";
        }
        if(phone.contains("+")) {
            return String.format("0%s",phone.substring(3));
        }
        return phone;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

}
