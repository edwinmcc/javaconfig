<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <link rel="stylesheet" type="text/css" href="<c:out value='/javaconfig/resources/css/layout.css'/>" />
        <script src="<c:out value='/javaconfig/resources/js/jquery-ui-1.11.2/jquery.js'/>" type='text/javascript'></script>
        <script src="<c:out value='/javaconfig/resources/js/jquery-ui-1.11.2/jquery-ui.js'/>" type='text/javascript'></script>
        <script src="<c:out value='/javaconfig/resources/js/login.js'/>" type='text/javascript'></script>
        <title>Welcome Stock Systems</title>
    </head>
    <body>  
        <div id='fullbody' class='clsFullBody'>
            <div id='topRowDiv' class='clsTopRowDiv'>
                <div id='topDiv' class='clsTopDiv'>
                    <div style="border: 0px solid #EEE;"></div>
                </div>
            </div>
            <div id='midRowDiv' class='clsMidRowDiv'>
                <div id='leftDiv' class='clsLeftDiv'>
                </div>
                <div id='centerDiv' class='clsCenterDiv'>
                    <div style="border: 0px solid #EEE; width: 100%; height: 100%;" >
                        <form name="loginForm" action="<c:out value='/javaconfig/login'/>" method="POST">
                            <h1>Welcome to Warehouse & Stock Management for Melbourne, Australia</h1>
                                <div>
                                        <c:if test="${param.loginfailed != null}">
                                    <div>
                                            Failed to login.
                                            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                                    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                                            </c:if>
                                    </div>
                                            </c:if>
                                                    <p>${message}</p>
                                    </div>
                                    <div class="loginBorder">
                                    <p class='txtloginForm'>Login Form</p>
                                    <div><img class='imgLogin'  src="<c:out value='/javaconfig/resources/images/mail-icon.png'/>" /><input name="username"  id='txtEmail' tabIndex="1" class='txtUn' value="Enter your email-id" /></div>
                                    <div><img class='imgLogin'  src="<c:out value='/javaconfig/resources/images/password-icon.png'/>" /><input name="password" type="password" id='txtPwd' tabIndex="2" class='txtPwd' /></div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <div ><input id='btnSubmit' tabIndex="3" class='clsBtnSubmit' type='submit' value="Login"></div>
                                    </div>
                        </form>
                    </div>
                </div>
                <div id='rightDiv' class='clsRightDiv'>
                </div>
            </div>
            <div id='botRowDiv' class='clsBotRowDiv'>
                <div id='botDiv' class='clsBotDiv'>
                </div>
            </div>
        </div>
    </body>
</html>