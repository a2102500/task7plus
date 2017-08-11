package com.ljm.util;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import javax.swing.*;

/**
 * Created by liujm on 2017/7/31.
 */
public class qiniuyunSDK {

    public static void main(String[] args) throws Exception{
        //打开文件选择框:
        //这一段是志勇那看到的,我在网页sdk中找不到这一段,之后下载他的sdk,去里面搜索fc看能不能找到这段
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser fc=new JFileChooser();
        fc.showOpenDialog(null);
        String filePath=""+fc.getSelectedFile();
        System.out.println("选择的文件路径:"+filePath);//输出文件路径

        //把单斜杠变成双斜杠,我猜是为了防止路径输入错误导致无法传
        String newURL=filePath.replaceAll("\\\\","\\\\"+"\\\\");
        System.out.println("转以后的文件路径:"+newURL);


//构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "zSapXCbTCZlAfPLF1FKGdWDRplwctF2IVhef5iT2";
        String secretKey = "j_ZfQdGTghQeQKMUBwqFAdjjWS2-s67sGeV7yh0M";
        String bucket = "liujiaming";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\www.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "www.png";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

        Response response = uploadManager.put(localFilePath, key, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);

    }
}

