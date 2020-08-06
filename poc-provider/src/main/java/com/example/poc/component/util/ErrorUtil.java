package com.example.poc.component.util;

import com.common.poc.components.dto.BaseErrorDto;
import com.example.poc.component.constant.MessageConstant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorUtil {

    private final MessageUtil messageUtil;

    public ErrorUtil(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    public List<BaseErrorDto> build400ErrorList(String key) {
        return this.buildErrorList(HttpStatus.BAD_REQUEST.value(), key);
    }

    public List<BaseErrorDto> build400ErrorList(String key, String detail) {
        return this.buildErrorList(HttpStatus.BAD_REQUEST.value(), key, detail);
    }

    public List<BaseErrorDto> build408ErrorList(String key) {
        return this.buildErrorList(HttpStatus.REQUEST_TIMEOUT.value(), key);
    }

    public List<BaseErrorDto> build408ErrorList(String key, String detail) {
        return this.buildErrorList(HttpStatus.REQUEST_TIMEOUT.value(), key, detail);
    }

    public List<BaseErrorDto> build500ErrorList(String key) {
        return this.buildErrorList(HttpStatus.INTERNAL_SERVER_ERROR.value(), key);
    }

    public List<BaseErrorDto> build500ErrorList(String key, String detail) {
        return this.buildErrorList(HttpStatus.INTERNAL_SERVER_ERROR.value(), key, detail);
    }

    public List<BaseErrorDto> buildErrorList(int status, String key) {
        List<BaseErrorDto> list = new ArrayList();
        list.add(this.buildError(status, key));
        return list;
    }

    public List<BaseErrorDto> buildErrorList(int status, String key, String detail) {
        List<BaseErrorDto> list = new ArrayList();
        list.add(this.buildError(status, key, detail));
        return list;
    }

    public BaseErrorDto buildError(int status, String key) {
        BaseErrorDto error =
            BaseErrorDto
                .builder()
                .status(String.valueOf(status))
                .code(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_CODE))
                .title(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_TITLE))
                .detail(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_DETAIL))
                .build();
        return error;
    }

    public BaseErrorDto buildError(int status, String key, String detail) {
        BaseErrorDto error =
            BaseErrorDto
                .builder()
                .status(String.valueOf(status))
                .code(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_CODE))
                .title(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_TITLE))
                .detail(detail)
                .build();
        return error;
    }

    public BaseErrorDto buildError(int status, String key, Object[] objs) {
        BaseErrorDto error =
            BaseErrorDto
                .builder()
                .status(String.valueOf(status))
                .code(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_CODE))
                .title(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_TITLE))
                .detail(messageUtil.get(key + MessageConstant.MESSAGE_SUFFIX_DETAIL, objs))
                .build();
        return error;
    }
}
