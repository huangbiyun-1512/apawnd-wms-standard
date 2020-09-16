package com.maersk.highjump.generics.service;

public interface OutboundService {

  void scaLoadedAck();

  Object retrieveDataFromCacheIfExisted(String key);

  void updateCache(String key);

  void removeCache(String key);
}
