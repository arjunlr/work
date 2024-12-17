package com.philips.itaap.supplychainit.ngh.service;

import com.bazaarvoice.jolt.JsonUtils;
import com.philips.itaap.supplychainit.ngh.config.AppConstants;
import com.philips.itaap.supplychainit.ngh.config.ApplicationProperties;
import com.philips.itaap.supplychainit.ngh.model.receipt.KafkaMessage;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@XSlf4j
@SuppressWarnings({"PMD-START", "CPD-START"})
public class NiopRecieptsService {

    @Autowired
    WebClientService webClientService;

    @Autowired
    ApplicationProperties properties;

    /**
     * This method is used for processing the message received from Kafka topic.
     *
     * @param kafkaMessage Consumed Message from kafka topic.
     * @return Mono of Boolean
     */
    public Mono<Boolean> process(KafkaMessage kafkaMessage) {

        kafkaMessage.getApiHeader().setProvider(AppConstants.NIOP_US);
        if (log.isDebugEnabled()) {
            log.debug("process() : NIOP Request -> {}", JsonUtils.toJsonString(kafkaMessage));
        }
        //TODO : Business needs to provide NIOP token credentials and API URL
        return webClientService.getNIOPToken(properties.getTokenUrl())
                .flatMap(tokenResponse -> webClientService.invokeGoodReceiptApi(kafkaMessage, tokenResponse.getToken(), properties.getNiopRecieptUrl()))
                .flatMap(niopResponse -> Mono.just(true));
    }

}