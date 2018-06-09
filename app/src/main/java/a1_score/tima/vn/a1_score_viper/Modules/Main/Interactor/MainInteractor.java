package a1_score.tima.vn.a1_score_viper.Modules.Main.Interactor;

import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Modules.Main.Interface.MainInterface;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainInteractor implements MainInterface.InteractorInput {

    private MainInterface.InteractorOutput presenter;

    public MainInteractor(MainInterface.InteractorOutput presenter) {
        this.presenter = presenter;
    }

    @Override
    public void lauchLoginScreen() {
        presenter.lauchLoginOutput();
    }

    @Override
    public void lauchRegisterScreen() {
        presenter.lauchRegisterOutput();
    }

    @Override
    public void unRegister() {
        presenter = null;
    }

}
