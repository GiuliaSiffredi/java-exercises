package it.iol.ws.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Lookup in resources/application.properties
 */
@Component
public class ApplicationProperties implements IApplicationProperties {

    @Value("${file.out}")
    private String fileOut;

    @Override
    public String getFileOut() {
        return fileOut;
    }
    
    @Value("${email.sender}")
    private String emailSender;

    @Override
    public String getEmailSender() {
        return emailSender;
    }
}
