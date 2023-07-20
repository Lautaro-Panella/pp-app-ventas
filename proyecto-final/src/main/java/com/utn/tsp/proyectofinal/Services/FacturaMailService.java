package com.utn.tsp.proyectofinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class FacturaMailService {

    @Autowired
    JavaMailSender mailSender;

    @Value("${mail.from}")
    String from;

    /**
     *
     * @param mailTo
     * @param facturaId
     * @return
     * @throws MessagingException
     */
    @Async
    public boolean sendEmail(String mailTo, String facturaId) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(this.from);
            helper.setTo(mailTo);
            helper.setSubject("Malva Love - Envío de comprobante #" + facturaId);
            helper.setText("Gracias por su compra!");

            // Se adjunta Factura en PDF
            String fileName = facturaId + "_factura.pdf";
            FileSystemResource file = new FileSystemResource(new File("src//main//resources//facturas//" + fileName));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
