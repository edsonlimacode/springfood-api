package com.springfood.infrastructure.service.email;

import com.springfood.domain.interfaces.SendEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import jakarta.mail.internet.MimeMessage;

@Service
public class SmptEmailService implements SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freeMarkerConfiguration;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

            String body = this.processTemplate(message);

            mimeMessageHelper.setFrom("<no-reply>@foodapi.com.br");
            mimeMessageHelper.setTo(message.getDestinations().toArray(new String[0]));
            mimeMessageHelper.setSubject(message.getSubject());
            mimeMessageHelper.setText(body, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new SendEmailException("Não foi possível enviar o email", e);
        }
    }

    private String processTemplate(Message message) {

        try {
            Template template = freeMarkerConfiguration.getTemplate(message.getBody());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new SendEmailException("Não foi possível enviar o email", e);
        }
    }
}
