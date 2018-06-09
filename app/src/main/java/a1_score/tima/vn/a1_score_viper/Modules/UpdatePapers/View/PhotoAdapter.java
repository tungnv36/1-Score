package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View;

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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.UpdateFamilyEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.UpdatePapersEntity;
import a1_score.tima.vn.a1_score_viper.R;

public class PhotoAdapter extends BaseAdapter {

    private List<UpdatePapersEntity> lstPaper;
    private int height;

    public PhotoAdapter(List<UpdatePapersEntity> lstPaper, int height) {
        this.lstPaper = lstPaper;
        this.height = height;
    }

    @Override
    public int getCount() {
        return lstPaper == null ? 0 : lstPaper.size();
    }

    @Override
    public Object getItem(int position) {
        return lstPaper.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lstPaper.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_paper, null);
            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        }

        UpdatePapersEntity updatePapersEntity = lstPaper.get(position);

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        LinearLayout llImage = (LinearLayout) convertView.findViewById(R.id.llImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        tvTitle.setText(updatePapersEntity.getTitle());

        if(updatePapersEntity.isShow()) {
            ivImage.setVisibility(View.VISIBLE);
            llImage.setVisibility(View.GONE);
            if(updatePapersEntity.getImage() != null) {
                ivImage.setImageBitmap(updatePapersEntity.getImage());
            }
        } else {
            ivImage.setVisibility(View.GONE);
            llImage.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
