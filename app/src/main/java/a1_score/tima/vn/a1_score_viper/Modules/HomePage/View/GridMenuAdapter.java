package a1_score.tima.vn.a1_score_viper.Modules.HomePage.View;

import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.format.TextStyle;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Entity.MenuEntity;
import a1_score.tima.vn.a1_score_viper.R;

public class GridMenuAdapter extends BaseAdapter {

    private List<MenuEntity> cityList;
    private int heightScreen;

    public GridMenuAdapter(List<MenuEntity> cityList, int heightScreen) {
        this.cityList = cityList;
        this.heightScreen = heightScreen;
    }

    @Override
    public int getCount() {
        return cityList == null ? 0 : cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cityList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_menu, null);
            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightScreen));//(int)(heightScreen / 5)
        }

        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);

        tvTitle.setTypeface(Commons.setFont(parent.getContext(), parent.getContext().getResources().getString(R.string.font_segoe)), Typeface.BOLD);
        tvScore.setTypeface(Commons.setFont(parent.getContext(), parent.getContext().getResources().getString(R.string.font_segoe)));

        ivIcon.setImageResource(cityList.get(position).getIcon());
        tvTitle.setText(cityList.get(position).getTitle());
        tvScore.setText(cityList.get(position).getScore());

        return convertView;
    }
}
