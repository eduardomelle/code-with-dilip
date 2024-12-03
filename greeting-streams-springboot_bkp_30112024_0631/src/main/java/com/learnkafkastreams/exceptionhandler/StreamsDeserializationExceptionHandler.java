package com.learnkafkastreams.exceptionhandler;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamsDeserializationExceptionHandler implements DeserializationExceptionHandler {

    int errorCounter = 0;

    @Override
    public void configure(Map<String, ?> arg0) {

    }

    @Override
    public DeserializationHandlerResponse handle(ProcessorContext context, ConsumerRecord<byte[], byte[]> record,
            Exception exception) {
        log.error("Exception is : {}, and the kafka record is {}", exception.getMessage(), record, exception);

        log.info("errorCounter : {}", this.errorCounter);

        if (this.errorCounter < 2) {
            this.errorCounter++;
            return DeserializationHandlerResponse.CONTINUE;
        }

        return DeserializationHandlerResponse.FAIL;
    }

}
