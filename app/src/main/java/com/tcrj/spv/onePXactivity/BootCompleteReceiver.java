package com.tcrj.spv.onePXactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.tcrj.spv.service.MyIntentService;
import com.tcrj.spv.views.utils.XMLName;

/**
 * @author wcf
 * @time 2017/7/25 9:34
 * @desc
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    private boolean index = true;

    @Override
    public void onReceive(Context context, Intent intent) {


        SharedPreferences e = context.getSharedPreferences(XMLName.NAME_USER_INFO, 0);
        int IsSell = e.getInt(XMLName.NAME_ISSELL, -1);

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            System.out.println("监听到息屏广播");
            HooliganActivity. startHooligan();

            if(IsSell == 1){

                //上传实时位置信息
                Intent i = new Intent(context, MyIntentService.class);
                //启动IntentService
                context.startService(i);
            }
        } else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            System.out.println("监听到亮屏广播");
            HooliganActivity.killHooligan();
            if(IsSell == 1){

//                if(index) {
//                    index = false;
//                    Log.e("TAG", "监听到亮屏广播 上传位置信息");

                    //上传实时位置信息
                    Intent i = new Intent(context, MyIntentService.class);
                    //启动IntentService
                    context.startService(i);
//                }else {
//                    index = true;
//                }
            }
        }
    }
}

