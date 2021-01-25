package com.korearbazar.ecom.Interface;

import com.korearbazar.ecom.model.BestSellerModel;
import com.korearbazar.ecom.model.NewProdModel;

public interface BestSeller {
    void setNewProd(NewProdModel newProdModel);

    void setBestSeller(BestSellerModel bestSellerModel);
}
