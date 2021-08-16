package com.example.camelproba.transformers;

import com.example.camelproba.models.InProduct;
import com.example.camelproba.models.OutProduct;
import org.springframework.stereotype.Component;

@Component
public class InProductToOutProductBeanConverter {

  public static OutProduct toOutProduct (InProduct inProduct) {
    OutProduct outProduct = new OutProduct();
    outProduct.setSku(inProduct.getSku());
    outProduct.setWarehouse(inProduct.getWarehouse());
    outProduct.setBarcode(inProduct.getBarcode());
    outProduct.setDescription(inProduct.getSku()+" "+inProduct.getSize()+" "+inProduct.getColour());
    outProduct.setValue(inProduct.getValue());
    switch (inProduct.getCategory()){
      case "T-Shirts":
        outProduct.setDepartment("Clothing");
        break;
      case "Bracelet":
        outProduct.setDepartment("Accessories");
        break;
    }
    switch (inProduct.getOccasion()){
      case "Casual":
        outProduct.setPackaging("Standard");
        break;
      case "Formal":
        outProduct.setPackaging("Luxury");
        break;
    }
    return outProduct;
  }

}
