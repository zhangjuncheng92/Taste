package com.zjc.taste.ui.wedding;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.taste.R;
import com.zjc.taste.db.model.goods.Goods;
import com.zjc.taste.db.model.wedding.Wedding;
import com.zjc.taste.ui.goods.GoodsDetailFragment;
import com.zjc.taste.utils.Constants;


/**
 * @author Z
 * @Filename WeddingDetailActivity.java
 * @Date 2017.08.19
 * @description 婚宴活动界面
 */
public class WeddingDetailActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        if (getIntent().getExtras() == null) {

        } else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.ARGUMENT)) {
            //加载商品详情界面
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            WeddingDetailFragment fragment = WeddingDetailFragment.newInstance((Wedding) getIntent().getExtras().getSerializable(Constants.ARGUMENT));
            trans.addToBackStack(null);
            trans.add(R.id.root, fragment).commit();
        }
    }
}
