package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 请假申请：详情数据
 * Created by leict on 2017/12/7.
 */

public class LeaveApplyDetailEntity implements Serializable {
    /**
     * State : 1
     * Msg : 操作成功
     * list : []
     * leaveInfo : null
     * DeptName : null
     * leaves : [{"XingMing":"韩天琛","BuMen":"产品三部","YuanGongBianHao":"A00423","QingJiaLeiBie":"0","QingJiaShiJian":"2018-05-03 16:40","QingJiaJieShu":"2018-05-03 18:00","gongjitianshu_Note":"0.17","QingJiaShiYou":"外出办理私事","bumenshenhe_Note":"同意","bumenshenhe_Checker":"王伟平","bumenshenhe_RDT":"2018-05-03 16:45","ldsh_Note":"","ldsh_Checker":"","ldsh_RDT":"","laobanshenhe_Note":"","laobanshenhe_Checker":"","laobanshenhe_RDT":"","renlishencha_Note":"","renlishencha_Checker":"","renlishencha_RDT":"","zjlsh_Note":"","zjlsh_Checker":"","zjlsh_RDT":"","renzishenhe_Note":"","renzishenhe_Checker":"","renzishenhe_RDT":""}]
     */
    private int WorkId;
        private String FkNodeId;

    public LeaveApplyDetailEntity(int WorkId, String FkNodeId) {
        this.WorkId = WorkId;
        this.FkNodeId = FkNodeId;
    }
    private int State;
    private String Msg;
    private Object leaveInfo;
    private Object DeptName;
    private List<?> list;
    private List<LeavesBean> leaves;

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

    public Object getLeaveInfo() {
        return leaveInfo;
    }

    public void setLeaveInfo(Object leaveInfo) {
        this.leaveInfo = leaveInfo;
    }

    public Object getDeptName() {
        return DeptName;
    }

    public void setDeptName(Object DeptName) {
        this.DeptName = DeptName;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<LeavesBean> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeavesBean> leaves) {
        this.leaves = leaves;
    }

    public static class LeavesBean {
        /**
         * XingMing : 韩天琛
         * BuMen : 产品三部
         * YuanGongBianHao : A00423
         * QingJiaLeiBie : 0
         * QingJiaShiJian : 2018-05-03 16:40
         * QingJiaJieShu : 2018-05-03 18:00
         * gongjitianshu_Note : 0.17
         * QingJiaShiYou : 外出办理私事
         * bumenshenhe_Note : 同意
         * bumenshenhe_Checker : 王伟平
         * bumenshenhe_RDT : 2018-05-03 16:45
         * ldsh_Note :
         * ldsh_Checker :
         * ldsh_RDT :
         * laobanshenhe_Note :
         * laobanshenhe_Checker :
         * laobanshenhe_RDT :
         * renlishencha_Note :
         * renlishencha_Checker :
         * renlishencha_RDT :
         * zjlsh_Note :
         * zjlsh_Checker :
         * zjlsh_RDT :
         * renzishenhe_Note :
         * renzishenhe_Checker :
         * renzishenhe_RDT :
         */

        private String XingMing;
        private String BuMen;
        private String YuanGongBianHao;
        private String QingJiaLeiBie;
        private String QingJiaShiJian;
        private String QingJiaJieShu;
        private String gongjitianshu_Note;
        private String QingJiaShiYou;
        private String bumenshenhe_Note;
        private String bumenshenhe_Checker;
        private String bumenshenhe_RDT;
        private String ldsh_Note;
        private String ldsh_Checker;
        private String ldsh_RDT;
        private String laobanshenhe_Note;
        private String laobanshenhe_Checker;
        private String laobanshenhe_RDT;
        private String renlishencha_Note;
        private String renlishencha_Checker;
        private String renlishencha_RDT;
        private String zjlsh_Note;
        private String zjlsh_Checker;
        private String zjlsh_RDT;
        private String renzishenhe_Note;
        private String renzishenhe_Checker;
        private String renzishenhe_RDT;

        public String getXingMing() {
            return XingMing;
        }

        public void setXingMing(String XingMing) {
            this.XingMing = XingMing;
        }

        public String getBuMen() {
            return BuMen;
        }

        public void setBuMen(String BuMen) {
            this.BuMen = BuMen;
        }

        public String getYuanGongBianHao() {
            return YuanGongBianHao;
        }

        public void setYuanGongBianHao(String YuanGongBianHao) {
            this.YuanGongBianHao = YuanGongBianHao;
        }

        public String getQingJiaLeiBie() {
            return QingJiaLeiBie;
        }

        public void setQingJiaLeiBie(String QingJiaLeiBie) {
            this.QingJiaLeiBie = QingJiaLeiBie;
        }

        public String getQingJiaShiJian() {
            return QingJiaShiJian;
        }

        public void setQingJiaShiJian(String QingJiaShiJian) {
            this.QingJiaShiJian = QingJiaShiJian;
        }

        public String getQingJiaJieShu() {
            return QingJiaJieShu;
        }

        public void setQingJiaJieShu(String QingJiaJieShu) {
            this.QingJiaJieShu = QingJiaJieShu;
        }

        public String getGongjitianshu_Note() {
            return gongjitianshu_Note;
        }

        public void setGongjitianshu_Note(String gongjitianshu_Note) {
            this.gongjitianshu_Note = gongjitianshu_Note;
        }

        public String getQingJiaShiYou() {
            return QingJiaShiYou;
        }

        public void setQingJiaShiYou(String QingJiaShiYou) {
            this.QingJiaShiYou = QingJiaShiYou;
        }

        public String getBumenshenhe_Note() {
            return bumenshenhe_Note;
        }

        public void setBumenshenhe_Note(String bumenshenhe_Note) {
            this.bumenshenhe_Note = bumenshenhe_Note;
        }

        public String getBumenshenhe_Checker() {
            return bumenshenhe_Checker;
        }

        public void setBumenshenhe_Checker(String bumenshenhe_Checker) {
            this.bumenshenhe_Checker = bumenshenhe_Checker;
        }

        public String getBumenshenhe_RDT() {
            return bumenshenhe_RDT;
        }

        public void setBumenshenhe_RDT(String bumenshenhe_RDT) {
            this.bumenshenhe_RDT = bumenshenhe_RDT;
        }

        public String getLdsh_Note() {
            return ldsh_Note;
        }

        public void setLdsh_Note(String ldsh_Note) {
            this.ldsh_Note = ldsh_Note;
        }

        public String getLdsh_Checker() {
            return ldsh_Checker;
        }

        public void setLdsh_Checker(String ldsh_Checker) {
            this.ldsh_Checker = ldsh_Checker;
        }

        public String getLdsh_RDT() {
            return ldsh_RDT;
        }

        public void setLdsh_RDT(String ldsh_RDT) {
            this.ldsh_RDT = ldsh_RDT;
        }

        public String getLaobanshenhe_Note() {
            return laobanshenhe_Note;
        }

        public void setLaobanshenhe_Note(String laobanshenhe_Note) {
            this.laobanshenhe_Note = laobanshenhe_Note;
        }

        public String getLaobanshenhe_Checker() {
            return laobanshenhe_Checker;
        }

        public void setLaobanshenhe_Checker(String laobanshenhe_Checker) {
            this.laobanshenhe_Checker = laobanshenhe_Checker;
        }

        public String getLaobanshenhe_RDT() {
            return laobanshenhe_RDT;
        }

        public void setLaobanshenhe_RDT(String laobanshenhe_RDT) {
            this.laobanshenhe_RDT = laobanshenhe_RDT;
        }

        public String getRenlishencha_Note() {
            return renlishencha_Note;
        }

        public void setRenlishencha_Note(String renlishencha_Note) {
            this.renlishencha_Note = renlishencha_Note;
        }

        public String getRenlishencha_Checker() {
            return renlishencha_Checker;
        }

        public void setRenlishencha_Checker(String renlishencha_Checker) {
            this.renlishencha_Checker = renlishencha_Checker;
        }

        public String getRenlishencha_RDT() {
            return renlishencha_RDT;
        }

        public void setRenlishencha_RDT(String renlishencha_RDT) {
            this.renlishencha_RDT = renlishencha_RDT;
        }

        public String getZjlsh_Note() {
            return zjlsh_Note;
        }

        public void setZjlsh_Note(String zjlsh_Note) {
            this.zjlsh_Note = zjlsh_Note;
        }

        public String getZjlsh_Checker() {
            return zjlsh_Checker;
        }

        public void setZjlsh_Checker(String zjlsh_Checker) {
            this.zjlsh_Checker = zjlsh_Checker;
        }

        public String getZjlsh_RDT() {
            return zjlsh_RDT;
        }

        public void setZjlsh_RDT(String zjlsh_RDT) {
            this.zjlsh_RDT = zjlsh_RDT;
        }

        public String getRenzishenhe_Note() {
            return renzishenhe_Note;
        }

        public void setRenzishenhe_Note(String renzishenhe_Note) {
            this.renzishenhe_Note = renzishenhe_Note;
        }

        public String getRenzishenhe_Checker() {
            return renzishenhe_Checker;
        }

        public void setRenzishenhe_Checker(String renzishenhe_Checker) {
            this.renzishenhe_Checker = renzishenhe_Checker;
        }

        public String getRenzishenhe_RDT() {
            return renzishenhe_RDT;
        }

        public void setRenzishenhe_RDT(String renzishenhe_RDT) {
            this.renzishenhe_RDT = renzishenhe_RDT;
        }
    }


//    /**
//     * State : 1
//     * Msg : 操作成功
//     * list : [{"XingMing":"仇峰峰","BuMen":"软件开发部","YuanGongBianHao":"A00088","QingJiaLeiBie":"2","QingJiaShiJian":"2017-09-13 08:30","QingJiaJieShu":"2017-09-15 18:00","gongjitianshu_Note":"3.00","QingJiaShiYou":"家人生病住院，回老家照顾","bumenshenhe_Note":"同意","bumenshenhe_Checker":"李苏培","bumenshenhe_RDT":"2017-09-20 16:45","ldsh_Note":"同意","ldsh_Checker":"李玲","ldsh_RDT":"2017-09-22 11:40","laobanshenhe_Note":"","laobanshenhe_Checker":"","laobanshenhe_RDT":"","renlishencha_Note":"","renlishencha_Checker":"","renlishencha_RDT":"","zjlsh_Note":"","zjlsh_Checker":"","zjlsh_RDT":"","renzishenhe_Note":"同意","renzishenhe_Checker":"","renzishenhe_RDT":""}]
//     */
//
//    private int State;
//    private String Msg;
//    private List<ListBean> leaves;
//
//    public int getState() {
//        return State;
//    }
//
//    public void setState(int State) {
//        this.State = State;
//    }
//
//    public String getMsg() {
//        return Msg;
//    }
//
//    public void setMsg(String Msg) {
//        this.Msg = Msg;
//    }
//
//    public List<ListBean> getList() {
//        return leaves;
//    }
//
//    public void setList(List<ListBean> list) {
//        this.leaves = list;
//    }
//
//    private int WorkId;
//    private String FkNodeId;
//
//    public LeaveApplyDetailEntity(int WorkId, String FkNodeId) {
//        this.WorkId = WorkId;
//        this.FkNodeId = FkNodeId;
//    }
//
//    public static class ListBean {
//        /**
//         * XingMing : 仇峰峰
//         * BuMen : 软件开发部
//         * YuanGongBianHao : A00088
//         * QingJiaLeiBie : 2
//         * QingJiaShiJian : 2017-09-13 08:30
//         * QingJiaJieShu : 2017-09-15 18:00
//         * gongjitianshu_Note : 3.00
//         * QingJiaShiYou : 家人生病住院，回老家照顾
//         * bumenshenhe_Note : 同意
//         * bumenshenhe_Checker : 李苏培
//         * bumenshenhe_RDT : 2017-09-20 16:45
//         * ldsh_Note : 同意
//         * ldsh_Checker : 李玲
//         * ldsh_RDT : 2017-09-22 11:40
//         * laobanshenhe_Note :
//         * laobanshenhe_Checker :
//         * laobanshenhe_RDT :
//         * renlishencha_Note :
//         * renlishencha_Checker :
//         * renlishencha_RDT :
//         * zjlsh_Note :
//         * zjlsh_Checker :
//         * zjlsh_RDT :
//         * renzishenhe_Note : 同意
//         * renzishenhe_Checker :
//         * renzishenhe_RDT :
//         */
//
//        private String XingMing;
//        private String BuMen;
//        private String YuanGongBianHao;
//        private String QingJiaLeiBie;
//        private String QingJiaShiJian;
//        private String QingJiaJieShu;
//        private String gongjitianshu_Note;
//        private String QingJiaShiYou;
//        private String bumenshenhe_Note;
//        private String bumenshenhe_Checker;
//        private String bumenshenhe_RDT;
//        private String ldsh_Note;
//        private String ldsh_Checker;
//        private String ldsh_RDT;
//        private String laobanshenhe_Note;
//        private String laobanshenhe_Checker;
//        private String laobanshenhe_RDT;
//        private String renlishencha_Note;
//        private String renlishencha_Checker;
//        private String renlishencha_RDT;
//        private String zjlsh_Note;
//        private String zjlsh_Checker;
//        private String zjlsh_RDT;
//        private String renzishenhe_Note;
//        private String renzishenhe_Checker;
//        private String renzishenhe_RDT;
//
//        public String getXingMing() {
//            return XingMing;
//        }
//
//        public void setXingMing(String XingMing) {
//            this.XingMing = XingMing;
//        }
//
//        public String getBuMen() {
//            return BuMen;
//        }
//
//        public void setBuMen(String BuMen) {
//            this.BuMen = BuMen;
//        }
//
//        public String getYuanGongBianHao() {
//            return YuanGongBianHao;
//        }
//
//        public void setYuanGongBianHao(String YuanGongBianHao) {
//            this.YuanGongBianHao = YuanGongBianHao;
//        }
//
//        public String getQingJiaLeiBie() {
//            return QingJiaLeiBie;
//        }
//
//        public void setQingJiaLeiBie(String QingJiaLeiBie) {
//            this.QingJiaLeiBie = QingJiaLeiBie;
//        }
//
//        public String getQingJiaShiJian() {
//            return QingJiaShiJian;
//        }
//
//        public void setQingJiaShiJian(String QingJiaShiJian) {
//            this.QingJiaShiJian = QingJiaShiJian;
//        }
//
//        public String getQingJiaJieShu() {
//            return QingJiaJieShu;
//        }
//
//        public void setQingJiaJieShu(String QingJiaJieShu) {
//            this.QingJiaJieShu = QingJiaJieShu;
//        }
//
//        public String getGongjitianshu_Note() {
//            return gongjitianshu_Note;
//        }
//
//        public void setGongjitianshu_Note(String gongjitianshu_Note) {
//            this.gongjitianshu_Note = gongjitianshu_Note;
//        }
//
//        public String getQingJiaShiYou() {
//            return QingJiaShiYou;
//        }
//
//        public void setQingJiaShiYou(String QingJiaShiYou) {
//            this.QingJiaShiYou = QingJiaShiYou;
//        }
//
//        public String getBumenshenhe_Note() {
//            return bumenshenhe_Note;
//        }
//
//        public void setBumenshenhe_Note(String bumenshenhe_Note) {
//            this.bumenshenhe_Note = bumenshenhe_Note;
//        }
//
//        public String getBumenshenhe_Checker() {
//            return bumenshenhe_Checker;
//        }
//
//        public void setBumenshenhe_Checker(String bumenshenhe_Checker) {
//            this.bumenshenhe_Checker = bumenshenhe_Checker;
//        }
//
//        public String getBumenshenhe_RDT() {
//            return bumenshenhe_RDT;
//        }
//
//        public void setBumenshenhe_RDT(String bumenshenhe_RDT) {
//            this.bumenshenhe_RDT = bumenshenhe_RDT;
//        }
//
//        public String getLdsh_Note() {
//            return ldsh_Note;
//        }
//
//        public void setLdsh_Note(String ldsh_Note) {
//            this.ldsh_Note = ldsh_Note;
//        }
//
//        public String getLdsh_Checker() {
//            return ldsh_Checker;
//        }
//
//        public void setLdsh_Checker(String ldsh_Checker) {
//            this.ldsh_Checker = ldsh_Checker;
//        }
//
//        public String getLdsh_RDT() {
//            return ldsh_RDT;
//        }
//
//        public void setLdsh_RDT(String ldsh_RDT) {
//            this.ldsh_RDT = ldsh_RDT;
//        }
//
//        public String getLaobanshenhe_Note() {
//            return laobanshenhe_Note;
//        }
//
//        public void setLaobanshenhe_Note(String laobanshenhe_Note) {
//            this.laobanshenhe_Note = laobanshenhe_Note;
//        }
//
//        public String getLaobanshenhe_Checker() {
//            return laobanshenhe_Checker;
//        }
//
//        public void setLaobanshenhe_Checker(String laobanshenhe_Checker) {
//            this.laobanshenhe_Checker = laobanshenhe_Checker;
//        }
//
//        public String getLaobanshenhe_RDT() {
//            return laobanshenhe_RDT;
//        }
//
//        public void setLaobanshenhe_RDT(String laobanshenhe_RDT) {
//            this.laobanshenhe_RDT = laobanshenhe_RDT;
//        }
//
//        public String getRenlishencha_Note() {
//            return renlishencha_Note;
//        }
//
//        public void setRenlishencha_Note(String renlishencha_Note) {
//            this.renlishencha_Note = renlishencha_Note;
//        }
//
//        public String getRenlishencha_Checker() {
//            return renlishencha_Checker;
//        }
//
//        public void setRenlishencha_Checker(String renlishencha_Checker) {
//            this.renlishencha_Checker = renlishencha_Checker;
//        }
//
//        public String getRenlishencha_RDT() {
//            return renlishencha_RDT;
//        }
//
//        public void setRenlishencha_RDT(String renlishencha_RDT) {
//            this.renlishencha_RDT = renlishencha_RDT;
//        }
//
//        public String getZjlsh_Note() {
//            return zjlsh_Note;
//        }
//
//        public void setZjlsh_Note(String zjlsh_Note) {
//            this.zjlsh_Note = zjlsh_Note;
//        }
//
//        public String getZjlsh_Checker() {
//            return zjlsh_Checker;
//        }
//
//        public void setZjlsh_Checker(String zjlsh_Checker) {
//            this.zjlsh_Checker = zjlsh_Checker;
//        }
//
//        public String getZjlsh_RDT() {
//            return zjlsh_RDT;
//        }
//
//        public void setZjlsh_RDT(String zjlsh_RDT) {
//            this.zjlsh_RDT = zjlsh_RDT;
//        }
//
//        public String getRenzishenhe_Note() {
//            return renzishenhe_Note;
//        }
//
//        public void setRenzishenhe_Note(String renzishenhe_Note) {
//            this.renzishenhe_Note = renzishenhe_Note;
//        }
//
//        public String getRenzishenhe_Checker() {
//            return renzishenhe_Checker;
//        }
//
//        public void setRenzishenhe_Checker(String renzishenhe_Checker) {
//            this.renzishenhe_Checker = renzishenhe_Checker;
//        }
//
//        public String getRenzishenhe_RDT() {
//            return renzishenhe_RDT;
//        }
//
//        public void setRenzishenhe_RDT(String renzishenhe_RDT) {
//            this.renzishenhe_RDT = renzishenhe_RDT;
//        }
//    }
}
