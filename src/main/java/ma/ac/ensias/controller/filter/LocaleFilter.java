package ma.ac.ensias.controller.filter;

import ma.ac.ensias.controller.attribute.LocaleValue;
import ma.ac.ensias.controller.attribute.RequestParameterName;
import ma.ac.ensias.controller.attribute.SessionAttributeName;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String currentLocale = request.getParameter(RequestParameterName.CURRENT_LOCALE);
        HttpSession session = request.getSession();
        if(currentLocale != null && !currentLocale.isEmpty()) {
            session.setAttribute(SessionAttributeName.CURRENT_LOCALE, currentLocale);
        }else{
            String sessionLocale = (String)session.getAttribute(SessionAttributeName.CURRENT_LOCALE);
            if(sessionLocale == null || sessionLocale.isEmpty()){
                session.setAttribute(SessionAttributeName.CURRENT_LOCALE, LocaleValue.EN.getLocale());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}