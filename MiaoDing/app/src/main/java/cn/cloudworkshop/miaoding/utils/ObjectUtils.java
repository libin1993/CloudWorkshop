package cn.cloudworkshop.miaoding.utils;

import java.util.List;

/**
 * Author：Libin on 2018/10/26 14:01
 * Email：1993911441@qq.com
 * Describe：
 */
public class ObjectUtils {
    public static <T> boolean isNotNull(List<T> list) {

        return list != null && list.size() > 0;
    }
}
