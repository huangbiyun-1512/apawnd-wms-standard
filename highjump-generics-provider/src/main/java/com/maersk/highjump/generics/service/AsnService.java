package com.maersk.highjump.generics.service;

import com.maersk.commons.component.exception.BusinessException;
import com.maersk.highjump.generics.dto.AsnDto;

public interface AsnService {

  void createAsn(AsnDto asnDto);

  void deleteAsn(String criteria) throws BusinessException;
}
