package com.zjc.taste.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mobo.mobolibrary.logs.Logs;
import com.mobo.mobolibrary.model.ErrorInfo;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.mobo.mobolibrary.ui.widget.empty.EmptyLayout;
import com.mobo.mobolibrary.util.BasicNetworkUtils;
import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.R;
import com.zjc.taste.app.MApp;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.jpush.JPushUtil;
import com.zjc.taste.utils.Constants;

import org.apache.http.Header;

import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * @author Z
 * @Filename ResultResponseHandler.java
 * @Date 2015-05-30
 * @description 网络请求的结果返回消息队列
 */
public abstract class ResultResponseHandler<T> extends TextHttpResponseHandler implements DialogInterface.OnDismissListener {
    /**
     * 请求类型码
     */
    private int requestCode;
    /**
     * 对话框形式请求
     */
    private String message;
    private ProgressDialog mProgressDialog;
    /**
     * 如果是遮罩布局请求
     */
    private EmptyLayout emptyLayout;
    /**
     * 如果是下拉刷新请求
     */
    private EasyRecyclerView recyclerView;

    private Context mContext;
    private JsonParser jsonParser;
    private boolean isCheckNetWork = true; // 是否检查网络，默认检查

    public ResultResponseHandler(Context mContext, JsonParser jsonParser) {
        this.mContext = mContext;
        this.jsonParser = jsonParser;
    }

    public ResultResponseHandler(Context mContext, EmptyLayout emptyLayout, JsonParser jsonParser) {
        this.mContext = mContext;
        this.emptyLayout = emptyLayout;
        this.jsonParser = jsonParser;
        requestCode = Constants.REQUEST_CODE_EMPTY;
    }

    public ResultResponseHandler(Context mContext, String message, JsonParser jsonParser) {
        this.mContext = mContext;
        this.message = message;
        this.jsonParser = jsonParser;
        requestCode = Constants.REQUEST_CODE_DIALOG;
    }

    public ResultResponseHandler(Context mContext, EasyRecyclerView recyclerView, JsonParser jsonParser) {
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        this.jsonParser = jsonParser;
        requestCode = Constants.REQUEST_CODE_SWIPE_REFRESH;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            if (requestCode == Constants.REQUEST_CODE_EMPTY) {
                //如果是遮罩布局请求
                emptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
            } else if (requestCode == Constants.REQUEST_CODE_DIALOG) {
                //如果是dialog请求
                mProgressDialog = Util.showProgressDialog(mContext, message);
                mProgressDialog.setOnDismissListener(this);
            } else if (requestCode == Constants.REQUEST_CODE_SWIPE_REFRESH) {
                recyclerView.setRefreshingColorResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public void onCancel() {
        super.onCancel();
    }

    @Override
    public void onRetry(int retryNo) {
        try {
            if (!BasicNetworkUtils.checkNetwork(mContext)) {
                return;
            } else {
                super.onRetry(retryNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    @Override
//    public void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {
//        try {
//            if ((boolean) this.getTag()) {
//                byte[] result = new Aes("_8%V$0&^%H?*9y@r").decrypt(Gzip.ungzip(responseBytes));
//                String s = new String(result, "utf-8");
//                Logs.i("ResultResponseHandler", s + "");
//                if (s.startsWith("<")) {
//                    onResultFailure(getFailErrorinfo());
//                    return;
//                }
//                if (jsonParser != null) {
//                    if (requestCode == Constants.REQUEST_CODE_SWIPE_REFRESH) {
//                        onSwipeFail();
//                    }
//
//                    if (requestCode == Constants.REQUEST_CODE_EMPTY) {
//                        //如果是遮罩布局请求
//                        ResultMessage<T> resultMessage = jsonParser.parseResultMessage(s);
//                        if (resultMessage.isFlag()) {
//                            //如果empty不为空，并且获取的数据为0，则显示无数据界面。
//                            if (resultMessage.getResult() != null && resultMessage.getResult().size() == 0) {
//                                if (emptyLayout != null) {
//                                    emptyLayout.setErrorType(EmptyLayout.NODATA_ENABLE_CLICK);
//                                }
//                                onNotDataSuccess(resultMessage.getResult());
//                                return;
//                            }
//                            Logs.i("ResultResponseHandler", resultMessage.getResult().size() + "");
//                            onSuccess(resultMessage.getResult());
//                        } else {
//                            onResultFailure(resultMessage.getErrorInfo());
//                        }
//                    } else {
//                        //如果是dialog请求
//                        ResultMessage<T> resultMessage = jsonParser.parseResultMessage(s);
//                        if (resultMessage.isFlag()) {
//                            onSuccess(resultMessage.getResult());
//                            Logs.i("ResultResponseHandler", resultMessage.getResult().size() + "");
//                        } else {
//                            onResultFailure(resultMessage.getErrorInfo());
//                        }
//                    }
//                }
//            } else {
//                onSuccess(statusCode, headers, new String(responseBytes, "utf-8"));
//            }
//
//
//        } catch (Exception e) {
//            onResultFailure(getFailErrorinfo());
//            e.printStackTrace();
//        }
//}

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        try {
            Logs.i("ResultResponseHandler", responseString + "");
            if (responseString.startsWith("<")) {
                onResultFailure(getFailErrorInfo());
                return;
            }
            if (jsonParser != null) {
                if (requestCode == Constants.REQUEST_CODE_SWIPE_REFRESH) {
                    onSwipeFail();
                }

                if (requestCode == Constants.REQUEST_CODE_EMPTY) {
                    //如果是遮罩布局请求
                    ResultMessage<T> resultMessage = jsonParser.parseResultMessage(responseString);
                    if (resultMessage.isFlag()) {
                        //如果empty不为空，并且获取的数据为0，则显示无数据界面。
                        if (resultMessage.getResult() != null && resultMessage.getResult().size() == 0) {
                            if (emptyLayout != null) {
                                emptyLayout.setErrorType(EmptyLayout.NODATA_ENABLE_CLICK);
                            }
                            onNotDataSuccess(resultMessage.getResult());
                            return;
                        }
                        Logs.i("ResultResponseHandler", resultMessage.getResult().size() + "");
                        onSuccess(resultMessage.getResult());
                    } else {
                        onResultFailure(resultMessage.getMsg());
                    }
                } else {
                    //如果是dialog请求
                    ResultMessage<T> resultMessage = jsonParser.parseResultMessage(responseString);
                    if (resultMessage.isFlag()) {
                        onSuccess(resultMessage.getResult());
                        Logs.i("ResultResponseHandler", resultMessage.getResult().size() + "");
                    } else {
                        onResultFailure(resultMessage.getMsg());
                    }
                }
            }
        } catch (Exception e) {
            onResultFailure(getFailErrorInfo());
            e.printStackTrace();
        }
    }

    @NonNull
    private String getNotNetErrorInfo() {
        return mContext.getResources().getString(R.string.error_view_click_to_refresh);
    }

    @NonNull
    private String getFailErrorInfo() {
        return mContext.getResources().getString(R.string.error_view_click_to_refresh);
    }

    @Override
    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
        Logs.i("ResultResponseHandler", s + "");
        if (requestCode == Constants.REQUEST_CODE_SWIPE_REFRESH) {
            onSwipeFail();
        }

        if (BasicNetworkUtils.checkNetwork(MApp.getInstance().getApplicationContext())) {
            onResultFailure(getFailErrorInfo());
        } else {
            //网络中断
            onResultFailure(getNotNetErrorInfo());
        }
    }

    public void onSuccess(List<T> result) {
        if (requestCode == Constants.REQUEST_CODE_EMPTY) {
            //如果是遮罩布局请求
            emptyLayout.dismiss();
        } else if (requestCode == Constants.REQUEST_CODE_DIALOG) {
            //如果是dialog请求
            dismissDialog();
        }
        onResultSuccess(result);
    }

    public abstract void onResultSuccess(List<T> result);

    public void onNotDataSuccess(List<T> result) {
    }

    public void onResultFailure(String msg) {
        try {
            if (msg != null) {
                if (TextUtils.equals(msg, "认证信息有误，请重新登录")) {
                    JPushInterface.setAlias(MApp.getInstance().getApplicationContext(), "", null);
                    SharePreferencesUtil.getInstance().removePwd();
                    SharePreferencesUtil.getInstance().setLogin(false);
//                    ((ZBaseActivity) mContext).startActivity(LoginActivity.class);
                    JPushUtil.setAliasAndTags();
                    Util.showCustomMsgLong("当前账号已在别的设备登录，若非本人操作，您的登录密码可能已经泄露，请及时改密。");
                } else {
                    if (requestCode != Constants.REQUEST_CODE_EMPTY) {
                        Util.showCustomMsg(msg);
                    }
                }
            }

            if (requestCode == Constants.REQUEST_CODE_EMPTY) {
                //如果是遮罩布局请求
                emptyLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            } else if (requestCode == Constants.REQUEST_CODE_DIALOG) {
                //如果是dialog请求
                dismissDialog();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关掉下拉刷新
     */
    public void onSwipeFail() {
        recyclerView.getSwipeToRefresh().setRefreshing(false);
    }

    /**
     * dialog形式请求
     */
    public void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mContext != null) {
            HttpUtilsAsync.getInstance().cancelRequests(mContext, true);
        }
    }

}
