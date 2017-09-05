package com.zjc.taste.db.parser.goods;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.taste.db.model.goods.GoodsDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GoodsDetailParser extends JsonParser<GoodsDetail> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<GoodsDetail>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<GoodsDetail>>() {
        }.getType();
    }

}
