package cn.cloudworkshop.miaoding.mvp.goods;

import java.util.List;

import cn.cloudworkshop.miaoding.base.BasePresenter;
import cn.cloudworkshop.miaoding.base.BaseView;
import cn.cloudworkshop.miaoding.bean.GoodsBean;

/**
 * Author：Libin on 2018/10/29 10:52
 * Email：1993911441@qq.com
 * Describe：
 */
public interface GoodsContract {
    interface View extends BaseView {

        void hideLoading();

        void finishRefresh();

        void finishLoad();

        void loadError();

        void initView(List<GoodsBean.DataBean.itemDataBean> userList);
    }

    interface Presenter extends BasePresenter<View> {
        void initData(int page, int type);
    }
}
