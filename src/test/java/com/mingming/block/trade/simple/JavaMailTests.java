package com.mingming.block.trade.simple;

import com.mingming.block.trade.TradeApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class JavaMailTests extends TradeApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送包含简单文本的邮件spring-h
     */
    @Test
    public void sendTxtMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 设置收件人，寄件人
//        simpleMailMessage.setTo(new String[]{"398110112@qq.com"});
//        simpleMailMessage.setFrom("15101186970@163.com");
//        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
//        simpleMailMessage.setText("这里是一段简单文本。");
//        // 发送邮件
//        javaMailSender.send(simpleMailMessage);
//
//        System.out.println("邮件已发送");
    }

}
