package com.televital.fptelemedicine.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.ModelAndView;

public class ViewsController extends MultiActionController implements
		InitializingBean {

	//private static final Logger log = Logger.getLogger(ViewsController.class);
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public ModelAndView unauthorizedAccessHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("UnauthorizedView"); 
	}

	public ModelAndView AdminMainHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("AdminMainView"); 
	}

	public ModelAndView DoctorManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("DoctorManagementView"); 
	}

	public ModelAndView LabManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("LabManagementView"); 
	}
	
	public ModelAndView FamilyPhysicianHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("FamilyPhysicianView"); 
	}
	
	public ModelAndView FamilyPhysicianManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("FamilyPhysicianManagementView"); 
	}
	
	public ModelAndView SpecalistManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("SpecalistManagementView"); 
	}
		
	public ModelAndView LabServicesManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("LabServicesManagementView"); 
	}
	
	public ModelAndView PatientListLabManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("PatientListLabManagementView"); 
	}
	
	public ModelAndView DoctorViewManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("DoctorViewManagementView"); 
	}
	
	public ModelAndView SpecalistFavHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("SpecalistFavView"); 
	}
	
	public ModelAndView LabFavHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("LabFavView"); 
	}
	
	public ModelAndView MyNetworkHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("MyNetworkView"); 
	}
	
	public ModelAndView MyLabNetworkHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("MyNetworkLabView"); 
	}
	
	public ModelAndView JoinNetworkHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("JoinNetworkView"); 
	}
	
	public ModelAndView JoinLabHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("JoinNetworkLabView"); 
	}
	public ModelAndView RandomValueHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("RandomValueView"); 
	}
	
	
	public ModelAndView ChangeDoctorManegementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ChangeDoctorProfileData"); 
	}
	 
	public ModelAndView ChangeLabManagementHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ChangeLabProfile"); 
	}
	
	public ModelAndView ViewDocMainPageHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("DoctorViewManagementView"); 
	}
	
	public ModelAndView ViewLabMainPageHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("LabViewManagementView"); 
	}
	
	public ModelAndView ViewHelpPageHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewHelpPageView"); 
	}
	
	public ModelAndView ViewTermPageHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewTermPageView"); 
	}
	
	public ModelAndView ViewPrivacyPageHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewPrivacyPageView"); 
	}
		
	public ModelAndView ViewHelpIndexAHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewHelpIndexAView"); 
	}
	
	public ModelAndView ViewContentPageAHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewContentPageAView"); 
	}
	
	public ModelAndView ViewFPStatisticsHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewFPStatisticsView"); 
	}
	
	public ModelAndView ViewSepStatisticsHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewSepStatisticsView"); 
	}
	public ModelAndView ViewLabStatisticsHandler(HttpServletRequest request,HttpServletResponse reponse)
	{ 
		return new ModelAndView("ViewLabStatisticsView"); 
	}
	
}
