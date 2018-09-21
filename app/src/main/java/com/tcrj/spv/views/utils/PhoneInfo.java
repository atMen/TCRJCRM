package com.tcrj.spv.views.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by leict on 2018/8/29.
 */

public class PhoneInfo {
    private TelephonyManager telephonemanager;
    private String IMSI;
    private Context ctx;
    /**
     * 获取手机国际识别码IMEI
     * */
    public  PhoneInfo(Context context){
        ctx=context;
        telephonemanager=(TelephonyManager)context
                .getSystemService(Context.TELEPHONY_SERVICE);
    }




    /**
     * 获取手机号码
     * */
    public String getNativePhoneNumber(){

        String nativephonenumber=null;
        nativephonenumber = telephonemanager.getLine1Number();

        return nativephonenumber;
    }

    /**
     * 获取手机服务商信息
     *
     * */
    public String  getProvidersName(){
        String providerName=null;
        try{
            IMSI=telephonemanager.getSubscriberId();
            //IMSI前面三位460是国家号码，其次的两位是运营商代号，00、02是中国移动，01是联通，03是电信。
            System.out.print("IMSI是："+IMSI);
            if(IMSI.startsWith("46000")||IMSI.startsWith("46002")){
                providerName="中国移动";
            }else if(IMSI.startsWith("46001")){
                providerName="中国联通";
            }else if(IMSI.startsWith("46003")){
                providerName="中国电信";
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return providerName;

    }
    /**
     * 获取手机信息
     * */
    public String getPhoneInfo(){

        TelephonyManager tm=(TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb=new StringBuilder();

        sb.append("\nDeviceID(IMEI)"+tm.getDeviceId());
        sb.append("\nDeviceSoftwareVersion:"+tm.getDeviceSoftwareVersion());
        sb.append("\ngetLine1Number:"+tm.getLine1Number());
        sb.append("\nNetworkCountryIso:"+tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator:"+tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName:"+tm.getNetworkOperatorName());
        sb.append("\nNetworkType:"+tm.getNetworkType());
        sb.append("\nPhoneType:"+tm.getPhoneType());
        sb.append("\nSimCountryIso:"+tm.getSimCountryIso());
        sb.append("\nSimOperator:"+tm.getSimOperator());
        sb.append("\nSimOperatorName:"+tm.getSimOperatorName());
        sb.append("\nSimSerialNumber:"+tm.getSimSerialNumber());
        sb.append("\ngetSimState:"+tm.getSimState());
        sb.append("\nSubscriberId:"+tm.getSubscriberId());
        sb.append("\nVoiceMailNumber:"+tm.getVoiceMailNumber());

        return sb.toString();

    }


    /**
     * AndroidId 拼接 Serial Number
     * @param context
     * @return
     */
    public static String getUniqueId(Context context){
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        try {
            return toMD5(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return id;
        }
    }


    /**
     * MD5加密
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString();
    }


    /**
     * 获取imei
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyMgr.getDeviceId();
        return imei;
    }


    /**
     * 通过反射拿到imei
     * @param context
     * @return
     */
    public static String getMachineImei(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Class clazz = manager.getClass();
        String imei = "";
        try {
            Method getImei=clazz.getDeclaredMethod("getImei",int.class);
            getImei.setAccessible(true);
            imei = (String) getImei.invoke(manager);
        } catch (Exception e) {
        }
        return imei;
    }

}
