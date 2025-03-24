<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../includes/admin-header.jsp" />

<div class="container-fluid mt-4">
    <div class="row mb-4">
        <div class="col">
            <h1>Gestion des Services</h1>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/admin/dashboard">Tableau de bord</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Services</li>
                </ol>
            </nav>
        </div>
        <div class="col-auto">
            <a href="/logout" class="btn btn-outline-danger">Déconnexion</a>
        </div>
    </div>

    <div class="row mb-4">
        <div class="col-md-3">
            <div class="list-group">
                <a href="/admin/dashboard" class="list-group-item list-group-item-action">Tableau de bord</a>
                <a href="/admin/services" class="list-group-item list-group-item-action active">Gestion des services</a>
                <a href="/admin/locations" class="list-group-item list-group-item-action">Gestion des localisations</a>
                <a href="/admin/users" class="list-group-item list-group-item-action">Gestion des utilisateurs</a>
            </div>
        </div>

        <div class="col-md-9">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h4>Liste des services</h4>
                    <a href="/admin/services/add" class="btn btn-primary">Ajouter un service</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${services}" var="service">
                                <tr>
                                    <td>${service.id}</td>
                                    <td>${service.name}</td>
                                    <td>${service.description}</td>
                                    <td>
                                        <div class="btn-group btn-group-sm">
                                            <a href="/admin/services/edit/${service.id}" class="btn btn-warning">Modifier</a>
                                            <a href="/admin/services/delete/${service.id}" class="btn btn-danger"
                                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce service?')">Supprimer</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty services}">
                                <tr>
                                    <td colspan="4" class="text-center">Aucun service disponible</td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
