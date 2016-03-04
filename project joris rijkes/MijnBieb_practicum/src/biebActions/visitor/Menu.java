package biebActions.visitor;

import java.util.Map;

import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Menu extends ActionSupport implements ApplicationAware{


	private ApplicationMap application;
	
	
	public String execute() {
		Integer aantal = (Integer)application.get("aantal");
		if(aantal==null){
			aantal = 0;
		}
		aantal++;
		application.put("aantal",aantal);
		
		return ActionSupport.SUCCESS;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public void setApplication(Map application) {
		this.application = (ApplicationMap) application;
		}


}
