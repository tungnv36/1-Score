package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity.LoanAuthRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity.LoanAuthResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanResponse;

public interface LoanAuthInterface {
    interface View {
        void uploadVideoSuccess(String msg);
        void uploadVideoFail(String err);

        void registerLoanCreditSuccess(String msg);
        void registerLoanCreditFail(String err);
    }

    interface Presenter {
        void onDestroy();
        void uploadVideo(String des);
        void registerLoanCredit(String username, long value, int duration, int packageId, int paymentMethodId, int purposeId);
    }

    interface InteractorInput {
        void unRegister();
        void uploadVideo(String des);
        void registerLoanCredit(String username, long value, int duration, int packageId, int paymentMethodId, int purposeId);
    }

    interface InteractorOutput {
        void uploadVideoSuccess(String msg);
        void uploadVideoFail(String err);

        void registerLoanCreditSuccess(String msg);
        void registerLoanCreditFail(String err);
    }

    interface Wireframe {

    }

    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();

        void uploadVideo(final OnResponse<String, LoanAuthResponse> m_Response, String token, LoanAuthRequest loanAuthRequest);
        void registerLoanCredit(final OnResponse<String, LoanResponse> m_Response, String token, LoanRequest loanRequest);
        void updateRegisterLoanCreditToDB(LoanResponse.LoancreditEntity loancreditEntity, String username);
    }
}
