package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface;

public interface LoanAuthInterface {
    interface View {

    }

    interface Presenter {
        void onDestroy();
    }

    interface InteractorInput {
        void unRegister();
    }

    interface InteractorOutput {

    }

    interface Wireframe {

    }

    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();
    }
}
