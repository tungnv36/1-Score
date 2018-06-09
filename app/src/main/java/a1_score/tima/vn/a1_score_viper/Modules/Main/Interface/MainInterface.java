package a1_score.tima.vn.a1_score_viper.Modules.Main.Interface;

import android.app.Activity;
import android.widget.ImageView;

import me.tankery.lib.circularseekbar.CircularSeekBar;


public interface MainInterface {
    //View
    interface View {
        void onDestroy();
    }
    //Presenter
    interface Presenter {
        void lauchLoginScreen();
        void lauchRegisterScreen();
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void lauchLoginScreen();
        void lauchRegisterScreen();
        void unRegister();
    }

    interface InteractorOutput {
        void lauchLoginOutput();
        void lauchRegisterOutput();
    }
    //Wireframe
    interface Wireframe {
        void goToLoginView(Activity activity);
        void goToRegisterView(Activity activity);
    }
}
