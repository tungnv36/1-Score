package a1_score.tima.vn.a1_score_viper.Modules.Register.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Register.Interactor.RegisterInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Wireframe.RegisterWireframe;

public class RegisterPresenter implements RegisterInterface.Presenter, RegisterInterface.InteractorOutput {

    private RegisterInterface.View view;
    private RegisterInterface.InteractorInput interactorInput;
    private RegisterInterface.Wireframe wireframe;

    public RegisterPresenter(RegisterInterface.View view) {
        this.view = view;
        interactorInput = new RegisterInteractor(view, this);
        wireframe = new RegisterWireframe();
    }

    @Override
    public void register(String username, String password, String confirmPassword, String email) {
        interactorInput.register(username, password, confirmPassword, email);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void EdittextEmpty(int type, String error) {
        view.EdittextEmpty(type, error);
    }

    @Override
    public void registerSuccess(String msg, String phoneNumber) {
        wireframe.gotToOTP((Activity)view, phoneNumber);
        ((Activity)view).finish();
    }

    @Override
    public void registerFailed(String error) {
        view.registerFailed(error);
    }

}
