package it.iol.ws.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties implements IApplicationProperties {

    @Value("${logging.level.root}")
    private String logLevel;

    public String getLogLevel() {
        return logLevel;
    }
}
