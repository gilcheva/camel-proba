package com.example.camelproba.models;

import lombok.Data;

@Data
public class InProduct {
  String sku;
  String warehouse;
  String barcode;
  String colour;
  int size;
  double value;
  String category;
  String occasion;
}
