package com.philips.itaap.supplychainit.ngh.model.movement.outputpayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.GoodsMovementBase;

import java.util.List;


public class GoodsMovement extends GoodsMovementBase {


    @JsonProperty("serialNumbers")
    private List<SerialNumbers> serialNumbers;

    public List<SerialNumbers> getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(List<SerialNumbers> serialNumbers) {
        this.serialNumbers = serialNumbers;
    }
}

