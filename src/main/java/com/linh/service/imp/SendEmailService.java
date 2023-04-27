package com.linh.service.imp;

import com.linh.dto.SendEmailDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class SendEmailService {

    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Async("SCMExecutor")
    public void sendResetPassMail(String resetPassToken, SendEmailDTO request){
        log.info("Execute method with configured executor - "+ Thread.currentThread().getName());
        log.info("sending email");
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            ClassPathResource image = new ClassPathResource("static/file/logo.png");
            ClassPathResource facebook = new ClassPathResource("static/file/facebook.jpg");
            ClassPathResource twitter = new ClassPathResource("static/file/twitter.jpg");
            Template template = configuration.getTemplate("mailResetPassword.tfl");
            Map<String, Object> input = new HashMap<>();
            input.put("title", "Reset password");
            input.put("token", resetPassToken);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, input);
            helper.setTo(request.getTo());
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);
            helper.addInline("logo", image);
            helper.addInline("facebook", facebook);
            helper.addInline("twitter", twitter);
            mailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
