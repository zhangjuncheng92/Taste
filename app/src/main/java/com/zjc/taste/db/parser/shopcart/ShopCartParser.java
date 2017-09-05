package com.zjc.taste.db.parser.shopcart;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.taste.db.model.shopcart.ShopCart;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShopCartParser extends JsonParser<ShopCart> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<ShopCart>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<ShopCart>>() {
        }.getType();
    }

}
