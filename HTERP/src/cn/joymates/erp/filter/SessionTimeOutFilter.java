package cn.joymates.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.joymates.erp.domain.User;

/**
 * session过期
 * 
 * @author Jackie Hou
 *
 */
public class SessionTimeOutFilter implements Filter {
	
	private Log logger = LogFactory.getLog(SessionTimeOutFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpSession sess = req.getSession();
		HttpServletResponse resp = (HttpServletResponse)arg1;
		resp.setCharacterEncoding("UTF-8");
		User user = (User)sess.getAttribute("loggedUser");
		if (user == null || user.getUserId() == null) {
			logger.info("session过期，请重新登录！");
			req.getRequestDispatcher("/error.jsp").forward(req,
					resp);
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
