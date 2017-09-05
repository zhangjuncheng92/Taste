package com.zjc.taste.ui.goods.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.zjc.taste.db.model.goods.GoodsDetail;
import com.zjc.taste.db.model.main.Advertisement;
import com.zjc.taste.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
public class GoodsRollPagerAdapter extends LoopPagerAdapter {
    private List<GoodsDetail.ImageListEntity> list = new ArrayList<>();
    private Context mContext;

    public GoodsRollPagerAdapter(Context mContext, RollPagerView viewPager) {
        super(viewPager);
        this.mContext = mContext;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        final GoodsDetail.ImageListEntity service = list.get(position);
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        GenericDraweeHierarchy draweeHierarchy = imageView.getHierarchy();
        draweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        FrescoImageLoader.getInstance().displayImage(imageView, Constants.getUrlByResource(service.getUri()));
//        FrescoImageLoader.getInstance().displayImage(imageView, Constants.BASE_URL_DOWNLOAD + service.getAdvertisementPicture());
//        imageView.setTag(service);
//        imageView.setOnClickListener(new OnAdvertisementClick());
        return imageView;
    }

    @Override
    public int getRealCount() {
        return list.size();
    }

    public void add(GoodsDetail.ImageListEntity imageListEntity) {
        if (imageListEntity == null) {
            return;
        }
        this.list.add(imageListEntity);
        this.notifyDataSetChanged();
    }

    public void addAll(List<GoodsDetail.ImageListEntity> list) {
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

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
