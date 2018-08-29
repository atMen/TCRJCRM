package com.tcrj.spv.views;

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
import com.tcrj.spv.views.adapter.FinishedAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.utils.Utils;
import com.tcrj.spv.views.xlist.XListView;

/**
 * 完结审批
 * Created by leict on 2017/11/3.
 */

public class FinishApprovalFragment extends Fragment implements ApprovalCallBack.View {
    private ApprovalCallBack.Presenter presenter;
    private View view;
    private XListView listView;
    private FinishedAdapter adapter = null;

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
        ((BaseActivity) getContext()).showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        ((BaseActivity) getContext()).dismisProgressDialog();
    }

    @Override
    public void getData(ApprovalEntity entity) {
        if (!Utils.isStringEmpty(entity.getListInfo())) {
            adapter = new FinishedAdapter(getContext());
            adapter.setData(entity.getListInfo());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ApprovalEntity.ListInfoBean entity = (ApprovalEntity.ListInfoBean) adapter.getItem(position - 1);
                    if (entity.getFlowType().equals("请假申请") || entity.getFlowType().equals("请假流程")) {

                        if(entity.getFkNodeId().substring(0,2).equals("22"))
                        {
                            Intent intent = new Intent(getContext(), LeaveApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getContext(), NewLeaveApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivity(intent);
                        }


                    } else if (entity.getFlowType().equals("出差流程") || entity.getFlowType().equals("出差申请")) {
                        if(entity.getFkNodeId().substring(0,2).equals("24")) {
                            Intent intent = new Intent(getContext(), TravelApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getContext(), NewTravelApplyDetailActivity.class);
                            intent.putExtra("WorkID", entity.getWorkId());
                            intent.putExtra("NodeID", entity.getFkNodeId());
                            startActivity(intent);
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

        if(isVisibleToUser){
            //TODO now it's visible to user
            Log.e("TAG","3可见");
            new ApprovalPresenter(FinishApprovalFragment.this, 3);
            presenter.start();
        } else {
            //TODO now it's invisible to user
            Log.e("TAG","3不可见");
        }


    }
}
