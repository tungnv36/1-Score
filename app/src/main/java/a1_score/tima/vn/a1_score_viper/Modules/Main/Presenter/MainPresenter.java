package a1_score.tima.vn.a1_score_viper.Modules.Main.Presenter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Modules.Main.Interactor.MainInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Main.Interface.MainInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Main.Wireframe.MainWireframe;
import a1_score.tima.vn.a1_score_viper.R;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainPresenter implements MainInterface.Presenter, MainInterface.InteractorOutput {

    private MainInterface.InteractorInput mInteractorInput;
    private MainInterface.Wireframe mWireframe;
    private MainInterface.View mView;

    public MainPresenter(MainInterface.View view) {
        mInteractorInput = new MainInteractor(this);
        mWireframe = new MainWireframe();
        mView = view;
    }

    @Override
    public void lauchLoginScreen() {
        mInteractorInput.lauchLoginScreen();
    }

    @Override
    public void lauchRegisterScreen() {
        mInteractorInput.lauchRegisterScreen();
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mWireframe = null;
    }

    @Override
    public void lauchLoginOutput() {
        mWireframe.goToLoginView((Activity) mView);
    }

    @Override
    public void lauchRegisterOutput() {
        mWireframe.goToRegisterView((Activity) mView);
    }
}
