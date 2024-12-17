package com.philips.itaap.supplychainit.ngh.model.movement.inputpayload;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.GoodsMovementItemsBase;

public class GoodsMovementItems extends GoodsMovementItemsBase {


    @JsonProperty("serialNumberDetails")
    private SerialNumberDetails serialNumberDetails;

    public SerialNumberDetails getSerialNumberDetails() {
        return serialNumberDetails;
    }

    public void setSerialNumberDetails(SerialNumberDetails serialNumberDetails) {
        this.serialNumberDetails = serialNumberDetails;
    }
}
