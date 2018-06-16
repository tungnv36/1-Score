package a1_score.tima.vn.a1_score_viper.Modules.Setting.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.View.ChangePhoneView;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;

public class SettingWireframe implements SettingInterface.Wireframe {

    @Override
    public void goToChangePhone(Activity activity) {
        Intent intent = new Intent(activity, ChangePhoneView.class);
        activity.startActivity(intent);
    }

}
