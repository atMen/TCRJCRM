package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 签到记录
 * Created by leict on 2017/11/1.
 */

public class SignRecordEntity implements Serializable {

    /**
     * State : 1
     * Msg : 操作成功
     * KaoQinList : [{"KQRQ":"2017-10-23 星期一","QDSJ":"","QDDZ":"未签到","QTSJ":"","QTDZ":"未签退"}]
     */

    public int State;
    public String Msg;
    public List<KaoQinListBean> KaoQinList;
    public int StaffID;
    public String StaffNo;
    public String lastMouth;

    public SignRecordEntity(int StaffID, String StaffNo,String lastMouth) {
        this.StaffID = StaffID;
        this.StaffNo = StaffNo;
        this.lastMouth = lastMouth;
    }

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

    public List<KaoQinListBean> getKaoQinList() {
        return KaoQinList;
    }

    public void setKaoQinList(List<KaoQinListBean> KaoQinList) {
        this.KaoQinList = KaoQinList;
    }

    public static class KaoQinListBean {
        /**
         * KQRQ : 2017-10-23 星期一
         * QDSJ : 签到时间
         * QDDZ : 未签到
         * QTSJ : 签退时间
         * QTDZ : 未签退
         */

        private String KQRQ;
        private String QDSJ;
        private String QDDZ;
        private String QTSJ;
        private String QTDZ;

        public String getKQRQ() {
            return KQRQ;
        }

        public void setKQRQ(String KQRQ) {
            this.KQRQ = KQRQ;
        }

        public String getQDSJ() {
            return QDSJ;
        }

        public void setQDSJ(String QDSJ) {
            this.QDSJ = QDSJ;
        }

        public String getQDDZ() {
            return QDDZ;
        }

        public void setQDDZ(String QDDZ) {
            this.QDDZ = QDDZ;
        }

        public String getQTSJ() {
            return QTSJ;
        }

        public void setQTSJ(String QTSJ) {
            this.QTSJ = QTSJ;
        }

        public String getQTDZ() {
            return QTDZ;
        }

        public void setQTDZ(String QTDZ) {
            this.QTDZ = QTDZ;
        }
    }
}
