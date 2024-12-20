package com.learnkafkastreams.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafkastreams.domain.Greeting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GreetingStreamsTopology {

    public static final String GREETINGS = "greetings";

    public static final String GREETINGS_OUTPUT = "greetings-output";

    private final ObjectMapper objectMapper;

    public GreetingStreamsTopology(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void process(StreamsBuilder streamsBuilder) {
        var greetingsStream = streamsBuilder.stream(GREETINGS,
                Consumed.with(Serdes.String(), new JsonSerde<>(Greeting.class, this.objectMapper)));
        greetingsStream.print(Printed.<String, Greeting>toSysOut().withLabel("greetingsStream"));

        var modifiedStream = greetingsStream
                .mapValues((readOnlyKey, value) -> {
                    if (value.message().equals("Error")) {
                        throw new IllegalStateException("Error occurred");
                    }

                    return new Greeting(value.message().toUpperCase(), value.timeStamp());
                });
        modifiedStream.print(Printed.<String, Greeting>toSysOut().withLabel("modifiedStream"));
        modifiedStream.to(GREETINGS_OUTPUT,
                Produced.with(Serdes.String(), new JsonSerde<>(Greeting.class, this.objectMapper)));
    }

}
