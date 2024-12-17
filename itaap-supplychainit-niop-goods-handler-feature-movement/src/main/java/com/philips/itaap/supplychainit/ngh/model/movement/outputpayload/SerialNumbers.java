package com.philips.itaap.supplychainit.ngh.model.movement.outputpayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SerialNumbers {

    @JsonProperty("serialNumber")
    private String serialNumber;

    @JsonProperty("materialDocumentItem")
    private String materialDocumentItem;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMaterialDocumentItem() {
        return materialDocumentItem;
    }

    public void setMaterialDocumentItem(String materialDocumentItem) {
        this.materialDocumentItem = materialDocumentItem;
    }
}
