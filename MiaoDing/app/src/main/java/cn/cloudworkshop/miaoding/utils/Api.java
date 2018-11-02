package cn.cloudworkshop.miaoding.utils;


import cn.cloudworkshop.miaoding.bean.GoodsBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Author：Libin on 2018/10/11 09:34
 * Email：1993911441@qq.com
 * Describe：
 */
public interface Api {
    @GET("index.php/index/index5_4/goods_list")
    Observable<GoodsBean> getData(@Query("type") int type, @Query("classify_id") int classifyId, @Query("page") int page);
}



