package cn.joymates.erp.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import cn.joymates.erp.domain.User;

/**
 * 用户权限检查filter
 * 
 * @author Jackie Hou
 *
 */
public class UserRightCheckerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String reqUri = request.getRequestURI();
		String prjPath = request.getContextPath();
		
		reqUri = StringUtils.substring(reqUri, 
				(reqUri.indexOf(prjPath) + prjPath.length() + 1));
		
		HttpSession sess = request.getSession();
		User user = (User)sess.getAttribute("loggedUser");
//		Set<String> urlSet = user.getUrlSet();
		Set<String> urlSet = null;
		
		if (urlSet.contains(reqUri)) {
			chain.doFilter(req, resp);
		} else {
			req.getRequestDispatcher("/error1.jsp").forward(req,
					resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
