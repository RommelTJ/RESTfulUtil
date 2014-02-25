<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
	<script>
	/* $(document).ready(function(){
	    alert("ready");
	$.ajax({
		type:"GET",
		dataType:"text",
		url:"http://localhost:8080/getInfo/testrestfulutil",
		success:function(response){
			alert(response);},
			error:function(response){alert(response.error);}
	});
	}); */
	
	<%--<%
     TestRestFulClient.testGetParentInfo();
	 Cookie cookie = TestRestFulClient.testGetParentPortalCookie("404554","1070741");
	 //response.addCookie(cookie);
	%> --%>
		
	function getSeatingChart(){
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getBannerId";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidm').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:"pidm="+pidm,
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
	}

	function getLevelResd() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getLevelResd";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var id = $('#pidmForGetLevelResd').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{id:id,pSource:"RESD"},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
	}
	
	function getInsertOnlineDepositChecklistAcknowledgement() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getInsertOnlineDepositChecklistAcknowledgement";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var id = $('#pidmForGetInsertOnlineDepositChecklistAcknowledgement').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{pidm:id,checkBox:"Y"},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
	}//getInsertOnlineDepositChecklistAcknowledgement
	
    function getDepositFlag() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getDepositFlag";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var id = $('#pidmForGetDepositFlag').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:"id="+id,
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }
	
    function updateParentAuthNumber() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getUpdateParentAuthNumber";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var parentPidm = $('#parentPidmForUpdateParentAuthNumber').val();
		var childPidm = $('#childPidmForUpdateParentAuthNumber').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{parentAuthNumber:1,childPidm:childPidm,parentPidm:parentPidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }//updateParentAuthNumber
	
	function checkforDuplicateParent(){
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getCheckForDuplicateParent";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForCheckforDuplicateParent').val();
        alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{childPidm:pidm,parentEmail:"joesphhoie@sandiego.edu"},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }//
	
	function getParentPinAndUsername() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getParentPinAndUsername";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForGetParentPinAndUsername').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{parentPidm:pidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
				
		});
    }

    function getParentAuthNumber() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getParentAuthNumber";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var parentPidm = $('#parentPidmForGetParentAuthNumber').val();
		var childPidm = $('#childPidmForGetParentAuthNumber').val();
		alert(parentPidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{childPidm:childPidm,parentPidm:parentPidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }//getParentAuthNumber
	
	function getCreateNewParent() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getCreateNewParent";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#emailForGetCreateNewParent').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{childPidm:pidm,parentFirstName:"joesph",parentLastName:"hoie",parentMiddleName:"",relation:"M",parentEmail:"joesphhoie@sandiego.edu"},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }
	
    function getCheckDuplicateEmailAddress() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getCheckDuplicateEmailAddress";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var email = $('#emailForGetCheckDuplicateEmailAddress').val();
		alert(email);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{parentEmail:email},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }//getCheckDuplicateEmailAddress
	
	function callGetChangeMealPlan() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getConfirmMealPlan";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForGetChangeMealPlam').val();
		alert(pidm);
		//var oracleCall="{? = call baninst1.usd_channel_meal_plan_new.f_get_display(?,?,?,?,?,?,?,?,?,?,?)}";
		var oracle = "declare v_str varchar2(5000); begin v_str := baninst1.usd_channel_meal_plan_new.f_get_display (?, ?, ?,?,?,?,?,?,null,null,null);end;";
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{oracleCall:oracle,m_ProAmt:"0",m_ProAmt_str:"",m_rate:"0",m_new_plan:"",m_plan:"",m_old_plan:"",pidm:pidm,p_MRemain:"",cbox:"",tiv:"",t_type:"",p_term:"",pdd_40:"0",pdd_41:"",pdd_45:"",pcc:"",pmin_dd:"0",pmin_cc:"0",p_titleiv:"",ov_pidm:"",p_comments:""},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }
	
	function callGetMealPlanOverride() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getMealPlanOverride";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForGetMealPlanOverride').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{userPidm:pidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }
	
	function getOverrideStartPage() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getOverrideStartPage";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForGetOverrideStartPage').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{p_pidm:pidm,pid:"",ov_pidm:""},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }
	
	function callBannerProcedure() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/callBannerProcedure";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForcallBannerProcedure').val();
		alert(pidm);
		var oracleCall="{call usd_emergency_notification.p_display(?,?)}";
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{oracleCall:oracleCall,pidm:pidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }

	function getEmailAddress() {
        var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getEmailAddress";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		var pidm = $('#pidmForGetEmailAddress').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:"pidm="+pidm,
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
	}

	function getChannelMarkup() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getChannelWithAidy";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		//alert($('#pidmForGetChannelMarkup').val());
		var pidm = $('#pidmForGetChannelMarkup').val();
		var oracleCall="{? = call usd_financial_aid_status.f_get_dispsumm(?,?)}";
		var aidy="";
        $.ajax({
			type:"POST",
			dataType:"text",
			data:{oracleCall:oracleCall,aidy:aidy,pidm:pidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }
	
	function getChannelMarkupWithoutAidy() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getChannelWithoutAidy";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getSeatingChart"; */
		//alert($('#pidmForGetChannelMarkupWithoutAidy').val());
		var pidm = $('#pidmForGetChannelMarkupWithoutAidy').val();
		var oracleCall="{? = call baninst1.usd_usdpix_upload_ws.f_get_start_page(?)}";
        $.ajax({
			type:"POST",
			dataType:"text",
			data:{oracleCall:oracleCall,pidm:pidm},
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(error){alert(error);}
		});
    }

	function enableAccess() {
		var indicator = "N";
		var rootUrl = "http://localhost:8080/restfulUtil/getInfo/USDRESTfulUtil/webAccessEnabling";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/webAccessEnabling"; */
		var username = $('#username').val();
		$.ajax({
		    type:"POST",
			dataType:"json",
			data:"username="+username+"&indicator="+indicator,
			url:rootUrl,
			success:function(response){
				alert(response.rows);},
				error:function(){alert("error");}
			});
	}
	
    function getPidm() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getPidm";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getPidm"; */
		var id = $('#username1').val();
		alert(id);
		$.ajax({
		    type:"POST",
			dataType:"json",
			data:"id="+id,
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(){alert("error");}
		});
    }

    function getTerms() {
        var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getTerms";
        var pidm = $('#pidmForGetTerms').val();
        $.ajax({
            type:"POST",
            dataType:"text",
            data:"pidm="+pidm,
            url:rootUrl,
            success:function(response){
                alert(response);},
            error:function(){alert("error");}
        });
    } //getTerms

    function getGPAForTerm() {
        var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getGPAForTerm";
        var pidm = $('#pidmForGetGPAForTerm').val();
        var term = $('#termCode').val();
        $.ajax({
            type:"POST",
            dataType:"text",
            data:{pidm:pidm,term:term},
            url:rootUrl,
            success:function(response){
                alert(response);},
            error:function(error){alert(error);}
        });
    }//getGPAForTerm

    function getTotalEarnedCredits() {
        var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getTotalEarnedCredits";
        var pidm = $('#pidmForGetTotalEarnedCredits').val();
        $.ajax({
            type:"POST",
            dataType:"text",
            data:"pidm="+pidm,
            url:rootUrl,
            success:function(response){
                alert(response);},
            error:function(){alert("error");}
        });
    } //getTotalEarnedCredits
	
	function checkHolds() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/checkHolds";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/checkHolds"; */
        var pidm = $('#pidmForCheckHolds').val();
		$.ajax({
			type:"POST",
			dataType:"text",
			data:"pidm="+pidm,
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(){alert("error");}
		});
    }
	
	function checkTranscriptHold() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/checkTranscriptHold";
		/*  var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/checkTranscriptHold"; */
		var pidm = $('#pidmForCheckTranscriptHold').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:"pidm="+pidm,
			url:rootUrl,
			success:function(response){
				alert(response);},
				error:function(){alert("error");}
		});
    }
	
	function getParentAuthNumberForParentView() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getParentAuthNumberForParentView";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getPidm"; */
		var childPidm = $('#getParentAuthNumberForParentViewChildPidm').val();
		var parentPidm = $('#getParentAuthNumberForParentViewParentPidm').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{childPidm:childPidm,parentPidm:parentPidm},
			url:rootUrl,
			success:function(response){
				 alert(response)
		            },
				error:function(){alert("error");}
		});
    }
	
	function getParentInfo() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getParentInfo";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getPidm"; */
		var pidm = $('#pidmForGetParentInfo').val();
        alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"json",
			data:"childPidm="+pidm,
			url:rootUrl,
			success:function(response){
				  //var obj = response;
		            if (response.length >= 1) {
		                jQuery('#ParentDropDown').html('');
		                jQuery('#bulletPermission').show();
		                jQuery.each(response, function (i, item) {
		                	jQuery('#ParentDropDown').append(jQuery('<option></option>').val(item.parent_pidm).html(item.parent_first_name + " " + item.parent_last_name));
		                });
		            }},
				error:function(){alert("error");}
		});
    }
	
	function getParentInfoByEmailAddress() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getParentInfoByEmailAddress";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getPidm"; */
		var email = $('#emailForGetParentInfoByEmailAddress').val();
		alert(email);
		$.ajax({
			type:"POST",
			dataType:"json",
			data:"emailAddress="+email,
			url:rootUrl,
			success:function(response){
				  //var item = response;
				  alert(response.relation_code);
		                //jQuery('#ParentDropDownForEmail').html('');
		                jQuery('#bulletPermission').show();
		                alert(obj.parent_pidm);
		                jQuery('#ParentDropDownForEmail').append(jQuery('<option></option>').val(item.parent_pidm).html(item.parent_first_name + " " + item.parent_last_name));
		            },
				error:function(){alert("error");}
		});
    }

    function getLinkChildToAlreadyExistingParent() {
	    var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getLinkChildToAlreadyExistingParent";
	    /* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getPidm"; */
	    var email = $('#emailForGetLinkChildToAlreadyExistingParent').val();
	    alert(email);
        $.ajax({
		    type:"POST",
		    dataType:"text",
		    data:{childPidm:"612292",parentEmail:"shoiey@sandiego.edu"},
		    url:rootUrl,
		    success:function(response){
			  //var item = response;
			  alert(response);
	        },
			error:function(){alert("error");}
	    });
    }//getLinkChildToAlreadyExistingParent

    function getChildInfo() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getChildInfo";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/USDRESTfulUtil/getPidm"; */
		var pidm = $('#pidmForGetChildInfo').val();
		alert(pidm);
		$.ajax({
			type:"POST",
			dataType:"json",
			data:"parentPidm="+pidm,
			url:rootUrl,
			success:function(response){
				  //var obj = response;
		            if (response.length >= 1) {
		                jQuery('#ChildDropDown').html('');
		                jQuery.each(obj, function (i, item) {
		               	jQuery('#ChildDropDown').append(jQuery('<option></option>').val(item.pidm).html(item.first_name + " " + item.last_name));
        		                });
		            }},
				error:function(){alert("error");}
		});
    }
	
	function getRoles() {
		var rootUrl = "http://localhost:8080/getInfo/USDRESTfulUtil/getRoles";
		/* var rootUrl = "http://allproxy.sandiego.edu/restfulUtil/getInfo/testrestfulutil/getRoles"; */
		var bannerId = $('#bannerId').val();
		$.ajax({
		    type:"POST",
			dataType:"json",
			data:"bannerId="+bannerId,
			url:rootUrl,
			success:function(response){
			alert(response.roles);},
			error:function(){alert("error");}
        });
    }
	</script>

	<h2>Welcome to USD RESTful Utilities project!</h2>
	<!-- <p><a href="getInfo/myresource">Get Hello</a>
    <p><a href="getInfo/testrestfulutil">Get Hello</a> -->
	<form method="POST" name="postIt" action="getInfo/USDRESTfulUtil/getBannerId">
        <label for="pidm">PIDM</label> <input type="text" value="" id="pidm" name="pidm" /> <input type="submit" value="Get Banner ID" />
	</form>
	-----------------------------------------------------------------------------------------
	<!-- <br>
		Banner ID <input type="text" value="" id="bannerId" name="bannerId" />
		<input type="submit" value="Get Roles" id="submitToGetRoles" onclick="getRoles()" />
		<br>
	------------------------------------------------------------------------------------------
		<br>
		CRN: <input type="text" value="" id="crn" name="crn" />
		<br>
		TERM: <input type="text" value="" id="term" name="term" />
		<input type="submit" value="Get Seating Chart" id="submitToGetSeatingChart" onclick="getSeatingChart()" />
		<br>
		<br>
		-------------------------------------------------------------------------------------------
		<br>
		<br>
		-------------------------------------------------------------------------------------------
		<br>
		<br>
		Username <input type="text" value="" id="username" name="username" />
		<input type="submit" value="Enable Access" id="submitToEnableAccess" onclick="enableAccess()" />
		<br>
		<br> -->
		----------------------------------
			<br>
		<br>
        <label for="username1">Banner/Spriden ID</label> <input type="text" value="" id="username1" name="username" />
		<input type="submit" value="get Pidm" id="getPidm" onclick="getPidm()" />
		<br>
		<br>
		---------------------------------
			<br>
		<br>
        <label for="pidmForCheckHolds">PIDM</label> <input type="text" value="" id="pidmForCheckHolds" name="pidmForCheckHolds" />
		<input type="submit" value="Check Holds" id="checkHolds" onclick="checkHolds()" />
		<br>
		<br>
		---------------------------------
			<br>
		<br>
        <label for="pidmForGetParentInfo">Child PIDM</label> <input type="text" value="" id="pidmForGetParentInfo" name="pidmForGetParentInfo" />
		<input type="submit" value="Get Parent Info" id="getParentInfo" onclick="getParentInfo()" />
		<br>
		<br>
        <label for="ParentDropDown"></label><select id="ParentDropDown" name="parentDropDown"></select>
		---------------------------------
			<br>
		<br>
        <label for="pidmForGetChildInfo">Parent PIDM</label> <input type="text" value="" id="pidmForGetChildInfo" name="pidmForGetChildInfo" />
		<input type="submit" value="Get Child Info" id="getChildInfo" onclick="getChildInfo()" />
		<br>
		<br>
        <label for="ChildDropDown"></label><select id="ChildDropDown" name="childDropDown"></select>
		---------------------------------
			<br>
		<br>
        <label for="getParentAuthNumberForParentViewChildPidm">Child PIDM </label> <input type="text" value="" id="getParentAuthNumberForParentViewChildPidm" name="getParentAuthNumberForParentViewChildPidm" />
        <label for="getParentAuthNumberForParentViewParentPidm">Parent PIDM</label> <input type="text" value="" id="getParentAuthNumberForParentViewParentPidm" name="getParentAuthNumberForParentViewParentPidm" />
		<input type="submit" value="Parent Auth Number" id="getParentAuthNumberForParentView" onclick="getParentAuthNumberForParentView()" />
		<br>
		<br>
			---------------------------------
			<br>
		<br>
        <label for="pidmForGetChannelMarkup">Child PIDM</label> <input type="text" value="" id="pidmForGetChannelMarkup" name="getChannelMarkup" />
		<input type="submit" value="Get Channel Markup" id="getChannelMarkup" onclick="getChannelMarkup()" />
		<br>
		<br>
				---------------------------------
			<br>
		<br>
        <label for="pidmForGetChannelMarkupWithoutAidy">Child PIDM</label> <input type="text" value="" id="pidmForGetChannelMarkupWithoutAidy" name="getChannelMarkupWithoutAidy" />
		<input type="submit" value="Get Channel Markup Without Aidy" id="getChannelMarkupWithoutAidy" onclick="getChannelMarkupWithoutAidy()" />
		<br>
		<br>
			---------------------------------
			<br>
		<br>
        <label for="pidmForGetEmailAddress">PIDM</label> <input type="text" value="" id="pidmForGetEmailAddress" name="pidmForGetEmailAddress" />
		<input type="submit" value="Get Email Address" id="getEmailAddress" onclick="getEmailAddress()" />
		<br>
		<br>
			---------------------------------
			<br>
		<br>
        <label for="pidmForCheckTranscriptHold">PIDM</label> <input type="text" value="" id="pidmForCheckTranscriptHold" name="pidmForCheckTranscriptHold" />
		<input type="submit" value="Check Transcript Hold" id="checkTranscriptHold" onclick="checkTranscriptHold()" />
		<br>
		<br>
			---------------------------------
			<br>
		<br>
        <label for="pidmForcallBannerProcedure">PIDM</label> <input type="text" value="" id="pidmForcallBannerProcedure" name="pidmcallBannerProcedure" />
		<input type="submit" value="Call Banner Procedure" id="callBannerProcedure" onclick="callBannerProcedure()" />
		<br>
		<br>
			---------------------------------
			<br>
		<br>
        <label for="pidmForGetChangeMealPlam">PIDM</label> <input type="text" value="" id="pidmForGetChangeMealPlam" name="pidmForGetChangeMealPlam" />
		<input type="submit" value="get Change Meal Plan" id="callGetChangeMealPlan" onclick="callGetChangeMealPlan()" />
		<br>
		<br>
				---------------------------------
			<br>
		<br>
        <label for="pidmForGetMealPlanOverride">PIDM</label> <input type="text" value="" id="pidmForGetMealPlanOverride" name="pidmForGetMealPlanOverride" />
		<input type="submit" value="get Change Meal Plan" id="callGetMealPlanOverride" onclick="callGetMealPlanOverride()" />
		<br>
		<br>
				---------------------------------
			<br>
		<br>
        <label for="pidmForGetOverrideStartPage">PIDM</label> <input type="text" value="" id="pidmForGetOverrideStartPage" name="pidmForGetOverrideStartPage" />
		<input type="submit" value="get override start page" id="callGetOverrideStartPage" onclick="getOverrideStartPage()" />
		<br>
		<br>
				---------------------------------
			<br>
		<br>
        <label for="pidmForCheckforDuplicateParent">PIDM</label> <input type="text" value="" id="pidmForCheckforDuplicateParent" name="pidmForCheckforDuplicateParent" />
		<input type="submit" value="check for duplicate parent" id="callCheckforDuplicateParent" onclick="checkforDuplicateParent()" />
		<br>
		<br>---------------------------------
			<br>
		<br>
        <label for="emailForGetParentInfoByEmailAddress">Parent Email</label> <input type="text" value="" id="emailForGetParentInfoByEmailAddress" name="emailForGetParentInfoByEmailAddress" />
		<input type="submit" value="Get Parent Info By EMail Address" id="getParentInfoByEmailAddress" onclick="getParentInfoByEmailAddress()" />
		<br>
		<br>
        <label for="ParentDropDownForEmail"></label><select id="ParentDropDownForEmail" name="parentDropDownForEmail"></select>
		---------------------------------
			<br>
		<br>
        <label for="emailForGetLinkChildToAlreadyExistingParent">Email</label> <input type="text" value="" id="emailForGetLinkChildToAlreadyExistingParent" name="pidmForGetLinkChildToAlreadyExistingParent" />
		<input type="submit" value="link child to already existing parent" id="callGetLinkChildToAlreadyExistingParent" onclick="getLinkChildToAlreadyExistingParent()" />
		<br>
		<br>
				---------------------------------
		---------------------------------
			<br>
		<br>
        <label for="emailForGetCreateNewParent">PIDM</label> <input type="text" value="" id="emailForGetCreateNewParent" name="pidmForGetCreateNewParent" />
		<input type="submit" value="create new parent" id="callGetCreateNewParent" onclick="getCreateNewParent()" />
		<br>
		<br>
				---------------------------------
				---------------------------------
			<br>
		<br>
        <label for="emailForGetCheckDuplicateEmailAddress">Email</label> <input type="text" value="" id="emailForGetCheckDuplicateEmailAddress" name="emailForGetCheckDuplicateEmailAddress" />
		<input type="submit" value="check for duplicate email address" id="callGetCheckDuplicateEmailAddress" onclick="getCheckDuplicateEmailAddress()" />
		<br>
		<br>
				---------------------------------
				---------------------------------
			<br>
		<br>
        <label for="pidmForGetParentPinAndUsername">Parent PIDM</label> <input type="text" value="" id="pidmForGetParentPinAndUsername" name="pidmForGetParentPinAndUsername" />
		<input type="submit" value="get parent pin and username" id="callGetParentPinAndUsername" onclick="getParentPinAndUsername()" />
		<br>
		<br>
				---------------------------------
				<br>
		<br>
        <label for="parentPidmForGetParentAuthNumber">Parent PIDM</label> <input type="text" value="" id="parentPidmForGetParentAuthNumber" name="parentPidmForGetParentAuthNumber" />
        <label for="childPidmForGetParentAuthNumber">Child PIDM</label> <input type="text" value="" id="childPidmForGetParentAuthNumber" name="childPidmForGetParentAuthNumber" />
		<input type="submit" value="get parent auth number" id="callGetParentAuthNumber" onclick="getParentAuthNumber()" />
		<br>
		<br>
				---------------------------------	
				---------------------------------
				<br>
		<br>
        <label for="parentPidmForUpdateParentAuthNumber">Parent PIDM</label> <input type="text" value="" id="parentPidmForUpdateParentAuthNumber" name="parentPidmForUpdateParentAuthNumber" />
        <label for="childPidmForUpdateParentAuthNumber">Child PIDM</label> <input type="text" value="" id="childPidmForUpdateParentAuthNumber" name="childPidmForUpdateParentAuthNumber" />
		
		<input type="submit" value="change parent auth number" id="callUpdateParentAuthNumber" onclick="updateParentAuthNumber()" />
		<br>
		<br>
		
		
				---------------------------------
				
						
				<br>
		<br>
        <label for="pidmForGetDepositFlag">Banner ID</label> <input type="text" value="" id="pidmForGetDepositFlag" name="pidmForGetDepositFlag" />
		  <input type="submit" value="get deposit flag" id="callGetDepositFlag" onclick="getDepositFlag()" />
		<br>
		<br>
				---------------------------------
				<br>
		<br>
        <label for="pidmForGetLevelResd">Banner ID</label> <input type="text" value="" id="pidmForGetLevelResd" name="pidmForGetLevelResd" />
		  <input type="submit" value="get Level" id="callGetLevelResd" onclick="getLevelResd()" />
		<br>
		<br>
				---------------------------------
						<br>
		<br>
        <label for="pidmForGetInsertOnlineDepositChecklistAcknowledgement">PIDM</label> <input type="text" value="" id="pidmForGetInsertOnlineDepositChecklistAcknowledgement" name="pidmForGetInsertOnlineDepositChecklistAcknowledgement" />
		  <input type="submit" value="get inset online deposit ack" id="callGetLInsertOnlineDepositChecklistAcknowledgement" onclick="getInsertOnlineDepositChecklistAcknowledgement()" />
		<br>
		<br>
				---------------------------------
    <br>
    <br>
    <label for="pidmForGetTerms">PIDM</label> <input type="text" value="" id="pidmForGetTerms" name="pidmForGetTerms" />
    <input type="submit" value="Get Terms" id="getTerms" onclick="getTerms()" />
    <br>
    <br>
    ---------------------------------
    <br>
    <br>
    <label for="pidmForGetGPAForTerm">PIDM</label> <input type="text" value="" id="pidmForGetGPAForTerm" name="pidmForGetGPAForTerm" />
    <label for="termCode">Term Code (e.g. 201409)</label> <input type="text" value="" id="termCode" name="termCode" />
    <input type="submit" value="Get GPA For Term" id="getGPAForTerm" onclick="getGPAForTerm()" />
    <br>
    <br>
    ---------------------------------
    <br>
    <br>
    <label for="pidmForGetTotalEarnedCredits">PIDM</label> <input type="text" value="" id="pidmForGetTotalEarnedCredits" name="pidmForGetTotalEarnedCredits" />
    <input type="submit" value="Get Sum of Credits Earned" id="getTotalEarnedCredits" onclick="getTotalEarnedCredits()" />
    <br>
    <br>
    ---------------------------------

    </body>
</html>
