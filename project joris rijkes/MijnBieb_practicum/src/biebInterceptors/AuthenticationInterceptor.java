package biebInterceptors;

import java.util.Map;

import biebAware.UserAware;
import biebDomain.User;
import biebDomain.UserRole;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class AuthenticationInterceptor implements Interceptor {

	public void destroy() {
	}

	public void init() {
	}

	public String intercept( ActionInvocation actionInvocation ) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = actionInvocation.getInvocationContext().getSession();
		
		User user = (User) session.get("user" );
		
		if (user == null) {
		    return Action.LOGIN;
		} 
		else {
				
		    Action action = ( Action ) actionInvocation.getAction();
		    
		    if (action instanceof UserAware) {
		        ((UserAware)action).setUser(user);
		    }
		    
			if (user.getUr() != UserRole.MEMBER) {
				return Action.LOGIN;
			} else {
				return actionInvocation.invoke();
			}
		}
	}
}
