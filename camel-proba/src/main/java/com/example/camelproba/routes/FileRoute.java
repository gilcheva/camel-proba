package com.example.camelproba.routes;

import com.example.camelproba.models.InProduct;
import com.example.camelproba.models.OutProduct;
import com.example.camelproba.transformers.ChangeFileNameProcessor;
import com.example.camelproba.transformers.InProductToOutProductBeanConverter;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

  public static final String INCORRECT_JSON_MESSAGE = "Incorrect json format.";
  public static final String ONE_FILE_COPIED_MSG = "One file copied.";

  @Override
  public void configure() throws Exception {

    onException(MismatchedInputException.class, IllegalArgumentException.class)
        .useOriginalMessage()
        .log(INCORRECT_JSON_MESSAGE)
        .to("file:D:/error")
        .handled(true)
        .end();

    onException(IllegalArgumentException.class)
        .useOriginalMessage()
        .log(exceptionMessage().toString())
        .to("file:D:/error")
        .handled(true)
        .end();

    from("file:D:/inbox?noop=true")
        .routeId("first")
        .unmarshal(new JacksonDataFormat(InProduct.class))
        .process(new ChangeFileNameProcessor())
        .bean(new InProductToOutProductBeanConverter())
        .marshal(new JacksonDataFormat(OutProduct.class))
        .to("file:D:/outbox")
        .log(ONE_FILE_COPIED_MSG)
        .stop();
  }

}
