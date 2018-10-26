package cn.cloudworkshop.miaoding.base;

import java.lang.ref.WeakReference;

/**
 * Author：Libin on 2018/10/25 13:53
 * Email：1993911441@qq.com
 * Describe：Presenter生命周期包装、View的绑定和解除，P层实现的基类
 */
public class BasePresenterImpl<V  extends BaseView> implements BasePresenter<V>{

    private WeakReference<V> view;

    protected V getView() {
        return view.get();
    }


    protected boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @Override
    public void attach(V mView) {
        view = new WeakReference<V>(mView);
    }

    @Override
    public void detach() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }
}
