package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.dialogListviewInfo;

import java.util.List;


/**
 *
 * Created by  on 2017/10/25.
 */

public class DialogListAdapter extends BaseAdapter {
    private List<dialogListviewInfo.NodesBean> itemList;
    private final Context context;
    private final LayoutInflater inflater;
    private int select = -1;

    public DialogListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setData(dialogListviewInfo list) {

        if(list != null){
            itemList = list.getNodes();

        }

    }

    @Override
    public int getCount() {

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

        if(itemList == null){
            return null;
        }


        dialogListviewInfo.NodesBean data = itemList.get(id);
        if (data == null){
            return null;
        }

        final ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_message, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (CheckedTextView) view.findViewById(R.id.ctv_single_choice);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        view.setId(id);
        viewHolder.name.setText(data.getNName());
        if (select == id) {
            viewHolder.name.setChecked(true);
        } else {
            viewHolder.name.setChecked(false);
        }

        return view;
    }

    private class ViewHolder {
        CheckedTextView name;

    }

    public void setSelectItem(int position) {
        this.select = position;
    }

}
