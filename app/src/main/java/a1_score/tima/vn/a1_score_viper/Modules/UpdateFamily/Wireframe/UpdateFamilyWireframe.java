package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraActivity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface.UpdateFamilyInterface;

public class UpdateFamilyWireframe implements UpdateFamilyInterface.Wireframe {

    @Override
    public void gotoCamera(Activity activity, int type, int imageType) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("IMAGE_TYPE", imageType);
        activity.startActivityForResult(intent, Commons.TAKE_PHOTO_REQUEST_CODE);
    }

}
