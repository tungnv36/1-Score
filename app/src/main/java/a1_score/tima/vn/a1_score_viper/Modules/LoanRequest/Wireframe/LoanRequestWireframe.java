package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.View.LoanRegistrationView;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.View.UpdateProfileView;

public class LoanRequestWireframe implements LoanRequestInterface.Wireframe {

    @Override
    public void goToLoanRegistration(Activity activity) {
        Intent intent = new Intent(activity, LoanRegistrationView.class);
        activity.startActivity(intent);
    }

}
