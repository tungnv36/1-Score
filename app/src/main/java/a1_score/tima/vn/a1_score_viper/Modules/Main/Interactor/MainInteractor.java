package a1_score.tima.vn.a1_score_viper.Modules.Main.Interactor;

import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Modules.Main.Interface.MainInterface;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainInteractor implements MainInterface.InteractorInput {

    private MainInterface.InteractorOutput mPresenter;

    public MainInteractor(MainInterface.InteractorOutput presenter) {
        mPresenter = presenter;
    }

    @Override
    public void lauchLoginScreen() {
        mPresenter.lauchLoginOutput();
    }

    @Override
    public void lauchRegisterScreen() {
        mPresenter.lauchRegisterOutput();
    }

    @Override
    public void unRegister() {
        mPresenter = null;
    }

}
