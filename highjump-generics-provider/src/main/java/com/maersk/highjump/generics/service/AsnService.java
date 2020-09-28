package com.maersk.highjump.generics.service;

import com.maersk.highjump.generics.dto.AsnDto;

public interface AsnService {

  AsnDto retrieve(String whId, String shipmentNumber);
  void create(AsnDto asnDto);
  int update(AsnDto asnDto);
  int delete(String whId, String shipmentNumber, String clientCode);
  void merge(AsnDto asnDto);
  void renew(AsnDto asnDto);
}
