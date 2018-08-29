package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 出差申请详情
 * Created by leict on 2017/12/8.
 */

public class TravelApplyDetailEntity implements Serializable {
    /**
     * State : 1
     * Msg : 操作成功
     * travel : null
     * list : []
     * DeptName : null
     * temp : [{"ChuChaShenQingRen":"李建峰","ShenQingRenBuMen":"营销六部员工","TongXingRenYuan":"屈停停等","ChuChaShiYou":"延安新区智慧城市项目实施","MuBiaoJiHua":"协调开工","JiHuaShiJian":"2018-05-28 06:46","FanHuiRiQi":"2018-06-01 22:46","GongJi":"","MuBiaoDiZhi":"延安新区","JiaoTongGongJu":"2","JiaoTongFei":"200","ZhuSuFei":"1","QiTaZaFei":"1000","FeiYongHeJi":"1201","bumenshenhe_Note":"同意！","bumenshenhe_Checker":"陈晓娟","bumenshenhe_RDT":"2018-05-30 10:42","renliziyuanfuhe_Note":"","renliziyuanfuhe_Checker":"季虹","renliziyuanfuhe_RDT":"2018-05-30 13:36","ShiJiFanHuanShiJian":"2018-06-01 19:38","DaKaShiJian":"2018-06-04 07:55","ChuChaZongJie":"初步完成预期目标。完成章程沟通，智慧交通实施开展工作。","BuMenShenHeFanHuiShiJian":"","bumenshenhefanhuishijian_Checker":"陈晓娟","bumenshenhefanhuishijian_RDT":"2018-06-06 13:43","renzifuhefanhuishijian_Note":"","renzifuhefanhuishijian_Checker":"季虹","renzifuhefanhuishijian_RDT":"2018-06-06 16:10","caiwubaoxiao_Note":"1026.5","caiwubaoxiao_Checker":"李娜","caiwubaoxiao_RDT":"2018-06-11 15:50"}]
     */
    private int WorkId;
    private String FkNodeId;

    public TravelApplyDetailEntity(int workId, String fkNodeId) {
        this.WorkId = workId;
        this.FkNodeId = fkNodeId;
    }
    private int State;
    private String Msg;
    private Object travel;
    private Object DeptName;
    private List<?> list;
    private List<TempBean> temp;

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

    public Object getTravel() {
        return travel;
    }

    public void setTravel(Object travel) {
        this.travel = travel;
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

    public List<TempBean> getTemp() {
        return temp;
    }

    public void setTemp(List<TempBean> temp) {
        this.temp = temp;
    }

    public static class TempBean {
        /**
         * ChuChaShenQingRen : 李建峰
         * ShenQingRenBuMen : 营销六部员工
         * TongXingRenYuan : 屈停停等
         * ChuChaShiYou : 延安新区智慧城市项目实施
         * MuBiaoJiHua : 协调开工
         * JiHuaShiJian : 2018-05-28 06:46
         * FanHuiRiQi : 2018-06-01 22:46
         * GongJi :
         * MuBiaoDiZhi : 延安新区
         * JiaoTongGongJu : 2
         * JiaoTongFei : 200
         * ZhuSuFei : 1
         * QiTaZaFei : 1000
         * FeiYongHeJi : 1201
         * bumenshenhe_Note : 同意！
         * bumenshenhe_Checker : 陈晓娟
         * bumenshenhe_RDT : 2018-05-30 10:42
         * renliziyuanfuhe_Note :
         * renliziyuanfuhe_Checker : 季虹
         * renliziyuanfuhe_RDT : 2018-05-30 13:36
         * ShiJiFanHuanShiJian : 2018-06-01 19:38
         * DaKaShiJian : 2018-06-04 07:55
         * ChuChaZongJie : 初步完成预期目标。完成章程沟通，智慧交通实施开展工作。
         * BuMenShenHeFanHuiShiJian :
         * bumenshenhefanhuishijian_Checker : 陈晓娟
         * bumenshenhefanhuishijian_RDT : 2018-06-06 13:43
         * renzifuhefanhuishijian_Note :
         * renzifuhefanhuishijian_Checker : 季虹
         * renzifuhefanhuishijian_RDT : 2018-06-06 16:10
         * caiwubaoxiao_Note : 1026.5
         * caiwubaoxiao_Checker : 李娜
         * caiwubaoxiao_RDT : 2018-06-11 15:50
         */

        private String ChuChaShenQingRen;
        private String ShenQingRenBuMen;
        private String TongXingRenYuan;
        private String ChuChaShiYou;
        private String MuBiaoJiHua;
        private String JiHuaShiJian;
        private String FanHuiRiQi;
        private String GongJi;
        private String MuBiaoDiZhi;
        private String JiaoTongGongJu;
        private String JiaoTongFei;
        private String ZhuSuFei;
        private String QiTaZaFei;
        private String FeiYongHeJi;
        private String bumenshenhe_Note;
        private String bumenshenhe_Checker;
        private String bumenshenhe_RDT;
        private String renliziyuanfuhe_Note;
        private String renliziyuanfuhe_Checker;
        private String renliziyuanfuhe_RDT;
        private String ShiJiFanHuanShiJian;
        private String DaKaShiJian;
        private String ChuChaZongJie;
        private String BuMenShenHeFanHuiShiJian;
        private String bumenshenhefanhuishijian_Checker;
        private String bumenshenhefanhuishijian_RDT;
        private String renzifuhefanhuishijian_Note;
        private String renzifuhefanhuishijian_Checker;
        private String renzifuhefanhuishijian_RDT;
        private String caiwubaoxiao_Note;
        private String caiwubaoxiao_Checker;
        private String caiwubaoxiao_RDT;

        public String getChuChaShenQingRen() {
            return ChuChaShenQingRen;
        }

        public void setChuChaShenQingRen(String ChuChaShenQingRen) {
            this.ChuChaShenQingRen = ChuChaShenQingRen;
        }

        public String getShenQingRenBuMen() {
            return ShenQingRenBuMen;
        }

        public void setShenQingRenBuMen(String ShenQingRenBuMen) {
            this.ShenQingRenBuMen = ShenQingRenBuMen;
        }

        public String getTongXingRenYuan() {
            return TongXingRenYuan;
        }

        public void setTongXingRenYuan(String TongXingRenYuan) {
            this.TongXingRenYuan = TongXingRenYuan;
        }

        public String getChuChaShiYou() {
            return ChuChaShiYou;
        }

        public void setChuChaShiYou(String ChuChaShiYou) {
            this.ChuChaShiYou = ChuChaShiYou;
        }

        public String getMuBiaoJiHua() {
            return MuBiaoJiHua;
        }

        public void setMuBiaoJiHua(String MuBiaoJiHua) {
            this.MuBiaoJiHua = MuBiaoJiHua;
        }

        public String getJiHuaShiJian() {
            return JiHuaShiJian;
        }

        public void setJiHuaShiJian(String JiHuaShiJian) {
            this.JiHuaShiJian = JiHuaShiJian;
        }

        public String getFanHuiRiQi() {
            return FanHuiRiQi;
        }

        public void setFanHuiRiQi(String FanHuiRiQi) {
            this.FanHuiRiQi = FanHuiRiQi;
        }

        public String getGongJi() {
            return GongJi;
        }

        public void setGongJi(String GongJi) {
            this.GongJi = GongJi;
        }

        public String getMuBiaoDiZhi() {
            return MuBiaoDiZhi;
        }

        public void setMuBiaoDiZhi(String MuBiaoDiZhi) {
            this.MuBiaoDiZhi = MuBiaoDiZhi;
        }

        public String getJiaoTongGongJu() {
            return JiaoTongGongJu;
        }

        public void setJiaoTongGongJu(String JiaoTongGongJu) {
            this.JiaoTongGongJu = JiaoTongGongJu;
        }

        public String getJiaoTongFei() {
            return JiaoTongFei;
        }

        public void setJiaoTongFei(String JiaoTongFei) {
            this.JiaoTongFei = JiaoTongFei;
        }

        public String getZhuSuFei() {
            return ZhuSuFei;
        }

        public void setZhuSuFei(String ZhuSuFei) {
            this.ZhuSuFei = ZhuSuFei;
        }

        public String getQiTaZaFei() {
            return QiTaZaFei;
        }

        public void setQiTaZaFei(String QiTaZaFei) {
            this.QiTaZaFei = QiTaZaFei;
        }

        public String getFeiYongHeJi() {
            return FeiYongHeJi;
        }

        public void setFeiYongHeJi(String FeiYongHeJi) {
            this.FeiYongHeJi = FeiYongHeJi;
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

        public String getRenliziyuanfuhe_Note() {
            return renliziyuanfuhe_Note;
        }

        public void setRenliziyuanfuhe_Note(String renliziyuanfuhe_Note) {
            this.renliziyuanfuhe_Note = renliziyuanfuhe_Note;
        }

        public String getRenliziyuanfuhe_Checker() {
            return renliziyuanfuhe_Checker;
        }

        public void setRenliziyuanfuhe_Checker(String renliziyuanfuhe_Checker) {
            this.renliziyuanfuhe_Checker = renliziyuanfuhe_Checker;
        }

        public String getRenliziyuanfuhe_RDT() {
            return renliziyuanfuhe_RDT;
        }

        public void setRenliziyuanfuhe_RDT(String renliziyuanfuhe_RDT) {
            this.renliziyuanfuhe_RDT = renliziyuanfuhe_RDT;
        }

        public String getShiJiFanHuanShiJian() {
            return ShiJiFanHuanShiJian;
        }

        public void setShiJiFanHuanShiJian(String ShiJiFanHuanShiJian) {
            this.ShiJiFanHuanShiJian = ShiJiFanHuanShiJian;
        }

        public String getDaKaShiJian() {
            return DaKaShiJian;
        }

        public void setDaKaShiJian(String DaKaShiJian) {
            this.DaKaShiJian = DaKaShiJian;
        }

        public String getChuChaZongJie() {
            return ChuChaZongJie;
        }

        public void setChuChaZongJie(String ChuChaZongJie) {
            this.ChuChaZongJie = ChuChaZongJie;
        }

        public String getBuMenShenHeFanHuiShiJian() {
            return BuMenShenHeFanHuiShiJian;
        }

        public void setBuMenShenHeFanHuiShiJian(String BuMenShenHeFanHuiShiJian) {
            this.BuMenShenHeFanHuiShiJian = BuMenShenHeFanHuiShiJian;
        }

        public String getBumenshenhefanhuishijian_Checker() {
            return bumenshenhefanhuishijian_Checker;
        }

        public void setBumenshenhefanhuishijian_Checker(String bumenshenhefanhuishijian_Checker) {
            this.bumenshenhefanhuishijian_Checker = bumenshenhefanhuishijian_Checker;
        }

        public String getBumenshenhefanhuishijian_RDT() {
            return bumenshenhefanhuishijian_RDT;
        }

        public void setBumenshenhefanhuishijian_RDT(String bumenshenhefanhuishijian_RDT) {
            this.bumenshenhefanhuishijian_RDT = bumenshenhefanhuishijian_RDT;
        }

        public String getRenzifuhefanhuishijian_Note() {
            return renzifuhefanhuishijian_Note;
        }

        public void setRenzifuhefanhuishijian_Note(String renzifuhefanhuishijian_Note) {
            this.renzifuhefanhuishijian_Note = renzifuhefanhuishijian_Note;
        }

        public String getRenzifuhefanhuishijian_Checker() {
            return renzifuhefanhuishijian_Checker;
        }

        public void setRenzifuhefanhuishijian_Checker(String renzifuhefanhuishijian_Checker) {
            this.renzifuhefanhuishijian_Checker = renzifuhefanhuishijian_Checker;
        }

        public String getRenzifuhefanhuishijian_RDT() {
            return renzifuhefanhuishijian_RDT;
        }

        public void setRenzifuhefanhuishijian_RDT(String renzifuhefanhuishijian_RDT) {
            this.renzifuhefanhuishijian_RDT = renzifuhefanhuishijian_RDT;
        }

        public String getCaiwubaoxiao_Note() {
            return caiwubaoxiao_Note;
        }

        public void setCaiwubaoxiao_Note(String caiwubaoxiao_Note) {
            this.caiwubaoxiao_Note = caiwubaoxiao_Note;
        }

        public String getCaiwubaoxiao_Checker() {
            return caiwubaoxiao_Checker;
        }

        public void setCaiwubaoxiao_Checker(String caiwubaoxiao_Checker) {
            this.caiwubaoxiao_Checker = caiwubaoxiao_Checker;
        }

        public String getCaiwubaoxiao_RDT() {
            return caiwubaoxiao_RDT;
        }

        public void setCaiwubaoxiao_RDT(String caiwubaoxiao_RDT) {
            this.caiwubaoxiao_RDT = caiwubaoxiao_RDT;
        }
    }


//    /**
//     * State : 1
//     * Msg : 操作成功
//     * list : [{"ChuChaShenQingRen":"雷陈涛","ShenQingRenBuMen":"软件开发部","TongXingRenYuan":"无","ChuChaShiYou":"跟兰州联合畅想公司沟通国土资源电子政务系统需求","MuBiaoJiHua":"给客户讲解我公司国土资源电子政务系统的流程及模块功能。","JiHuaShiJian":"2016-03-01 03:53","FanHuiRiQi":"2016-03-03 00:00","GongJi":"","MuBiaoDiZhi":"甘肃-兰州联合畅想公司","JiaoTongGongJu":"1","JiaoTongFei":"0","ZhuSuFei":"0","QiTaZaFei":"0","FeiYongHeJi":"0","bumenshenhe_Note":"同意","bumenshenhe_Checker":"李苏培","bumenshenhe_RDT":"2016-02-29 19:12","renliziyuanfuhe_Note":"同意","renliziyuanfuhe_Checker":"刘文涛","renliziyuanfuhe_RDT":"2016-03-01 08:43","ShiJiFanHuanShiJian":"2016-03-02 06:00","DaKaShiJian":"2016-03-02 07:12","ChuChaZongJie":"","BuMenShenHeFanHuiShiJian":"同意","bumenshenhefanhuishijian_Checker":"李苏培","bumenshenhefanhuishijian_RDT":"2016-03-02 10:18","renzifuhefanhuishijian_Note":"确认","renzifuhefanhuishijian_Checker":"刘文涛","renzifuhefanhuishijian_RDT":"2016-03-02 10:18","caiwubaoxiao_Note":"709","caiwubaoxiao_Checker":"韩跳跳","caiwubaoxiao_RDT":"2016-04-14 19:35"}]
//     */
//
//    private int State;
//    private String Msg;
//    private List<ListBean> temp;
//    private int WorkId;
//    private String FkNodeId;
//
//    public TravelApplyDetailEntity(int workId, String fkNodeId) {
//        this.WorkId = workId;
//        this.FkNodeId = fkNodeId;
//    }
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
//        return temp;
//    }
//
//    public void setList(List<ListBean> list) {
//        this.temp = list;
//    }
//
//    public static class ListBean {
//        /**
//         * ChuChaShenQingRen : 雷陈涛
//         * ShenQingRenBuMen : 软件开发部
//         * TongXingRenYuan : 无
//         * ChuChaShiYou : 跟兰州联合畅想公司沟通国土资源电子政务系统需求
//         * MuBiaoJiHua : 给客户讲解我公司国土资源电子政务系统的流程及模块功能。
//         * JiHuaShiJian : 2016-03-01 03:53
//         * FanHuiRiQi : 2016-03-03 00:00
//         * GongJi :
//         * MuBiaoDiZhi : 甘肃-兰州联合畅想公司
//         * JiaoTongGongJu : 1
//         * JiaoTongFei : 0
//         * ZhuSuFei : 0
//         * QiTaZaFei : 0
//         * FeiYongHeJi : 0
//         * bumenshenhe_Note : 同意
//         * bumenshenhe_Checker : 李苏培
//         * bumenshenhe_RDT : 2016-02-29 19:12
//         * renliziyuanfuhe_Note : 同意
//         * renliziyuanfuhe_Checker : 刘文涛
//         * renliziyuanfuhe_RDT : 2016-03-01 08:43
//         * ShiJiFanHuanShiJian : 2016-03-02 06:00
//         * DaKaShiJian : 2016-03-02 07:12
//         * ChuChaZongJie :
//         * BuMenShenHeFanHuiShiJian : 同意
//         * bumenshenhefanhuishijian_Checker : 李苏培
//         * bumenshenhefanhuishijian_RDT : 2016-03-02 10:18
//         * renzifuhefanhuishijian_Note : 确认
//         * renzifuhefanhuishijian_Checker : 刘文涛
//         * renzifuhefanhuishijian_RDT : 2016-03-02 10:18
//         * caiwubaoxiao_Note : 709
//         * caiwubaoxiao_Checker : 韩跳跳
//         * caiwubaoxiao_RDT : 2016-04-14 19:35
//         */
//
//        private String ChuChaShenQingRen;
//        private String ShenQingRenBuMen;
//        private String TongXingRenYuan;
//        private String ChuChaShiYou;
//        private String MuBiaoJiHua;
//        private String JiHuaShiJian;
//        private String FanHuiRiQi;
//        private String GongJi;
//        private String MuBiaoDiZhi;
//        private String JiaoTongGongJu;
//        private String JiaoTongFei;
//        private String ZhuSuFei;
//        private String QiTaZaFei;
//        private String FeiYongHeJi;
//        private String bumenshenhe_Note;
//        private String bumenshenhe_Checker;
//        private String bumenshenhe_RDT;
//        private String renliziyuanfuhe_Note;
//        private String renliziyuanfuhe_Checker;
//        private String renliziyuanfuhe_RDT;
//        private String ShiJiFanHuanShiJian;
//        private String DaKaShiJian;
//        private String ChuChaZongJie;
//        private String BuMenShenHeFanHuiShiJian;
//        private String bumenshenhefanhuishijian_Checker;
//        private String bumenshenhefanhuishijian_RDT;
//        private String renzifuhefanhuishijian_Note;
//        private String renzifuhefanhuishijian_Checker;
//        private String renzifuhefanhuishijian_RDT;
//        private String caiwubaoxiao_Note;
//        private String caiwubaoxiao_Checker;
//        private String caiwubaoxiao_RDT;
//
//        public String getChuChaShenQingRen() {
//            return ChuChaShenQingRen;
//        }
//
//        public void setChuChaShenQingRen(String ChuChaShenQingRen) {
//            this.ChuChaShenQingRen = ChuChaShenQingRen;
//        }
//
//        public String getShenQingRenBuMen() {
//            return ShenQingRenBuMen;
//        }
//
//        public void setShenQingRenBuMen(String ShenQingRenBuMen) {
//            this.ShenQingRenBuMen = ShenQingRenBuMen;
//        }
//
//        public String getTongXingRenYuan() {
//            return TongXingRenYuan;
//        }
//
//        public void setTongXingRenYuan(String TongXingRenYuan) {
//            this.TongXingRenYuan = TongXingRenYuan;
//        }
//
//        public String getChuChaShiYou() {
//            return ChuChaShiYou;
//        }
//
//        public void setChuChaShiYou(String ChuChaShiYou) {
//            this.ChuChaShiYou = ChuChaShiYou;
//        }
//
//        public String getMuBiaoJiHua() {
//            return MuBiaoJiHua;
//        }
//
//        public void setMuBiaoJiHua(String MuBiaoJiHua) {
//            this.MuBiaoJiHua = MuBiaoJiHua;
//        }
//
//        public String getJiHuaShiJian() {
//            return JiHuaShiJian;
//        }
//
//        public void setJiHuaShiJian(String JiHuaShiJian) {
//            this.JiHuaShiJian = JiHuaShiJian;
//        }
//
//        public String getFanHuiRiQi() {
//            return FanHuiRiQi;
//        }
//
//        public void setFanHuiRiQi(String FanHuiRiQi) {
//            this.FanHuiRiQi = FanHuiRiQi;
//        }
//
//        public String getGongJi() {
//            return GongJi;
//        }
//
//        public void setGongJi(String GongJi) {
//            this.GongJi = GongJi;
//        }
//
//        public String getMuBiaoDiZhi() {
//            return MuBiaoDiZhi;
//        }
//
//        public void setMuBiaoDiZhi(String MuBiaoDiZhi) {
//            this.MuBiaoDiZhi = MuBiaoDiZhi;
//        }
//
//        public String getJiaoTongGongJu() {
//            return JiaoTongGongJu;
//        }
//
//        public void setJiaoTongGongJu(String JiaoTongGongJu) {
//            this.JiaoTongGongJu = JiaoTongGongJu;
//        }
//
//        public String getJiaoTongFei() {
//            return JiaoTongFei;
//        }
//
//        public void setJiaoTongFei(String JiaoTongFei) {
//            this.JiaoTongFei = JiaoTongFei;
//        }
//
//        public String getZhuSuFei() {
//            return ZhuSuFei;
//        }
//
//        public void setZhuSuFei(String ZhuSuFei) {
//            this.ZhuSuFei = ZhuSuFei;
//        }
//
//        public String getQiTaZaFei() {
//            return QiTaZaFei;
//        }
//
//        public void setQiTaZaFei(String QiTaZaFei) {
//            this.QiTaZaFei = QiTaZaFei;
//        }
//
//        public String getFeiYongHeJi() {
//            return FeiYongHeJi;
//        }
//
//        public void setFeiYongHeJi(String FeiYongHeJi) {
//            this.FeiYongHeJi = FeiYongHeJi;
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
//        public String getRenliziyuanfuhe_Note() {
//            return renliziyuanfuhe_Note;
//        }
//
//        public void setRenliziyuanfuhe_Note(String renliziyuanfuhe_Note) {
//            this.renliziyuanfuhe_Note = renliziyuanfuhe_Note;
//        }
//
//        public String getRenliziyuanfuhe_Checker() {
//            return renliziyuanfuhe_Checker;
//        }
//
//        public void setRenliziyuanfuhe_Checker(String renliziyuanfuhe_Checker) {
//            this.renliziyuanfuhe_Checker = renliziyuanfuhe_Checker;
//        }
//
//        public String getRenliziyuanfuhe_RDT() {
//            return renliziyuanfuhe_RDT;
//        }
//
//        public void setRenliziyuanfuhe_RDT(String renliziyuanfuhe_RDT) {
//            this.renliziyuanfuhe_RDT = renliziyuanfuhe_RDT;
//        }
//
//        public String getShiJiFanHuanShiJian() {
//            return ShiJiFanHuanShiJian;
//        }
//
//        public void setShiJiFanHuanShiJian(String ShiJiFanHuanShiJian) {
//            this.ShiJiFanHuanShiJian = ShiJiFanHuanShiJian;
//        }
//
//        public String getDaKaShiJian() {
//            return DaKaShiJian;
//        }
//
//        public void setDaKaShiJian(String DaKaShiJian) {
//            this.DaKaShiJian = DaKaShiJian;
//        }
//
//        public String getChuChaZongJie() {
//            return ChuChaZongJie;
//        }
//
//        public void setChuChaZongJie(String ChuChaZongJie) {
//            this.ChuChaZongJie = ChuChaZongJie;
//        }
//
//        public String getBuMenShenHeFanHuiShiJian() {
//            return BuMenShenHeFanHuiShiJian;
//        }
//
//        public void setBuMenShenHeFanHuiShiJian(String BuMenShenHeFanHuiShiJian) {
//            this.BuMenShenHeFanHuiShiJian = BuMenShenHeFanHuiShiJian;
//        }
//
//        public String getBumenshenhefanhuishijian_Checker() {
//            return bumenshenhefanhuishijian_Checker;
//        }
//
//        public void setBumenshenhefanhuishijian_Checker(String bumenshenhefanhuishijian_Checker) {
//            this.bumenshenhefanhuishijian_Checker = bumenshenhefanhuishijian_Checker;
//        }
//
//        public String getBumenshenhefanhuishijian_RDT() {
//            return bumenshenhefanhuishijian_RDT;
//        }
//
//        public void setBumenshenhefanhuishijian_RDT(String bumenshenhefanhuishijian_RDT) {
//            this.bumenshenhefanhuishijian_RDT = bumenshenhefanhuishijian_RDT;
//        }
//
//        public String getRenzifuhefanhuishijian_Note() {
//            return renzifuhefanhuishijian_Note;
//        }
//
//        public void setRenzifuhefanhuishijian_Note(String renzifuhefanhuishijian_Note) {
//            this.renzifuhefanhuishijian_Note = renzifuhefanhuishijian_Note;
//        }
//
//        public String getRenzifuhefanhuishijian_Checker() {
//            return renzifuhefanhuishijian_Checker;
//        }
//
//        public void setRenzifuhefanhuishijian_Checker(String renzifuhefanhuishijian_Checker) {
//            this.renzifuhefanhuishijian_Checker = renzifuhefanhuishijian_Checker;
//        }
//
//        public String getRenzifuhefanhuishijian_RDT() {
//            return renzifuhefanhuishijian_RDT;
//        }
//
//        public void setRenzifuhefanhuishijian_RDT(String renzifuhefanhuishijian_RDT) {
//            this.renzifuhefanhuishijian_RDT = renzifuhefanhuishijian_RDT;
//        }
//
//        public String getCaiwubaoxiao_Note() {
//            return caiwubaoxiao_Note;
//        }
//
//        public void setCaiwubaoxiao_Note(String caiwubaoxiao_Note) {
//            this.caiwubaoxiao_Note = caiwubaoxiao_Note;
//        }
//
//        public String getCaiwubaoxiao_Checker() {
//            return caiwubaoxiao_Checker;
//        }
//
//        public void setCaiwubaoxiao_Checker(String caiwubaoxiao_Checker) {
//            this.caiwubaoxiao_Checker = caiwubaoxiao_Checker;
//        }
//
//        public String getCaiwubaoxiao_RDT() {
//            return caiwubaoxiao_RDT;
//        }
//
//        public void setCaiwubaoxiao_RDT(String caiwubaoxiao_RDT) {
//            this.caiwubaoxiao_RDT = caiwubaoxiao_RDT;
//        }
//    }
}
