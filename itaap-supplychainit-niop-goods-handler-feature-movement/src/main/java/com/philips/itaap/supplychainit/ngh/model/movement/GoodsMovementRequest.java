package com.philips.itaap.supplychainit.ngh.model.movement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.movement.inputpayload.InputApiHeader;
import com.philips.itaap.supplychainit.ngh.model.movement.inputpayload.InputGoodsMovement;

public class GoodsMovementRequest {

    @JsonProperty("apiHeader")
    private InputApiHeader apiHeader;

    @JsonProperty("goodsMovement")
    private InputGoodsMovement goodsMovement;

    public InputApiHeader getApiHeader() {
        return apiHeader;
    }

    public void setApiHeader(InputApiHeader apiHeader) {
        this.apiHeader = apiHeader;
    }

    public InputGoodsMovement getGoodsMovement() {
        return goodsMovement;
    }

    public void setGoodsMovement(InputGoodsMovement goodsMovement) {
        this.goodsMovement = goodsMovement;
    }
}
