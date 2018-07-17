package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipResponse;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public interface LoanRequestInterface {
    //View
    interface View {
        void initAvatar(Bitmap bmp);
        void initData(String fullName, int level, long score);
        void getLoanCreditPackageSuccess(List<LoanResponse.LoanCreditPackagesEntity> loanCreditPackagesEntities);
        void getLoanCreditPackageFail(String err);

        void openOrCloseInfo(RelativeLayout view, Button button, boolean isOpen, int position);
        void onDestroy();
    }
    //Presenter
    interface Presenter {
        void initAvatar();
        void initData();
        void getLoanCreditPackage();

        void goToLoanRegistration();
        void initAnimationLogo(ImageView view);
        void setupAnimationProgress(ProgressBar progress, int start, int end);
        void openOrCloseInfo(Context context, RelativeLayout view, Button button, boolean isOpen, int position);
        void setupAnimationItem(Context context, android.view.View view);
        void setupAnimationOpenOrClose(Context context, android.view.View view, boolean isOpen);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initAvatar();
        void initData();
        void getLoanCreditPackage();

        void goToLoanRegistration();
        void initAnimationLogo(ImageView view);
        void setupAnimationProgress(ProgressBar progress, int start, int end);
        void openOrCloseInfo(Context context, RelativeLayout view, Button button, boolean isOpen, int position);
        void setupAnimationItem(Context context, android.view.View view);
        void setupAnimationItemOpenOrClose(Context context, android.view.View view, boolean isOpen);
        void unRegister();
    }

    interface InteractorOutput {
        void initAvatarOutput(Bitmap bmp);
        void initDataOutput(String fullName, int level, long score);
        void getLoanCreditPackageSuccess(List<LoanResponse.LoanCreditPackagesEntity> loanCreditPackagesEntities);
        void getLoanCreditPackageFail(String err);

        void goToLoanRegistrationOutput();
        void runAnimationLogo(ImageView view);
        void runAnimationProgress(ProgressBar progress, int start, int end);
        void openOrCloseInfoOutput(Context context, RelativeLayout view, Button button, boolean isOpen, int position);
        void runAnimationItem(Context context, android.view.View view);
        void runAnimationItemOpenOrClose(Context context, android.view.View view, boolean isOpen);
    }
    //Wireframe
    interface Wireframe {
        void goToLoanRegistration(Activity activity);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getFullName();
        long getScore();
        int getLevel();
        String getToken();

        void getLoanCreditPackage(final OnResponse<String, LoanResponse> m_Response, String token);
    }
}
