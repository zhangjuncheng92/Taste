package com.zjc.taste.api;

import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ConstantsParams;

import org.apache.http.entity.ByteArrayEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by asus1 on 2016/10/27.
 */
public class ApiUtil {
    public static String KEY = "_8%V&0&^%_?*9y@r";
    public static String KEYNOTICE = "_8%V&0&^%_?*9y@5";
    public static String CODING = "UTF-8";

    private static final ApiUtil me;

    static {
        me = new ApiUtil();
    }

    private ApiUtil() {
    }

    public static ApiUtil getInstance() {
        return me;
    }


    /***
     * 添加token
     *
     * @param oldUrl
     * @return
     */
    public String addToken(String oldUrl) {
        return oldUrl + "&t=" + DevBase.getToken();
    }


    public ByteArrayEntity getByteArrayEntity(JsonObject jsonObject) {
        try {
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
            return byteArrayEntity;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 把json转化为AsyncHttpClient专属对象
     *
     * @param jsonObject
     * @return
     */
    public ByteArrayEntity getAesByteArrayEntity(JsonObject jsonObject) {
        byte[] s = new byte[0];
        try {
            s = new Aes(KEY).encrypt(jsonObject.toString().getBytes(CODING));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(Gzip.gzip(s));
        return byteArrayEntity;
    }


    public ByteArrayEntity getByteArrayEntityNotice(JsonObject jsonObject) {
        byte[] s = new byte[0];
        try {
            s = new Aes(KEYNOTICE).encrypt(jsonObject.toString().getBytes(CODING));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(Gzip.gzip(s));
        return byteArrayEntity;
    }


    /***
     * 把接口编码拼接为完整的URL
     *
     * @param code
     * @return
     */
    public String getURLByApiCode(String code) {
        if (SharePreferencesUtil.getInstance().isLogin()) {
            return Constants.BASE_SYSTEM_IP + code;
        } else {
            return Constants.BASE_SYSTEM_IP + code;
        }
    }

    /***
     * 创建一个请求对象并把分页对象添加进去
     *
     * @param start
     * @return
     */
    public JsonObject getPostRequest(int start) {
        JsonObject page = new JsonObject();
        page.addProperty("start", start);
        page.addProperty("limit", ConstantsParams.PAGE_SIZE);
        return page;
    }

    /***
     * 创建一个请求对象并把分页对象添加进去
     *
     * @return
     */
    public JsonObject getPostRequest() {
        JsonObject postRequest = new JsonObject();
        return postRequest;
    }


    /***
     * 创建一个请求对象并把分页对象添加进去
     *
     * @param postRequest
     * @return
     */
    public JsonObject getRequestBody(JsonObject postRequest) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("PostRequ", postRequest.toString());
        requestBody.addProperty("DevBase", DevBase.getAll());
        return requestBody;
    }

    /***
     * 创建一个请求对象并把分页对象添加进去
     *
     * @param postRequest
     * @return
     */
    public RequestParams getRequestParams(JsonObject postRequest) {
        RequestParams params = new RequestParams();
        params.put("param",postRequest);
        return params;
    }
}
