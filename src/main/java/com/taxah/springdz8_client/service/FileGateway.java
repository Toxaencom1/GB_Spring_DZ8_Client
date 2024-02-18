package com.taxah.springdz8_client.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Messaging gateway interface for writing data to a file.
 */
@MessagingGateway(defaultRequestChannel = "textInputChannel")
public interface FileGateway {
    /**
     * Writes data to a file.
     *
     * @param fileName The name of the file to write to.
     * @param data     The data to write to the file.
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}
