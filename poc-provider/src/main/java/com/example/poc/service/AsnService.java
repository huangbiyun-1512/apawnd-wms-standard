package com.example.poc.service;

import com.common.poc.components.exception.BusinessException;
import com.example.poc.dto.AsnDto;

public interface AsnService {

  void createAsn(AsnDto asnDto);

  void deleteAsn(String criteria) throws BusinessException;
}
