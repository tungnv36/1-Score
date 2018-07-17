package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;

public interface LoanRegistrationInterface {
    //View
    interface View {

    }
    //Presenter
    interface Presenter {
        void getPurpose();
        void getPaymentMethod();

        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void getLoanDictionary();

        void unRegister();
    }

    interface InteractorOutput {
        void getPurposeOutput(List<LoanDictionaryResponse.PurposeEntity> purposeEntities);
        void getPaymentMethodOutput(List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();

        void getLoanDictionary(final OnResponse<String, LoanDictionaryResponse> m_Response, String token);
    }
    //Wireframe
    interface Wireframe {

    }
}
