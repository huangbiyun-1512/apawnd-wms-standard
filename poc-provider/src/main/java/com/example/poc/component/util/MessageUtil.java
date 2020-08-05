package com.example.poc.component.util;

import lombok.extern.slf4j.Slf4j;
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
}
