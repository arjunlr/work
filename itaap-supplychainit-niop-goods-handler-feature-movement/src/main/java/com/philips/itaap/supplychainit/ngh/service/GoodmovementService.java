package com.philips.itaap.supplychainit.ngh.service;

import com.bazaarvoice.jolt.JsonUtils;
import com.philips.itaap.supplychainit.ngh.config.ApplicationProperties;
import com.philips.itaap.supplychainit.ngh.model.movement.GoodsMovementRequest;
import com.philips.itaap.supplychainit.ngh.model.movement.OutputPayload;
import com.philips.itaap.supplychainit.ngh.model.movement.inputpayload.GoodsMovementItems;
import com.philips.itaap.supplychainit.ngh.model.movement.inputpayload.InputApiHeader;
import com.philips.itaap.supplychainit.ngh.model.movement.outputpayload.GoodsMovement;
import com.philips.itaap.supplychainit.ngh.model.movement.outputpayload.OutputApiHeader;
import com.philips.itaap.supplychainit.ngh.model.movement.outputpayload.SerialNumbers;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@XSlf4j
@SuppressWarnings({"PMD-START", "CPD-START"})
public class GoodmovementService {

    @Autowired
    WebClientService webClientService;

    @Autowired
    ApplicationProperties properties;

    /**
     * This method is used for processing the message received from Kafka topic.
     *
     * @param input Consumed Message from kafka topic.
     * @return Mono of OutputPayload
     */
    public Mono<OutputPayload> transformPayload(GoodsMovementRequest input) {

        log.info("GoodsMovementPayload: ->{}", JsonUtils.toJsonString(input));


        // Populate ApiHeader from InputPayload
        OutputApiHeader apiHeader = new OutputApiHeader();
        InputApiHeader inputApiHeader = input.getApiHeader();
        apiHeader.setCorrelationId(inputApiHeader.getCorrelationId());
        apiHeader.setRecordName(inputApiHeader.getRecordName());
        apiHeader.setRecordId(inputApiHeader.getRecordId());
        String deliveryPlant = input.getGoodsMovement().getGoodsMovementItems().get(0).getDeliveryPlant();
        switch (deliveryPlant) {
            case "USLX":
                apiHeader.setConsumer("USLX");
                break;
            case "USPX":
                apiHeader.setConsumer("USPX");
                break;
            default:
                log.error("Invalid delivery plant: {}", deliveryPlant);
                return Mono.error(new IllegalArgumentException("Invalid delivery plant: " + deliveryPlant));
        }

        // Create OutputPayload instance
        OutputPayload output = new OutputPayload();

        apiHeader.setProvider(inputApiHeader.getProvider());
        apiHeader.setConsumerTimestamp(inputApiHeader.getConsumerTimestamp());
        apiHeader.setTestMode(inputApiHeader.getTestMode());
        apiHeader.setInfo1(inputApiHeader.getInfo1());
        apiHeader.setInfo2(inputApiHeader.getInfo2());
        apiHeader.setInfo3(inputApiHeader.getInfo3());
        output.setApiHeader(apiHeader);

        GoodsMovement goodsMovement = new GoodsMovement();
        goodsMovement.setRefDocNumber(input.getGoodsMovement().getRefDocNumber());
        goodsMovement.setDocDate(input.getGoodsMovement().getDocDate());
        goodsMovement.setPostingDate(input.getGoodsMovement().getPostingDate());
        goodsMovement.setHeaderText(input.getGoodsMovement().getHeaderText());

        // Mapping GoodsMovementItems from input, based on quantity
        int quantity = Integer.parseInt(input.getGoodsMovement().getGoodsMovementItems().get(0).getQuantity());
        List<GoodsMovementItems> goodsMovementItems = IntStream.range(0, quantity)
                .mapToObj(i -> {
                    GoodsMovementItems item = new GoodsMovementItems();
                    item.setMaterialId(input.getGoodsMovement().getGoodsMovementItems().get(0).getMaterialId());
                    item.setDeliveryPlant(input.getGoodsMovement().getGoodsMovementItems().get(0).getDeliveryPlant());
                    item.setBatch(input.getGoodsMovement().getGoodsMovementItems().get(0).getBatch());
                    item.setQuantity("1");
                    item.setMovementType(input.getGoodsMovement().getGoodsMovementItems().get(0).getMovementType());
                    return item;
                })
                .collect(Collectors.toList());

        goodsMovement.setGoodsMovementItems(goodsMovementItems);


        // Mapping SerialNumbers from input, based on quantity
        List<String> serialNumbers = List.of(input.getGoodsMovement().getGoodsMovementItems().get(0).getSerialNumberDetails().getSerialNumber().split(","));
        List<SerialNumbers> serialNumberList = IntStream.range(0, quantity)
                .mapToObj(i -> {
                    SerialNumbers serial = new SerialNumbers();
                    serial.setSerialNumber(serialNumbers.get(i % serialNumbers.size())); // Cycle through serial numbers
                    serial.setMaterialDocumentItem(String.format("%04d", i + 1));
                    return serial;
                })
                .collect(Collectors.toList());

        goodsMovement.setSerialNumbers(serialNumberList);


        output.setGoodsMovement(goodsMovement);
        return Mono.just(output)
                .doOnSuccess(payload -> log.debug("payload() Apigee Request: -> {}", JsonUtils.toJsonString(payload)));
    }
}
