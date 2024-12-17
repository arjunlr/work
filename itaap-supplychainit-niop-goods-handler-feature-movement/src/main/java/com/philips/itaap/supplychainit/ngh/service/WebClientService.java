package com.philips.itaap.supplychainit.ngh.service;

import com.bazaarvoice.jolt.JsonUtils;
import com.philips.itaap.ms.dev.base.exception.ServiceException;
import com.philips.itaap.supplychainit.ngh.config.AppConstants;
import com.philips.itaap.supplychainit.ngh.config.ApplicationProperties;
import com.philips.itaap.supplychainit.ngh.model.TokenResponse;
import com.philips.itaap.supplychainit.ngh.model.movement.GoodsMovementRequest;
import com.philips.itaap.supplychainit.ngh.model.receipt.KafkaMessage;
import com.philips.itaap.supplychainit.ngh.model.receipt.NIOPTokenResponse;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@XSlf4j
@SuppressWarnings({"PMD-START", "CPD-START"})
public class WebClientService {

//    @Autowired
//    EmailService emailService;

    @Autowired
    ApplicationProperties properties;

    @Autowired
    private WebClient webClient;

    public Mono<TokenResponse> getToken(MultiValueMap<String, String> formData, String url) {

        if (log.isInfoEnabled()) {
            log.info("getToken() : Called");
        }

//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//        formData.add(AppConstants.GRANT_TYPE, properties.getGrantType());
//        formData.add(AppConstants.CLIENT_ID, properties.getClientId());
//        formData.add(AppConstants.CLIENT_SECRET, properties.getClientSecret());
//        formData.add(AppConstants.SCOPE, properties.getScope());

        /* WebClient Post call */
        WebClient.ResponseSpec responseSpec = getRequestBodySpec(url,
                MediaType.APPLICATION_FORM_URLENCODED, HttpMethod.POST).body(BodyInserters.fromFormData(formData)).retrieve();

        return getOnStatus(responseSpec).bodyToMono(TokenResponse.class).doOnError(exception -> {
//            EmailBody emailBody = EmailBody.builder()
//                    .goldenId(kafkaMessage.getOpportunity().getOpportunityId())
//                    .ownerFirstName(kafkaMessage.getOpportunity().getOwnerFirstName())
//                    .ownerLastName(kafkaMessage.getOpportunity().getOwnerLastName())
//                    .errorDetails("Apigee Generate Token:" + " " + exception).build();
//            emailService.sendErrorEmail(emailBody, kafkaMessage.getOpportunity().getOwnerEmail(), kafkaMessage.getApiHeader().getInfo1());
        });
    }

    public Mono<NIOPTokenResponse> getNIOPToken(String url) {
        Map<String, String> formData = new HashMap<>();
        formData.put(AppConstants.USERNAME, properties.getUsername());
        formData.put(AppConstants.PASSWORD, properties.getPassword());
        if (log.isInfoEnabled()) {
            log.info("getNIOPToken() : Called->{}", JsonUtils.toJsonString(formData));
        }

        /* WebClient Post call */
        WebClient.ResponseSpec responseSpec = getRequestBodySpec(url,
                MediaType.APPLICATION_JSON, HttpMethod.POST).bodyValue(JsonUtils.toJsonString(formData)).retrieve();

        return getOnStatus(responseSpec).bodyToMono(NIOPTokenResponse.class).doOnError(exception -> {
//            EmailBody emailBody = EmailBody.builder()
//                    .goldenId(kafkaMessage.getOpportunity().getOpportunityId())
//                    .ownerFirstName(kafkaMessage.getOpportunity().getOwnerFirstName())
//                    .ownerLastName(kafkaMessage.getOpportunity().getOwnerLastName())
//                    .errorDetails("Apigee Generate Token:" + " " + exception).build();
//            emailService.sendErrorEmail(emailBody, kafkaMessage.getOpportunity().getOwnerEmail(), kafkaMessage.getApiHeader().getInfo1());
        }).doOnSuccess(dataResponse -> {
            if (log.isInfoEnabled()) {
                log.info("getNIOPToken() : NIOP Token Response ->{}", dataResponse);
            }
        });
    }

    public Mono<String> invokeGoodMovementApi(GoodsMovementRequest payload, String token, String url) {
        if (log.isInfoEnabled()) {
            log.info("invokeGoodHandlerApi(): Apigee Payload Json Request -> {}", JsonUtils.toJsonString(payload));
        }

        WebClient.ResponseSpec responseSpec = getRequestBodySpec(url, MediaType.APPLICATION_JSON, HttpMethod.POST)
                .bodyValue(JsonUtils.toJsonString(payload))
                .headers(httpHeaders -> {
                    httpHeaders.setBearerAuth(token);
                    if (log.isDebugEnabled()) {
                        log.debug("invokeGoodHandlerApi() : httpHeaders -> {}", httpHeaders);
                    }
                }).retrieve();

        return getOnStatus(responseSpec)
                .bodyToMono(String.class)
                .doOnError(exception -> {
//                    EmailBody emailBody = EmailBody.builder()
//                            .goldenId(kafkaMessage.getOpportunity().getOpportunityId())
//                            .ownerFirstName(kafkaMessage.getOpportunity().getOwnerFirstName())
//                            .ownerLastName(kafkaMessage.getOpportunity().getOwnerLastName())
//                            .errorDetails("Apigee Update API:" + " " + exception).build();
//                    emailService.sendErrorEmail(emailBody, kafkaMessage.getOpportunity().getOwnerEmail(), kafkaMessage.getApiHeader().getInfo1());
                })
                .doOnSuccess(dataResponse -> {
                    if (log.isInfoEnabled()) {
                        log.info("invokeGoodHandlerApi() : Apigee Response ->{}", dataResponse);
                    }
                });
    }

    public Mono<String> invokeGoodReceiptApi(KafkaMessage payload, String token, String url) {
        if (log.isInfoEnabled()) {
            log.info("invokeGoodReceiptApi(): Goods Receipt Payload Json Request -> {}", JsonUtils.toJsonString(payload));
        }

        WebClient.ResponseSpec responseSpec = getRequestBodySpec(url, MediaType.APPLICATION_JSON, HttpMethod.POST)
                .bodyValue(JsonUtils.toJsonString(payload))
                .headers(httpHeaders -> {
                    httpHeaders.setBearerAuth(token);
                    if (log.isDebugEnabled()) {
                        log.debug("invokeGoodReceiptApi() : httpHeaders -> {}", httpHeaders);
                    }
                }).retrieve();

        return getOnStatus(responseSpec)
                .bodyToMono(String.class)
                .doOnError(exception -> {
//                    EmailBody emailBody = EmailBody.builder()
//                            .goldenId(kafkaMessage.getOpportunity().getOpportunityId())
//                            .ownerFirstName(kafkaMessage.getOpportunity().getOwnerFirstName())
//                            .ownerLastName(kafkaMessage.getOpportunity().getOwnerLastName())
//                            .errorDetails("Apigee Update API:" + " " + exception).build();
//                    emailService.sendErrorEmail(emailBody, kafkaMessage.getOpportunity().getOwnerEmail(), kafkaMessage.getApiHeader().getInfo1());
                })
                .doOnSuccess(dataResponse -> {
                    if (log.isInfoEnabled()) {
                        log.info("invokeGoodReceiptApi() : NIOP Response ->{}", dataResponse);
                    }
                });
    }

    /**
     * This method is used to prepare RequestBodySpec for WebClient.
     *
     * @param path      String Path
     * @param mediaType MediaType type
     * @return WebClient Response Spec
     */
    private WebClient.RequestBodySpec getRequestBodySpec(String path, MediaType mediaType, HttpMethod httpMethod) {
        if (log.isDebugEnabled()) {
            log.debug("getRequestBodySpec() : path -> {}, mediaType -> {}, httpMethod -> {}", path, mediaType, httpMethod);
        }

        return webClient.method(httpMethod).uri(path).contentType(mediaType);
    }

    /**
     * This method is used to capture exception if status is 4xx/5xx exception is thrown else ResponseSpec is returned.
     *
     * @param responseSpec WebClient.ResponseSpec
     * @return ResponseSpec
     */
    private WebClient.ResponseSpec getOnStatus(WebClient.ResponseSpec responseSpec) {
        return responseSpec.onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class).flatMap(responseBody -> {
            BasicJsonParser parser = new BasicJsonParser();
            String errorMessage = (String) parser.parseMap(responseBody).get("message");
            if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
                log.error("apigeeService(): Exception Occurred {} -> {}", response.statusCode(), errorMessage);
            }
            throw new ServiceException(response.statusCode(), response.rawStatusCode(), responseBody);
        }));
    }
}