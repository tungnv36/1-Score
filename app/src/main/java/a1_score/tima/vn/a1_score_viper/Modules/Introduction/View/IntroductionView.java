package a1_score.tima.vn.a1_score_viper.Modules.Introduction.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface.IntroductionInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Presenter.IntroductionPresenter;
import a1_score.tima.vn.a1_score_viper.Modules.Main.View.MainView;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroductionView extends AppCompatActivity implements IntroductionInterface.View {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_skip)
    Button btnSkip;

    private MyViewPagerAdapter mViewPagerAdapter;
    private int[] mLayouts;
    private TextView[] mDots;

    private IntroductionInterface.Presenter mPresenter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mPresenter = new IntroductionPresenter(this);

        // Checking for first time launch - before calling setContentView()
        mPresenter.checkFirstLaunch();

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // mLayouts of all welcome sliders
        // add few more mLayouts if you want
        mLayouts = new int[] {
                R.layout.activity_introduction_first,
                R.layout.activity_introduction_second,
                R.layout.activity_introduction_third
        };

        // adding bottom mDots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        mViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setEnabled(false);
        btnSkip.setTextColor(Color.parseColor("#DDDDDD"));

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.backPagePresenter(viewPager.getCurrentItem());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.nextPagePresenter(viewPager.getCurrentItem(), mLayouts.length);
            }
        });
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void setPage(int changePage) {
        viewPager.setCurrentItem(changePage);
    }

    @Override
    public void finishView() {
        this.finish();
    }

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        layoutDots.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.dot_inactive_screen3));
            layoutDots.addView(mDots[i]);
        }

        if (mDots.length > 0)
            mDots[currentPage].setTextColor(getResources().getColor(R.color.dot_active_screen3));
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position == mLayouts.length - 1) {
                btnNext.setText(getString(R.string.start));
            } else if (position == 0) {
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.GONE);
            } else {
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(mLayouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
