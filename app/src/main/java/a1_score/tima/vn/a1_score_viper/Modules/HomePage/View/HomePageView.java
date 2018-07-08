package a1_score.tima.vn.a1_score_viper.Modules.HomePage.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Entity.MenuEntity;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Presenter.HomePagePresenter;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePageView extends AppCompatActivity implements HomePageInterface.View, View.OnClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.ibMenu)
    ImageButton ibMenu;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibChat)
    ImageButton ibChat;
    @BindView(R.id.tvChat)
    TextView tvChat;
    @BindView(R.id.rlChat)
    RelativeLayout rlChat;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.rlBar)
    RelativeLayout rlBar;
    @BindView(R.id.rlTop)
    LinearLayout rlTop;
    @BindView(R.id.ibCall)
    ImageButton ibCall;
    @BindView(R.id.llCall)
    LinearLayout llCall;
    @BindView(R.id.ibChatBoard)
    ImageButton ibChatBoard;
    @BindView(R.id.llChat)
    LinearLayout llChat;
    @BindView(R.id.sbLevel)
    CircularSeekBar sbLevel;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvCurrentLevel)
    TextView tvCurrentLevel;
    @BindView(R.id.tvNextLevel)
    TextView tvNextLevel;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.gvMenu)
    GridView gvMenu;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    private List<MenuEntity> mMenuList;
    private GridMenuAdapter mGridMenuAdapter;

    private HomePageInterface.Presenter mPresenter;
    private int mScoreOfLevel = 80;
    private int mStartScore = 0;

    public static boolean isLogout = false;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();//Đổi màu thanh status
        changeHeightBanner();//set lại chiều cao banner theo tỷ lệ màn hình
        Commons.setupSizeLogo(this, ivLogo, sbLevel);//set lại kích thước logo và thanh progress level theo tỉ lệ màn hình
        addMenu();//Thêm grid menu
        styleView();//Đổi font chữ button

        mPresenter = new HomePagePresenter(this);

        gvMenu.setOnItemClickListener(this);
        ibMenu.setOnClickListener(this);
        ibChat.setOnClickListener(this);
        ibCall.setOnClickListener(this);
        ibChatBoard.setOnClickListener(this);
        ivLogo.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                avi.show();
                mPresenter.initAvatar();
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPresenter.initData();
            }
        }).start();
//        mPresenter.initAnimationLogo(ivLogo);
        mPresenter.setupAnimationSeekBar(sbLevel, mStartScore, mScoreOfLevel);
        if (isLogout) {
            isLogout = false;
            finish();
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void styleView() {
        tvName.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

    private void addMenu() {
        mMenuList = new ArrayList<>();
        mMenuList.add(new MenuEntity(1, R.drawable.ic_search, getResources().getString(R.string.menu_profile), "300/1000 điểm"));
        mMenuList.add(new MenuEntity(2, R.drawable.ic_loan, getResources().getString(R.string.menu_loan_request), ""));
        mMenuList.add(new MenuEntity(3, R.drawable.ic_mini_game, getResources().getString(R.string.menu_mini_game), "1500 điểm"));
        mMenuList.add(new MenuEntity(4, R.drawable.ic_contact, getResources().getString(R.string.menu_introduction), "150 điểm/người"));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = ((int) (4 * displayMetrics.heightPixels / 10) - 60) / 2;

        mGridMenuAdapter = new GridMenuAdapter(mMenuList, height);
        gvMenu.setAdapter(mGridMenuAdapter);
    }

    public void changeHeightBanner() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ViewGroup.LayoutParams params = rlTop.getLayoutParams();
        params.height = (int) (0.38 * displayMetrics.heightPixels);//Thay đổi chiều cao banner = 38% chiều cao màn hình
        rlTop.setLayoutParams(params);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Commons.TAKE_PHOTO_REQUEST_CODE) {
            if (data != null) {
                avi.show();
                String filePath = data.getStringExtra(getString(R.string.result));
                int type = data.getIntExtra(getString(R.string.type), 0);
                int imageType = data.getIntExtra(getString(R.string.image_type), 0);
                mPresenter.updateImage(type, imageType, filePath);
            }
        }
    }

    @Override
    public void setProgressValue(int progress) {

    }

    @Override
    public void callSupportFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initData(final LoginResponse.UserEntity userEntity) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvName.setText(userEntity.getFullname());
            }
        });
    }

    @Override
    public void initAvatar(final Bitmap bmp) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ivLogo.setImageBitmap(bmp);
                avi.hide();
            }
        });
    }

    @Override
    public void updateImage(int imageType, Bitmap img) {
        ivLogo.setImageBitmap(img);
        avi.hide();
    }

    @Override
    public void updateImageFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
        avi.hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibMenu:
                mPresenter.goToSetting();
                break;
            case R.id.ibChat:
                mPresenter.setupAnimationSupport(HomePageView.this, llCall, R.anim.right_to_left, R.anim.left_to_right);
                mPresenter.setupAnimationSupport(HomePageView.this, llChat, R.anim.right_to_left_delay, R.anim.left_to_right_delay);
                break;
            case R.id.ibCall:
                mPresenter.setupAnimationSupport(HomePageView.this, llCall, R.anim.right_to_left, R.anim.left_to_right);
                mPresenter.setupAnimationSupport(HomePageView.this, llChat, R.anim.right_to_left_delay, R.anim.left_to_right_delay);
                mPresenter.callSupport(HomePageView.this, getString(R.string.phone_number));
                break;
            case R.id.ibChatBoard:
                mPresenter.setupAnimationSupport(HomePageView.this, llCall, R.anim.right_to_left, R.anim.left_to_right);
                mPresenter.setupAnimationSupport(HomePageView.this, llChat, R.anim.right_to_left_delay, R.anim.left_to_right_delay);
                break;
            case R.id.ivLogo:
                boolean result = Commons.checkPermission2(HomePageView.this);
                if (result) {
                    mPresenter.takePhoto(3, 0);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 1 => llFontCMND
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch (requestCode) {
            case 9:
                permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (permissionGranted) {
            mPresenter.callSupport(HomePageView.this, getString(R.string.phone_number));
        } else {
            Toast.makeText(this, getString(R.string.permission_fail), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.setupAnimationPress(HomePageView.this.getApplicationContext(), view);
        switch (position) {
            case 0:
                mPresenter.goToProfile();
                break;
            case 1:
                mPresenter.goToLoanRequest();
                break;
            case 2:
                break;
            case 3:
                mPresenter.goToIntroduceFriends();
                break;
        }
    }
}
