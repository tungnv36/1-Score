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

    private MainInterface.InteractorInput interactorInput;
    private MainInterface.Wireframe wireframe;
    private MainInterface.View view;

    public MainPresenter(MainInterface.View view) {
        interactorInput = new MainInteractor(this);
        wireframe = new MainWireframe();
        this.view = view;
    }

    @Override
    public void lauchLoginScreen() {
        interactorInput.lauchLoginScreen();
    }

    @Override
    public void lauchRegisterScreen() {
        interactorInput.lauchRegisterScreen();
    }

    @Override
    public void onDestroy() {
        interactorInput.unRegister();
        interactorInput = null;
        wireframe = null;
    }

    @Override
    public void lauchLoginOutput() {
        wireframe.goToLoginView((Activity) view);
    }

    @Override
    public void lauchRegisterOutput() {
        wireframe.goToRegisterView((Activity) view);
    }
}
