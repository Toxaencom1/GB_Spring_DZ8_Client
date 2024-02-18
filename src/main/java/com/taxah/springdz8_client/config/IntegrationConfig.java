package com.taxah.springdz8_client.config;

import com.taxah.springdz8_client.model.RequestStringDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Configuration class for integration components.
 */
@Configuration
public class IntegrationConfig {

    /**
     * Creates a message channel for text input.
     *
     * @return The created message channel.
     */
    @Bean
    public MessageChannel textInputChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a message channel for file writing.
     *
     * @return The created message channel.
     */
    @Bean
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }

    /**
     * Configures the main transformer to decorate text requests.
     *
     * @return The configured transformer.
     */
    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> mainTransformer() {
        return text -> RequestStringDecorator.getInstance().decorate(text);
    }

    /**
     * Configures the message handler for writing messages to a file.
     *
     * @return The configured file writing message handler.
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("src/main/resources/files"));
        handler.setAutoCreateDirectory(true);
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);

        return handler;
    }

    /**
     * Creates a bean for the request string decorator.
     *
     * @return The request string decorator bean.
     */
    @Bean
    public RequestStringDecorator decoratorBean() {
        return RequestStringDecorator.getInstance();
    }
}
