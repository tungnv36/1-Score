package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraActivity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdateProfileWireframe implements UpdateProfileInterface.Wireframe {

    @Override
    public void gotoCamera(Activity activity, int type, int imageType) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra(activity.getString(R.string.type), type);
        intent.putExtra(activity.getString(R.string.image_type), imageType);
        activity.startActivityForResult(intent, Commons.TAKE_PHOTO_REQUEST_CODE);
    }

}
