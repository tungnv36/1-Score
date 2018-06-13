package a1_score.tima.vn.a1_score_viper.Common.API;

/**
 * Created by hoangngoc on 8/16/16.
 */

public abstract class OnResponse<T, TF> {
    public void onStart(){}
    public void onFinish(){}
    public abstract void onResponseSuccess(String tag, T rs, TF extraData);
    public abstract void onResponseError(String tag, String message);
}
