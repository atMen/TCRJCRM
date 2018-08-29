package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.model.dialogListviewInfo;
import com.tcrj.spv.views.adapter.DialogListAdapter;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Api;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by leict on 2018/7/25.
 */

public class selectListviewDialog extends Dialog {

        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private String nodeId;
        private int workId;
        private int jdID;//节点id

        private String title;
        private EditText sptj;
        private ListView listView;

        private selectListviewDialog(Builder builder) {
            super(builder.context);
            context = builder.context;
            height = builder.height;
            width = builder.width;
            cancelTouchout = builder.cancelTouchout;
            title = builder.title;
            workId = builder.workId;
            nodeId = builder.nodeId;
        }


        private selectListviewDialog(Builder builder, int resStyle) {
            super(builder.context, resStyle);
            context = builder.context;
            height = builder.height;
            width = builder.width;
            cancelTouchout = builder.cancelTouchout;
            title = builder.title;
            workId = builder.workId;
            nodeId = builder.nodeId;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.dialog_select_list);

            initview();
            loadData();

            setCanceledOnTouchOutside(cancelTouchout);

            Window win = getWindow();
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.width= WindowManager.LayoutParams.MATCH_PARENT;
            lp.height= WindowManager.LayoutParams.WRAP_CONTENT;
            win.getDecorView().setPadding(25, 0, 25, 0);
            win.setAttributes(lp);
        }

    private Api api;
    public void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApplication.getConfig())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getNewLeaveCheckDialogList(new dialogListviewInfo(4,workId, nodeId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<dialogListviewInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","onError"+e.toString());
                    }

                    @Override
                    public void onNext(dialogListviewInfo entity) {
                        Log.e("TAG","onNext");

                        setListData(entity);
                    }
                });
    }


    DialogListAdapter adapterlist;
    private void setListData(dialogListviewInfo entity) {
        List<dialogListviewInfo.NodesBean> nodes = entity.getNodes();
        if(nodes != null && nodes.size() > 0){

            //获取节点选择默认第一项的id
            jdID =nodes.get(0).getID();
            adapterlist = new DialogListAdapter(context);
            adapterlist.setData(entity);
            adapterlist.setSelectItem(0);
            listView.setAdapter(adapterlist);


        }

    }

    private void initview() {

        sptj = (EditText)findViewById(R.id.edt_spyj);
        listView = (ListView) findViewById(R.id.dialog_istview);
        TextView viewBytitle = (TextView) findViewById(R.id.title_dialog);

        Button btn_sure = (Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(new clickListener());

        viewBytitle.setText(title);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogListviewInfo.NodesBean itemList = (dialogListviewInfo.NodesBean) adapterlist.getItem(position);
                jdID = itemList.getID();
                adapterlist.setSelectItem(position);
                adapterlist.notifyDataSetChanged();
            }
        });



    }

    public View getEditText(){
        return sptj;
    }

    public static final class Builder {

            private Context context;
            private int height, width;
            private boolean cancelTouchout;
            private int resStyle = -1;
            private String title;
        private String nodeId;
        private int workId;


            public Builder(Context context) {
                this.context = context;
            }



            public Builder setTitle(String s) {
                title = s;
                return this;
            }

        public Builder setWorkId(int work) {
            workId = work;
            return this;
        }

        public Builder setNodeId(String node) {
            nodeId = node;
            return this;
        }

            public Builder heightpx(int val) {
                height = val;
                return this;
            }

            public Builder widthpx(int val) {
                width = val;
                return this;
            }


            public Builder heightDimenRes(int dimenRes) {
                height = context.getResources().getDimensionPixelOffset(dimenRes);
                return this;
            }

            public Builder widthDimenRes(int dimenRes) {
                width = context.getResources().getDimensionPixelOffset(dimenRes);
                return this;
            }

            public Builder style(int resStyle) {
                this.resStyle = resStyle;
                return this;
            }

            public Builder cancelTouchout(boolean val) {
                cancelTouchout = val;
                return this;
            }

//            public Builder addViewOnclick(int viewRes,View.OnClickListener listener){
//                view.findViewById(viewRes).setOnClickListener(listener);
//                return this;
//            }


            public selectListviewDialog build() {
                if (resStyle != -1) {
                    return new selectListviewDialog(this, resStyle);
                } else {
                    return new selectListviewDialog(this);
                }
            }
        }


    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
               this.clickListenerInterface = clickListenerInterface;
           }



   private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
                  // TODO Auto-generated method stub
                   int id = v.getId();
                     switch (id) {
                        case R.id.btn_sure:

                            String info = sptj.getText().toString().trim();
                            //获取审批意见和节点idT
                            clickListenerInterface.doConfirm(info,jdID);


                                 break;
//                            case R.id.cancel:
//                               clickListenerInterface.doCancel();
//                                 break;

                            default:
                                break;
                          }
                 }

     };


    private ClickListenerInterface clickListenerInterface;
    public interface ClickListenerInterface {
     public void doConfirm(String info, int jdID);
     }

}
