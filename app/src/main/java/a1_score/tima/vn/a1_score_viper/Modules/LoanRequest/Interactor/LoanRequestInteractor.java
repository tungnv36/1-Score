package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interactor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.io.File;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.DataStore.LoanRequestDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanEntity;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;

public class LoanRequestInteractor implements LoanRequestInterface.InteractorInput {

    private LoanRequestInterface.View mView;
    private LoanRequestInterface.InteractorOutput mPresenter;
    private LoanRequestInterface.DataStore mDataStore;

    public LoanRequestInteractor(LoanRequestInterface.View view, LoanRequestInterface.InteractorOutput presenter) {
        mView = view;
        mPresenter = presenter;
        mDataStore = LoanRequestDataStore.getInstance(view);
    }

    @Override
    public void initAvatar() {
        String filePath = Environment.getExternalStorageDirectory()
                + File.separator + Constant.ROOT_FOLDER + File.separator
                + Constant.PHOTO_FOLDER + File.separator + mDataStore.getUser() + "_avatar.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        mPresenter.initAvatarOutput(bitmap);
    }

    @Override
    public void initData() {
        mPresenter.initDataOutput(mDataStore.getFullName(), mDataStore.getLevel(), mDataStore.getScore());
    }

    @Override
    public void getLoanCreditPackage() {
        mDataStore.getLoanCreditPackage(new OnResponse<String, LoanResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LoanResponse extraData) {
                if(extraData != null) {
                    mPresenter.getLoanCreditPackageSuccess(extraData.getLoancreditpackages());
                } else {
                    mPresenter.getLoanCreditPackageFail("");
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mPresenter.getLoanCreditPackageFail(message);
            }
        }, String.format("Bearer %s", mDataStore.getToken()));
    }

    @Override
    public void goToLoanRegistration(LoanEntity loanEntity) {
        mPresenter.goToLoanRegistrationOutput(loanEntity);
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        mPresenter.runAnimationLogo(view);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        mPresenter.runAnimationProgress(progress, start, end);
    }

    @Override
    public void openOrCloseInfo(Context context, RelativeLayout view, Button button, boolean isOpen, int position) {
        mPresenter.openOrCloseInfoOutput(context, view, button, isOpen, position);
    }

    @Override
    public void setupAnimationItem(Context context, View view) {
        mPresenter.runAnimationItem(context, view);
    }

    @Override
    public void setupAnimationItemOpenOrClose(Context context, View view, boolean isOpen) {
        mPresenter.runAnimationItemOpenOrClose(context, view, isOpen);
    }

    @Override
    public void unRegister() {
        mPresenter = null;
    }

}
