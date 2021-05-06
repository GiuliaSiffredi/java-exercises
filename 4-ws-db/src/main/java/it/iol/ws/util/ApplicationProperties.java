package it.iol.ws.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Lookup in resources/application-{env}.properties
 */
@Component
@Getter
public class ApplicationProperties implements IApplicationProperties {

    @Value("${logging.level.root}")
    private String logLevel;

}
