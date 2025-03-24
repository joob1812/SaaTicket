<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Utilisateurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h3>${user.id == null ? 'Ajouter un utilisateur' : 'Modifier l\'utilisateur'}</h3>
                    </div>
                    <div class="card-body">
                        <form:form action="/admin/users/save" method="post" modelAttribute="user">
                            <form:hidden path="id" />
                            
                            <div class="mb-3">
                                <label for="username" class="form-label">Nom d'utilisateur</label>
                                <form:input path="username" class="form-control" required="true" />
                            </div>
                            
                            <div class="mb-3">
                                <label for="password" class="form-label">Mot de passe</label>
                                <form:password path="password" class="form-control" required="${user.id == null}" />
                                <c:if test="${user.id != null}">
                                    <small class="text-muted">Laissez vide pour conserver le mot de passe actuel</small>
                                </c:if>
                            </div>
                            
                            <div class="mb-3">
                                <label for="role" class="form-label">Rôle</label>
                                <form:select path="role" class="form-select" required="true">
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role}" ${user.role == role ? 'selected' : ''}>${role}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            
                            <div class="mb-3">
                                <label for="location" class="form-label">Localisation (pour les agents)</label>
                                <form:select path="location.id" class="form-select">
                                    <option value="">-- Aucune localisation --</option>
                                    <c:forEach items="${locations}" var="location">
                                        <option value="${location.id}" ${user.location != null && user.location.id == location.id ? 'selected' : ''}>
                                            ${location.name} (${location.service.name})
                                        </option>
                                    </c:forEach>
                                </form:select>
                                <small class="text-muted">Obligatoire pour les agents, facultatif pour les administrateurs</small>
                            </div>
                            
                            <div class="d-flex justify-content-between">
                                <a href="/admin/users" class="btn btn-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary">Enregistrer</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
