package edu.sandiego.restfulUtil.beans;

public class ParentInfo {
    
	private int pidm;
    private String childPidm;
	private String parent_first_name;
    private String parent_last_name;
    private String mi;
    private String relation_code;
    private String email_address;

    public ParentInfo(){}



	public int getPidm() {
		return pidm;
	}





	public void setPidm(int pidm) {
		this.pidm = pidm;
	}





	public String getParent_first_name() {
		return parent_first_name;
	}



	public void setParent_first_name(String parent_first_name) {
		this.parent_first_name = parent_first_name;
	}



	public String getParent_last_name() {
		return parent_last_name;
	}



	public void setParent_last_name(String parent_last_name) {
		this.parent_last_name = parent_last_name;
	}



	public String getMi() {
		return mi;
	}



	public void setMi(String mi) {
		this.mi = mi;
	}


		public String getRelation_code() {
			return relation_code;
		}









		public void setRelation_code(String relation_code) {
			this.relation_code = relation_code;
		}


	public String getEmail_address() {
		return email_address;
	}



	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	



	public String getChildPidm() {
		return childPidm;
	}

	public void setChildPidm(String childPidm) {
		this.childPidm = childPidm;
	}

	/*public String toString() {
		
		return  "EMAIL ADDR : "+email_address+
				", FIRST NAME : "+parent_first_name+
				", LAST NAME :  "+parent_last_name+
				" PIDM :  "+pidm;
	}*/
}
