package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Presenter;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interactor.UpdateSocialNetworkInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interface.UpdateSocialNetworkInterface;

public class UpdateSocialNetworkPresenter implements UpdateSocialNetworkInterface.Presenter, UpdateSocialNetworkInterface.InteractorOutput {

    private UpdateSocialNetworkInterface.View mView;
    private UpdateSocialNetworkInterface.InteractorInput mInteractorInput;

    public UpdateSocialNetworkPresenter(UpdateSocialNetworkInterface.View view) {
        mView = view;
        mInteractorInput = new UpdateSocialNetworkInteractor(view, this);
    }

    @Override
    public void allowFacebook(String facebookId, String facebookName, String facebookAddress, String facebookEmail) {
        mInteractorInput.allowFacebook(facebookId, facebookName, facebookAddress, facebookEmail);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    //Output

    @Override
    public void allowFacebookFailed(String err) {
        mView.allowFacebookFailed(err);
    }
}
