package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 意向产品
 * Created by leict on 2017/11/22.
 */

public class IntentProductEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * RecordCount : 26
     * ListInfo : [{"PId":38,"PName":"点金档案管理系统软件"}]
     */

    private int State;
    private String Msg;
    private int RecordCount;
    private List<ListInfoBean> ListInfo;

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int RecordCount) {
        this.RecordCount = RecordCount;
    }

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public static class ListInfoBean {
        /**
         * PId : 38
         * PName : 点金档案管理系统软件
         */

        private int PId;
        private String PName;
        private int flag;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getPId() {
            return PId;
        }

        public void setPId(int PId) {
            this.PId = PId;
        }

        public String getPName() {
            return PName;
        }

        public void setPName(String PName) {
            this.PName = PName;
        }
    }
}
