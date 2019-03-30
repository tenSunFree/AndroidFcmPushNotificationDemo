package com.home.androidfcmpushnotificationdemo.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.home.androidfcmpushnotificationdemo.R;

public class CustomFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    /** 當接收到Firebase發出的消息後, 取得相關內容, 顯示相關的通知 */
    private void sendNotification(String messageBody) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String channelId = "FirebaseMessagingChanne";
        String channelName = "Firebase通知";
        NotificationChannel helloNotificationChannel = new NotificationChannel(
                channelId, // channelId是用來提供分辨Android的通知管道, 如果在Firebase撰寫通知的時候, 有特別設定通知管道欄的話, 那麼channelId就必須要與其相同, 才會出發該通知
                channelName, // 設置給使用者觀看的Channel的名稱
                NotificationManager.IMPORTANCE_HIGH); // 開啟通知, 會彈出, 發出提示音, 狀態欄中顯示
        String channelDescription = "用來接收Firebase發出的通知";
        helloNotificationChannel.setDescription(channelDescription); // 可以指定設置中Channel的描述
        helloNotificationChannel.enableLights(true); // 開啟指示燈, 如果設備有的話
        helloNotificationChannel.enableVibration(true); // 開啟震動
        notificationManager.createNotificationChannel(helloNotificationChannel); // 創建通知管道
        String contentTitle = "FCM Notifications";
        Notification.Builder builder =
                new Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.firebase_logo) // 指定一個圖形資源設定通知在狀態列上顯示的小圖示, 它也會在通知畫面的右下角出現, 建議的大小是32×32畫素
                        .setContentTitle(contentTitle) // 設定通知內容的標題
                        .setContentText(messageBody); // 設定通知內容的訊息
        notificationManager.notify(1, builder.build()); // 將Notification物件傳送至系統
    }
}
