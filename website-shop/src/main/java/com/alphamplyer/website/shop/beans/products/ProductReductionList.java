package com.alphamplyer.website.shop.beans.products;

import java.util.List;

public class ProductReductionList {

    public List<Integer> productIDs;
    public Integer reductionID;

    public ProductReductionList(List<Integer> productIDs, Integer reductionID) {
        this.productIDs = productIDs;
        this.reductionID = reductionID;
    }
}