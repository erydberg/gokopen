<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Start och målgång</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<script>
var path = '${pageContext.request.contextPath}';
var csspath = path + '/css/';
$(document).ready(function(){
	$('.nav-box').on("click", ".patrol", function(){
		var status = $(this).data('status');
		var patrolid = $(this).data('id');
		if(status=="registered"){
			changetoactive(this, patrolid);
		}
		if(status=="active"){
			changetofinished(this, patrolid);
		}
		if(status=="finished"){
			changetoresigned(this, patrolid);
		}
		if(status=="resigned"){
			changetoregistered(this, patrolid);
		}
	}); 
	});
function changetoactive(obj, id){
    jQuery.ajax({
     url    : path + '/startfinish/activate/'+id,
     type   : 'PUT',

     success : function(data){
    	 	$(obj).data('status','active');
			$(obj).attr("src",csspath + "active.png");
               },
      error : function(errorData){
				alert("Det gick inte att uppdatera statusen");
              }
    });
}
function changetofinished(obj, id) {
	jQuery.ajax({
		url : path + '/startfinish/finished/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','finished');
			$(obj).attr("src",csspath + "finished.png");
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}
function changetoresigned(obj, id) {
	jQuery.ajax({
		url : path + '/startfinish/resigned/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','resigned');
			$(obj).attr("src",csspath + "resigned.png");
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}
function changetoregistered(obj, id) {
	jQuery.ajax({
		url : path + '/startfinish/registered/' + id,
		type : 'PUT',

		success : function(data) {
			$(obj).data('status','registered');
			$(obj).attr("src",csspath + "registered.png");
		},
		error : function(errorData) {
			alert("Det gick inte att uppdatera statusen");
		}
	});
}
    </script>
</head>
<body>
	<div class="nav-box">
		<c:if test="${not empty errormsg }">
			<div class="errorblock">${errormsg}</div>
		</c:if>

		<h1>Start och målgång</h1>
		<table>
			<tr>
				<th>Status <a href="${pageContext.request.contextPath}/startfinish/sortbystatus"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Patrull <a href="${pageContext.request.contextPath}/startfinish/"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Klass <a href="${pageContext.request.contextPath}/startfinish/sortbytrack"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Kår <a href="${pageContext.request.contextPath}/startfinish/sortbytroop"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Antal kontroller <a href="${pageContext.request.contextPath}/startfinish/sortbycompletedstations"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
				<th>Antal poäng <a href="${pageContext.request.contextPath}/startfinish/sortbyscore"><img src="${pageContext.request.contextPath}/css/arrow-down.png"></a></th>
			</tr>
			<c:forEach items="${patrols }" var="patrol" varStatus="status">
			<c:set var='trclass' value=''></c:set>
			<c:choose>
 				<c:when test="${(status.index)%2 eq 1}"><c:set var='trclass' value='class="odd"'></c:set></c:when>
 			</c:choose>
			<tr ${trclass}>
					<c:if test="${patrol.status=='REGISTERED' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="registered" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/registered.png"/>
					</td>
					</c:if>
					<c:if test="${patrol.status=='ACTIVE' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="active" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/active.png"/>
					</td>
					</c:if>
					<c:if test="${patrol.status=='FINISHED' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="finished" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/finished.png"/>
					</td>
					</c:if>
					<c:if test="${patrol.status=='RESIGNED' }">
					<td class="patrolstatus">
						<img class="patrol" data-status="resigned" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/resigned.png"/>
					</td>
					</c:if>
					<c:if test="${empty patrol.status }">
						<td class="patrolstatus">
							<img class="patrol" data-status="registered" data-id="${patrol.patrolId}" src="${pageContext.request.contextPath}/css/registered.png"/>
						</td>
					</c:if>
					<td><a href="">${patrol.patrolName }</a></td>
					<td>${patrol.track.trackName }</td>
					<td>${patrol.troop }</td>
					<td>${patrol.totalReportedStations}</td>
					<td>${patrol.totalScore} (${patrol.totalScorePoint}+${patrol.totalStylePoint})</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div>
		<a href="${pageContext.request.contextPath}/">Tillbaka</a>
	</div>
</body>
</html>