package com.ljm.util;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by CK on 2017/5/15.
 */
public class SDKTestSendTemplateSMS {
//    public static void main(String[] args) {
//        HashMap<String, Object> result = null;
//        String Phone = "13007299384";
//        String Code = RandNum.getSix();

    public  void sentcode(String phone,String code) {
        HashMap<String, Object> result = null;
        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init("app.cloopen.com", "8883");
        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
        restAPI.setAccount(
                "8a216da85bf14b6a015c09bcd0f806b2", "b2cb6df3bcfb467d940208f9592b7b38");
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAppId("8aaf07085d7cf73f015d7df51ad200ce");
        // 请使用管理控制台中已创建应用的APPID。
        result = restAPI.sendTemplateSMS(phone, "1", new String[]{code, "5"});
        System.out.println("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }
    }
}
