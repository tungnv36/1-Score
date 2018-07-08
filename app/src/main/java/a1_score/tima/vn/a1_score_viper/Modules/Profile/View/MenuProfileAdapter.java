package a1_score.tima.vn.a1_score_viper.Modules.Profile.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.Profile.Entity.MenuEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuProfileAdapter extends RecyclerView.Adapter<MenuProfileAdapter.ProfileViewHolder> {

    private List<MenuEntity> mMenuList;
    private Context mContext;
    private ProfileInterface.Presenter mPresenter;

    public MenuProfileAdapter(Context context, ProfileInterface.Presenter presenter, List<MenuEntity> menuList) {
        mMenuList = menuList;
        mContext = context;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_profile, parent, false);
//        Animation anim = AnimationUtils.loadAnimation(context, R.anim.item_move);
//        view.startAnimation(anim);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final MenuEntity menuEntity = mMenuList.get(position);

        holder.ivIcon.setImageResource(menuEntity.getIcon());
        holder.tvTitle.setText(menuEntity.getTitle());
        holder.tvSubTitle.setText(menuEntity.getSubTitle());

//        presenter.setupAnimationProgress(holder.pbLevel, menuEntity.getLastProgress(), menuEntity.getProgress());
        holder.pbLevel.setProgress(menuEntity.getProgress());

        if(!menuEntity.isShowSubTitle()) {
            holder.tvSubTitle.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setupAnimationPress(mContext, v);
                switch (position) {
                    case 0:
                        mPresenter.goToUpdateProfile();
                        break;
                    case 1:
                        mPresenter.goToUpdateJob();
                        break;
                    case 2:
                        mPresenter.goToUpdateFamily();
                        break;
                    case 3:
                        mPresenter.goToUpdateSocialNetwork();
                        break;
                    case 4:
                        mPresenter.goToUpdatePapers();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuList == null ? 0 : mMenuList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvSubTitle)
        TextView tvSubTitle;
        @BindView(R.id.llViewText)
        LinearLayout llViewText;
        @BindView(R.id.ibArrow)
        ImageButton ibArrow;
        @BindView(R.id.pbLevel)
        ProgressBar pbLevel;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
