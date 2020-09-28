package com.maersk.asia.wms.standard.dc.service;

import com.maersk.asia.wms.standard.dc.dto.AsnDto;

public interface AsnService {

  AsnDto retrieve(String whId, String shipmentNumber);
  void create(AsnDto asnDto);
  int update(AsnDto asnDto);
  int delete(String whId, String shipmentNumber, String clientCode);
  void merge(AsnDto asnDto);
  void renew(AsnDto asnDto);
}
