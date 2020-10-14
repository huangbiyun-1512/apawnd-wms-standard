package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.wms.standard.mapper.ItemMasterMapper;
import com.maersk.apawnd.wms.standard.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

  private final ItemMasterMapper itemMasterMapper;

  public ItemServiceImpl(ItemMasterMapper itemMasterMapper) {
    this.itemMasterMapper = itemMasterMapper;
  }

  @Override
  public boolean isItemExisted(String whId, String itemNumber) {
    if (itemMasterMapper.selectCountByWhIdAndItemNumber(whId, itemNumber) > 0) {
      return true;
    }

    return false;
  }
}
