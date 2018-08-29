package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.ListInfoBean;

import java.util.List;

/**
 * Created by leict on 2016/4/6.
 */
public class FollowRecrodTypeAdapter extends BaseAdapter {
    private List<ListInfoBean> itemList;
    private final Context context;
    private final LayoutInflater inflater;
    private FollowStateOnClick listener = null;
    private int selectItem = -1;

    public FollowRecrodTypeAdapter(Context context, List<ListInfoBean> itemList) {
        this.context = context;
        this.itemList = itemList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setSelectItemId(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (itemList == null)
            return 0;
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ListInfoBean entity = (ListInfoBean) itemList.get(position);
        if (entity == null)
            return null;
        final ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_public2_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.nameView = (TextView) view.findViewById(R.id.item_content);
            viewHolder.layoutContent = (LinearLayout) view.findViewById(R.id.layout_item_content);
            viewHolder.item_imag = (ImageView) view.findViewById(R.id.item_imag);
            view.setTag(viewHolder);
            view.setId(position);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (position == selectItem) {
//            viewHolder.layoutContent.setBackgroundColor(Color.parseColor("#151f41"));
            viewHolder.nameView.setTextColor(Color.parseColor("#ff7800"));
            viewHolder.item_imag.setVisibility(View.VISIBLE);
//            view.setBackgroundColor(Color.parseColor("#151f41"));
        } else {
//            viewHolder.layoutContent.setBackgroundColor(Color.parseColor("#ffffff"));
            viewHolder.nameView.setTextColor(Color.parseColor("#737373"));
//            view.setBackgroundColor(Color.parseColor("#ffffff"));
            viewHolder.item_imag.setVisibility(View.GONE);
        }
        viewHolder.nameView.setText(entity.getMaturityName());
        return view;
    }

    private class ViewHolder {
        LinearLayout layoutContent = null;
        TextView nameView = null;
        ImageView item_imag= null;
    }

    public void setItemClickListener(FollowStateOnClick listener) {
        this.listener = listener;
    }

    public interface FollowStateOnClick {
        void onclickLister(int position);
    }
}
