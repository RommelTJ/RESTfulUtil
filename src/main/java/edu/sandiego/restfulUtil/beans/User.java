package edu.sandiego.restfulUtil.beans;

import edu.sandiego.restfulUtil.enumeration.MessageEnum;

public class User {
	private String bannerId;
	private String pidm;
	public MessageEnum status;
	private String errorDescription;
	private String userAccountHold;
    private String[] terms;

	public User(){}
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public String getPidm() {
		return pidm;
	}
	public void setPidm(String pidm) {
		this.pidm = pidm;
	}
	public MessageEnum getStatus() {
		return status;
	}
	public void setStatus(MessageEnum status) {
		this.status = status;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getUserAccountHold() {
		return userAccountHold;
	}
	public void setUserAccountHold(String userAccountHold) {
		this.userAccountHold = userAccountHold;
	}
    public String[] getTerms() {
        return terms;
    }
    public void setTerms(String[] terms) {
        this.terms = terms;
    }

}
