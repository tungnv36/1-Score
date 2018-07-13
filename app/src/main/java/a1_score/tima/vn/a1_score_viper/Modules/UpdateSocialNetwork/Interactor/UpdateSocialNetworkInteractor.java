package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.DataStore.UpdateSocialNetworkDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interface.UpdateSocialNetworkInterface;

public class UpdateSocialNetworkInteractor implements UpdateSocialNetworkInterface.InteractorInput {

    private UpdateSocialNetworkInterface.InteractorOutput mInteractorOutput;
    private UpdateSocialNetworkInterface.DataStore mDataStore;
    private UpdateSocialNetworkInterface.View mView;

    public UpdateSocialNetworkInteractor(UpdateSocialNetworkInterface.View view, UpdateSocialNetworkInterface.InteractorOutput interactorOutput) {
        mInteractorOutput = interactorOutput;
        mView = view;
        mDataStore = UpdateSocialNetworkDataStore.getInstance(view);
    }

    @Override
    public void allowFacebook(String facebookId, String facebookName, String facebookAddress, String facebookEmail) {
        final String username = mDataStore.getUser();
        FacebookRequest facebookRequest = new FacebookRequest();
        facebookRequest.setUsername(username);
        facebookRequest.setFacebookId(facebookId);
        facebookRequest.setFacebookName(facebookName);
        facebookRequest.setFacebookAddress(facebookAddress);
        facebookRequest.setFacebookEmail(facebookEmail);

        mDataStore.allowFacebook(new OnResponse<String, FacebookResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, FacebookResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mDataStore.updateFacebookInfoToDB(username, extraData);
                } else {
                    mInteractorOutput.allowFacebookFailed(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        }, String.format("Bearer %s", mDataStore.getToken()), facebookRequest);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mView = null;
        mDataStore = null;
    }

}
