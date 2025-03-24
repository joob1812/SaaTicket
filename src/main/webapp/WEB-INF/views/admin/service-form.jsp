<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3>${service.id == null ? 'Ajouter un service' : 'Modifier le service'}</h3>
                </div>
                <div class="card-body">
                    <form:form action="/admin/services/save" method="post" modelAttribute="service">
                        <form:hidden path="id" />

                        <div class="mb-3">
                            <label for="name" class="form-label">Nom du service</label>
                            <form:input path="name" class="form-control" required="true" />
                        </div>

                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <form:textarea path="description" class="form-control" rows="3" />
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="/admin/services" class="btn btn-secondary">Annuler</a>
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