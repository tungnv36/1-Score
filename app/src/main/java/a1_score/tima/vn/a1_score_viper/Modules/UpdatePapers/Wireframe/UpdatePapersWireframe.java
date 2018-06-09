package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.CameraType;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraActivity;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.View.CameraView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdatePapersWireframe implements UpdatePapersInterface.Wireframe {

    @Override
    public void gotoCamera(Activity activity, int type) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra(activity.getString(R.string.type), type);
        activity.startActivityForResult(intent, Commons.TAKE_PHOTO_REQUEST_CODE);
    }

}
