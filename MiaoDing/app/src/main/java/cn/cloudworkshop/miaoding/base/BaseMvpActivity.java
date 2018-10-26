package cn.cloudworkshop.miaoding.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author：Libin on 2018/10/25 13:57
 * Email：1993911441@qq.com
 * Describe：
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView{

    protected P mPresenter;

    protected abstract P initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in initPresenter()?");
        }
        mPresenter.attach(this);

    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
