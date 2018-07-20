package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface;

import android.app.Activity;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.CalculatorProfitRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.CalculatorProfitResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;

public interface LoanRegistrationInterface {
    //View
    interface View {
        void initPurpose(List<LoanDictionaryResponse.PurposeEntity> purposeEntities);
        void initPaymentMethod(List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities);

        void calculatorLoanCreditProfitSuccess(CalculatorProfitResponse.LoancreditprofitEntity profitEntity);
        void calculatorLoanCreditProfitFail(String err);
    }
    //Presenter
    interface Presenter {
        void getLoanDictionary();
        void calculatorLoanCreditProfit(int packageId, int duration, long value);

        void goToLoanAuth(LoanRequest loanRequest);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void getLoanDictionary();
        void calculatorLoanCreditProfit(int packageId, int duration, long value);
        void goToLoanAuth(LoanRequest loanRequest);
        void unRegister();
    }

    interface InteractorOutput {
        void goToLoanAuthOutput(LoanRequest loanRequest);
        void calculatorLoanCreditProfitSuccess(CalculatorProfitResponse.LoancreditprofitEntity profitEntity);
        void calculatorLoanCreditProfitFail(String err);

        void getPurposeOutput(List<LoanDictionaryResponse.PurposeEntity> purposeEntities);
        void getPaymentMethodOutput(List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();
        boolean checkLoanDicExist();
        List<LoanDictionaryResponse.PurposeEntity> getPurposeDic();
        List<LoanDictionaryResponse.PaymentMethodEntity> getPaymentMethodDic();
        long updateLoanDicToDB(LoanDictionaryResponse loanDictionaryResponse);

        void getLoanDictionary(final OnResponse<String, LoanDictionaryResponse> m_Response, String token);
        void calculatorProfit(final OnResponse<String, CalculatorProfitResponse> m_Response, String token, CalculatorProfitRequest calculatorProfitRequest);
    }
    //Wireframe
    interface Wireframe {
        void goToLoanAuth(Activity activity, LoanRequest loanRequest);
    }
}
