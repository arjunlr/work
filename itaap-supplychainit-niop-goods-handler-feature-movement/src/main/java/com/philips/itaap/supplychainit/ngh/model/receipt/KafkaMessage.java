package com.philips.itaap.supplychainit.ngh.model.receipt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.ApiHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("CPD-START")
public class KafkaMessage {

    @JsonProperty("apiHeader")
    public ApiHeader apiHeader;

    @JsonProperty("goodsReceipts")
    public GoodsReceipts goodsReceiptItem;


    public ApiHeader getApiHeader() {
        return apiHeader;
    }

    public void setApiHeader(ApiHeader apiHeader) {
        this.apiHeader = apiHeader;
    }

    public GoodsReceipts getGoodsReceiptItem() {
        return goodsReceiptItem;
    }

    public void setGoodsReceiptItem(GoodsReceipts goodsReceiptItem) {
        this.goodsReceiptItem = goodsReceiptItem;
    }

}
