package com.tcrj.spv.views;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.ApprovalCallBack;
import com.tcrj.spv.model.ApprovalEntity;
import com.tcrj.spv.presenter.ApprovalPresenter;
import com.tcrj.spv.views.adapter.LeaderExamineAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogLoading;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

/**
 * 待办审批
 * Created by leict on 2017/11/3.
 */

public class PendApprovalFragment extends Fragment implements ApprovalCallBack.View {
    private ApprovalCallBack.Presenter presenter;
    private View view;
    private XListView listView;
    private LeaderExamineAdapter adapter = null;
    private Context mContext;

    public PendApprovalFragment() {

    }
    public PendApprovalFragment(LeaderExamineActivity leaderExamineActivity) {
        mContext = leaderExamineActivity;
        Log.e("TAG","PendApprovalFragment()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_approval, container, false);
        listView = (XListView) view.findViewById(R.id.pend_listview);
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);
        return view;
    }

    @Override
    public void setPresenter(ApprovalCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    @Override
    public void getData(ApprovalEntity entity) {
        if (!Utils.isStringEmpty(entity.getListInfo())) {
            adapter = new LeaderExamineAdapter(getContext());
            adapter.setData(entity.getListInfo());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ApprovalEntity.ListInfoBean entity = (ApprovalEntity.ListInfoBean) adapter.getItem(position - 1);
//                    String dUrl = entity.getDUrl();
//                    Log.e("TAG","dUrl:"+dUrl);
//                    Intent Fwintent = new Intent(getContext(), MyApprovalDetailsActivity.class);
//                    Fwintent.putExtra("Title", entity.getFlowType());
//                    Fwintent.putExtra("Url", entity.getDUrl());
//                    startActivity(Fwintent);

                    //原生实现
                    if (entity.getFlowType().equals("请假申请") || entity.getFlowType().equals("请假流程")) {


                        if(entity.getFkNodeId().substring(0,2).equals("22"))
                        {
                            Intent intent = new Intent(getContext(), LeaveApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getContext(), NewNowLeaveApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivityForResult(intent, 1);
                        }
                    } else if (entity.getFlowType().equals("出差流程") || entity.getFlowType().equals("出差申请")) {
                        if(entity.getFkNodeId().substring(0,2).equals("24")) {
                            Intent intent = new Intent(getContext(), TravelApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getContext(), NewNowTravelApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivityForResult(intent, 1);
                        }
                    } else {
                        ((BaseActivity) getContext()).displayToast("请在PC端口查看详情");
                        return;
                    }
                }
            });
        }
    }

    @Override
    public String setParamStaffNo() {
        return BaseApplication.getUserInfoEntity().getEntity().getUserTitle();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("TAG","setUserVisibleHint");
            if(isVisibleToUser){
                //TODO now it's visible to user
                Log.e("TAG","可见");
                new ApprovalPresenter(PendApprovalFragment.this, 1);
                presenter.start();
            } else {
                //TODO now it's invisible to user
                Log.e("TAG","不可见");
            }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==4){
            if(data != null) {
                new ApprovalPresenter(PendApprovalFragment.this, 1);
                presenter.start();
            }
        }
    }

    public DialogLoading loading;
    public void showProgressDialog() {
        loading = DialogLoading.getInstance(mContext);
        loading.setMessage("正在加载...")
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }

    /**
     * 关闭进度条
     */
    public void dismisProgressDialog() {
        if (loading == null) {
            return;
        } else {
            if (loading.isShowing()) {
                loading.dismiss();
                loading = null;
            }
        }
    }
}
