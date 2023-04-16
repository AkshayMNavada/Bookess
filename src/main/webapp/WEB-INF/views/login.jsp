<%@ page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Bookess</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous">
        </script>
        <script type="text/javascript">
            function Change() {
                var e = document.getElementById("options");
                var selOption = e.options[e.selectedIndex].value;
                if(selOption ==  'ADMIN'){
                    document.getElementById("login").value = "admin@admin.com";
                    document.getElementById("password").value = "admin@123";
                } else if(selOption ==  'USER'){
                    document.getElementById("login").value = "";
                    document.getElementById("password").value = "";
                }
            }
        </script>
        <link href="resources/css/login.css" rel="stylesheet" type="text/css">

        <style>
            .error {
                color: red;
            }
        </style>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">Bookess</a>
                <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="dashboard">Dashboard</a>
                        </li>
                        <%
                        String id = (String) session.getAttribute("email");
                        if (id == null) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="register">Register</a>
                        </li>
                        <%
                        }
                        if (id != null) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                        <%
                        }
                        %>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <div>
                <div class="wrapper fadeInDown">
                    <div id="formContent">
                        <div class="fadeIn first">
                            <h2 class='sign'>Sign In!</h2>
                            <div>
                                <span class='error'>
                                    <c:if test="${error != null }">
                                        ${error }
                                    </c:if>
                                </span>
                            </div>
                        </div>
                        <br />
                        <form action="login" method="POST">
                            <input type="text" id="login" class="fadeIn first" name="email" placeholder="Email" value="" />
                            <input type="password" id="password" class="fadeIn second" name="password" placeholder="Password" value="" />

                            <select id="options" onchange="Change()">
                                <%
                                    List<String> roles = (List) request.getAttribute("roles");
                                    Collections.reverse(roles);
                                    for(String role:roles)
                                    {
                                %>
                                    <option value=<%=role %>><%=  role%></option>
                                <%
                                    }
                                %>

                                <%-- <c:forEach items="${roles}" var="role">
                                    <option value=${role}>${role}</option>
                                </c:forEach> --%>

                            </select>

                            <input type="submit" class="fadeIn third" value="Log In" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>