package a1_score.tima.vn.a1_score_viper.Modules.Profile.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraActivity;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.View.UpdateFamilyView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.View.UpdateJobView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View.UpdatePapersView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.View.UpdateProfileView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.View.UpdateSocialNetworkView;
import a1_score.tima.vn.a1_score_viper.R;

public class ProfileWireframe implements ProfileInterface.Wireframe {

    @Override
    public void goToUpdateProfile(Activity activity) {
        Intent intent = new Intent(activity, UpdateProfileView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToUpdateJob(Activity activity) {
        Intent intent = new Intent(activity, UpdateJobView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToUpdateFamily(Activity activity) {
        Intent intent = new Intent(activity, UpdateFamilyView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToUpdateSocialNetwork(Activity activity) {
        Intent intent = new Intent(activity, UpdateSocialNetworkView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToUpdatePapers(Activity activity) {
        Intent intent = new Intent(activity, UpdatePapersView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToCamera(Activity activity, int type, int imageType) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra(activity.getString(R.string.type), type);
        intent.putExtra(activity.getString(R.string.image_type), imageType);
        activity.startActivityForResult(intent, Commons.TAKE_PHOTO_REQUEST_CODE);
    }

}
