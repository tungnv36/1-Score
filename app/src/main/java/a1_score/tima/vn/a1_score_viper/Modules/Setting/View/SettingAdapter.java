package a1_score.tima.vn.a1_score_viper.Modules.Setting.View;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.HomePage.View.HomePageView;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity.SettingEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ProfileViewHolder> {

    private List<SettingEntity> lstMenu;
    private Context context;
    private SettingInterface.Presenter presenter;

    public SettingAdapter(Context context, SettingInterface.Presenter presenter, List<SettingEntity> lstMenu) {
        this.lstMenu = lstMenu;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_setting_01, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final SettingEntity settingEntity = lstMenu.get(position);

        holder.ivIcon.setImageResource(settingEntity.getIcon());
        holder.tvTitle.setText(settingEntity.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0://change pass
                        break;
                    case 1://change phone
                        presenter.goToChangePhone();
                        break;
                    case 2://logout
                        HomePageView.isLogout = true;
                        ((Activity)context).finish();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstMenu == null ? 0 : lstMenu.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.ivRight)
        ImageView ivRight;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
