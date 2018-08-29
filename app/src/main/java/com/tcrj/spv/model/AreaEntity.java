package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 区域
 * Created by leict on 2017/11/21.
 */

public class AreaEntity implements Serializable {

    private List<AreaListBean> AreaList;

    public List<AreaListBean> getAreaList() {
        return AreaList;
    }

    public void setAreaList(List<AreaListBean> AreaList) {
        this.AreaList = AreaList;
    }

    public static class AreaListBean {
        /**
         * ccode : 110101
         * name : 东城区
         * acode : 110100
         */

        private String ccode;
        private String name;
        private String acode;

        public String getCcode() {
            return ccode;
        }

        public void setCcode(String ccode) {
            this.ccode = ccode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAcode() {
            return acode;
        }

        public void setAcode(String acode) {
            this.acode = acode;
        }
    }
}
