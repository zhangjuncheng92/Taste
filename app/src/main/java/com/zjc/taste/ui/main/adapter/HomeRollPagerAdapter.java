package com.zjc.taste.ui.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.zjc.taste.db.model.main.Advertisement;
import com.zjc.taste.ui.goods.GoodsDetailActivity;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ConstantsParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
public class HomeRollPagerAdapter extends LoopPagerAdapter {
    private List<Advertisement> list = new ArrayList<>();
    private Context mContext;

    public HomeRollPagerAdapter(Context mContext, RollPagerView viewPager) {
        super(viewPager);
        this.mContext = mContext;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        Advertisement service = list.get(position);
        if (service.getResList() != null && service.getResList().size() > 0) {
            FrescoImageLoader.getInstance().displayImage(imageView, Constants.getUrlByResource(service.getResList().get(0).getUri()));
            imageView.setOnClickListener(new OnAdvertisementClick());
        }
        return imageView;
    }

    @Override
    public int getRealCount() {
        return list.size();
    }

    public void add(Advertisement advertisement) {
        if (advertisement == null) {
            return;
        }
        this.list.add(advertisement);
        this.notifyDataSetChanged();
    }

    public void addAll(List<Advertisement> list) {
        if (list == null) {
            return;
        }
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    class OnAdvertisementClick implements View.OnClickListener {
        public void onClick(View v) {
            // 设置图片点击事件
            try {
                Advertisement service = list.get((int) v.getTag());
                if (service.getType() == ConstantsParams.GOODS_SELF) {
                    //商品
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.ARGUMENT, service.getId());
                    ((ZBaseActivity) mContext).startActivity(GoodsDetailActivity.class, bundle);
                } else if (service.getType() == ConstantsParams.GOODS_SELF) {

                } else if (service.getType() == ConstantsParams.GOODS_SELF) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
