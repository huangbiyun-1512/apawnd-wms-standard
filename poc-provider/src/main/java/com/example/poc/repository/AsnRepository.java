package com.example.poc.repository;

import com.example.poc.model.EsbRcptModel;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AsnRepository {

  Integer insert(EsbRcptModel esbRcptModel);
  Page<EsbRcptModel> findByPage();
}
