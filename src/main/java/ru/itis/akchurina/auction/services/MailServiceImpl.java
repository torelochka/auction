package ru.itis.akchurina.auction.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import ru.itis.akchurina.auction.dto.AuctionDto;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

@Component
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private final Template confirmMailTemplate;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            this.confirmMailTemplate = configuration.getTemplate("mail/winner.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.javaMailSender = javaMailSender;
    }

    @SneakyThrows
    @Override
    public void sendWinnerEmail(AuctionDto auctionDto) {
        StringWriter writer = new StringWriter();

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("winner_email", auctionDto.getWinner().getEmail());
        attributes.put("auction_id", auctionDto.getId().toString());
        confirmMailTemplate.process(attributes, writer);
        MimeMessagePreparator winnerMessage = getEmail(auctionDto.getWinner().getEmail(), writer.toString(), "Результат аукциона");
        MimeMessagePreparator ownerMessage = getEmail(auctionDto.getOwner().getEmail(), writer.toString(), "Результат аукциона");
        javaMailSender.send(winnerMessage);
        javaMailSender.send(ownerMessage);
    }

    private MimeMessagePreparator getEmail(String email, String mailText, String subject) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mailFrom);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailText, true);
        };
    }
}
