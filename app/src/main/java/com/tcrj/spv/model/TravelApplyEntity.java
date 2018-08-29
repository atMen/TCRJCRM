package com.tcrj.spv.model;

import java.util.List;

/**
 * 出差申请
 * Created by leict on 2017/11/15.
 */

public class TravelApplyEntity {

    /**
     * State : 1
     * Msg : 调用成功
     * TransportType : ["汽车","火车","高铁","飞机","自驾车","工车"]
     */

    private int State;
    private String Msg;
    private List<String> TransportType;
    private int type;
    private String FanHuiRiQi;
    private String JiHuaShiJian;
    private String MuBiaoDiZhi;
    private String QiTaZaFei;
    private String ShenQingRenBuMen;
    private String UserNo;
    private String TongXingRenYuan;
    private String ZhuSuFei;
    private String MuBiaoJiHua;
    private String JiaoTongFei;
    private String JiaoTongGongJu;
    private String ChuChaShenQingRen;
    private String ChuChaShiYou;
    private String workId;

    public TravelApplyEntity(int type, String fanHuiRiQi,
                             String jiHuaShiJian, String muBiaoDiZhi, String qiTaZaFei,
                             String shenQingRenBuMen, String userNo, String tongXingRenYuan,
                             String zhuSuFei, String muBiaoJiHua, String jiaoTongFei,
                             String jiaoTongGongJu, String chuChaShenQingRen, String chuChaShiYou, String workId) {
        this.type = type;
        this.FanHuiRiQi = fanHuiRiQi;
        this.JiHuaShiJian = jiHuaShiJian;
        this.MuBiaoDiZhi = muBiaoDiZhi;
        this.QiTaZaFei = qiTaZaFei;
        this.ShenQingRenBuMen = shenQingRenBuMen;
        this.UserNo = userNo;
        this.TongXingRenYuan = tongXingRenYuan;
        this.ZhuSuFei = zhuSuFei;
        this.MuBiaoJiHua = muBiaoJiHua;
        this.JiaoTongFei = jiaoTongFei;
        this.JiaoTongGongJu = jiaoTongGongJu;
        this.ChuChaShenQingRen = chuChaShenQingRen;
        this.ChuChaShiYou = chuChaShiYou;
        this.workId = workId;
    }

    public TravelApplyEntity(int type) {
        this.type = type;
    }

    public TravelApplyEntity(int type, String fanHuiRiQi, String jiHuaShiJian, String muBiaoDiZhi, String qiTaZaFei, String shenQingRenBuMen, String userNo, String tongXingRenYuan, String zhuSuFei, String muBiaoJiHua, String jiaoTongFei, String jiaoTongGongJu, String chuChaShenQingRen, String chuChaShiYou) {
        this.type = type;
        this.FanHuiRiQi = fanHuiRiQi;
        this.JiHuaShiJian = jiHuaShiJian;
        this.MuBiaoDiZhi = muBiaoDiZhi;
        this.QiTaZaFei = qiTaZaFei;
        this.ShenQingRenBuMen = shenQingRenBuMen;
        this.UserNo = userNo;
        this.TongXingRenYuan = tongXingRenYuan;
        this.ZhuSuFei = zhuSuFei;
        this.MuBiaoJiHua = muBiaoJiHua;
        this.JiaoTongFei = jiaoTongFei;
        this.JiaoTongGongJu = jiaoTongGongJu;
        this.ChuChaShenQingRen = chuChaShenQingRen;
        this.ChuChaShiYou = chuChaShiYou;
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

    public List<String> getTransportType() {
        return TransportType;
    }

    public void setTransportType(List<String> TransportType) {
        this.TransportType = TransportType;
    }
}
