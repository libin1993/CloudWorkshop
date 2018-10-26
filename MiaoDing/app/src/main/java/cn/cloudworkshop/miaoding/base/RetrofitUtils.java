package cn.cloudworkshop.miaoding.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.cloudworkshop.miaoding.application.MyApp;
import cn.cloudworkshop.miaoding.utils.Api;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Libin on 2018/10/19 16:19
 * Email：1993911441@qq.com
 * Describe：
 */
public class RetrofitUtils {

    private volatile static RetrofitUtils INSTANCE;

    //构造方法私有
    private RetrofitUtils() {
    }

    //获取单例
    public static RetrofitUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitUtils();
                }
            }
        }
        return INSTANCE;
    }




    public  Api request() {

        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.cloudworkshop.cn/")
                .build()
                .create(Api.class);
    }

}
