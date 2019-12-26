package com.mycompany.myapp.service;

import com.mycompany.myapp.config.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class JhipsterKafkaKafkaConsumer {
    private final Logger log = LoggerFactory.getLogger(JhipsterKafkaKafkaConsumer.class);

    @KafkaListener(topics = "KafkaMessage", groupId = "group_id")
    public void consume(String message) {
        log.debug("Consumed message: " + message);
    }
}
