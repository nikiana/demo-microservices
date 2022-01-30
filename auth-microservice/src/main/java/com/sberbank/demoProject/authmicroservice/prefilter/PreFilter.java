package com.sberbank.demoProject.authmicroservice.prefilter;

import com.sberbank.demoProject.authmicroservice.security.SecurityUser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Настраиваем редирект запросов через Zuul
 */
@Component
public class PreFilter extends ZuulFilter {

    /**
     * Тип "pre" - фильтр будет вызываться до того, как запрос будет перенаправлен на другой сервис
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * Порядок фильтра в цепочке
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Наш фильтр добавляет хедер "USER_ID" в запрос перед редиректом
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser) {
            SecurityUser userInfo = (SecurityUser) authentication.getPrincipal();
            requestContext.addZuulRequestHeader("USER_ID", userInfo.getId().toString());
        }
        return null;
    }
}
