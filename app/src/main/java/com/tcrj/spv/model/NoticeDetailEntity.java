package com.tcrj.spv.model;

import java.io.Serializable;
import java.util.List;

/**
 * 通知公告：详情
 * Created by leict on 2017/11/6.
 */

public class NoticeDetailEntity implements Serializable {
    /**
     * State : 1
     * Msg : 操作成功
     * Entity : {"Id":35,"Title":"关于国庆节放假时间安排的通知","Content":"<p style=\"font-family: 宋体;font-size: medium;white-space: normal;text-align: center;background-color: rgb(255, 255, 255)\"><strong><span style=\"font-size: 24px\">关于<\/span><\/strong><strong><span style=\"font-size: 24px\">国庆节放假时间安排的通知<\/span><\/strong><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">&nbsp;<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 华文仿宋;font-size: 21px\">全体员工：<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;text-indent: 40px;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 华文仿宋;font-size: 21px\">根据国务院办公厅<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">2016年度国庆节放假时间安排<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">，<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">陕西天诚软件有限公司<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">国庆节<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">假期安排如下<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">：<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;text-indent: 40px;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 华文仿宋;font-size: 21px\">2016年<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">10<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">月<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">1<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">日<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\"><span style=\"font-family:华文仿宋\">（星期六）至<\/span>10月7日（星期五）放假七天，10月8日（星期六）<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">、<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">10<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">月<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">9<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\"><span style=\"font-family:华文仿宋\">日<\/span>(星期<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">日<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">)<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">照<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">常上班。<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;text-indent: 43px;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 华文仿宋;font-size: 21px\">特此通知<\/span><span style=\"font-family: 华文仿宋;font-size: 21px\">。<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;text-indent: 43px;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 华文仿宋;font-size: 21px\">请各部门负责人做好节前工作安排。<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;margin-bottom: 16px;text-indent: 43px;line-height: 22.4px;background-color: rgb(255, 255, 255)\">　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　<span style=\"font-family: 仿宋_GB2312;line-height: 29.8667px;font-size: 21px\">&nbsp;<\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;margin-bottom: 16px;text-indent: 43px;line-height: 22.4px;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 仿宋_GB2312;line-height: 29.8667px;font-size: 21px\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;　　　　　　　　<span style=\"font-family:仿宋_GB2312\">陕西天诚软件有限公司<\/span><\/span><\/p><p style=\"font-family: 宋体;font-size: medium;white-space: normal;background-color: rgb(255, 255, 255)\"><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 　　　　　　　　　　<span style=\"font-family:仿宋_GB2312\">二零一六<\/span><\/span><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">年<\/span><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">九<\/span><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">月<\/span><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">二十七<\/span><span style=\"font-family: 仿宋_GB2312;font-size: 21px\">日<\/span><\/p><p><br/><\/p>","Release":"发布","ReceivingObject":"企业","DoStaffName":"张海艳","AddTime":"2016-09-27 15:03:56","ReleaseTime":"2016-09-27 15:03:56"}
     * FileList : [{"Item1":"http://113.200.26.66:8000/upfile/201609270303560.pdf","Item2":"2016年度国庆节放假时间安排通知"}]
     */

    public NoticeDetailEntity(int Id) {

        this.Id = Id;
    }
    private int Id;
    private int State;
    private String Msg;
    private EntityBean Entity;
    private List<FileListBean> FileList;

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

    public EntityBean getEntity() {
        return Entity;
    }

    public void setEntity(EntityBean Entity) {
        this.Entity = Entity;
    }

    public List<FileListBean> getFileList() {
        return FileList;
    }

    public void setFileList(List<FileListBean> FileList) {
        this.FileList = FileList;
    }

    public static class EntityBean {
        /**
         * Id : 35
         * Title : 关于国庆节放假时间安排的通知
         * Content : <p style="font-family: 宋体;font-size: medium;white-space: normal;text-align: center;background-color: rgb(255, 255, 255)"><strong><span style="font-size: 24px">关于</span></strong><strong><span style="font-size: 24px">国庆节放假时间安排的通知</span></strong></p><p style="font-family: 宋体;font-size: medium;white-space: normal;background-color: rgb(255, 255, 255)"><span style="font-family: 仿宋_GB2312;font-size: 21px">&nbsp;</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;background-color: rgb(255, 255, 255)"><span style="font-family: 华文仿宋;font-size: 21px">全体员工：</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;text-indent: 40px;background-color: rgb(255, 255, 255)"><span style="font-family: 华文仿宋;font-size: 21px">根据国务院办公厅</span><span style="font-family: 华文仿宋;font-size: 21px">2016年度国庆节放假时间安排</span><span style="font-family: 华文仿宋;font-size: 21px">，</span><span style="font-family: 华文仿宋;font-size: 21px">陕西天诚软件有限公司</span><span style="font-family: 华文仿宋;font-size: 21px">国庆节</span><span style="font-family: 华文仿宋;font-size: 21px">假期安排如下</span><span style="font-family: 华文仿宋;font-size: 21px">：</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;text-indent: 40px;background-color: rgb(255, 255, 255)"><span style="font-family: 华文仿宋;font-size: 21px">2016年</span><span style="font-family: 华文仿宋;font-size: 21px">10</span><span style="font-family: 华文仿宋;font-size: 21px">月</span><span style="font-family: 华文仿宋;font-size: 21px">1</span><span style="font-family: 华文仿宋;font-size: 21px">日</span><span style="font-family: 华文仿宋;font-size: 21px"><span style="font-family:华文仿宋">（星期六）至</span>10月7日（星期五）放假七天，10月8日（星期六）</span><span style="font-family: 华文仿宋;font-size: 21px">、</span><span style="font-family: 华文仿宋;font-size: 21px">10</span><span style="font-family: 华文仿宋;font-size: 21px">月</span><span style="font-family: 华文仿宋;font-size: 21px">9</span><span style="font-family: 华文仿宋;font-size: 21px"><span style="font-family:华文仿宋">日</span>(星期</span><span style="font-family: 华文仿宋;font-size: 21px">日</span><span style="font-family: 华文仿宋;font-size: 21px">)</span><span style="font-family: 华文仿宋;font-size: 21px">照</span><span style="font-family: 华文仿宋;font-size: 21px">常上班。</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;text-indent: 43px;background-color: rgb(255, 255, 255)"><span style="font-family: 华文仿宋;font-size: 21px">特此通知</span><span style="font-family: 华文仿宋;font-size: 21px">。</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;text-indent: 43px;background-color: rgb(255, 255, 255)"><span style="font-family: 华文仿宋;font-size: 21px">请各部门负责人做好节前工作安排。</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;margin-bottom: 16px;text-indent: 43px;line-height: 22.4px;background-color: rgb(255, 255, 255)">　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　<span style="font-family: 仿宋_GB2312;line-height: 29.8667px;font-size: 21px">&nbsp;</span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;margin-bottom: 16px;text-indent: 43px;line-height: 22.4px;background-color: rgb(255, 255, 255)"><span style="font-family: 仿宋_GB2312;line-height: 29.8667px;font-size: 21px">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;　　　　　　　　<span style="font-family:仿宋_GB2312">陕西天诚软件有限公司</span></span></p><p style="font-family: 宋体;font-size: medium;white-space: normal;background-color: rgb(255, 255, 255)"><span style="font-family: 仿宋_GB2312;font-size: 21px">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 　　　　　　　　　　<span style="font-family:仿宋_GB2312">二零一六</span></span><span style="font-family: 仿宋_GB2312;font-size: 21px">年</span><span style="font-family: 仿宋_GB2312;font-size: 21px">九</span><span style="font-family: 仿宋_GB2312;font-size: 21px">月</span><span style="font-family: 仿宋_GB2312;font-size: 21px">二十七</span><span style="font-family: 仿宋_GB2312;font-size: 21px">日</span></p><p><br/></p>
         * Release : 发布
         * ReceivingObject : 企业
         * DoStaffName : 张海艳
         * AddTime : 2016-09-27 15:03:56
         * ReleaseTime : 2016-09-27 15:03:56
         */

        private int Id;
        private String Title;
        private String Content;
        private String Release;
        private String ReceivingObject;
        private String DoStaffName;
        private String AddTime;
        private String ReleaseTime;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getRelease() {
            return Release;
        }

        public void setRelease(String Release) {
            this.Release = Release;
        }

        public String getReceivingObject() {
            return ReceivingObject;
        }

        public void setReceivingObject(String ReceivingObject) {
            this.ReceivingObject = ReceivingObject;
        }

        public String getDoStaffName() {
            return DoStaffName;
        }

        public void setDoStaffName(String DoStaffName) {
            this.DoStaffName = DoStaffName;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String ReleaseTime) {
            this.ReleaseTime = ReleaseTime;
        }
    }

    public static class FileListBean {
        /**
         * Item1 : http://113.200.26.66:8000/upfile/201609270303560.pdf
         * Item2 : 2016年度国庆节放假时间安排通知
         */

        private String Item1;
        private String Item2;

        public String getItem1() {
            return Item1;
        }

        public void setItem1(String Item1) {
            this.Item1 = Item1;
        }

        public String getItem2() {
            return Item2;
        }

        public void setItem2(String Item2) {
            this.Item2 = Item2;
        }
    }


//    /**
//     * State : 1
//     * Msg : 操作成功
//     * Entity : {"Id":35,"Title":"关于国庆节放假时间安排的通知","Content":"<p style=\"font-family:>","Release":"发布","ReceivingObject":"企业","DoStaffName":"张海艳","AddTime":"2016-09-27 15:03:56","ReleaseTime":"2016-09-27 15:03:56"}
//     */
//
//    private int State;
//    private String Msg;
//    private EntityBean Entity;
//    private int Id;
//
//    public NoticeDetailEntity(int Id) {
//
//        this.Id = Id;
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
//    public EntityBean getEntity() {
//        return Entity;
//    }
//
//    public void setEntity(EntityBean Entity) {
//        this.Entity = Entity;
//    }
//
//    public static class EntityBean {
//        /**
//         * Id : 35
//         * Title : 关于国庆节放假时间安排的通知
//         * Content : <p style="font-family:>
//         * Release : 发布
//         * ReceivingObject : 企业
//         * DoStaffName : 张海艳
//         * AddTime : 2016-09-27 15:03:56
//         * ReleaseTime : 2016-09-27 15:03:56
//         */
//
//        private int Id;
//        private String Title;
//        private String Content;
//        private String Release;
//        private String ReceivingObject;
//        private String DoStaffName;
//        private String AddTime;
//        private String ReleaseTime;
//
//        public int getId() {
//            return Id;
//        }
//
//        public void setId(int Id) {
//            this.Id = Id;
//        }
//
//        public String getTitle() {
//            return Title;
//        }
//
//        public void setTitle(String Title) {
//            this.Title = Title;
//        }
//
//        public String getContent() {
//            return Content;
//        }
//
//        public void setContent(String Content) {
//            this.Content = Content;
//        }
//
//        public String getRelease() {
//            return Release;
//        }
//
//        public void setRelease(String Release) {
//            this.Release = Release;
//        }
//
//        public String getReceivingObject() {
//            return ReceivingObject;
//        }
//
//        public void setReceivingObject(String ReceivingObject) {
//            this.ReceivingObject = ReceivingObject;
//        }
//
//        public String getDoStaffName() {
//            return DoStaffName;
//        }
//
//        public void setDoStaffName(String DoStaffName) {
//            this.DoStaffName = DoStaffName;
//        }
//
//        public String getAddTime() {
//            return AddTime;
//        }
//
//        public void setAddTime(String AddTime) {
//            this.AddTime = AddTime;
//        }
//
//        public String getReleaseTime() {
//            return ReleaseTime;
//        }
//
//        public void setReleaseTime(String ReleaseTime) {
//            this.ReleaseTime = ReleaseTime;
//        }
//    }
}
