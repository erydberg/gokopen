<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        
        
        <c:if test="${score.station.waypoint }">
        	<label for="visitedWaypoint">Patrullen har passerat</label>
			<form:checkbox path="visitedWaypoint" id="visitedWaypoint" /> Ja
			<c:if test="${score.visitedWaypoint}"><p><small>Vill du avmarkera passagen, ta bort hela "poängen" istället.</small></p></c:if>
        </c:if>
        <c:if test="${not score.station.waypoint }">
        <div class="form-box">
          <fieldset>
          <h3>Poäng</h3>
          <div class="radio-score">
           <c:forEach var="j" begin="${score.station.minScore}" end="${score.station.maxScore}">
              <c:if test="${score.scorePoint==j }"><form:radiobutton path="scorePoint" value="${j}" checked="checked"/><label for="scorePoint${j+1}">${j}</label></c:if>
              <c:if test="${score.scorePoint!=j }"><form:radiobutton path="scorePoint" value="${j}"/><label for="scorePoint${j+1}">${j}</label></c:if>
              </c:forEach>
          </div>
          </fieldset>
        </div>
        <div class="form-box">
          <fieldset>
            <h3>Stilpoäng:</h3>
	          <div class="radio-score">
	          	<c:forEach var="i" begin="${score.station.minStyleScore}" end="${score.station.maxStyleScore}">
        			<c:if test="${score.stylePoint==i }"><form:radiobutton path="stylePoint" value="${i}" checked="checked"/><label for="stylePoint${i+1}">${i}</label></c:if>
        			<c:if test="${score.stylePoint!=i }"><form:radiobutton path="stylePoint" value="${i}"/><label for="stylePoint${i+1}">${i}</label></c:if>        			
       			</c:forEach>
    	      </div>            
          </fieldset>
        </div>
        </c:if>