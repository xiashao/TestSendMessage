package com.vmeet.netsocket.bean;

import android.os.Build;
import android.os.Environment;

/**
 * 常量类
 */
public class Constants {
    public static final String basePath = "www.vmeet.cc";
    public static final String Version = "4";
    public static String WEB_IP="www.vmeet.cc"; // 版本更新时的ip eg:www.vmeet.cc
    public static int WEB_PORT=30020; // 版本更新时的ip  eg:30010
    public static final int HeaderLength = 32;//默认消息头长度
    public static int clientType = ClientType.Android.value();//默认sendLog类型
    public static Thread ConnectServerThread;
    public static boolean serverConnected = true;
    public static String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    public static String LogName = getPhoneType(); //向服务器注册的name

    //=====前端接收的所有action=====
    //sendfile发送成功提醒action,聊天页面图片显示
    public static final String Action_Socket_SendFileSuccess = basePath + ".socket_sendfileSuccess"+Version;
    //getfile成功提醒action,聊天页面图片显示
    public static final String Action_Socket_GetFileSuccess = basePath + ".socket_getfileSuccess"+Version;
    //getfile中getfiledir成功刷新提醒action
    public static final String Action_Socket_GetFileDirSucess = basePath + ".socket_getfiledirSucess"+Version;
    public static final String Action_Socket_GetFileDirSucess_Dispatch = basePath + ".socket_getfiledirSucess_Dispatch"+Version;
    //sendfile中sendfiledir成功刷新提醒action
    public static final String Action_Socket_SendFileDirSucess = basePath + ".socket_sendfiledirSucess"+Version;
    //更新目录
    public static final String Action_Socket_updateFileDir = basePath + ".socket_updateFileDir"+Version;
    public static final String Action_Socket_updateDirDetail = basePath + ".socket_updateDirDetail"+Version;
    //userlist刷新提醒action
    public static final String Action_Socket_UserListSet = basePath + ".socket_userlist"+Version;
    //对微应用的选项进行更新
    public static final String Action_update_appRole1 = basePath + ".Action_update_appRole1"+Version;
    //对rowMeRole进行更新了
    public static final String Action_update_rowMeRole = basePath + ".Action_update_rowMeRole"+Version;
    //用户状态更新action
    public static final String Action_UserState = basePath + ".Action_UserState"+Version;
    //应用更新通知
    public static final String Action_Update_App = basePath + ".Action_Update_App"+Version;
    public static final String Action_Update_AppSrv = basePath + ".Action_Update_AppSrv";
    //msg气泡通知
    public static final String Action_Msg_Data = basePath + ".Action_Msg_Data"+Version;
    //发消息后lanuncheractivity进行刷新
    public static final String Action_Update_Launcher = basePath + ".Action_Update_Launcher"+Version;
    //退出登录
    public static final String Action_Exit_Activity = basePath + ".Action_Exit_Activity"+Version;
    //应用applayfragment刷新
    public static final String Action_Apply_Refresh_Setting = basePath + ".Action_Apply_Refresh_Setting"+Version;
    //后台server广播接收action
    public static final String Action_Server = basePath + ".server"+Version;
    public static final String Action_Socket_Alarm = basePath + ".socket_alarm"+Version;
    public static final String Action_Server_UpdateTbl = basePath + ".server_updateTbl"+Version;
    public static final String Action_Server_UpdateDir = basePath + ".server_updateDir"+Version;
    //点击Notification进入welAcitiy
    public static final String Action_OpenWel_Activity = basePath + ".Action_OpenWel_Activity"+Version;
    //收到noticeList 通知界面进行刷新通知
    public static final String Action_Notice = basePath + ".notice"+Version;
    public static final String DOWNLOADSUCCESS = basePath + ".DOWNLOADSUCCESS"+Version;//下载文件成功action
    public static final String DOWNLOADFAIL = basePath + ".DOWNLOADFAIL"+Version;//下载文件失败action
    public static final String DOWNLOAE_FILE_FULLPATH = basePath + ".DOWNLOAE_FILE_FULLPATH"+Version;//下载文件成功全路径action
    public static final String SENDSUCCESS = basePath + ".SENDSUCCESS"+Version;//上传文件成功action
    public static final String DIRSENDSUCCESS = basePath + ".DIRSENDSUCCESS"+Version;//文件夹上传成功action

    public static final String Action_Vote = basePath + ".vote"+Version;//投票
    public static final String Action_SCH = basePath + ".richeng"+Version;//日程
    public static final String Action_Docflow = basePath + ".docflow"+Version;//公文流转
    public static final String Action_Car = basePath + ".car"+Version;//用车
    public static final String Action_CarMe = basePath + ".car_me"+Version;//用车
    public static final String Action_Canteen_Update = basePath + ".car_me"+Version;//食堂订单刷新
    public static final String Action_OA = basePath + ".OA"+Version;//OA刷新
    public static final String Action_OA_Handle = basePath + ".OA_Handle"+Version;//OA办
    public static final String Action_OA_Finish = basePath + ".OA_Finish"+Version;//OA办完成
    public static final String Action_OA_PWD = basePath + ".OA_PWD"+Version;//OA办密码

    public static final String Action_Del = basePath + "del"+Version; //云盘删除节点
    public static final String Action_Cloud_Update = basePath + "cloud_update"+Version; //云盘删除节点
    public static final String Action_docShared = basePath + "docShared"+Version; //资料分享


    public static final String Action_PJ = basePath + "PJ"+Version; //评价推送
    public static final String Action_HuiWu = basePath + ".huiwu"+Version;//会务

    //待办
    public static final String Action_DaiBan_DelRow = basePath + ".daiban.delRow"+Version;//待办删除row
    //通用-删除row General
    public static final String Action_General_DelRow = basePath + ".General.delRow"+Version;//通用删除row
    // updatetbl
    public static final String Action_UpadateTbl_Goodslist = basePath + "Action_UpadateTbl_Goodslist"+Version; //更新外卖
    public static final String Action_UpadateTbl_Food = basePath + "Action_UpadateTbl_Food"+Version; //更新食堂

    //searchRow
    public static final String Action_searchRow_Groups = basePath + "Action_searchRow_Groups"+Version; //更新企业群

    //文档
    public static final String Action_doc_wendang = basePath + "Action_doc_wendang"+Version; //更新文档

    //msg 发送视频
    public static final String Action_Msg_SEND_VIDEO = basePath + ".Action_Msg_Send_Video"+Version;
    public static final String Action_Msg_ShowVideo_Finish = basePath + ".ShowVideoActivity2_finish_MyVideoActivity"+Version;
    //ViewPagerAcivity_finish_ShowImageActivtiy



    public static final String Action_updategroup_refresh = "updategroup_refresh";
    public static final String Action_remove_group = "remove_group";

    public static final String Action_msgChat_back ="msgChat_back";
    //消息气泡长按
    public static final String Action_LongClickRefresh ="LongClickRefresh";
    public static final String Action_ViewPagerAcivity_finish_ShowImageActivtiy = "ViewPagerAcivity_finish_ShowImageActivtiy";

    public static final String Action_com_android_camera_action_CROP="com.android.camera.action.CROP";
    public static final String Action_wel_finish ="wel_finish";
    public static final String Action_SysUnlock ="SysUnlock";
    //个人名片
    public static final String Action_Card_rowStr ="Card_rowStr";
    public static final String Action_tuya_points = "tuya.points";

    public static final String Action_intentpage = "intentpage";

    public static final String Action_update_myIcon = basePath + "Action_update_myIcon"+Version; //更新我的头像

    public static String getPhoneType() {
        // 硬件制造商（手机品牌）
        String type = Build.MANUFACTURER;
        // 手机版本
        String model = Build.MODEL;
        return type + ":" + model;
    }

    public static void showLog(Object text) {
        android.util.Log.e("com.vmeet.net", "---" + text + "---");
    }
}
