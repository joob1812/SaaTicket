<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Localisations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
    <div class="container mt-4">
        <jsp:include page="../includes/admin-header.jsp" />
        
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Liste des Localisations</h2>
            <a href="${pageContext.request.contextPath}/admin/locations/add" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Ajouter une localisation
            </a>
        </div>
        
        <div class="card">
            <div class="card-body">
                <c:if test="${empty locations}">
                    <div class="alert alert-info">Aucune localisation n'est disponible.</div>
                </c:if>
                
                <c:if test="${not empty locations}">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nom</th>
                                    <th>Adresse</th>
                                    <th>Service</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${locations}" var="location">
                                    <tr>
                                        <td>${location.id}</td>
                                        <td>${location.name}</td>
                                        <td>${location.address}</td>
                                        <td>${location.service.name}</td>
                                        <td>
                                            <div class="btn-group" role="group">
                                                <a href="/admin/locations/edit/${location.id}" class="btn btn-sm btn-warning">
                                                    <i class="bi bi-pencil-square"></i> Modifier
                                                </a>
                                                <a href="/admin/locations/delete/${location.id}" class="btn btn-sm btn-danger" 
                                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette localisation?');">
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
