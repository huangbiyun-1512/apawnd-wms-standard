package com.maersk.highjump.generics.service;

import com.maersk.highjump.generics.model.CarrierModel;

import java.util.List;

public interface CarrierService {

  List<CarrierModel> retrieveByCarrierName(String carrierName);
}
