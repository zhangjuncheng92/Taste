package com.zjc.taste.db.parser.address;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.taste.db.model.shopcart.Address;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddressParser extends JsonParser<Address> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<Address>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<Address>>() {
        }.getType();
    }

}
