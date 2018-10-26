package cn.cloudworkshop.miaoding.application;

import android.app.Application;
import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;

import cn.cloudworkshop.miaoding.utils.MyRefreshHeader;

/**
 * Author：Libin on 2018/10/26 16:42
 * Email：1993911441@qq.com
 * Describe：
 */
public class MyApp extends Application {
    private static MyApp mContext;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColors(0xfff2f4f5, 0xffaaaaaa);//全局设置主题颜色
                return new MyRefreshHeader(context);

            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColors(0xfff2f4f5, 0xffaaaaaa);//全局设置主题颜色
                ClassicsFooter classicsFooter = new ClassicsFooter(context);
                BallSpinFadeLoaderIndicator ball = new BallSpinFadeLoaderIndicator();
                ball.setColor(0xffaaaaaa);
                classicsFooter.setProgressDrawable(ball);
                classicsFooter.setTextSizeTitle(12);

                return classicsFooter;
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getContext() {
        return mContext;
    }
}
