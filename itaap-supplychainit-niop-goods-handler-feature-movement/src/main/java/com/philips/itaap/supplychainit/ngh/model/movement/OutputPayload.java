package com.philips.itaap.supplychainit.ngh.model.movement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.movement.outputpayload.GoodsMovement;
import com.philips.itaap.supplychainit.ngh.model.movement.outputpayload.OutputApiHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OutputPayload {

    @JsonProperty("apiHeader")
    private OutputApiHeader apiHeader;

    @JsonProperty("goodsMovement")
    private GoodsMovement goodsMovement;

    public OutputApiHeader getApiHeader() {
        return apiHeader;
    }

    public GoodsMovement getGoodsMovement() {
        return goodsMovement;
    }

    public void setGoodsMovement(GoodsMovement goodsMovement) {
        this.goodsMovement = goodsMovement;
    }
}