<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
    <div class="container mt-4">
        <jsp:include page="../includes/admin-header.jsp" />
        
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Liste des Utilisateurs</h2>
            <a href="/admin/users/add" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Ajouter un utilisateur
            </a>
        </div>
        
        <div class="card">
            <div class="card-body">
                <c:if test="${empty users}">
                    <div class="alert alert-info">Aucun utilisateur n'est disponible.</div>
                </c:if>
                
                <c:if test="${not empty users}">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nom d'utilisateur</th>
                                    <th>Rôle</th>
                                    <th>Localisation</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.username}</td>
                                        <td><span class="badge bg-${user.role == 'ADMIN' ? 'danger' : 'success'}">${user.role}</span></td>
                                        <td>${user.location != null ? user.location.name : 'N/A'}</td>
                                        <td>
                                            <div class="btn-group" role="group">
                                                <a href="/admin/users/edit/${user.id}" class="btn btn-sm btn-warning">
                                                    <i class="bi bi-pencil-square"></i> Modifier
                                                </a>
                                                <a href="/admin/users/delete/${user.id}" class="btn btn-sm btn-danger" 
                                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur?');">
                                                    <i class="bi bi-trash"></i> Supprimer
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
