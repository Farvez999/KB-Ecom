package com.frz.korearbazar.Interface;

import com.frz.korearbazar.model.BestSellerModel;
import com.frz.korearbazar.model.NewProdModel;

public interface BestSeller {
    void setNewProd(NewProdModel newProdModel);

    void setBestSeller(BestSellerModel bestSellerModel);
}
