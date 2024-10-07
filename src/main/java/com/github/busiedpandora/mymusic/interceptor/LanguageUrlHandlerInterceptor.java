package com.github.busiedpandora.mymusic.interceptor;

import com.github.busiedpandora.mymusic.controller.MyMusicController;
import com.github.busiedpandora.mymusic.exception.UnknownLanguageAbbreviationException;
import com.github.busiedpandora.mymusic.model.Language;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;
import java.util.Map;

@Component
public class LanguageUrlHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if(mv != null && localeResolver!= null) {
            Map attributeVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            String langAbbr = (String) attributeVariables.get(MyMusicController.ATTRIBUTE_LANGUAGE);

            if(langAbbr == null) {
                langAbbr = Language.getDefaultLanguage().getAbbreviation();
            }

            if(Language.getAbbreviations().contains(langAbbr)) {
                localeResolver.setLocale(request, response, new Locale(langAbbr));
                mv.addObject(MyMusicController.MODEL_LANGUAGE, langAbbr);
            }
            else {
                throw new UnknownLanguageAbbreviationException(langAbbr);
            }
        }
    }
}
