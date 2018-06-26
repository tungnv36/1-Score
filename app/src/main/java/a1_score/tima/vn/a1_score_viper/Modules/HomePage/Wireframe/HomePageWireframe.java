package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Wireframe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraActivity;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.IntroduceFriends.View.IntroduceFriendsView;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.View.LoanRequestView;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.View.ProfileView;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.View.SettingView;
import a1_score.tima.vn.a1_score_viper.R;

public class HomePageWireframe implements HomePageInterface.Wireframe {

    @Override
    public void goToProfile(Activity activity) {
        Intent intent = new Intent(activity, ProfileView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToLoanRequest(Activity activity) {
        Intent intent = new Intent(activity, LoanRequestView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToIntroduceFriends(Activity activity) {
        Intent intent = new Intent(activity, IntroduceFriendsView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToSetting(Activity activity) {
        Intent intent = new Intent(activity, SettingView.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.start_activity, R.anim.finish_activity);
    }

    @Override
    public void goToCamera(Activity activity, int type, int imageType) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra(activity.getString(R.string.type), type);
        intent.putExtra(activity.getString(R.string.image_type), imageType);
        activity.startActivityForResult(intent, Commons.TAKE_PHOTO_REQUEST_CODE);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void callSupportOutput(Context context, String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(context.getString(R.string.phone_number)));
        context.startActivity(callIntent);
    }

}
