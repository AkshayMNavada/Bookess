<%@ page import="java.util.List"%>
<%@ page import="com.spring.mvc.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            .book-container {
              display: inline-grid;
              grid-template-columns: auto;
              background-color: #FFFFFF;
              padding: 10px;
            }

            .grid-item {
              background-color: rgba(194, 212, 242, 0.8);
              border: 2px solid rgba(0, 0, 0, 0.8);
              padding: 20px;
              font-size: 20px;
              text-align: center;
            }

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
                            <a class="nav-link active" aria-current="page" href="dashboard">Dashboard</a>
                        </li>
                        <%
                        String id = (String) session.getAttribute("username");
                        if (id == null) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="register">Register</a>
                        </li>
                        <%
                        } else if (id != null && id=="admin") {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/book">
                                Add New Book
                            </a>
                        </li>
                        <li class="nav-item" id="logout">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                        <%
                            } else {
                        %>
                        <li class="nav-item" id="logout">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                        <li class="nav-item" id="liked-books">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/dashboard/liked-books">Liked Books</a>
                        </li>
                        <li class="nav-item" id="read-later-books">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/dashboard/read-later-books">Read Later Books</a>
                        </li>
                        <%
                        }
                        %>
                    </ul>
                </div>
            </div>
        </nav>

        <%
            id = (String) session.getAttribute("username");
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
            <%
                id = (String) session.getAttribute("username");
                if (id == null) {
            %>
            <div class="book-container">
                <c:forEach items="${books}" var="book" varStatus="loop">
                    <div class="grid-item">
                        <div class="row">
                            <span style="font-size:30px; font-style:bold; float:left;">${book.title}</span><br><br>
                            <span style="font-style:bold; float:left;">Author : ${book.author}</span><br><br>
                            <span style="float:left;" class="description">${book.description}</span><br>
                            <br>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%
                }
                if (id != null) {
            %>
            <div class="book-container">
                <c:forEach items="${books}" var="book" varStatus="loop">
                    <div class="grid-item">
                        <div class="row">
                            <span style="font-size:30px; font-style:bold; float:left;">${book.title}</span><br><br>
                            <span style="font-style:bold; float:left;">Author : ${book.author}</span><br><br>
                            <span style="float:left;" class="description">${book.description}</span><br>
                            <%
                                if (id != null && id!="admin") {
                            %>
                            <div class="column">
                                <c:choose>
                                    <c:when test="${book.liked}" >
                                        <span style="width:50%; padding-right:20px; color:green;">Added to <span style="font-style:bold;font-family:cursive;font-size:25px;">LIKED</span> books</span>
                                        <a id="unlike" href="${pageContext.servletContext.contextPath}/dashboard/unlikeBook/${loop.index}" style="color:red; cursor:pointer; text-decoration:none; font-size:15px;">
                                            Unlike
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="width:50%; padding-right:20px; float:left;">
                                            <a id="like" href="${pageContext.servletContext.contextPath}/dashboard/likeBook/${loop.index}" style="color:blue; cursor:pointer; text-decoration:none;">
                                                Like
                                            </a>
                                        </span>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${book.readLater}">
                                        <span style="width:50%; padding-left:20px; color:green;">Added to <span style="font-style:bold;font-family:cursive;font-size:25px;">READ LATER</span> section</span>
                                        <a id="remove-read-later" href="${pageContext.servletContext.contextPath}/dashboard/removeReadLater/${loop.index}" style="color:red; cursor:pointer; text-decoration:none; font-size:15px;">
                                            Remove from read later section
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="width:50%; padding-left:20px; float:right;">
                                            <a id="readLater" href="${pageContext.servletContext.contextPath}/dashboard/readLater/${loop.index}" style="color:blue; cursor:pointer; text-decoration:none;">
                                                Read later
                                            </a>
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <%
                                }
                            %>

                            <br>

                            <%--
                                <td><a href="admin/edit/${book.id }">Edit</a>
                                <a href="admin/delete/${book.id }"><i class="fa fa-trash"></i></a></td>
                            --%>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%
                }
            %>
        </div>
    </body>
</html>