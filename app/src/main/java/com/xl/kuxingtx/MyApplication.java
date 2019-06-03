package com.xl.kuxingtx;

import android.app.Application;

import org.xutils.x;
import com.xuexiang.xui.XUI;

//初始化
public class MyApplication extends Application {
    public static String httpHead="http://"+"192.168.43.99:8000/";
    public static String webUri_login = httpHead + "login/";
    public static String webUri_register = httpHead + "register/";
    public static String webUri_resetPassword = httpHead + "resetPassword/";
    public static String webUri_query_info = httpHead + "query_info/";
    public static String webUri_modify_info = httpHead + "modify_info/";
    public static String webUri_relation_add = httpHead + "relation_add/";

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
    }
}