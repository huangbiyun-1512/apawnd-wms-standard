package com.example.poc.service;

public interface OutboundService {

  void scaLoadedAck();

  Object retrieveDataFromCacheIfExisted(String key);

  void updateCache(String key);

  void removeCache(String key);
}
