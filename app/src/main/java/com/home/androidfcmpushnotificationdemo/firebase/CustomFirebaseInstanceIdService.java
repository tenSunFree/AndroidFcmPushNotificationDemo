package com.home.androidfcmpushnotificationdemo.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.home.androidfcmpushnotificationdemo.eventbus.TokenEvent;
import org.greenrobot.eventbus.EventBus;

public class CustomFirebaseInstanceIdService extends FirebaseInstanceIdService {

    /**
     * 只會在以下情況下觸發
     * 1. 第一次取得Token
     * 2. APP安裝或移除
     * 3. 使用者清除APP資料
     * 4. APP刪除了Instance ID
     * 5. 當Token的安全性遭到破壞時
     * 6. 定期性更新(依照Google文檔, Token約6個月會更新一次)
     */
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken(); // 取得FirebaseInstanceId的token
        sendTokenToServer(token);
    }

    /**
     * 將token上傳至伺服器
     */
    private void sendTokenToServer(String token) {
        // 在這裡可以將token上傳至伺服器
        // ...
        TokenEvent tokenEvent = new TokenEvent(); // 創建一個TokenEvent的事件
        tokenEvent.setToken(token);
        EventBus.getDefault().post(tokenEvent); // 發射TokenEvent的事件
    }
}
