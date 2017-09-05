package com.mobo.mobolibrary.parser;


import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ErrorInfo;
import com.mobo.mobolibrary.model.ResultMessage;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ErrorinfoParser extends JsonParser<ErrorInfo> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<ErrorInfo>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<ErrorInfo>>() {
        }.getType();
    }

}
