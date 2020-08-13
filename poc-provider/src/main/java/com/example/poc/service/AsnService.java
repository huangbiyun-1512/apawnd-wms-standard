package com.example.poc.service;

import com.example.poc.dto.AsnDto;
import com.example.poc.model.EsbRcptModel;
import com.github.pagehelper.Page;

public interface AsnService {

  void createAsn(AsnDto asnDto);
  Page<EsbRcptModel> findAsnByPage(int pageNo, int pageSize);

}
