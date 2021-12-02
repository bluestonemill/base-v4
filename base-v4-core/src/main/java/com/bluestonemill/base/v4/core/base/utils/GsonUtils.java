package com.bluestonemill.base.v4.core.base.utils;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class GsonUtils {

    private static final Gson GSON = new Gson();

    public static Gson simpleGson() {
        return GSON;
    }

    /**
     * 根据字段路径，寻找字段值
     * @param jsonElement root
     * @param path 字段路径
     * @return
     */
    public static JsonElement findElementByPath(JsonElement jsonElement, String path) {
        if (jsonElement == null) {
            return JsonNull.INSTANCE;
        }
        if (path.indexOf(StrUtil.DOT) > 0) {
            String field = path.substring(0, path.indexOf(StrUtil.DOT));
            JsonElement fieldElement = jsonElement.getAsJsonObject().get(field);
            String substring = path.substring(path.indexOf(StrUtil.DOT) + 1);
            return findElementByPath(fieldElement, substring);
        }
        return jsonElement.getAsJsonObject().get(path);
    }

    public static boolean checkIsNull(JsonElement jsonElement) {
        return jsonElement == null || jsonElement == JsonNull.INSTANCE;
    }
}
