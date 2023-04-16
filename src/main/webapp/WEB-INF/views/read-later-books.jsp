<%@ page import="java.util.List"%>
<%@ page import="com.spring.mvc.entity.Book"%>
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

        <style type="text/css">
            .description {
                font-size: 15px;
                line-height: 1.5em;
                height: 5em;
                overflow: hidden;
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
                    <ul class="navbar-nav ">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="javascript:history.back()">Dashboard</a>
                        </li>
                        <li class="nav-item" id="logout">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <%
            String id = (String) session.getAttribute("username");
            if (id != null) {
        %>
        <div class="d-flex justify-content-center">
            <span class="username" style="color: #00b1b3;font-size:40px;">
                Welcome ${sessionScope.username}
                <br>
            </span>
        </div>
        <%
            }
        %>

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Book title</th>
                        <th scope="col">Book author</th>
                        <th scope="col">Book description</th>
                        <th scope="col">Read Later</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${readLaterBooks}" var="book">
                        <tr>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td style="width:600px;">${book.description}</td>

                            <c:choose>
                                <c:when test="${book.readLater}">
                                    <td>YES</td>
                                </c:when>
                                <c:otherwise>
                                    <td>NO</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>