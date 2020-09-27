package com.maersk.highjump.generics.service;

import com.maersk.highjump.generics.dto.AsnDto;

public interface AsnService {

  void create(AsnDto asnDto);
  int deleteByWhIdAndShipmentNumberAndClientCode(
      String whId, String shipmentNumber, String clientCode);
  void replace(AsnDto asnDto);
}
