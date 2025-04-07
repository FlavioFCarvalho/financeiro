package com.reobotnet.financeiro.utils;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    private final MessageSource messageSource;

    public MessageUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String chave) {
        return messageSource.getMessage(chave, null, LocaleContextHolder.getLocale());
    }

    public String get(String chave, Object... parametros) {
        return messageSource.getMessage(chave, parametros, LocaleContextHolder.getLocale());
    }
}
