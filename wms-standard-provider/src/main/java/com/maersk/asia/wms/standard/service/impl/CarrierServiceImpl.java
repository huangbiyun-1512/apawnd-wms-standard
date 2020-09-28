package com.maersk.asia.wms.standard.service.impl;

import com.maersk.asia.wms.standard.component.constant.CacheConstant;
import com.maersk.asia.wms.standard.mapper.CarrierMapper;
import com.maersk.asia.wms.standard.model.CarrierModel;
import com.maersk.asia.wms.standard.service.CarrierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarrierServiceImpl implements CarrierService {

  private final CarrierMapper carrierMapper;

  public CarrierServiceImpl(CarrierMapper carrierMapper) {
    this.carrierMapper = carrierMapper;
  }

  @Cacheable(
      cacheNames = CacheConstant.CACHE_CONFIGURATION_KEY_DC_2H,
      key = "#root.targetClass.name + '.' + #root.method.name + '.' + #carrierName",
      unless = "#result == null || #result.size() == 0")
  @Override
  public List<CarrierModel> retrieveByCarrierName(String carrierName) {
    List<CarrierModel> carrierModels = carrierMapper.selectByCarrierName(carrierName);

    return carrierModels;
  }

}
