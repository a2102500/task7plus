package com.ljm.controller;

//import com.liujiaming.util.SDKTestSendTemplateSMS;

import com.ljm.util.ImagesUtil;
import com.ljm.util.RandNum;
import com.ljm.util.SDKTestSendTemplateSMS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;

/**
 * Created by liujm on 2017/8/3.
 */
@Controller
public class SMtest {

    private static final Log log = LogFactory.getLog(SMtest.class);

    SDKTestSendTemplateSMS sms = new SDKTestSendTemplateSMS();

    @RequestMapping(value = "/a/login",method = RequestMethod.GET)
    public String login(){
        return "code";
    }


    @RequestMapping(value = "/a/setcode", method = RequestMethod.GET)
    public String  getCode(HttpServletRequest request, HttpServletResponse response, String phone) throws Exception {

        log.info("============>phone" + phone);
        String code = RandNum.getSix();
        log.info("===========>code"+code);
        sms.sentcode(phone,code);

        response.setContentType("text/html;charset=UTF-8");
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("code", code);
        return "code";
    }

//    @Autowired
//    ImagesUtil imagesUtil;


    @RequestMapping("/images")
    public String uploadImages(String file)throws Exception{
        ImagesUtil.qiniuyunSDK(file);
        return "code";
    }

}
