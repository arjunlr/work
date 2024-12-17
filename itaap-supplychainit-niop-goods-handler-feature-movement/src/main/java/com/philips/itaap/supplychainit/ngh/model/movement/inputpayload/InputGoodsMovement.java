package com.philips.itaap.supplychainit.ngh.model.movement.inputpayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.GoodsMovementBase;

public class InputGoodsMovement extends GoodsMovementBase {

    @JsonProperty("serialNumberDetails")
    private SerialNumberDetails serialNumberDetails;

    public SerialNumberDetails getSerialNumberDetails() {
        return serialNumberDetails;
    }

    public void setSerialNumberDetails(SerialNumberDetails serialNumberDetails) {
        this.serialNumberDetails = serialNumberDetails;
    }
}
