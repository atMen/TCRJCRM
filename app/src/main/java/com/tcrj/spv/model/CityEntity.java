package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 市区
 * Created by leict on 2017/11/21.
 */

public class CityEntity implements Serializable {

    private List<CityListBean> CityList;

    public List<CityListBean> getCityList() {
        return CityList;
    }

    public void setCityList(List<CityListBean> CityList) {
        this.CityList = CityList;
    }

    public static class CityListBean {
        /**
         * code : 110100
         * name : 北京市
         * pcode : 110000
         */

        private String code;
        private String name;
        private String pcode;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }
    }
}
