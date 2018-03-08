package com.ongraph.config;

import com.ongraph.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter implements Filter {
    @Autowired
    MongoDBCredentials mongoDBCredentials;

    public static final String TENANT_HEADER = "X-TenantID";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Inside Filter");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;
        String tenant = req.getHeader(TENANT_HEADER);
        boolean tenantSet = false;

        if(StringUtils.isEmpty(tenant)) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().write("{\"error\": \"No tenant supplied\"}");
            res.getWriter().flush();
            return;
        } else {
            if(StringUtils.equals(tenant, mongoDBCredentials.getPepsicoDatabaseName())) {
                TenantContext.setTenant(tenant);
            }
            tenantSet = true;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}

