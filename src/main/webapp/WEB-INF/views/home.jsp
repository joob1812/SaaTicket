<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File d'attente - Accueil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header text-center">
                    <h2>Bienvenue au système de gestion de file d'attente</h2>
                    <p>Veuillez sélectionner un service</p>
                </div>
                <div class="card-body">
                    <div class="row row-cols-1 row-cols-md-2 g-4">
                        <c:forEach items="${services}" var="service">
                            <div class="col">
                                <div class="card h-100 text-center">
                                    <div class="card-body">
                                        <h5 class="card-title">${service.name}</h5>
                                        <p class="card-text">${service.description}</p>
                                        <a href="/service/${service.id}" class="btn btn-primary">Sélectionner</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="card-footer text-center">
                    <a href="/login" class="btn btn-outline-secondary">Connexion Admin/Agent</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
