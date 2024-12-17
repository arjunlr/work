package com.philips.itaap.supplychainit.ngh.controller;

import com.bazaarvoice.jolt.JsonUtils;
import com.philips.itaap.supplychainit.ngh.model.movement.GoodsMovementRequest;
import com.philips.itaap.supplychainit.ngh.model.movement.OutputPayload;
import com.philips.itaap.supplychainit.ngh.service.GoodmovementService;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@XSlf4j
@RestController
public class GoodsController {

    @Autowired
    GoodmovementService goodsMovementService;


    @PreAuthorize("hasPermission('','api','write')")
    @PostMapping(value = "${api.niop-goods-handler}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OutputPayload> goodsMovement(@RequestBody GoodsMovementRequest payload) {

        if (log.isDebugEnabled()) {
            log.debug("goodsMovementService(): Request accepted -> {}", JsonUtils.toJsonString(payload));
        }

        return goodsMovementService.transformPayload(payload)
                .doOnError(e -> {
                    if (log.isErrorEnabled()) {
                        log.error("goodsMovement(): Error -> {}", e.getMessage());
                    }
                });


    }
}






