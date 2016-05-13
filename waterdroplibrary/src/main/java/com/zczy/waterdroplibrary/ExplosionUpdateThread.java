package com.zczy.waterdroplibrary;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Message;
import android.view.SurfaceHolder;

/**
 * The thead will finish when you called setRunning(false) or Explosion ended.
 * 
 * @author Peter.Ding
 * 
 */
public class ExplosionUpdateThread extends Thread {
    private SurfaceHolder mHolder;
    private DropCover mDropCover;
    private DropCover.ExplosionUpdateHandler mHandler;
    private boolean isRunning = false;

    public ExplosionUpdateThread(SurfaceHolder holder, DropCover dropCover, DropCover.ExplosionUpdateHandler pHandler) {
        mHolder = holder;
        mDropCover = dropCover;
        mHandler = pHandler;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        boolean isAlive = true;
        while (isRunning && isAlive) {
            Canvas canvas = mHolder.lockCanvas();
            if (canvas != null) {
                isAlive = mDropCover.render(canvas);
                mHolder.unlockCanvasAndPost(canvas);
                mDropCover.update();
            }
        }
        Message msg = new Message();
        Bundle b = new Bundle();// 存放数据
        b.putString("state","ExplosionComplete");
        msg.setData(b);
        mHandler.sendMessage(msg); // 向Handler发送消息，更新UI
    }
}
