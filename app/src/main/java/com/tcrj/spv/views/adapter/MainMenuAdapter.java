package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tcrj.spv.R;
import com.tcrj.spv.model.MenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页菜单适配器
 * Created by leict on 2017/10/25.
 */

public class MainMenuAdapter extends BaseAdapter {
    private List<MenuEntity> itemList;
    private final Context context;
    private final LayoutInflater inflater;

    public MainMenuAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = new ArrayList<MenuEntity>();
    }

    public void setData(List<MenuEntity> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
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
    public View getView(int id, View view, ViewGroup parent) {
        final MenuEntity entity = (MenuEntity) itemList.get(id);
        if (entity == null)
            return null;
        final ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_menu_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.nameView = (TextView) view.findViewById(R.id.tv_menu_name);
            viewHolder.iconView = (ImageView) view.findViewById(R.id.grid_menu_image);
            view.setTag(viewHolder);
            view.setId(id);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameView.setText(entity.getMenuName());
        Glide.with(context).load(entity.getImageId()).into(viewHolder.iconView);
        return view;
    }

    private class ViewHolder {
        TextView nameView = null;
        ImageView iconView = null;
    }
}
