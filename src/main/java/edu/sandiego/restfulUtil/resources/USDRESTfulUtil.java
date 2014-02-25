package edu.sandiego.restfulUtil.resources;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import edu.sandiego.restfulUtil.beans.*;
import edu.sandiego.restfulUtil.business.UserBusinessObject;
import edu.sandiego.restfulUtil.enumeration.MessageEnum;
import edu.sandiego.restfulUtil.exceptions.InvalidInputException;
import edu.sandiego.restfulUtil.hibernate.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.ArrayList;
import java.util.List;

@Path("/USDRESTfulUtil")
public class USDRESTfulUtil extends ServletContainer {
    public static Log log = LogFactory.getLog(USDRESTfulUtil.class);
	
	@GET
	@Produces("text/plain")
	public String getHello(){
		return "Welcome to Sandiego RESTful Utilities project!!";
	}
	
    @POST
	@Path("/checkHolds")
	@Produces("text/plain")
	public String checkHolds(@FormParam("pidm") String pidm) throws InvalidInputException {
		int pidmInt;
		if(pidm.getClass()!="String".getClass()) {
			throw new InvalidInputException("Invalid Pidm Type");
		}
		try {
            pidmInt = Integer.parseInt(pidm);
		}
		catch(Throwable e) {
			throw new InvalidInputException("invalid pidm");
		}
        User user = UserBusinessObject.checkHold(pidmInt);
		if(user.status==MessageEnum.ERROR)
			return "error occured while processing your request";
		if(user.status==MessageEnum.NOTFOUND)
		    return "ID not found in Db";
        return user.getUserAccountHold();
    }
	
	@POST
	@Path("/checkTranscriptHold")
	@Produces("text/plain")
	public String getCheckTranscriptHold(@FormParam("pidm") String pidm) throws InvalidInputException {
		if(pidm.getClass()!="String".getClass()){
			throw new InvalidInputException("Invalid Pidm Type");
		}
		String isTranscriptHold = UserBusinessObject.checkTranscriptHold(pidm.trim());
        return isTranscriptHold;
    }

	@POST
	@Path("/callBannerProcedure")
	@Produces("text/plain")
	public String callBannerProcedure(@FormParam("oracleCall") String oracleCall,@FormParam("pidm") String pidm) throws InvalidInputException {
        int pidmInt;
		if(pidm.getClass()!="String".getClass()) {
			throw new InvalidInputException("Invalid Pidm Type");
		}
		try {
            pidmInt = Integer.parseInt(pidm);
        }
        catch(Throwable e) {
            throw new InvalidInputException("invalid pidm");
        }
		String channelMarkupOutput = UserBusinessObject.callBannerProcedure(pidmInt,oracleCall);
        return channelMarkupOutput;
    }

	@POST
	@Path("/getBannerId")
	@Produces("text/plain")
	public String getBannerId(@FormParam("pidm") String pidm) throws InvalidInputException {
        //System.out.println(context.getServerInfo());
	    System.out.println("pidm------------->>>>>: "+pidm+"<<<<<<<------------------------");
        if(pidm.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid Pidm Type");
        }
        try {
            Integer.parseInt(pidm);
        }
        catch(Throwable e) {
            throw new InvalidInputException("invalid pidm");
        }
        User user = UserBusinessObject.getBannerId(pidm);
        if(user.status==MessageEnum.ERROR)
            return "error occured";
        if(user.status==MessageEnum.NOTFOUND)
            return "ID not found in Db";
        return user.getBannerId();
    }
	
	@POST
	@Path("/getEmailAddress")
	@Produces("text/plain")
	public String getEmailAddress(@FormParam("pidm") String pidm) throws InvalidInputException {
        System.out.println("pidm------------->>>>>: "+pidm+"<<<<<<<------------------------");
        if(pidm.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid Pidm Type");
        }
        String emailAddress = UserBusinessObject.getEmailAddress(pidm);
        return emailAddress;
    }

	@POST
	@Path("/getPidm")
	@Produces("text/plain")
	public String getPidm(@FormParam("id") String id) {
		if(id.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid ID Type");
		}
        User user = UserBusinessObject.getPidm(id);
		if(user.status==MessageEnum.ERROR)
            return "error occured";
		if(user.status==MessageEnum.NOTFOUND)
            return "ID not found in Db";
		return user.getPidm();
	}
	
	@POST
	@Path("/getDepositFlag")
	@Produces("text/plain")
	public String getDepositFlag(@FormParam("id") String id) throws InvalidInputException {
	    if(id.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid ID Type");
		}
		if(id.equals("") || id.equals(null)|| id.equals("null")) {
            throw new InvalidInputException("Invalid ID Type");
		}
        String depositFlag = UserBusinessObject.getDepositFlag(id.trim());
        return depositFlag;
	}
	
	@POST
	@Path("/getLevelResd")
	@Produces("text/plain")
	public String getLevelResd(@FormParam("id") String id,@FormParam("pSource") String pSource) {
        if(id.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid ID Type");
		}
		if(id.equals("") || id.equals(null)|| id.equals("null")) {
			throw new InvalidInputException("Invalid ID Type");
		}
        String level = UserBusinessObject.getLevelRESD(id.trim(),pSource.trim());
        if(level==null || level.equals("null"))
            level="null";
        return level;
    }
	
	@POST
	@Path("/getInsertOnlineDepositChecklistAcknowledgement")
	@Produces("text/plain")
	public String getInsertOnlineDepositChecklistAcknowledgement(@FormParam("pidm") String pidm,@FormParam("checkBox") String checkBox) {
        if(pidm.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid ID Type");
        }
        if(pidm.equals("") || pidm.equals(null)|| pidm.equals("null")) {
            throw new InvalidInputException("Invalid ID Type");
        }
        int int_pidm = Integer.parseInt(pidm);
		String level = UserBusinessObject.getInsertOnlineDepositChecklistAcknowledgement(int_pidm,checkBox.trim());
		if(level==null || level.equals("null"))
            level="null";
        return level;
    }

    //############################################ Parent Portal Services Start ###############################################################
	
	@POST
	@Path("/getParentInfo")
	@Produces("application/json")
	public Response getParentInfo(@FormParam("childPidm") String childPidm) throws InvalidInputException {
        if(childPidm.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid Username Type");
		}
		try {
            Integer.parseInt(childPidm.trim());
        }
        catch(Throwable e) {
            throw new InvalidInputException("Invalid pidm");
        }
        System.out.println("*******************************: "+childPidm);
        ArrayList<ParentInfo> pInf = (ArrayList<ParentInfo>) UserBusinessObject.getParentInfo(childPidm);
		ResponseBuilder builder =Response.ok(pInf);
		return builder.build();
    }
	
	@POST
	@Path("/getChannelWithAidy")
	@Produces("text/plain")
	public String getChannelWithAidy(@FormParam("oracleCall") String oracleCall,@FormParam("aidy")String aidy,@FormParam("pidm") String pidm) {
        String channelMarkup = UserBusinessObject.getChannelWithAidy(oracleCall, aidy,pidm);
		return channelMarkup;
    }
	
	@POST
	@Path("/getConfirmMealPlan")
	@Produces("text/plain")
	public String getConfirmMealPlan(
			@FormParam("oracleCall") String oracleCall,@FormParam("m_ProAmt") String m_ProAmt,@FormParam("m_ProAmt_str") String m_ProAmt_str,
			@FormParam("m_rate") String m_rate,@FormParam("m_new_plan") String m_new_plan,@FormParam("m_plan") String m_plan,
			@FormParam("m_old_plan") String m_old_plan,@FormParam("pidm") String pidm,@FormParam("p_MRemain") String p_MRemain,@FormParam("cbox") String cbox,
			@FormParam("tiv") String tiv,@FormParam("t_type") String t_type,@FormParam("p_term") String p_term,@FormParam("pdd_40") String pdd_40,
			@FormParam("pdd_41") String pdd_41,@FormParam("pdd_45") String pdd_45,@FormParam("pcc") String pcc,@FormParam("pmin_dd") String pmin_dd,
			@FormParam("pmin_cc") String pmin_cc,@FormParam("p_titleiv") String p_titleiv,@FormParam("ov_pidm") String ov_pidm,@FormParam("p_comments") String p_comments) {
        int intPidm;
		int  int_p_MRemain=0;
		int int_ov_pidm = 0;
		if(m_ProAmt_str.equals(""))
            m_ProAmt_str=null;
		if(m_new_plan.equals(""))
			m_new_plan=null;
		if(m_plan.equals(""))
			m_plan=null;
		if(m_old_plan.equals(""))
			m_old_plan=null;
		if(cbox.equals(""))
			cbox=null;
		if(tiv.equals(""))
			tiv=null;
		if(t_type.equals(""))
			t_type=null;
		if(p_term.equals(""))
			p_term=null;
		if(p_titleiv.equals(""))
			p_titleiv=null;
		if(p_comments.equals(""))
			p_comments=null;
		if(p_MRemain.equals(""))
            p_MRemain="0";
		if(ov_pidm.equals(""))
			ov_pidm="0";

        float fm_ProAmt = (m_ProAmt.equals(""))?0:Float.parseFloat(m_ProAmt);
		float fm_rate =(m_rate.equals(""))?0: Float.parseFloat(m_rate);
		float fpdd_40 = (pdd_40.equals(""))?0:Float.parseFloat(pdd_40);
		float fpdd_41 = (pdd_41.equals(""))?0:Float.parseFloat(pdd_41);
		float fpdd_45 =(pdd_45.equals(""))?0:Float.parseFloat(pdd_45);
		float fpcc =(pcc.equals(""))?0: Float.parseFloat(pcc);
		float fpmin_dd = (pmin_dd.equals(""))?0:Float.parseFloat(pmin_dd);
		float fpmin_cc = (pmin_cc.equals(""))?0:Float.parseFloat(pmin_cc);
	    intPidm = Integer.parseInt(pidm);
		int_ov_pidm = Integer.parseInt(ov_pidm);
		int_p_MRemain = Integer.parseInt(p_MRemain);
		
		// "m_ProAmt:m_ProAmt,m_ProAmt_str:m_ProAmt_str,m_rate:m_rate,m_new_plan:m_new_plan,m_plan:m_plan,m_old_plan:m_old_plan,p_MRemain:p_MRemain,";
		//cbox:cbox,tiv:tiv,t_type:t_type,p_term:p_term,pdd_40:pdd_40,pdd_41:pdd_41,pdd_45:pdd_45,pcc:pcc,pmin_dd:pmin_dd,pmin_cc:pmin_cc,
		//p_titleiv:p_titleiv,ov_pidm:ov_pidm,p_comments:p_comments	
		
		ConfirmMealPlan confirmMealPlan = new ConfirmMealPlan();
		confirmMealPlan.setCbox(cbox);
		confirmMealPlan.setM_new_plan(m_new_plan);
		confirmMealPlan.setM_old_plan(m_old_plan);
		confirmMealPlan.setM_plan(m_plan);
		confirmMealPlan.setM_ProAmt(fm_ProAmt);
		confirmMealPlan.setM_ProAmt_str(m_ProAmt_str);
		confirmMealPlan.setM_rate(fm_rate);
		confirmMealPlan.setOv_pidm(int_ov_pidm);
		confirmMealPlan.setP_comments(p_comments);
		confirmMealPlan.setP_MRemain(int_p_MRemain);
		confirmMealPlan.setP_term(p_term);
		confirmMealPlan.setP_titleiv(p_titleiv);
		confirmMealPlan.setPcc(fpcc);
		confirmMealPlan.setPdd_40(fpdd_40);
		confirmMealPlan.setPdd_41(fpdd_41);
		confirmMealPlan.setPdd_45(fpdd_45);
		confirmMealPlan.setPmin_cc(fpmin_cc);
		confirmMealPlan.setPmin_dd(fpmin_dd);
		confirmMealPlan.setT_type(t_type);
		confirmMealPlan.setTiv(tiv);
		confirmMealPlan.setPidm(intPidm);

		String channelMarkup = UserBusinessObject.getConfirmMealPlan(oracleCall, confirmMealPlan);
		return channelMarkup;
	}

    @POST
	@Path("/getSubmitMealPlan")
	@Produces("text/plain")
	public String getSubmitMealPlan(@FormParam("oracleCall") String oracleCall,@FormParam("pidm") String pidm,@FormParam("p_term") String p_term,
			@FormParam("m_plan") String m_plan,@FormParam("p_mremain") String p_mremain,@FormParam("vdd_40") String vdd_40,@FormParam("vdd_41") String vdd_41,
			@FormParam("vdd_45") String vdd_45,@FormParam("vcc") String vcc,@FormParam("rdChoice") String rdChoice,@FormParam("t_type") String t_type,
			@FormParam("ov_pidm") String ov_pidm) {
        int int_p_mremain=0;
		int int_ov_pidm=0;
		if(t_type.equals(""))
			t_type=null;
		if(rdChoice.equals(""))
			rdChoice=null;
		if(p_mremain.equals(""))
			p_mremain="0";
        int intPidm = Integer.parseInt(pidm);
		if(ov_pidm.equals("")){
			int_ov_pidm=0;
		}
		else
			int_ov_pidm = Integer.parseInt(ov_pidm);
		if(p_term.equals(""))
			p_term=null;

        float fVcc = Float.parseFloat(vcc);
		float fVdd_40 = Float.parseFloat(vdd_40);
		float fVdd_41 = Float.parseFloat(vdd_41);
		float fVdd_45 = Float.parseFloat(vdd_45);
		int_p_mremain = Integer.parseInt(p_mremain);

        SubmitMealPlan submitMealPlan = new SubmitMealPlan();
		submitMealPlan.setVcc(fVcc);
		submitMealPlan.setVdd_40(fVdd_40);
		submitMealPlan.setVdd_41(fVdd_41);
		submitMealPlan.setVdd_45(fVdd_45);
		submitMealPlan.setPidm(intPidm);
		submitMealPlan.setOv_pidm(int_ov_pidm);
		submitMealPlan.setT_type(t_type);
		submitMealPlan.setP_mremain(int_p_mremain);
		submitMealPlan.setRdChoice(rdChoice);
		submitMealPlan.setP_term(p_term);
		submitMealPlan.setM_plan(m_plan);

        String channelMarkup = UserBusinessObject.getSubmitMealPlan(oracleCall, submitMealPlan);
		return channelMarkup;
    }
	
	@POST
	@Path("/getOverrideStartPage")
	@Produces("text/plain")
	public String overrideStartPage(@FormParam("p_pidm") String p_pidm,@FormParam("pid") String pid,@FormParam("ov_pidm") String ov_pidm) {
        if(pid.trim().equals(""))
            pid=null;
		if(ov_pidm.equals(""))
			ov_pidm="0";
        int int_p_pidm = Integer.parseInt(p_pidm);
		int int_ov_pidm = Integer.parseInt(ov_pidm);
		String channelMarkup = UserBusinessObject.overrideStartPage(int_p_pidm,pid,int_ov_pidm);
		return channelMarkup;
	}
	
    @POST
	@Path("/getMealPlanOverride")
	@Produces("text/plain")
	public String getMealPlanOverride(@FormParam("userPidm") String userPidm) {
        int pidm = Integer.parseInt(userPidm);
		String channelMarkup = UserBusinessObject.getOverRideStartUp(pidm);
        return channelMarkup;
    }//mealPlanOverride
	
	@POST
	@Path("/getChangeMealPlan")
	@Produces("text/plain")
	public String getChangeMealPlan(@FormParam("oracleCall") String oracleCall,@FormParam("pidm") String pidm,@FormParam("p_term") String p_term,
			@FormParam("pmeal_code") String pmeal_code,@FormParam("pmeal_remain") String pmeal_remain,@FormParam("vdd_40") String vdd_40,
			@FormParam("vdd_41") String vdd_41,@FormParam("vdd_45") String vdd_45,@FormParam("vcc") String vcc,@FormParam("vmeal_future") String vmeal_future,
			@FormParam("m_cs_ind") String m_cs_ind,@FormParam("ov_pidm") String ov_pidm) {
        int int_pmeal_remain;
		int int_ov_pidm=0;
		if(m_cs_ind.equals(""))
			m_cs_ind=null;
        if(vmeal_future.equals(""))
			vmeal_future=null;
		if(pmeal_remain.equals(""))
			pmeal_remain="0";
		int intPidm = Integer.parseInt(pidm);
		if(ov_pidm.equals("")){
			int_ov_pidm=0;
		}
		else
			int_ov_pidm = Integer.parseInt(ov_pidm);
		if(pmeal_code.equals(""))
			pmeal_code=null;
		if(p_term.equals(""))
			p_term=null;

		float fVcc = Float.parseFloat(vcc);
		float fVdd_40 = Float.parseFloat(vdd_40);
		float fVdd_41 = Float.parseFloat(vdd_41);
		float fVdd_45 = Float.parseFloat(vdd_45);
		int_pmeal_remain = Integer.parseInt(pmeal_remain);
		
		ChangeMealPlan changeMealPlan = new ChangeMealPlan();
		changeMealPlan.setM_cs_ind(m_cs_ind);
		changeMealPlan.setOv_pidm(int_ov_pidm);
		changeMealPlan.setPmeal_code(pmeal_code);
		changeMealPlan.setP_term(p_term);
		changeMealPlan.setPidm(intPidm);
		changeMealPlan.setVcc(fVcc);
		changeMealPlan.setVdd_40(fVdd_40);
		changeMealPlan.setVdd_41(fVdd_41);
		changeMealPlan.setVdd_45(fVdd_45);
		changeMealPlan.setVmeal_future(vmeal_future);
		changeMealPlan.setPmeal_remain(int_pmeal_remain);
		
		String channelMarkup = UserBusinessObject.getChangeMealPlan(oracleCall, changeMealPlan);
		return channelMarkup;
    }
	
	@POST
	@Path("/getChannelWithoutAidy")
	@Produces("text/plain")
	public String getChannelWithoutAidy(@FormParam("oracleCall") String oracleCall,@FormParam("pidm") String pidm) {
		String channelMarkup = UserBusinessObject.getChannelWithoutAidy(oracleCall,pidm);
		return channelMarkup;
	}
	
    @POST
	@Path("/getChildInfo")
	@Produces("application/json")
	public Response getChildInfo(@FormParam("parentPidm") String parentPidm) {
		List<ChildInfo> childList = UserBusinessObject.getChildInfo(parentPidm);
		ResponseBuilder builder = Response.ok(childList);
		return builder.build();
	}

	@POST
	@Path("/getParentPortalCookie")
	@Produces("text/plain")
	public String getParentPortalCookie(@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
	    String encStr = UserBusinessObject.getParentPortalCookie(childPidm, parentPidm);
	    return encStr;
    } //checkIfParentIsLinkedToAnotherStudent
	
	@POST
	@Path("/getCheckIfParentIsLinkedToAnotherStudent")
	@Produces("text/plain")
	public String getCheckIfParentIsLinkedToAnotherStudent(@FormParam("childPidm") String childPidm, @FormParam("parentEmail") String parentEmail) {
        return UserBusinessObject.checkIfParentIsLinkedToAnotherStudent(childPidm, parentEmail);
    }
	
	@POST
	@Path("/getCheckDuplicateEmailAddress")
	@Produces("text/plain")
	public String getCheckDuplicateEmailAddress(@FormParam("parentEmail") String parentEmail) {
        return UserBusinessObject.checkDuplicateEmailAddress(parentEmail);
    }//checkDuplicateEmailAddress

	@POST
	@Path("/getCheckForDuplicateParent")
	@Produces("text/plain")
	public String getCheckForDuplicateParent(@FormParam("childPidm") String childPidm, @FormParam("parentEmail") String parentEmail) {
        return UserBusinessObject.checkForDuplicateParent(childPidm, parentEmail);
    }
	
	@POST
	@Path("/getCreateNewParent")
	@Produces("text/plain")
	public String getCreateNewParent(@FormParam("childPidm") String childPidm,@FormParam("parentFirstName")String parentFirstName,@FormParam("parentLastName")String parentLastName,
			@FormParam("parentMiddleName") String parentMiddleName,@FormParam("relation")String relation,@FormParam("parentEmail")String parentEmail) {
        ParentInfo pInfo = new ParentInfo();
		pInfo.setChildPidm(childPidm.trim());
		pInfo.setMi(parentMiddleName.trim());
		pInfo.setParent_first_name(parentFirstName.trim());
		pInfo.setParent_last_name(parentLastName.trim());
		pInfo.setRelation_code(relation);
		pInfo.setEmail_address(parentEmail.trim());
		int parentPidm = UserBusinessObject.createNewParent(pInfo);
		String str = String.valueOf(parentPidm);
		return str;
	}

	@POST
	@Path("/getParentInfoByEmailAddress")
	@Produces("application/json")
	public Response getParentInfoByEmailAddress(@FormParam("emailAddress") String emailAddress) throws InvalidInputException {
        if(emailAddress.getClass()!="String".getClass()) {
            throw new InvalidInputException("Invalid Username Type");
		}
        System.out.println("*******************************: "+emailAddress);
		ArrayList<ParentInfo> pInf =(ArrayList<ParentInfo>) UserBusinessObject.getParentInfoByEmailAddress(emailAddress.trim());
		ResponseBuilder builder =Response.ok(pInf.get(0));
		return builder.build();
    }
	
	@POST
	@Path("/getLinkChildToAlreadyExistingParent")
	@Produces("text/plain")
	public String linkChildToAlreadyExistingParent(@FormParam("childPidm") String childPidm,@FormParam("parentEmail") String parentEmail) {
		//System.out.println("in service: "+childPidm);
		String outPut = UserBusinessObject.linkChildToAlreadyExistingParent(childPidm, parentEmail);
		return outPut;
	}

	@POST
	@Path("/getCheckForDuplicateEmailAddress")
	@Produces("text/plain")
	public String getCheckForDuplicateEmailAddress(@FormParam("parentEmail") String parentEmail) {
        return "";
    }

    @POST
	@Path("getParentAuthNumber")
	@Produces("text/plain")
	public String getParentAuthNumber(@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
        return UserBusinessObject.getParentAuthNumber(childPidm, parentPidm);
    }
	
	@POST
	@Path("getParentAuthNumberForParentView")
	@Produces("text/plain")
	public String getParentAuthNumberFromParentView(@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
        String authNo = UserBusinessObject.getParentAuthNumberForParentView(childPidm,parentPidm);
		return authNo;
    }
	
	@POST
	@Path("addNewParentRecord")
	@Produces("text/plain")
	public String addNewParentRecord(@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
        SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		return "";
    }
	
	@POST
	@Path("/linkExistingParentRecordToThisChild")
	@Produces("text/plain")
	public String linkExistingParentRecordToThisChild(@FormParam("childPidm") String childPidm, @FormParam("parentEmail") String parentEmail) {
		SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		return "";
    }
	
    @POST
	@Path("/checkForExistingLinkToParent")
	@Produces("text/plain")
	public Boolean isParentAlreadyLinkedToAnotherChild(@FormParam("childPidm") String childPidm, @FormParam("parentEmail") String parentEmail) {
        Boolean isDuplicate = Boolean.valueOf(false);
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
            Session session = sessionFactory.openSession();
            String value = session.getNamedQuery("checkForDuplicateParent").setParameter("childPidm",childPidm.trim()).setParameter("parentEmail", parentEmail.toUpperCase().trim()).uniqueResult().toString();
            session.close();
            if(Integer.parseInt(value)>0)
                isDuplicate =  Boolean.valueOf(true);
        }
        catch(Exception e) {
            System.out.println("exception!!--->>>: "+e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
        return isDuplicate;
    }
	
	@POST
	@Path("/checkForExistingLuminisEmail")
	@Produces("text/plain")
	public String isParentEmailUsedByLuminisAlready(@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
		SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		return "";
    }
	
	@POST
	@Path("/getUpdateParentAuthNumber")
	@Produces("text/plain")
	public String getUpdateParentAuthNumber(@FormParam("parentAuthNumber")String parentAuthNumber,@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
        return UserBusinessObject.updateParentAuthNumberForPermissions(parentAuthNumber, childPidm, parentPidm);
    }

	@POST
	@Path("/getChildPidmFromParentCookie")
	@Produces("text/plain")
	public String getChildPidmFromParentCookie(@FormParam("childPidm") String childPidm, @FormParam("parentPidm") String parentPidm) {
		SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		return "";
    }
	
    @POST
	@Path("/getParentPinAndUsername")
	@Produces("application/json")
	public Response getParentPinAndUsername(@FormParam("parentPidm") String parentPidm) {
        ParentPinUN parentInfo = new ParentPinUN();
		parentInfo = UserBusinessObject.getParentPinAndUsername(parentPidm);
		ResponseBuilder builder = Response.ok(parentInfo);
		return builder.build();
    }
    //############################################ Parent Portal Services Stop ###############################################################
	
	@POST
	@Path("/getChannelHtml")
	@Produces("text/plain")
	public String getChannelHtml(@FormParam("pidm") String pidm,@FormParam("pidm") String pkg_func) {
        try {
            String channelHtml="";
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
            Session session = sessionFactory.openSession();
            channelHtml = session.getNamedQuery("get_banner_channel_html").setParameter("pidm",pidm.trim()).uniqueResult().toString();
            session.close();
            return channelHtml;
        }
		catch(Exception e) {
			System.out.println("exception!!--->>>: "+e.getMessage());
			return "getChannelHtml--> "+e.toString();
		}
    }
	
	@POST
	@Path("/getRoles")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoles(@FormParam("bannerId") String bannerId) {
        List<String> roles = new ArrayList<String>();
	    JSONObject jObjO = new JSONObject();
	    JSONArray jArray = new JSONArray();
	    JSONObject jObj;
		
		SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		roles = session.getNamedQuery("getRoles").setParameter("bannerId",bannerId.trim()).list(); 
		session.close();
		
		for(int i=0;i<roles.size();i++) {
			jArray.add(i, roles.get(i));
			System.out.println(roles.get(i));
		}
		System.out.println(roles);
		jObjO.put("roles", jArray);
		System.out.println(jObjO);

		return jObjO.toString();
    }
	
	@POST
	@Path("/webAccessEnabling")
	@Produces("application/json")
	public String webAccessEnabling(@FormParam("indicator") String indicator,@FormParam("username") String username) {
        String response="";
		JSONObject jO = new JSONObject();
		System.out.println("Indicator: "+indicator+"-------"+"username: "+username);
		SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String updateQuery = "update gobtpac set gobtpac_pin_disabled_ind=:indicator where gobtpac_external_user= :username";
		SQLQuery query = session.createSQLQuery(updateQuery);
		query.setString("indicator",indicator.trim());
		query.setString("username",username.trim());
		int rowsAffected = query.executeUpdate();
		System.out.println("------------------------------------------: "+rowsAffected);
		jO.put("rows",rowsAffected);
		tx.commit();
		session.close();
		return jO.toString();
	}

    @POST
    @Path("/getTerms")
    @Produces("text/plain")
    public String getTerms(@FormParam("pidm") String pidm) throws InvalidInputException {
        try {
            return UserBusinessObject.getTerms(pidm).toString();
        }
        catch(Throwable e) {
            throw new InvalidInputException("invalid pidm");
        }
    }

    @POST
    @Path("/getGPAForTerm")
    @Produces("text/plain")
    public String getGPAForTerm(@FormParam("pidm") String pidm, @FormParam("term") String term) throws InvalidInputException {
        try {
            return UserBusinessObject.getGPAForTerm(pidm, term).toString();
        }
        catch(Throwable e) {
            throw new InvalidInputException("invalid input");
        }
    }

    @POST
    @Path("/getTotalEarnedCredits")
    @Produces("text/plain")
    public String getTotalEarnedCredits(@FormParam("pidm") String pidm) throws InvalidInputException {
        try {
            return UserBusinessObject.getTotalEarnedCredits(pidm).toString();
        }
        catch(Throwable e) {
            throw new InvalidInputException("invalid pidm");
        }
    }

}
