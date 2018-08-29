package com.tcrj.spv.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.WorkDailtItemEntity;
import com.tcrj.spv.views.WriteDailyActivity;

import java.util.List;

/**
 * Created by leict on 2017/12/27.
 */

public class NewDayLogItemLisAdapter extends BaseAdapter {

    private List<WorkDailtItemEntity.DataBean> entity;
    private Context context;
    private LayoutInflater mInflater;
    WorkDailtItemEntity entity1;

    public NewDayLogItemLisAdapter(WorkDailtItemEntity entity, Context context) {
        this.entity1 = entity;
        this.entity = entity.getData();
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return entity.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
             ViewHolder holder;
             if (convertView == null) {

                 //TODO：修改了ui，星期一测试，为上传到SVN
                     convertView = mInflater.inflate(R.layout.list_item_icon_text, null);
                     holder = new ViewHolder();
                     holder.worknature = (TextView) convertView.findViewById(R.id.text_worknature);
                     holder.tv_pname = (TextView) convertView.findViewById(R.id.tv_pname);
                     holder.tv_logcontent = (TextView) convertView.findViewById(R.id.tv_logcontent);
                     holder.tv_plancontent = (TextView) convertView.findViewById(R.id.tv_plancontent);
                    holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);

                     convertView.setTag(holder);
             } else {
                     holder = (ViewHolder) convertView.getTag();
             }

            holder.tv_pname.setText(entity.get(position).getPName());
            holder.worknature.setText(entity.get(position).getWorkNature());
            holder.tv_logcontent.setText(entity.get(position).getLogContent());
            holder.tv_plancontent.setText(entity.get(position).getPlanContent());


            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入编辑详情页面
//                    toActivity(position);
                    myListener.onclik(position);
                }
            });


             return convertView;
    }

    private void toActivity(int position) {
        WorkDailtItemEntity.DataBean dataBean = entity1.getData().get(position);
        Intent _Intent = new Intent();
        _Intent.setClass(context, WriteDailyActivity.class);
        _Intent.putExtra("data",dataBean);
        context.startActivity(_Intent);
    }

    static class ViewHolder {
        TextView worknature;
        TextView tv_pname;
        TextView tv_logcontent;
        TextView tv_plancontent;
        LinearLayout layout;
    }

    private OnMyclikListener myListener;


    //接口回调
    //定义回调接口
    public interface OnMyclikListener {
        public void onclik(int v) ;
    }

    //定义供Activity调用的函数
    public void setOnclikListener(OnMyclikListener onMyclikListener) {
        this.myListener = onMyclikListener;
    }

}
