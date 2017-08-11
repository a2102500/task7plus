package com.ljm.util;

/**
 * Created by liujm on 2017/8/9.
 */
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
 * Created by liujm on 2017/8/6.
 */
public class ImagesUtil {
    private static String accessKey=null;
    private static String secretKey=null;
    private static String bucket=null;
    private static Configuration cfg;

    public ImagesUtil(String accessKey,String secretKey,String bucket){
        this.accessKey=accessKey;
        this.secretKey=secretKey;
        this.bucket=bucket;
        this.cfg=new Configuration(Zone.zone2());
    }

    public  static void qiniuyunSDK(String file)throws Exception{
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

        UploadManager uploadManager=new UploadManager(cfg);

        String localFilePath=newURL;

        String key=file;
        Auth auth=Auth.create(accessKey,secretKey);
        String upToken=auth.uploadToken(bucket);
        try{
            Response response=uploadManager.put(localFilePath,key,upToken);
            System.out.println("上传文件,状态码:"+response.statusCode);
            //解析上传成功的结果
            DefaultPutRet putRet=new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            System.out.println("图片外链:"+"http://otjrc4xc4.bkt.clouddn.com/"+key);
        }catch(QiniuException ex){
            Response r=ex.response;
            //这是输出语句,与sout类似,不过会显示红色
            System.err.println(r.toString());
            try{
                System.err.println(r.bodyString());
            }catch(QiniuException ex2){

            }
        }

    }
}

