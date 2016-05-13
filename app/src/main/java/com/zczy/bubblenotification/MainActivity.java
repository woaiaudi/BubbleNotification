package com.zczy.bubblenotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zczy.waterdroplibrary.CoverManager;
import com.zczy.waterdroplibrary.DropCover;
import com.zczy.waterdroplibrary.WaterDrop;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends Activity {
    private Button activity_main_bt_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoverManager.getInstance().init(this);

        CoverManager.getInstance().setMaxDragDistance(150);
        CoverManager.getInstance().setExplosionTime(150);

        WaterDrop drop = (WaterDrop) findViewById(R.id.activity_main_drop);

        drop.setText(99);

        drop.setOnDragCompeteListener(new DropCover.OnDragCompeteListener() {

            @Override
            public void onDrag() {
                Toast.makeText(MainActivity.this, "remove:999",
                        Toast.LENGTH_SHORT).show();
            }
        });






        activity_main_bt_share = (Button) findViewById(R.id.activity_main_bt_share);
        activity_main_bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("中储智运");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.zczy56.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("“中储智运”致力于构建一个服务于广大客户的物流与供应链电子商务生态系统，持续为客户创造非凡价值与客户体验。平台建成运营后，将为货主彻底解决寻车困难，降低运输成本两成以上，极大提升承运司机揽货机会，增加业务收益一倍以上。通过智慧物流分析与预测技术，帮助货主制定不同的发货决策，帮助承运司机获得线路热门货物的货量预报，提前做好承运准备，真正为客户提供创造性的物流服务与体验，引领中国现代物流的前进与发展，实现中国物流行业的蜕变与革新。");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.zczy56.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("中储智运-好用的物流APP");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.zczy56.com");

// 启动分享GUI
        oks.show(this);
    }
}
