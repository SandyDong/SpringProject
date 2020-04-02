package com.dong.service.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.dong.controller.Login.LoginContoller;
import com.dong.entity.ApplicationEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


/**
 * @author
 */
@Service
public class ApplicationMailer implements Mailer {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationMailer.class);

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private TaskExecutor taskExecutor;


    @Autowired
    private SimpleMailMessage preConfiguredMessage;


    /**
     * 同步发送邮件
     *
     * @param email
     * @throws MessagingException
     * @throws IOException
     */
    public void sendMailBySynchronizationMode(ApplicationEmail email) throws MessagingException, IOException {
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage mime = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");
        helper.setFrom("IT腾讯云服务中心<xxx@126.com>");//发件人(应用专用邮箱账号)
        helper.setTo(email.getAddressee());//收件人
        //helper.setBcc("administrator@chinaptp.com");//暗送
        helper.setReplyTo("xxx@126.com");//回复到
        helper.setSubject(email.getSubject());//邮件主题
        helper.setText(email.getContent(), true);//true表示设定html格式

        mailSender.send(mime);
    }


    /**
     * 异步发送邮件
     *
     * @param email
     */
    public void sendMailByAsynchronousMode(final ApplicationEmail email) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMailBySynchronizationMode(email);
                } catch (Exception e) {
                    logger.error("异步发送邮件失败!-->"+e.getMessage());
                }
            }
        });
    }
}


