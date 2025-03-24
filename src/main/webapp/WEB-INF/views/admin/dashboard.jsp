<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord Administrateur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="refresh" content="60">
</head>
<body>

<jsp:include page="../includes/admin-header.jsp" />

<div class="container-fluid mt-4">
    <div class="row mb-4">
        <div class="col">
            <h1>Tableau de bord Administrateur</h1>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active" aria-current="page">Tableau de bord</li>
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
                <a href="/admin/dashboard" class="list-group-item list-group-item-action active">Tableau de bord</a>
                <a href="/admin/services" class="list-group-item list-group-item-action">Gestion des services</a>
                <a href="/admin/locations" class="list-group-item list-group-item-action">Gestion des localisations</a>
                <a href="/admin/users" class="list-group-item list-group-item-action">Gestion des utilisateurs</a>
            </div>
        </div>

        <div class="col-md-9">
            <div class="card">
                <div class="card-header">
                    <h4>État des files d'attente actives</h4>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>Service</th>
                                <th>Localisation</th>
                                <th>Numéro actuel</th>
                                <th>Personnes en attente</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${queueStatuses}" var="status">
                                <tr>
                                    <td>${status.serviceName}</td>
                                    <td>${status.locationName}</td>
                                    <td>${status.currentNumber}</td>
                                    <td>
                                        <span class="badge bg-${status.waitingCount > 10 ? 'danger' : 'info'} rounded-pill">
                                                ${status.waitingCount}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty queueStatuses}">
                                <tr>
                                    <td colspan="4" class="text-center">Aucune file d'attente active</td>
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
