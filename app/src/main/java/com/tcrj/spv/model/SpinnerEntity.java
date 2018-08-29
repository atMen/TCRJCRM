package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leict on 2016/1/22.
 */
public class SpinnerEntity implements Serializable {
    /**
     * State : 1
     * Msg : 操作成功!
     * ListInfo : [{"DId":"29","DName":"综合部"}]
     * ListInfo1 : [{"SId":"114","SName":"蔡孟姚","DId":"29"},{"SId":"182","SName":"测试1","DId":"29"},{"SId":"346","SName":"陈建国","DId":"29"},{"SId":"237","SName":"付雁斌","DId":"29"},{"SId":"119","SName":"苟婉蓉","DId":"29"},{"SId":"362","SName":"黄昱","DId":"29"},{"SId":"155","SName":"梁美娜","DId":"29"},{"SId":"238","SName":"马向阳","DId":"29"},{"SId":"1454","SName":"杨雪英","DId":"29"},{"SId":"351","SName":"尤亚云","DId":"29"}]
     */

    private int State;
    private String Msg;
    private int SId;

    public SpinnerEntity(int SId) {
        this.SId = SId;
    }

    public SpinnerEntity() {
    }

    private List<ListInfoBean> ListInfo;
    private List<ListInfo1Bean> ListInfo1;

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

    public List<ListInfoBean> getListInfo() {
        return ListInfo;
    }

    public void setListInfo(List<ListInfoBean> ListInfo) {
        this.ListInfo = ListInfo;
    }

    public List<ListInfo1Bean> getListInfo1() {
        return ListInfo1;
    }

    public void setListInfo1(List<ListInfo1Bean> ListInfo1) {
        this.ListInfo1 = ListInfo1;
    }

    public static class ListInfoBean {
        /**
         * DId : 29
         * DName : 综合部
         */

        private String DId;
        private String DName;

        public String getDId() {
            return DId;
        }

        public void setDId(String DId) {
            this.DId = DId;
        }

        public String getDName() {
            return DName;
        }

        public void setDName(String DName) {
            this.DName = DName;
        }
    }

    public static class ListInfo1Bean {
        /**
         * SId : 114
         * SName : 蔡孟姚
         * DId : 29
         */

        private String SId;
        private String SName;
        private String DId;

        public String getSId() {
            return SId;
        }

        public void setSId(String SId) {
            this.SId = SId;
        }

        public String getSName() {
            return SName;
        }

        public void setSName(String SName) {
            this.SName = SName;
        }

        public String getDId() {
            return DId;
        }

        public void setDId(String DId) {
            this.DId = DId;
        }
    }
    private int Id;
    private String spinnerName;
    private String cityId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    private String parentId;

    public String getSpinnerName() {
        return spinnerName;
    }

    public void setSpinnerName(String spinnerName) {
        this.spinnerName = spinnerName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }



}
