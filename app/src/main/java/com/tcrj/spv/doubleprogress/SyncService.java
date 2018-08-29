package com.tcrj.spv.doubleprogress;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tcrj.spv.service.MyIntentService;
import com.tcrj.spv.views.utils.XMLName;

/**
 * com.demo.yetote.doubleprogressguarddemo
 *
 * @author Swg
 * @date 2018/4/16 21:44
 */
public class SyncService extends Service {
    private SyncAdapter syncAdapter;
    private static final String TAG = "SyncService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        syncAdapter = new SyncAdapter(this, true);
    }

    class SyncAdapter extends AbstractThreadedSyncAdapter {
        public SyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        }

        @Override
        public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
//            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


            //判断当前应用是否是前台进程
            SharedPreferences e = getSharedPreferences(XMLName.NAME_USER_INFO, 0);
            int IsSell = e.getInt(XMLName.NAME_ISSELL, -1);
            Log.e(TAG, "onPerformSync: " + "拉活开始IsSell"+IsSell);


            if(IsSell == 1){
                //上传实时位置信息
                Intent Service = new Intent(getContext(), MyIntentService.class);
                //启动IntentService
                startService(Service);
            }


//            Intent intent = new Intent(getContext(),WelcomeActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            NotificationChannel channel = manager.getNotificationChannel();
//            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
//                Intent i = new Intent();
////                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
////                i.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
//                startActivity(i);
//                Toast.makeText(getApplicationContext(), "请手动将通知打开", Toast.LENGTH_SHORT).show();
//            }
//            Notification chat = new NotificationCompat.Builder(getApplicationContext(), "chat")
//                    .setContentTitle("收到拉活消息")
//                    .setContentText("拉活成功...")
//                    .setWhen(System.currentTimeMillis())
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
//                    .setAutoCancel(true)
//                    .build();
//            manager.notify(1, chat);

        }
    }
}
