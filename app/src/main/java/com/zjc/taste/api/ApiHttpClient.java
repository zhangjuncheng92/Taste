package com.zjc.taste.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zjc.taste.utils.ConstantsParams;

/**
 * @author Z
 * @Filename ApiHttpClient.java
 * @Date 2015-05-30
 * @description 接口管理类
 */
public class ApiHttpClient {

    private static final ApiHttpClient me;

    static {
        me = new ApiHttpClient();
    }

    private ApiHttpClient() {
    }

    public static ApiHttpClient getInstance() {
        return me;
    }

    /**
     * 查询首页置顶
     */
    public void getAdvertisement(int userId, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        postRequest.addProperty("userId", userId);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("pus002"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 查询首页推荐商品
     */
    public void getMainGoods(ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();

        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("pus001"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 加入购物车
     */
    public void addShopCart(int goodsNum, int goodsId, int userId, ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        JsonObject cartGoods = new JsonObject();

        cartGoods.addProperty("amount", goodsNum);
        cartGoods.addProperty("goodsId", goodsId);
        postRequest.add("cartGoods", cartGoods);
        postRequest.addProperty("userId", userId);
        postRequest.addProperty("inputType", ConstantsParams.SHOP_CART_CUMULATIVE);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("cts001"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 增加商品数量
     */
    public void addGoods(int goodsNum, int goodsId, int userId, ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        JsonObject cartGoods = new JsonObject();

        cartGoods.addProperty("amount", goodsNum);
        cartGoods.addProperty("goodsId", goodsId);
        postRequest.add("cartGoods", cartGoods);
        postRequest.addProperty("userId", userId);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("cts001"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 减少商品数量
     */
    public void minusGoods(int goodsNum, int goodsId, int userId, ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        JsonObject cartGoods = new JsonObject();

        cartGoods.addProperty("amount", goodsNum);
        cartGoods.addProperty("goodsId", goodsId);
        postRequest.add("cartGoods", cartGoods);
        postRequest.addProperty("userId", userId);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("cts001"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 批量删除商品从购物车
     */
    public void deleteByShopCart(JsonArray ids, int userId, ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        postRequest.add("ids", ids);
        postRequest.addProperty("userId", userId);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("cts002"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 获取购物车列表
     */
    public void getShopCart(int userId, ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        postRequest.addProperty("userId", userId);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("cts003"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }

    /**
     * 获取商品详情数据
     */
    public void getGoodsDetail(int userId, int goodsId, ResultResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = ApiUtil.getInstance().getPostRequest();
        postRequest.addProperty("userId", userId);
        postRequest.addProperty("id", goodsId);
        HttpUtilsAsync.post(ApiUtil.getInstance().getURLByApiCode("gos003"), ApiUtil.getInstance().getRequestParams(postRequest), asyncHttpResponseHandler);
    }


}
