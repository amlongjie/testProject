package com.mingming.block.trade.service;

import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dto.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(@NotNull JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    @ExHandlerAnnotation
    public ApiResponseVO<Boolean> send(String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[]{"398110112@qq.com"});
        simpleMailMessage.setFrom("15101186970@163.com");
        simpleMailMessage.setSubject("来自token-trade的邮件");
        simpleMailMessage.setText(body);
        // 发送邮件
        javaMailSender.send(simpleMailMessage);
        return ApiResponseVO.success(Boolean.TRUE);
    }
}
