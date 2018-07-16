package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.ImagesEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ProfileViewHolder> {

    private List<ImagesEntity> mImageEntities;
    private List<String> mTypeNames;
    private Context mContext;
    private UpdatePapersInterface.Presenter mPresenter;

    public ImagesAdapter(Context context, UpdatePapersInterface.Presenter presenter, List<ImagesEntity> imageEntities) {
        mImageEntities = imageEntities;
        mContext = context;
        mPresenter = presenter;
    }

    public void setTypeName(List<String> typeNames) {
        mTypeNames = typeNames;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_papers, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final ImagesEntity imageEntity = mImageEntities.get(position);
        try {
            File fImage = new File(
                    Environment.getExternalStorageDirectory()
                            + File.separator + Constant.ROOT_FOLDER + File.separator
                            + Constant.PHOTO_FOLDER + File.separator + imageEntity.getImageName() + ".jpg");
            if (fImage.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(fImage.getPath());
                if (bitmap != null) {
                    holder.ivImage.setImageBitmap(bitmap);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(mTypeNames != null && mTypeNames.size() > 0) {
            holder.tvName.setText(mTypeNames.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mImageEntities == null ? 0 : mImageEntities.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvName)
        TextView tvName;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
