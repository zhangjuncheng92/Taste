package com.zjc.taste.ui.wedding;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.mingle.sweetpick.SweetSheet;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.R;
import com.zjc.taste.db.model.goods.Goods;
import com.zjc.taste.db.model.wedding.Wedding;
import com.zjc.taste.ui.main.adapter.HomeRollPagerAdapter;
import com.zjc.taste.utils.Constants;

/**
 * @author Z
 * @Filename GoodsDetailFragment.java
 * @Date 2017.08.25
 * @description 商品详情
 */
public class WeddingDetailFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvSoure;
    private TextView tvSales;
    private TextView tvRule;
    private WebView wbView;
    private LinearLayout btPhone;
    private LinearLayout btShoppingCar;
    private TextView tvCard;
    public LocationClient mLocationClient;
    public MyLocationListener myListener = new MyLocationListener();
    public LocationClientOption option;
    private String city;

    private Handler mHandler = new Handler();
    private WebSettings mWebSettings;

    private Wedding wedding;

    private RollPagerView homeHeaderPagerView;// 用来放滑动图片的容器
    private HomeRollPagerAdapter homeHeaderAdapter;

    //底部弹窗
    private LinearLayout rl_bottom;
    private Button bt_one;
    private Button bt_two;
    private TextView tvBy;
    private SweetSheet mSweetCard;
    Dialog dialogSex;
    private TextView tvTitleDetil;
    private TextView tvPriceDetail;
    private TextView tvNumDetail;
    private TextView tvCardDetail;
    private TextView tvReduceDetail;
    private TextView tvAddDetail;
    private TextView tvNumberDetail;
    SimpleDraweeView image;
    private double latitude = 0.0;
    private double longitude = 0.0;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static WeddingDetailFragment newInstance(Wedding bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        WeddingDetailFragment fragment = new WeddingDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            wedding = (Wedding) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            city = location.getCity();
            //百度地图没定到位不返回null，返回的是4.9E-324
//            if (latitude == 4.9E-324 || longitude == 4.9E-324) {
//                SharePreferencesUtil.getInstance().savelatitude(30.543622 + "");
//                SharePreferencesUtil.getInstance().savelongitude(114.433890 + "");
//            } else {
//                SharePreferencesUtil.getInstance().savelatitude(String.valueOf(latitude));
//                SharePreferencesUtil.getInstance().savelongitude(String.valueOf(longitude));
//            }

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getActivity());
        mLocationClient.registerLocationListener(myListener);
        option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);// 打开gps
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000 * 1);
//        option.setNeedDeviceDirect(true);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.requestLocation();
        } else {

        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "商品详情");
    }

    @Override
    protected int inflateContentView() {
        return R.layout.shopping_heath_card_detail_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        initLocation();
//        initView();
//        initEven();
//        initRollViewPager();// 初始化图片并滚动
    }

    private void initEven() {
        dialogSex = new Dialog(getContext(), R.style.dialog);
        View viewSex = LayoutInflater.from(getContext()).inflate(R.layout.shopping_card_detail_bottom_card, null);
        // 设置dialog没有title
        dialogSex.setContentView(viewSex, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialogSex.getWindow();
        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialogSex.onWindowAttributesChanged(wl);

        image = (SimpleDraweeView) viewSex.findViewById(R.id.shopping_card_detail_bottom_picture);
        rl_bottom = (LinearLayout) viewSex.findViewById(R.id.shopping_card_detail_bottom);
        bt_one = (Button) viewSex.findViewById(R.id.shopping_card_detail_bottom_btOne);
        bt_two = (Button) viewSex.findViewById(R.id.shopping_card_detail_bottom_btTwo);
//        if (wedding.getCate() == 2) {
//            bt_two.setVisibility(View.GONE);
//        } else if (wedding.getCate() == 1) {
//            bt_one.setVisibility(View.GONE);
//        }
        tvTitleDetil = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_title);
        tvPriceDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_price);
        tvNumDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_num);
        tvCardDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_card);
        tvReduceDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_reduce);
        tvAddDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_add);
        tvNumberDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_number);
        tvBy = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_by);

//        FrescoImageLoader.getInstance().displayImage(image, Constants.BASE_URL_DOWNLOAD + wedding.getTilteImgId());
        tvNumberDetail.setText("1");
//        tvNumDetail.setText(tvNumberDetail.getText().toString());
////        SharePreferencesUtil.getInstance().saveNum(tvNumDetail.getText().toString());
//        tvTitleDetil.setText(wedding.getName());
//        tvPriceDetail.setText("¥" + wedding.getPrice() + "");

        if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡") || Integer.parseInt(tvNumberDetail.getText().toString()) > 1) {
            tvReduceDetail.setClickable(true);
        } else {
            tvReduceDetail.setClickable(false);
        }
        if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡")) {
            tvAddDetail.setClickable(true);
        } else {
            tvAddDetail.setClickable(false);
        }

        tvBy.setOnClickListener(this);
        tvReduceDetail.setOnClickListener(this);
        tvAddDetail.setOnClickListener(this);
        bt_one.setOnClickListener(this);
        bt_two.setOnClickListener(this);
        rl_bottom.setOnClickListener(this);

        tvTitle.setText(wedding.getName());
//        tvPrice.setText("¥" + wedding.getPrice() + "");
//        tvSoure.setText(wedding.getSoure());
//        tvSales.setText(wedding.getSales());
//        tvRule.setText(wedding.getRule());


        // 设置支持JavaScript等
        mWebSettings = wbView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setLightTouchEnabled(true);
        mWebSettings.setSupportZoom(true);
        wbView.setHapticFeedbackEnabled(false);

        wbView.addJavascriptInterface(new WebAppInterface(getContext()), "android");
        //修改
//        wbView.loadUrl(Constants.BASE_IP + "medical/" + wedding.getContentPath());
        wbView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }

        });
    }


    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        // 如果target 大于等于API 17，则需要加上如下注解
        //定义接口，提供给JS调用
        @JavascriptInterface
        public void pushNextView() {
            //跳转fragment
//            ShoppingHeathCardCheckBodyFragment shoppingHeathCardCheckBodyFragment = ShoppingHeathCardCheckBodyFragment.newInstance(wedding, city);
//            replaceFrg(shoppingHeathCardCheckBodyFragment, null, "ShoppingHeathCardCheckBodyFragment");
        }
    }


    private void initView() {
        homeHeaderPagerView = (RollPagerView) rootView.findViewById(R.id.main_advertisement);
        tvTitle = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_title);
        tvPrice = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_price);
        tvSoure = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_soure);
        tvSales = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_sales);
        tvRule = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_rule);
        btPhone = (LinearLayout) rootView.findViewById(R.id.shopping_heath_card_detail_phone);
        btShoppingCar = (LinearLayout) rootView.findViewById(R.id.shopping_heath_card_detail_shoppingCar);
        tvCard = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_card);
//        tvRule.setText(wedding.getRule());
        tvRule.setOnClickListener(this);
        tvCard.setOnClickListener(this);
        btPhone.setOnClickListener(this);
        btShoppingCar.setOnClickListener(this);

        tvSoure.setText(wedding.getDetail());
    }


    /**
     * 初始化图片
     */
    private void initRollViewPager() {
        setRollAdapter();
//        String img = wedding.getImgIds();
//        if (img != null) {
//            String[] list = img.split(",");
//            List<String> str = new ArrayList<String>();
//            for (int i = 0; i < list.length; i++) {
//                String s = list[i];
//                str.add(s);
//            }
//            homeHeaderAdapter.addAll(str);
//        }
    }

    private void setRollAdapter() {
        homeHeaderAdapter = new HomeRollPagerAdapter(getActivity(), homeHeaderPagerView);
        homeHeaderPagerView.setAdapter(homeHeaderAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_card_detail_bottom_reduce:
                if (Integer.parseInt(tvNumberDetail.getText().toString()) > 1) {
                    tvNumberDetail.setText(Integer.parseInt(tvNumberDetail.getText().toString()) - 1 + "");
                    tvNumDetail.setText(tvNumberDetail.getText().toString());
                } else {
                    tvNumberDetail.setText("1");
                }
//                SharePreferencesUtil.getInstance().saveNum(tvNumDetail.getText().toString());
                break;
            case R.id.shopping_card_detail_bottom_add:
                tvNumberDetail.setText(Integer.parseInt(tvNumberDetail.getText().toString()) + 1 + "");
                tvNumDetail.setText(tvNumberDetail.getText().toString());
//                SharePreferencesUtil.getInstance().saveNum(tvNumDetail.getText().toString());
                break;
            case R.id.shopping_heath_card_detail_phone://在线咨询
                Util.call(getContext(), "027-87880897");
                break;
            case R.id.shopping_card_detail_bottom_by://立即购买
                if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡")) {
                    dialogSex.cancel();
                    //订单填写界面
//                    ShoppingHeathCardOrderFragment fragment1 = ShoppingHeathCardOrderFragment.newInstance(wedding);
//                    replaceFrg(fragment1, null);
                } else {
                    Util.showCustomMsg("请选择卡类别");
                }
                break;
            case R.id.shopping_heath_card_detail_shoppingCar://立即购买
                //加入购物车
                if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡")) {
                    //订单填写界面
//                    ShoppingHeathCardOrderFragment fragment1 = ShoppingHeathCardOrderFragment.newInstance(wedding);
//                    replaceFrg(fragment1, null);
                } else {
                    dialogSex.show();
                }
                break;
            case R.id.shopping_heath_card_detail_card://卡类别
                dialogSex.show();
                break;
            case R.id.shopping_heath_card_detail_rule://服务
//                startToWeb(Constants.BASE_URL + "healthCard/toRule");
                break;
            case R.id.shopping_card_detail_bottom_btOne://实物卡
                tvCard.setText("实物卡");
                tvCardDetail.setText("实物卡");
//                SharePreferencesUtil.getInstance().saveCard(tvCardDetail.getText().toString());
                bt_one.setBackgroundResource(R.color.red);
                bt_two.setBackgroundResource(R.color.white);
                break;
            case R.id.shopping_card_detail_bottom_btTwo://电子卡
                tvCard.setText("电子卡");
                tvCardDetail.setText("电子卡");
//                SharePreferencesUtil.getInstance().saveCard(tvCardDetail.getText().toString());
                bt_two.setBackgroundResource(R.color.red);
                bt_one.setBackgroundResource(R.color.white);
                break;
            case R.id.shopping_heath_card_detail_frg:
                dialogSex.cancel();
                break;
        }
    }

    private void startToWeb(String url) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, url);
//        startActivity(HospitalWebActivity.class, bundle);
    }


}
