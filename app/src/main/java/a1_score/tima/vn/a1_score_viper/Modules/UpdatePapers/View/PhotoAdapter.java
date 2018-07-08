package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.R;

public class PhotoAdapter extends BaseAdapter {

    private List<PapersEntity> mPaperList;
    private int mHeight;

    public PhotoAdapter(List<PapersEntity> paperList, int height) {
        mPaperList = paperList;
        mHeight = height;
    }

    @Override
    public int getCount() {
        return mPaperList == null ? 0 : mPaperList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPaperList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mPaperList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_paper, null);
            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));
        }

        PapersEntity papersEntity = mPaperList.get(position);

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        LinearLayout llImage = (LinearLayout) convertView.findViewById(R.id.llImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        tvTitle.setText(papersEntity.getTitle());

        if(papersEntity.isShow()) {
            ivImage.setVisibility(View.VISIBLE);
            llImage.setVisibility(View.GONE);
            if(papersEntity.getImage() != null) {
                ivImage.setImageBitmap(papersEntity.getImage());
            }
        } else {
            ivImage.setVisibility(View.GONE);
            llImage.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
