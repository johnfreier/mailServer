package com.johnfreier.mail.config;

public interface Config {

    /**
     * Is the server enabled.
     * 
     * @return
     */
    boolean isEnabled();

    /**
     * Which port should the server listen on?
     * 
     * @return
     */
    int port();

}
