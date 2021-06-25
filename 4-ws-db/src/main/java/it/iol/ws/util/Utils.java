package it.iol.ws.util;

import lombok.val;

public abstract class Utils {

    public static String getEnv() {
        val env = "spring.profiles.active";
        return System.getenv(env) != null ? System.getenv(env) : System.getProperty(env);
    }
}