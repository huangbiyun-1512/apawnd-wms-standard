package com.example.poc.component.util;

import com.example.poc.component.constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class MessageUtil {

  private final MessageSource messageSource;

  public MessageUtil(
      @Autowired MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public String get(String key) {
    return this.get(key, null, LocaleContextHolder.getLocale());
  }

  public String get(String key, Object[] objects) {
    return this.get(key, objects, LocaleContextHolder.getLocale());
  }

  public String get(String key, Object[] objects, Locale locale) {
    return messageSource.getMessage(key, objects, locale);
  }

  public String getCode(String key) {
    return this.get(
        StringUtils.join(key, MessageConstant.MESSAGE_SUFFIX_CODE),
        null,
        LocaleContextHolder.getLocale());
  }

  public String getTitle(String key) {
    return this.get(
        StringUtils.join(key, MessageConstant.MESSAGE_SUFFIX_TITLE),
        null,
        LocaleContextHolder.getLocale());
  }

  public String getTitle(String key, Object[] objects) {
    return this.get(
        StringUtils.join(key, MessageConstant.MESSAGE_SUFFIX_TITLE),
        objects,
        LocaleContextHolder.getLocale());
  }

  public String getDetail(String key) {
    return this.get(
        StringUtils.join(key, MessageConstant.MESSAGE_SUFFIX_DETAIL),
        null,
        LocaleContextHolder.getLocale());
  }

  public String getDetail(String key, Object[] objects) {
    return this.get(
        StringUtils.join(key, MessageConstant.MESSAGE_SUFFIX_DETAIL),
        objects,
        LocaleContextHolder.getLocale());
  }
}
