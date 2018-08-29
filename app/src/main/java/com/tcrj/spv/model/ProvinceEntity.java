package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 省份
 * Created by leict on 2017/11/21.
 */

public class ProvinceEntity implements Serializable {

    private List<ProvinceListBean> ProvinceList;

    public List<ProvinceListBean> getProvinceList() {
        return ProvinceList;
    }

    public void setProvinceList(List<ProvinceListBean> ProvinceList) {
        this.ProvinceList = ProvinceList;
    }

    public static class ProvinceListBean {
        /**
         * code : 110000
         * name : 北京市
         */

        private String code;
        private String name;
        private int flag;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

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
    }
}
