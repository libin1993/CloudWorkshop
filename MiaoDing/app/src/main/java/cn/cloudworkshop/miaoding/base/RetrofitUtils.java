package cn.cloudworkshop.miaoding.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.cloudworkshop.miaoding.application.MyApp;
import cn.cloudworkshop.miaoding.utils.Api;
import cn.cloudworkshop.miaoding.utils.NetWorkUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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


    public Api request() {

        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getNetWorkInterceptor())
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


    /**
     * 应用拦截器，断网读取缓存，有网请求接口
     */
    public Interceptor getInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtils.isNetworkAvailable(MyApp.getContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }



    /**
     * 网络拦截器 设置缓存
     */
    public Interceptor getNetWorkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Response response = chain.proceed(chain.request());
                int maxAge = 60 * 60 * 24;//在线缓存时间
                return response.newBuilder()
                        .removeHeader("Pragma")//清除头信息
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public,max-age=" + maxAge)//若是0则为不设置缓存
                        .build();
            }
        };
    }


}
