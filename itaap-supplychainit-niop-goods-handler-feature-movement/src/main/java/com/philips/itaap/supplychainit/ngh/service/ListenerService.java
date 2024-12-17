package com.philips.itaap.supplychainit.ngh.service;


import com.bazaarvoice.jolt.JsonUtils;
import com.philips.itaap.ms.dev.base.exception.ServiceException;
import com.philips.itaap.supplychainit.ngh.model.receipt.KafkaMessage;
import com.philips.itaap.supplychainit.ngh.util.HandlerUtils;
import lombok.extern.slf4j.XSlf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@XSlf4j
@SuppressWarnings({"PMD-START", "CPD-START"})
public class ListenerService {

    @Autowired
    NiopRecieptsService niopRecieptsService;

    /**
     * This method is for reading the consumerRecord from Kafka topic.
     *
     * @param consumerRecord Actual consumerRecord received in kafka topic
     * @param acknowledgment Used to acknowledge the kafka server about the
     *                       successful consumption of the consumerRecord
     */
    @KafkaListener(topics = "${kafka.topic.read}", concurrency = "${kafka.consumer.concurrency}", groupId = "${spring.kafka.consumer.group-id}")
    public void readMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {

        if (log.isDebugEnabled()) {
            log.debug("------------------------------");
            log.debug("Received => Topic -> {}, Partition -> {}, OffSet -> {}, Message -> {} ", consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.value());
        }
        try {
            KafkaMessage kafkaMessage = HandlerUtils.readValue(consumerRecord.value(), KafkaMessage.class);
            if (log.isInfoEnabled()) {
                log.info("readMessage() : Kafka CDM message ->  {}", JsonUtils.toJsonString(kafkaMessage));
            }
            if (consumerRecord.value() == null) {
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Invalid Kafka Message : ".concat(consumerRecord.value()));
            }
            niopRecieptsService.process(kafkaMessage).doOnSuccess(isSuccess -> {
                acknowledgment.acknowledge();
                if (isSuccess) {
                    log.info("readMessage() : Processed successfully => Topic -> {}, Partition -> {}, OffSet -> {},Kafka Message -> {}", consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.value());
                } else {
                    log.info("readMessage() : Processed with failures => Topic -> {}, Partition -> {}, OffSet -> {}, Kafka Message -> {}", consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.value());
                }
            }).subscribe();
        } catch (Exception e) {
            acknowledgment.acknowledge();
            if (log.isErrorEnabled()) {
                log.error("readMessage() : Processed with failures => Topic -> {}, Partition -> {}, OffSet -> {}, Kafka Message -> {}", consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.value());
            }
        }
    }
}
