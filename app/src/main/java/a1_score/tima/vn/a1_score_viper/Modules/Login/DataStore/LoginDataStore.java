package a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class LoginDataStore implements LoginInterface.DataStore {

    private LoginInterface.View view;

    public LoginDataStore(LoginInterface.View view) {
        this.view = view;
    }

    @Override
    public String callLogin(String username, String password) {
        String result = "";
        if(username.equals("0987654321") && password.equals("123456")) {
            result = ((Activity)view).getString(R.string.login_success);
        } else {
            result = ((Activity)view).getString(R.string.login_failed);
        }
        return result;
    }

}
