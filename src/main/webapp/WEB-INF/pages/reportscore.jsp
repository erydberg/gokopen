
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Rapportera poäng</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/score.js"></script>
<script>
        <c:if test="${not empty alertmsg }">
            var msg = '${alertmsg }';
        </c:if>

        $(document).ready(function(){
        	if(typeof msg !== 'undefined'){
        		alert(msg);
        		$("input:radio").removeAttr("checked");
        		$("input:checkbox").removeAttr("checked");
        	}
        });
    </script>
</head>
<body>
	<c:if test="${not empty errormsg }">
		<div class="errorblock">${errormsg}</div>
	</c:if>
	<div class="nav-box">
		<div class="page-head">
			<h1>Rapportera poäng</h1>
		</div>
	</div>
	<c:if test="${empty score.station }">
		<form:form commandName="score" method="post"
			action="${pageContext.request.contextPath}/score/selectstation"
			cssClass="form-general">
			<form:hidden path="scoreId" id="scoreId" />
			<div class="form-box">
				<fieldset>
					<label for="station">Kontroll:</label>
					<form:select path="station" id="station">
						<option value="-1">-- Välj kontroll --</option>
						<form:options items="${stations}" itemLabel="stationName" />
					</form:select>
				</fieldset>
			</div>
			<div class="submit-area">
				<input type="submit" name="saveStation" value="Fortsätt" /> | <a
					href="${pageContext.request.contextPath}/startmenu/">Avbryt</a>
			</div>

		</form:form>
	</c:if>
	<c:if test="${not empty score.station }">
		<form:form commandName="score" method="post"
			action="${pageContext.request.contextPath}/score/savescore"
			cssClass="form-general">
			<form:hidden path="scoreId" id="scoreId" />
        Vald kontroll: ${score.station.stationName }
        <form:hidden path="station.stationId" id="station.stationId" />
			<div class="form-box">
				<panes> <leftpane>
				<fieldset>
					<label for="patrol">Patrull:</label>
					<form:select path="patrol" id="patrol">
						<option value="-1">-- Välj patrull --</option>
						<form:options items="${patrols}" itemLabel="patrolInfo" />
					</form:select>
				</fieldset>
				</leftpane> <rightpane> <quicknum> <label>Nr: <span
					id="current_filter"><span></label>
				<div>
					<button id="b7" onclick="filterPatrol(7);" type="button">7</button>
					<button id="b8" onclick="filterPatrol(8);" type="button">8</button>
					<button id="b9" onclick="filterPatrol(9);" type="button">9</button>
				</div>
				<div>
					<button id="b4" onclick="filterPatrol(4);" type="button">4</button>
					<button id="b5" onclick="filterPatrol(5);" type="button">5</button>
					<button id="b6" onclick="filterPatrol(6);" type="button">6</button>
				</div>
				<div>
					<button id="b1" onclick="filterPatrol(1);" type="button">1</button>
					<button id="b2" onclick="filterPatrol(2);" type="button">2</button>
					<button id="b3" onclick="filterPatrol(3);" type="button">3</button>
				</div>
				<div>
					<button id="bclr" onclick="clearFilter();" type="button">
						<b>C</b>
					</button>
					<button id="b0" onclick="filterPatrol(0);" type="button">0</button>
					<button id="bbs" onclick="backspaceFilter();" type="button">
						<b>&lt;</b>
					</button>
				</div>
				</quicknum> </rightpane> </panes>
			</div>

			<jsp:include page="include_editscore.jsp" flush="false"></jsp:include>

			<div class="submit-area">
				<input type="submit" name="saveScore" value="Spara" /> | <a
					href="${pageContext.request.contextPath}/startmenu/">Avbryt</a>
			</div>
		</form:form>
	</c:if>
</body>
</html>
