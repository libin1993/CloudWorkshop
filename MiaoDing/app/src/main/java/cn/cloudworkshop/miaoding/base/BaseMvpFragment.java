package cn.cloudworkshop.miaoding.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author：Libin on 2018/10/25 14:40
 * Email：1993911441@qq.com
 * Describe：
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView{
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = initPresenter();
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in initPresenter()?");
        }
        mPresenter.attach(this);
    }

    protected abstract P initPresenter();

    @Override
    public void onDestroyView() {
        if (mPresenter !=null){
            mPresenter.detach();
        }

        super.onDestroyView();

    }
}
