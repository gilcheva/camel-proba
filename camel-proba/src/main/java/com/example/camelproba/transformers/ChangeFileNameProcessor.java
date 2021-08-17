package com.example.camelproba.transformers;

import com.example.camelproba.models.InProduct;
import com.example.camelproba.models.OutProduct;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ChangeFileNameProcessor implements Processor {

  public static final String NO_SUCH_CATEGORY_MSG = "No such category.";
  public static final String NO_SUCH_OCCASION_MSG = "No such occasion.";

  private static int messageOrder = 1;

  @Override
  public void process(Exchange exchange) throws Exception {
    InProduct inProduct = exchange.getIn().getBody(InProduct.class);
    exchange.getIn().setHeader(Exchange.FILE_NAME, inProduct.getSku() + "_" + (messageOrder++) + ".json");
  }

}
