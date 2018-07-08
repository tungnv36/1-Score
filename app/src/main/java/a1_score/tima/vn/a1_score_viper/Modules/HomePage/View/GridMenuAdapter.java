package a1_score.tima.vn.a1_score_viper.Modules.HomePage.View;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Entity.MenuEntity;
import a1_score.tima.vn.a1_score_viper.R;

public class GridMenuAdapter extends BaseAdapter {

    private List<MenuEntity> mCityList;
    private int mHeightScreen;

    public GridMenuAdapter(List<MenuEntity> cityList, int heightScreen) {
        mCityList = cityList;
        mHeightScreen = heightScreen;
    }

    @Override
    public int getCount() {
        return mCityList == null ? 0 : mCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCityList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_menu, null);
            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightScreen));//(int)(heightScreen / 5)
        }

        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);

        tvTitle.setTypeface(Commons.setFont(parent.getContext(), parent.getContext().getResources().getString(R.string.font_segoe)), Typeface.BOLD);
        tvScore.setTypeface(Commons.setFont(parent.getContext(), parent.getContext().getResources().getString(R.string.font_segoe)));

        ivIcon.setImageResource(mCityList.get(position).getIcon());
        tvTitle.setText(mCityList.get(position).getTitle());
        tvScore.setText(mCityList.get(position).getScore());

        return convertView;
    }
}
