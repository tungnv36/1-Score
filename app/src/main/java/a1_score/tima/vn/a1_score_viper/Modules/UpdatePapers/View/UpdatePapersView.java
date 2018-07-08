package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Presenter.UpdatePapersPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatePapersView extends AppCompatActivity implements AdapterView.OnItemClickListener, UpdatePapersInterface.View {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvJobScore)
    TextView tvJobScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.gvPhoto)
    GridView gvPhoto;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.btUpdate)
    Button btUpdate;

    private List<PapersEntity> mPapersList;
    private PhotoAdapter mPhotoAdapter;

    private int mSelectedPosition = -1;
    private UpdatePapersInterface.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_papers);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();
        initPapers();

        mPresenter = new UpdatePapersPresenter(this);
    }

    private void initPapers() {
        mPapersList = new ArrayList<>();
        mPapersList.add(new PapersEntity(1, false, null, "Hộ khẩu", 2));
        mPapersList.add(new PapersEntity(2, false, null, "Giấy tờ nhà đất", 2));
        mPapersList.add(new PapersEntity(3, false, null, "Bảo hiểm y tế", 1));
        mPapersList.add(new PapersEntity(4, false, null, "Hoá đơn điện nước", 2));
        mPapersList.add(new PapersEntity(5, false, null, "Hoá đơn Internet", 2));
        mPapersList.add(new PapersEntity(6, false, null, "Đăng ký ô tô (Mặt trước)", 1));
        mPapersList.add(new PapersEntity(7, false, null, "Đăng ký ô tô (Mặt sau)", 1));
        mPapersList.add(new PapersEntity(8, false, null, "Đăng ký xe máy (Mặt trước)", 1));
        mPapersList.add(new PapersEntity(9, false, null, "Đăng ký xe máy (Mặt sau)", 1));

        int height = (int) (Commons.getDisplayMetrics(this).heightPixels / 5);

        mPhotoAdapter = new PhotoAdapter(mPapersList, height);
        gvPhoto.setAdapter(mPhotoAdapter);
        gvPhoto.setOnItemClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setupActionBar() {
        getSupportActionBar().setTitle(getString(R.string.paper));
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mSelectedPosition = position;
        boolean result = Commons.checkPermission2(UpdatePapersView.this);
        if(result) {
            mPresenter.takePhoto(mPapersList.get(position).getType());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Commons.MY_CAMERA_REQUEST_CODE:
                mPresenter.takePhoto(mPapersList.get(mSelectedPosition).getType());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Commons.TAKE_PHOTO_REQUEST_CODE) {
            if(data != null) {
                String filePath = data.getStringExtra("result");
                mPresenter.updateList(mPapersList.get(mSelectedPosition).getType(), mSelectedPosition, filePath);
            }
        }
    }

    @Override
    public void updateList(int position, Bitmap img) {
        mPapersList.get(mSelectedPosition).setImage(img);
        mPapersList.get(mSelectedPosition).setShow(true);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateListFailed(String err) {
        Toast.makeText(UpdatePapersView.this, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

}
