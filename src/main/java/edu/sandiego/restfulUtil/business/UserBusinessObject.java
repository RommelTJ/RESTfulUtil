package edu.sandiego.restfulUtil.business;

import edu.sandiego.restfulUtil.beans.*;
import edu.sandiego.restfulUtil.enumeration.MessageEnum;
import edu.sandiego.restfulUtil.hibernate.HibernateUtil;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBusinessObject {
    private static Log log = LogFactory.getLog(UserBusinessObject.class);
	private  UserBusinessObject(){}

    public static String checkForDuplicateParent(String childPidm, String parentEmail) {
	    String isDuplicate = "no";
		int pidmForChild = Integer.parseInt(childPidm.trim());
		try {
		    SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		    Session session = sessionFactory.openSession();
		    List<Integer> pidm = session.getNamedQuery("checkForDuplicateParent").setParameter("parentEmailAddress",parentEmail.trim()).setParameter("childPidm",pidmForChild).list();
		    System.out.println("isDuplicate Count:----->: "+pidm.size());
		    session.close();
		    if(pidm.size()>0)
                isDuplicate =  "yes";
		}
		catch(Exception e) {
			System.out.println("exception!!--->>>: "+e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return isDuplicate;
	}
	
    public static String checkDuplicateEmailAddress(String parentEmail) {
	    String isDuplicate = "no";
		try {
		    SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		    Session session = sessionFactory.openSession();
		    List<Integer> parentPidm = session.getNamedQuery("checkDuplicateEmailAddress").setParameter("parentEmailAddress",parentEmail.trim()).list();
		    System.out.println("isDuplicate Count:----->: "+parentPidm.size());
		    session.close();
		    if(parentPidm.size()>0)
			    isDuplicate =  "yes";
		}
		catch(Exception e) {
			System.out.println("exception!!--->>>: "+e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return isDuplicate;
    }

    public static String getParentAuthNumber(String childPidm, String parentPidm) {
	    String parentAuthNumber="";
	    try {
	        SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
	        Session session = sessionFactory.openSession();
            parentAuthNumber = session.getNamedQuery("getParentAuthNumber").setParameter("parentPidm",parentPidm.trim()).setParameter("childPidm",childPidm.trim()).uniqueResult().toString();
	        session.close();
        }
        catch(Exception e) {
		    System.out.println("exception!!--->>>: "+e.getMessage());
		    log.error(e.getMessage());
		    e.printStackTrace();
        }
        return parentAuthNumber;
     }

    public static ParentPinUN getParentPinAndUsername(String parentPidm) {
        ParentPinUN parentPinUn = new ParentPinUN();
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
	        Session session = sessionFactory.openSession();
	        parentPinUn = (ParentPinUN)session.getNamedQuery("get_parent_ID_info").setParameter("parentPidm",parentPidm.trim()).uniqueResult();
	        session.close();
	    }
        catch(Exception e) {
		    System.out.println("exception!!--->>>: "+e.getMessage());
		    log.error(e.getMessage());
		    e.printStackTrace();
	    }
	    return parentPinUn;
    }//checkDuplicateEmailAddress
	
    public static String checkIfParentIsLinkedToAnotherStudent(String childPidm, String parentEmail) {
        String isParentAlreadyLinkedToAnotherStudent = "no";
		int pidmForChild = Integer.parseInt(childPidm.trim());
		try {
		    SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		    Session session = sessionFactory.openSession();
		    List<Integer> pidm = session.getNamedQuery("checkIfParentIsLinkedToAnotherStudent").setParameter("parentEmailAddress",parentEmail.trim()).setParameter("childPidm",pidmForChild).list();
		    System.out.println("isDuplicate Count:----->: "+pidm.size());
		    session.close();
		    if(pidm.size()>0)
			    isParentAlreadyLinkedToAnotherStudent =  "yes";
		}
		catch(Exception e) {
			System.out.println("exception!!--->>>: "+e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return isParentAlreadyLinkedToAnotherStudent;
	}
	
    //*****************************************   getBannerId   Start  *************************************************
    public static User getBannerId(String pidm) {
	    String bannerID="";
		User user = new User();
		try{
            //System.out.println(context.getServerInfo());
			//System.out.println("pidm------------->>>>>: "+pidm+"<<<<<<<------------------------");
			SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getBannerID").setParameter("pidm",pidm.trim());
			bannerID = (String) query.uniqueResult().toString();
			if(bannerID==null) {
			    user.setStatus(MessageEnum.NOTFOUND);
				user.setErrorDescription("Pidm Does not exist in Database.");
				return user;
			}
			user.setBannerId(bannerID);
			user.setStatus(MessageEnum.OK);
			session.close();
        }
	    catch(JDBCException jE){
            user.setStatus(MessageEnum.ERROR);
			user.setErrorDescription(jE.getMessage());
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			user.setStatus(MessageEnum.ERROR);
			user.setErrorDescription(e.getMessage());
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return user;
	}
	
	// ---*****-----checkTranscriptHold Starts
	public static String checkTranscriptHold(String pidm){
        String isHold="";
		Transaction tx =null;
		try{
		    SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{? = call f_get_transcript_hold(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, pidm);
            cs.execute();
            isHold = cs.getString(1);
            tx.commit();
			session.close();
        }
	    catch(JDBCException jE){
		    log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return isHold;
    } // -------*****------checkTranscriptHold Ends
	
	//-----------getEmailAddress starts
	public static String  getEmailAddress(String pidm) {
		String emailAddress="";
		Transaction tx =null;
		try{
			SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{? = call  BANINST1.USD_BANNER_COMMON.get_banner_email(?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, pidm);
            cs.setString(3, "USD");
            cs.execute();
            emailAddress = cs.getString(1);
            tx.commit();
			session.close();
		}
		catch(JDBCException jE){
		    log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return emailAddress;
	} //-------getEmailAddress ends
	//*****************************************  getBannerId    Stop  *************************************************

	//*****************************************   getPidm   Start  *************************************************
	public static User getPidm(String id) {
        String pidm="";
		User user = new User();
		try{
		    //System.out.println(context.getServerInfo());
			//System.out.println("pidm------------->>>>>: "+pidm+"<<<<<<<------------------------");
			SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getPidm_SpridenID").setParameter("id",id.trim());
			pidm = query.uniqueResult().toString();
			if(pidm==null) {
			    user.setStatus(MessageEnum.NOTFOUND);
				user.setErrorDescription("Usernae Does not exist in Database.");
				return user;
			}
			user.setPidm(pidm);
			user.setStatus(MessageEnum.OK);
			session.close();
        }
	    catch(JDBCException jE) {
            user.setStatus(MessageEnum.ERROR);
			user.setErrorDescription(jE.getMessage());
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			user.setStatus(MessageEnum.ERROR);
			user.setErrorDescription(e.getMessage());
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return user;
    }
	//*****************************************    getPidm  Stop  *************************************************

	//*****************************************    getChildInfo  Start  *************************************************
	public static List<ChildInfo> getChildInfo(String parentPidm) {
		SessionFactory sessionFactory;
		Session session=null;
		try{
            sessionFactory = HibernateUtil.getBasicSessionFactory();
		    session = sessionFactory.openSession();
		    Query query = session.getNamedQuery("getChildInfo").setParameter("parentPidm", parentPidm);
            return query.list();
        }
		catch(Exception e) {
            e.printStackTrace();
			log.error(e.getMessage());
			log.error(e.toString());
			return null;
		}
	}
	//*****************************************    getChildInfo  Stop  *************************************************

	//*****************************************    getLevelRESD  Start  *************************************************
	public static String getLevelRESD(String pid,String pSource) {
        String level="";
		Transaction tx =null;
		try{
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{? = call  usd_luminis_common.f_get_levl_resd(?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, pid);
            cs.setString(3, pSource);
            cs.execute();
            level = cs.getString(1);
            System.out.println("level---------->: "+level);
            tx.commit();
			session.close();
        }
		catch(JDBCException jE) {
            log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return level;
	}
	//*****************************************    getLevelRESD  Stop  *************************************************
	
	//*****************************************    getInsertOnlineDepositChecklistAcknowledgement  Start  *************************************************
	public static String getInsertOnlineDepositChecklistAcknowledgement(int pidm,String checkBox) {
        String output="failure";
		Transaction tx =null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{call  usd_online_deposit_pay.p_insert_checklist(?,?)}");
            //cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(1, pidm);
            cs.setString(2, checkBox);
            cs.execute();
            output="success";
            tx.commit();
			session.close();
        }
	    catch(JDBCException jE) {
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return output;
	}
	//*****************************************    getInsertOnlineDepositChecklistAcknowledgement  Stop  *************************************************
	
    //*****************************************    getDepositFlag  Start  *************************************************
	public static String getDepositFlag(String pid) {
	    String depositFlag="";
        Transaction tx =null;
		try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
	        CallableStatement cs = con.prepareCall("{? = call  usd_luminis_common.f_get_deposit_flag(?)}");
	        cs.registerOutParameter(1, Types.VARCHAR);
	        cs.setString(2, pid);
	        cs.execute();
	        depositFlag = cs.getString(1);
	        tx.commit();
			session.close();
        }
		catch(JDBCException jE) {
            log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return depositFlag;
    }
    //*****************************************    getDepositFlag  Stop  *************************************************
	
	//*****************************************    checkHold  Start  *************************************************
	public static User checkHold(int pidm) {
		User user = new User();
		String checkHoldMessage;
		Transaction tx =null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{? = call usd_channel_student_holds.f_check_hold(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(2, pidm);
            cs.execute();
            checkHoldMessage = cs.getString(1);
            tx.commit();
			if(checkHoldMessage==null) {
				user.setStatus(MessageEnum.NOTFOUND);
				user.setErrorDescription("Username Does not exist in Database.");
				return user;
			}
			user.setUserAccountHold(checkHoldMessage);
			user.setStatus(MessageEnum.OK);
			session.close();
		}
		catch(JDBCException jE) {
			user.setStatus(MessageEnum.ERROR);
			user.setErrorDescription(jE.getMessage());
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			user.setStatus(MessageEnum.ERROR);
			user.setErrorDescription(e.getMessage());
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return user;
	}
	//*****************************************    checkHold  Stop  *************************************************

	// callBannerProcedure starts
	public static String callBannerProcedure(int pidm, String oracleCall){
        String channelMarkupOutput="";
		Transaction tx =null;
	    try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall(oracleCall);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.setInt(1, pidm);
            cs.execute();
            channelMarkupOutput = cs.getString("emer_contact_area");
            tx.commit();
			session.close();
        }
	    catch(JDBCException jE) {
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return channelMarkupOutput;
	} //callBannerProcedure ends
	
	//***getchannelMarkupWithAidy start
	public static String getChannelWithAidy(String oracleCall, String aidy_in,String pidm) {
        String channelMarkupOutput="";
		Transaction tx =null;
	    try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall(oracleCall);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, pidm);
            cs.setString(3,aidy_in);
            cs.execute();
            channelMarkupOutput = cs.getString(1);
            tx.commit();
			session.close();
		}
		catch(JDBCException jE) {
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
		return channelMarkupOutput;
	} //****getChannelMarkupWithAidy stop
	
    //***getSubmitMealPlan start
	public static String getSubmitMealPlan(String oracleCall, SubmitMealPlan submitMealPlan) {
        String channelMarkupOutput="";
		Transaction tx =null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			System.out.println("**: "+submitMealPlan.getPidm());
			System.out.println("**: "+submitMealPlan.getP_term());
            tx = session.beginTransaction();
			Connection con = session.connection();
			oracleCall=" begin ?:=baninst1.usd_channel_meal_plan_new.f_get_save (?,?,?,?,?,?,?,?,?,?,?);end;";
	        CallableStatement cs = con.prepareCall(oracleCall);
	        cs.registerOutParameter(1, Types.VARCHAR);
	        cs.setInt(2, submitMealPlan.getPidm());
            cs.setObject(3,submitMealPlan.getP_term());
	        cs.setObject(4,submitMealPlan.getM_plan());
	        cs.setInt(5,submitMealPlan.getP_mremain());
	        cs.setFloat(6,submitMealPlan.getVdd_40());
	        cs.setFloat(7,submitMealPlan.getVdd_41());
	        cs.setFloat(8,submitMealPlan.getVdd_45());
	        cs.setFloat(9,submitMealPlan.getVcc());
	        cs.setObject(10,submitMealPlan.getRdChoice());
	        cs.setObject(11,submitMealPlan.getT_type());
	        if(submitMealPlan.getOv_pidm()==0)
                cs.setObject(12,null);
            else
	            cs.setInt(12, submitMealPlan.getOv_pidm());
            cs.execute();
	        channelMarkupOutput = (String)cs.getObject(1);
	        System.out.println(channelMarkupOutput);
	        tx.commit();
			session.close();
		}
		catch(JDBCException jE) {
            System.out.println("JDBC error:--------->");
			System.out.println(jE.getMessage());
			System.out.println(jE.getStackTrace());
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
        }
        catch(Exception e) {
            System.out.println(" error:--------->");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            log.error(e.getMessage());
            log.error(e.toString());
            e.printStackTrace();
        }
        return channelMarkupOutput;
    } //****getsubmitMealPlan stop
		
	public static String overrideStartPage(int p_pidm, String pid, int ov_pidm) {
        String channelMarkupOutput="";
		Transaction tx =null;
		CallableStatement cs=null;
		try {
		    SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
			if(pid==null) {
			    cs = con.prepareCall(" {? = call baninst1.usd_channel_meal_plan_new.f_get_start_page(?)}");
		        cs.registerOutParameter(1, Types.VARCHAR);
		        cs.setInt(2, p_pidm);
			}
			else {
                cs = con.prepareCall(" {? = call baninst1.usd_channel_meal_plan_new.f_get_start_page(?,?,?)}");
	            cs.registerOutParameter(1, Types.VARCHAR);
	            cs.setInt(2, p_pidm);
	            cs.setObject(3, pid);
	            if(ov_pidm==0)
                    cs.setObject(4,null);
	            else
	            	cs.setInt(4, ov_pidm);
			}
            cs.execute();
            channelMarkupOutput = cs.getString(1);
            tx.commit();
            session.close();
        }
        catch(JDBCException jE) {
            log.error(jE.getMessage());
            log.error(jE.toString());
            jE.printStackTrace();
        }
        catch(Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
            e.printStackTrace();
        }
        return channelMarkupOutput;
    }
		
    //getOverRideStartUp Start
	public static String getOverRideStartUp(int pidm) {
        String channelMarkupOutput="";
		Transaction tx =null;
	    try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall(" {? = call baninst1.usd_channel_meal_plan_new.f_get_override (?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(2, pidm);
            cs.execute();
            channelMarkupOutput = cs.getString(1);
            tx.commit();
			session.close();
        }
        catch(JDBCException jE) {
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return channelMarkupOutput;
    } //getOverRideStartUp ends
	
    //***getChangeMealPlan start
	public static String getChangeMealPlan(String oracleCall, ChangeMealPlan changeMealPlan) {
        String channelMarkupOutput="";
		Transaction tx =null;
		try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
            //to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			System.out.println("**: "+changeMealPlan.getPidm());
			System.out.println("**: "+changeMealPlan.getP_term());
			System.out.println("**: "+changeMealPlan.getPmeal_code());
			System.out.println("**: "+changeMealPlan.getM_cs_ind());
			tx = session.beginTransaction();
			Connection con = session.connection();
			oracleCall=" begin ?:=baninst1.usd_channel_meal_plan_new.f_get_display (?,?,?,?,?,?,?,?,?,?,?);end;";
            CallableStatement cs = con.prepareCall(oracleCall);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(2, changeMealPlan.getPidm());
            cs.setObject(3,changeMealPlan.getP_term());
            cs.setObject(4,changeMealPlan.getPmeal_code());
            cs.setInt(5,changeMealPlan.getPmeal_remain());
            cs.setFloat(6,changeMealPlan.getVdd_40());
            cs.setFloat(7,changeMealPlan.getVdd_41());
            cs.setFloat(8,changeMealPlan.getVdd_45());
            cs.setFloat(9,changeMealPlan.getVcc());
            cs.setObject(10,changeMealPlan.getVmeal_future());
            cs.setObject(11,changeMealPlan.getM_cs_ind());
            if(changeMealPlan.getOv_pidm()==0)
            	cs.setObject(12,null);
            else
            	cs.setInt(12, changeMealPlan.getOv_pidm());
            cs.execute();
            channelMarkupOutput = (String)cs.getObject(1);
            System.out.println(channelMarkupOutput);
            tx.commit();
			session.close();
        }
        catch(JDBCException jE) {
            System.out.println("JDBC error:--------->");
			System.out.println(jE.getMessage());
			System.out.println(jE.getStackTrace());
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			System.out.println(" error:--------->");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return channelMarkupOutput;
	} //****getChangeMealPlan stop
	
    //***getConfirmMealPlan start
	public static String getConfirmMealPlan(String oracleCall, ConfirmMealPlan confirmMealPlan) {
        String channelMarkupOutput="";
		Transaction tx =null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
            //to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
            tx = session.beginTransaction();
			Connection con = session.connection();
			oracleCall=" begin ?:=baninst1.usd_channel_meal_plan_new.p_submit (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);end;";
            CallableStatement cs = con.prepareCall(oracleCall);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setFloat(2, confirmMealPlan.getM_ProAmt());
            cs.setObject(3, confirmMealPlan.getM_ProAmt_str());
            cs.setFloat(4, confirmMealPlan.getM_rate());
            cs.setObject(5, confirmMealPlan.getM_new_plan());
            cs.setObject(6, confirmMealPlan.getM_plan());
            cs.setObject(7, confirmMealPlan.getM_old_plan());
            cs.setInt(8, confirmMealPlan.getPidm());
            cs.setInt(9, confirmMealPlan.getP_MRemain());
            cs.setObject(10, confirmMealPlan.getCbox());
            cs.setObject(11, confirmMealPlan.getTiv());
            cs.setObject(12, confirmMealPlan.getT_type());
            cs.setObject(13, confirmMealPlan.getP_term());
            cs.setFloat(14, confirmMealPlan.getPdd_40());
            cs.setFloat(15, confirmMealPlan.getPdd_41());
            cs.setFloat(16, confirmMealPlan.getPdd_45());
            cs.setFloat(17, confirmMealPlan.getPcc());
            cs.setFloat(18, confirmMealPlan.getPmin_dd());
            cs.setFloat(19, confirmMealPlan.getPmin_cc());
            cs.setObject(20, confirmMealPlan.getP_titleiv());
            if(confirmMealPlan.getOv_pidm()==0)
            	cs.setObject(21,null);
            else
            	cs.setInt(21, confirmMealPlan.getOv_pidm());
            cs.setObject(22, confirmMealPlan.getP_comments());
            cs.execute();
            channelMarkupOutput = (String)cs.getObject(1);
            System.out.println(channelMarkupOutput);
            tx.commit();
			session.close();
        }
        catch(JDBCException jE) {
            System.out.println("JDBC error:--------->");
			System.out.println(jE.getMessage());
			System.out.println(jE.getStackTrace());
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
        }
		catch(Exception e) {
		    System.out.println(" error:--------->");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return channelMarkupOutput;
    } //****getConfirmMealPlan stop
	
    //***getchannelMarkupWithoutAidy start
	public static String getChannelWithoutAidy(String oracleCall,String pidm) {
        String channelMarkupOutput="";
		Transaction tx = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall(oracleCall);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, pidm);
            cs.execute();
            channelMarkupOutput = cs.getString(1);
            tx.commit();
			session.close();
        }
        catch(JDBCException jE) {
            log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return channelMarkupOutput;
    } //****getChannelMarkupWithoutAidy stop
	
    //*****************************************    getParentPortalCookie  Start  *************************************************
	public static String getParentPortalCookie(String childPidm, String parentPidm) {
        String encCk="";
        Transaction tx =null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{? = call twbkbssf.f_encode(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, parentPidm +"|"+childPidm);
            cs.execute();
            encCk = cs.getString(1);
            tx.commit();
            session.close();
        }
        catch(JDBCException jE) {
            log.error(jE.getMessage());
            log.error(jE.toString());
            jE.printStackTrace();
        }
        catch(Exception e) {
            if ((tx != null & tx.isActive())) {
                log.debug("GPC ROLLBACK Transaction: " + tx.toString());
                tx.rollback();
            }
            log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
        }
        return encCk;
    }
    //*****************************************    getParentPortalCookie  Stop  *************************************************
	
    //*****************************************    Parent Portal Services  Start  *************************************************
	public static List<ParentInfo> getParentInfo(String childPidm) {
        SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		//List<ParentInfo> pInfo = session.getNamedQuery("getParentInfo").setParameter("childPidm",childPidm.trim()).list();
		return session.getNamedQuery("getParentInfo").setParameter("childPidm",childPidm.trim()).list();
	    /*session.close();
		 return pInfo;*/
    }
	
	public static int createNewParent(ParentInfo pInfo) {
		Transaction tx =null;
        int outPut = 0;
		String outPutMessage;
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
			Session session = sessionFactory.openSession();
			//to call stored function in DB using hibernate is through session.connection(), the only way to grab jdbc connection out of session in hibernate
			tx = session.beginTransaction();
			Connection con = session.connection();
            CallableStatement cs = con.prepareCall("{CALL p_get_webparent_id(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.setInt(1, Integer.parseInt(pInfo.getChildPidm()));
            cs.setString(2, pInfo.getParent_last_name());
            cs.setString(3, pInfo.getParent_first_name());
            cs.setString(4, pInfo.getMi());
            cs.setString(5, pInfo.getRelation_code());
            cs.setString(6, pInfo.getEmail_address());
            cs.registerOutParameter(7, OracleTypes.NUMBER);
            cs.registerOutParameter(8, OracleTypes.VARCHAR);
            cs.execute();
            outPut = cs.getInt(7);
            outPutMessage = cs.getString(8);
            System.out.println("outputmessage:--> "+outPutMessage);
            tx.commit();
			session.close();
        }
        catch(JDBCException jE) {
			log.error(jE.getMessage());
			log.error(jE.toString());
			jE.printStackTrace();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
			e.printStackTrace();
		}
        return outPut;
    }
	
	public static String updateParentAuthNumberForPermissions(String parentAuthNumber,String childPidm,String parentPidm) {
        Transaction tx = null;
		String retStr="0";
		int retInsNum=0;
		Date currentDatetime = new Date(System.currentTimeMillis());
	    Timestamp timestamp = new Timestamp(currentDatetime.getTime());
	    try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Connection con = session.connection();
            tx = session.beginTransaction();
            PreparedStatement pstmt = con.prepareStatement("UPDATE baninst1.web4parent_auth set BINARY_AUTH_INT=?, CHANGE_DATE=? where STUDENT_PIDM=? and PARENT_PIDM=?");
            pstmt.setInt(1, Integer.parseInt(parentAuthNumber));
            pstmt.setTimestamp(2, timestamp);
            pstmt.setInt(3, Integer.parseInt(childPidm));
            pstmt.setInt(4, Integer.parseInt(parentPidm));
            retInsNum = pstmt.executeUpdate();
            retStr = String.valueOf(retInsNum);
            tx.commit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            if ((tx != null & tx.isActive())) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
        }
        return retStr;
    }

    public static String linkChildToAlreadyExistingParent(String childPidm,String parentEmail) {
        Transaction tx = null;
		String retStr="false";
		Date currentDatetime = new Date(System.currentTimeMillis());
	    Timestamp timestamp = new Timestamp(currentDatetime.getTime());
        try {
            SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
            Session session = sessionFactory.openSession();
            Query query = session.getNamedQuery("getParent_by_emailAddress").setParameter("emailAddress",parentEmail.trim());
            ArrayList<ParentInfo> parentList = (ArrayList<ParentInfo>) query.list();
            System.out.println("#########SIZEE#######: "+parentList.size());
            if(parentList.size()>0) {
                ParentInfo linkP = parentList.get(0);
                System.out.println("################: "+linkP.getPidm());
                tx = session.beginTransaction();
                Connection con = session.connection();
                /*PreparedStatement pstmt = con.prepareStatement("select * from WEB4PARENT_XREF where email_address=?");
	            pstmt.setString(1, parentEmail);
                ResultSet result = pstmt.executeQuery();
                result.next();
	            ParentInfo linkP = new ParentInfo();
	            linkP.setPidm(result.getInt(2));
	            linkP.setParent_first_name(result.getString(3));
	            linkP.setParent_last_name(result.getString(4));
	            linkP.setMi(result.getString(5));
	            linkP.setEmail_address(result.getString(7));
	            String relation = result.getString(6);*/
                PreparedStatement pstmt = con.prepareStatement("INSERT into baninst1.WEB4PARENT_XREF values (?,?,?,?,?,?,?,?,?)");
                pstmt.setInt(1, Integer.parseInt(childPidm));
                pstmt.setInt(2, linkP.getPidm());
                pstmt.setString(3, linkP.getParent_first_name());
                pstmt.setString(4, linkP.getParent_last_name());
                pstmt.setString(5, linkP.getMi());
                pstmt.setString(6, linkP.getRelation_code());
                pstmt.setString(7, linkP.getEmail_address());
                pstmt.setString(8, "");
                pstmt.setTimestamp(9, timestamp);
                Integer retInsNum1 = Integer.valueOf(pstmt.executeUpdate());
                System.out.println("------------------->>>>>>"+retInsNum1);
                pstmt = con.prepareStatement("INSERT into baninst1.web4parent_auth values (?,?,?,?,?)");
                pstmt.setInt(1, Integer.parseInt(childPidm));
                pstmt.setInt(2, linkP.getPidm());
                pstmt.setInt(3, 1);
                pstmt.setInt(4, 7);
                pstmt.setTimestamp(5, timestamp);
                Integer retInsNum2 = Integer.valueOf(pstmt.executeUpdate());
                System.out.println("-------------------------------->>>>>>"+retInsNum2);
                tx.commit();
                retStr = "true";
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            if ((tx != null & tx.isActive())) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
        }
        return retStr;
    }

	public static List<ParentInfo> getParentInfoByEmailAddress(String emailAddress) {
        System.out.println("In get parent info by email address:-->"+emailAddress);
		SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("getParent_by_emailAddress").setParameter("emailAddress",emailAddress.trim());
		//ParentInfo pIn = (ParentInfo)query.uniqueResult();
		//System.out.println("Parent info by email:---> "+pIn.getRelation_code());
		return query.list();
    }
	
	public static String getParentAuthNumberForParentView(String childPidm, String parentPidm) {
        SessionFactory sessionFactory = HibernateUtil.getBasicSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("getParentAuthNumber").setParameter("childPidm",childPidm.trim()).setParameter("parentPidm",parentPidm.trim());		
		return query.uniqueResult().toString();
	}
    //*****************************************    Parent Portal Services  Stop  ***************************************************

}
