package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.View.LoanRegistrationView;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanEntity;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.View.UpdateProfileView;

public class LoanRequestWireframe implements LoanRequestInterface.Wireframe {

    @Override
    public void goToLoanRegistration(Activity activity, LoanEntity loanEntity) {
        Intent intent = new Intent(activity, LoanRegistrationView.class);
        intent.putExtra("Id", loanEntity.getId());
        intent.putExtra("Name", loanEntity.getName());
        intent.putExtra("MinDuration", loanEntity.getMinduration());
        intent.putExtra("MaxDuration", loanEntity.getMaxduration());
        intent.putExtra("MinValue", loanEntity.getMinvalue());
        intent.putExtra("MaxValue", loanEntity.getMaxvalue());
        activity.startActivity(intent);
    }

}
