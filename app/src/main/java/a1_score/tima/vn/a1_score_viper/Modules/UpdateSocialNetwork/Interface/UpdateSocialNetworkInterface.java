package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interface;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookResponse;

public interface UpdateSocialNetworkInterface {

    interface View {
        void allowFacebookFailed(String err);
    }

    interface Presenter {
        void allowFacebook(String facebookId, String facebookName, String facebookAddress, String facebookEmail);
        void onDestroy();
    }

    interface InteractorInput {
        void allowFacebook(String facebookId, String facebookName, String facebookAddress, String facebookEmail);
        void unRegister();
    }

    interface InteractorOutput {
        void allowFacebookFailed(String err);
    }

    interface Wireframe {

    }

    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();

        void updateFacebookInfoToDB(String username, FacebookResponse facebookResponse);
        void allowFacebook(final OnResponse<String, FacebookResponse> m_Response, String token, FacebookRequest facebookRequest);
    }

}
