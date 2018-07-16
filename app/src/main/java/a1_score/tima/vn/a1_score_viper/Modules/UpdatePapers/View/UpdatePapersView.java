package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.ImagesEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Presenter.UpdatePapersPresenter;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatePapersView extends AppCompatActivity implements View.OnClickListener, UpdatePapersInterface.View {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvJobScore)
    TextView tvJobScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.rvPhoto)
    RecyclerView rvPhoto;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.btAddPaper)
    Button btAddPaper;

    private List<PapersEntity> mPapersList = new ArrayList<>();
    private ImageTypeAdapter mImageTypeAdapter;
    private List<ImagesEntity> mImageEntities = new ArrayList<>();
    private List<String> mTypeNames = new ArrayList<>();
    private ImagesAdapter mImagesAdapter;

    public static int sSelectedPosition = -1;
    private UpdatePapersInterface.Presenter mPresenter;

    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_papers);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();

        mPresenter = new UpdatePapersPresenter(this);
        mPresenter.getImageType();
        mPresenter.getImages();

        btAddPaper.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        btAddPaper.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        mSelectedPosition = position;
//        boolean result = Commons.checkPermission2(UpdatePapersView.this);
//        if(result) {
//            mPresenter.takePhoto(mPapersList.get(position).getCropType());
//        }
//    }

    private void showDialogAddPhotos() {
        try {
            mDialog = new Dialog(this);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.dialog_take_photo);
            mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            RecyclerView rvImageType = (RecyclerView) mDialog.findViewById(R.id.rvImageType);

            mImageTypeAdapter = new ImageTypeAdapter(this, mPresenter, mPapersList);
            Commons.setVerticalRecyclerView(this, rvImageType);
            rvImageType.setAdapter(mImageTypeAdapter);


            mDialog.show();
        } catch (Exception ex) {
            DialogUtils.showAlertDialog(UpdatePapersView.this, getString(R.string.dialog_title), ex.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Commons.MY_CAMERA_REQUEST_CODE:
//                mPresenter.takePhoto(mPapersList.get(mSelectedPosition).getCropType());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Commons.TAKE_PHOTO_REQUEST_CODE) {
            if(data != null) {
                String filePath = data.getStringExtra(getString(R.string.result));
                int cropType = data.getIntExtra(getString(R.string.type), 0);//type = 1: Vẽ khung ảnh chụp CMND, type = 2: Chụp ảnh thường (hợp đồng, hoá đơn, ...)
                mPresenter.updateImage(
                        cropType,
                        sSelectedPosition,
                        filePath,
                        mPapersList.get(sSelectedPosition).getTypeid());
            }
        }
    }

    private String getTypeName(String typeId) {
        for(PapersEntity papersEntity : mPapersList) {
            if(typeId.equals(String.valueOf(papersEntity.getTypeid()))) {
                return papersEntity.getTypename();
            }
        }
        return "";
    }

    @Override
    public void getImageTypeSuccess(List<PapersEntity> papersEntities) {
        mPapersList = papersEntities;
    }

    @Override
    public void getImageTypeFail(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void getImagesSeccess(List<ImagesEntity> imageEntities) {
        mTypeNames = new ArrayList<>();
        mImageEntities = imageEntities;
        for(ImagesEntity imagesEntity : imageEntities) {
            mTypeNames.add(getTypeName(imagesEntity.getTypeId()));
        }
        mImagesAdapter = new ImagesAdapter(this, mPresenter, mImageEntities);
        mImagesAdapter.setTypeName(mTypeNames);
        Commons.setVerticalRecyclerView(this, rvPhoto);
        rvPhoto.setAdapter(mImagesAdapter);
    }

    @Override
    public void updateList(int position, Bitmap img) {
        mPapersList.get(position).setDone(true);
        mImageTypeAdapter.notifyDataSetChanged();
        mPresenter.getImages();
    }

    @Override
    public void updateListFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddPaper:
                showDialogAddPhotos();
                break;
        }
    }
}
