package com.zjc.taste.db.parser.main;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.taste.db.model.main.MainGoods;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainGoodsParser extends JsonParser<MainGoods> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<MainGoods>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<MainGoods>>() {
        }.getType();
    }

}
