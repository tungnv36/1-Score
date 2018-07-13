package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.suke.widget.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.SocialNetworkRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interface.UpdateSocialNetworkInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Presenter.UpdateSocialNetworkPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateSocialNetworkView extends AppCompatActivity implements UpdateSocialNetworkInterface.View {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvJobScore)
    TextView tvJobScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.btUpdate)
    Button btUpdate;
    @BindView(R.id.lbFacebook)
    LoginButton lbFacebook;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.swOnOff)
    SwitchButton swOnOff;
    @BindView(R.id.rlFacebook)
    RelativeLayout rlFacebook;

    private List<SocialNetworkRequest> mSocialNetworkList;
    private SocialNetworkAdapter mSocialNetworkAdapter;

    private CallbackManager mCallbackManager;

    private UpdateSocialNetworkInterface.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_update_social_network);
        ButterKnife.bind(this);
        mPresenter = new UpdateSocialNetworkPresenter(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();
        setupSocialNetwork();

        lbFacebook.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        lbFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userID = loginResult.getAccessToken().getToken();
                onResult();
//                rlFacebook.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancel() {
                Log.e("CANCEL", "CANCEL");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("EROR", error.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onResult() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if(response != null) {
                    Log.d("JSON", response.getJSONObject().toString());
                    try {
                        mPresenter.allowFacebook(
                                object.getString("id"),
                                object.getString("name"),
                                "",
                                object.getString("email")
                        );
                    } catch (JSONException e) {
                        DialogUtils.showAlertDialog(UpdateSocialNetworkView.this, UpdateSocialNetworkView.this.getString(R.string.dialog_title), e.getMessage());
                    }
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    /*
     * Khởi tạo danh sách mạng xã hội
     */
    private void setupSocialNetwork() {
        swOnOff.setChecked(true);
//        mSocialNetworkList = new ArrayList<>();
//        mSocialNetworkList.add(new SocialNetworkRequest(1, R.mipmap.ic_facebook, "Facebook", true, R.color.color_facebook));
//        mSocialNetworkList.add(new SocialNetworkRequest(2, R.mipmap.ic_zalo, "Zalo", false, R.color.color_zalo));
//        mSocialNetworkList.add(new SocialNetworkRequest(3, R.mipmap.ic_instagram, "Instagram", false, R.color.color_instagram));
//        mSocialNetworkList.add(new SocialNetworkRequest(4, R.mipmap.ic_linkedin, "Linkedin", false, R.color.color_linkedin));
//
//        mSocialNetworkAdapter = new SocialNetworkAdapter(this, mSocialNetworkList);
//        Commons.setVerticalRecyclerView(this, rvSocialNetwork);
//        rvSocialNetwork.setAdapter(mSocialNetworkAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setupActionBar() {
        getSupportActionBar().setTitle(getString(R.string.social_network));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.main_dark_blue)));
        } else {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3499FF")));
        }
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main_dark_blue));
        }
    }

    private void styleView() {
        btUpdate.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

    @Override
    public void allowFacebookFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }
}
