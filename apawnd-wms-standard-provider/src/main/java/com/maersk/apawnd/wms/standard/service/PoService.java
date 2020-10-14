package com.maersk.apawnd.wms.standard.service;

public interface PoService {

  boolean isPoExisted(String whId, String poNumber);

  boolean isPoDetailExisted(
      String whId, String poNumber,
      String lineNumber, String itemNumber, String scheduleNumber);
}
