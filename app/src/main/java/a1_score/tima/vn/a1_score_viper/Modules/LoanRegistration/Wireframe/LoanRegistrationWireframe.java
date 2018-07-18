package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.View.LoanAuthView;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.View.LoanRegistrationView;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;

public class LoanRegistrationWireframe implements LoanRegistrationInterface.Wireframe {

    @Override
    public void goToLoanAuth(Activity activity, LoanRequest loanRequest) {
        Intent intent = new Intent(activity, LoanAuthView.class);
        intent.putExtra("Username", loanRequest.getUsername());
        intent.putExtra("Value", loanRequest.getValue());
        intent.putExtra("Duration", loanRequest.getDuration());
        intent.putExtra("PackageId", loanRequest.getPackageId());
        intent.putExtra("PaymentMethodId", loanRequest.getPaymentMethodId());
        intent.putExtra("PurposeId", loanRequest.getPurposeId());
        activity.startActivity(intent);
    }

}
