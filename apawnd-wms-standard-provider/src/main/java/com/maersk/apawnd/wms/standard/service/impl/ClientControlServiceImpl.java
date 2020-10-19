package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.wms.standard.component.constant.CacheConstant;
import com.maersk.apawnd.wms.standard.mapper.ClientControlMapper;
import com.maersk.apawnd.wms.standard.model.ClientControlModel;
import com.maersk.apawnd.wms.standard.service.ClientControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ClientControlServiceImpl implements ClientControlService {

  private final ClientControlMapper clientControlMapper;

  public ClientControlServiceImpl(ClientControlMapper clientControlMapper) {
    this.clientControlMapper = clientControlMapper;
  }

  @Cacheable(
      cacheNames = CacheConstant.CACHE_CONFIGURATION_KEY_DC_2H,
      key = "#root.targetClass.name + '.' + #root.method.name + '.' + " +
          "#whId + '.' + #clientCode + '.' + #controlType + '.' + #eventName",
      unless = "#result == null || #result.size() == 0")
  @Override
  @Transactional
  public List<ClientControlModel> retrieve(
      String whId, String clientCode, String controlType, String eventName) {
    return clientControlMapper.selectByWhIdAndClientCodeAndControlTypeAndC1(whId, clientCode, controlType, eventName);
  }
}
