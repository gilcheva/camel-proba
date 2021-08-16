package com.example.camelproba.models;

import lombok.Data;

@Data
public class OutProduct {
  String sku;
  String warehouse;
  String barcode;
  String description;
  double value;
  String department;
  String packaging;
}
