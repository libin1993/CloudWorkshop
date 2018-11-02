package cn.cloudworkshop.miaoding.mvp.goods;

import android.util.Log;

import cn.cloudworkshop.miaoding.base.BasePresenterImpl;
import cn.cloudworkshop.miaoding.base.RetrofitUtils;
import cn.cloudworkshop.miaoding.base.RxObserver;
import cn.cloudworkshop.miaoding.bean.GoodsBean;
import cn.cloudworkshop.miaoding.utils.ObjectUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：Libin on 2018/10/29 10:52
 * Email：1993911441@qq.com
 * Describe：
 */
public class GoodsPresenter extends BasePresenterImpl<GoodsContract.View> implements GoodsContract.Presenter {
    @Override
    public void initData(int page, final int type) {
        Log.e("libin", "initData: " + type);
        RetrofitUtils.getInstance()
                .request()
                .getData(1, 1, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<>(new RxObserver.Callback<GoodsBean>() {

                    @Override
                    public void onSuccess(GoodsBean goodsBean) {
                        if (ObjectUtils.isNotNull(goodsBean.getData().getData())) {
                            getView().initView(goodsBean.getData().getData());
                        }

                        switch (type) {
                            case 0:
                                getView().hideLoading();
                                break;
                            case 1:
                                getView().finishRefresh();
                                break;
                            case 2:
                                getView().finishLoad();
                                break;
                        }
                    }

                    @Override
                    public void onError() {
                        switch (type) {
                            case 1:
                                getView().finishRefresh();
                                break;
                            case 2:
                                getView().finishLoad();
                                break;
                        }
                        getView().loadError();
                    }

                }));
    }
}
