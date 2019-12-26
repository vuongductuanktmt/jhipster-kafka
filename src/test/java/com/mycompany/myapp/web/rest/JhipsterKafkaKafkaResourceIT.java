package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterKafkaApp;
import com.mycompany.myapp.service.JhipsterKafkaKafkaProducer;
import com.mycompany.myapp.service.JhipsterKafkaKafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.KafkaContainer;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = JhipsterKafkaApp.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class JhipsterKafkaKafkaResourceIT {

    private MockMvc restMockMvc;

    private static boolean started = false;

    private static KafkaContainer kafkaContainer;

    @Autowired
    private JhipsterKafkaKafkaProducer producer;

    @Autowired
    private JhipsterKafkaKafkaConsumer consumer;

    private static final int MAX_ATTEMPT = 5;

    @BeforeAll
    public static void startServer() {
        if (!started) {
            startTestcontainer();
            started = true;
        }
    }

    private static void startTestcontainer() {
        kafkaContainer = new KafkaContainer("5.3.1");
        kafkaContainer.start();
        System.setProperty("kafkaBootstrapServers", kafkaContainer.getBootstrapServers());
    }

    @BeforeEach
    public void setup() {
        JhipsterKafkaKafkaResource kafkaResource = new JhipsterKafkaKafkaResource(producer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();

        producer.init();
    }
}

