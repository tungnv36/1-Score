package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageTypeAdapter extends RecyclerView.Adapter<ImageTypeAdapter.ProfileViewHolder> {

    private List<PapersEntity> mPapersEntities;
    private Context mContext;
    private UpdatePapersInterface.Presenter mPresenter;

    public ImageTypeAdapter(Context context, UpdatePapersInterface.Presenter presenter, List<PapersEntity> papersEntities) {
        mPapersEntities = papersEntities;
        mContext = context;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_type, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final PapersEntity papersEntity = mPapersEntities.get(position);
        holder.tvName.setText(papersEntity.getTypename());
        holder.cbDone.setChecked(papersEntity.isDone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = Commons.checkPermission2(mContext);
                if(result) {
                    UpdatePapersView.sSelectedPosition = position;
                    mPresenter.takePhoto(papersEntity.getImagesize());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPapersEntities == null ? 0 : mPapersEntities.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cbDone)
        CheckBox cbDone;
        @BindView(R.id.tvTakePhotoTitle)
        TextView tvTakePhotoTitle;
        @BindView(R.id.tvName)
        TextView tvName;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
