package cn.bounter.mdc.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * 链路追踪过滤器
 */
@Component
@WebFilter("/")
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        //初始化TraceId
        MDC.put("traceId", UUID.randomUUID().toString());

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        //清理MDC
        MDC.clear();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
