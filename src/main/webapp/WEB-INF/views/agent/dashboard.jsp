<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord Agent</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="refresh" content="30">
</head>
<body>
<div class="container mt-4">
    <div class="row mb-4">
        <div class="col">
            <h1>Tableau de bord Agent</h1>
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

    <div class="row">
        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-header">
                    <h4>Gestion de la file d'attente</h4>
                    <p class="lead mb-0">
                        <strong>Agence:</strong> ${location.name} - ${location.service.name}
                    </p>
                </div>
                <div class="card-body text-center">
                    <h2 class="mb-4">Numéro en cours</h2>
                    <h1 class="display-1 fw-bold mb-4">${queue.currentNumber}</h1>

                    <div class="d-flex justify-content-center gap-3 mb-4">
                        <form action="/agent/queue/${queue.id}/previous" method="post">
                            <button type="submit" class="btn btn-lg btn-warning"
                            ${queue.currentNumber <= 0 ? 'disabled' : ''}>
                                Client précédent
                            </button>
                        </form>
                        <form action="/agent/queue/${queue.id}/next" method="post">
                            <button type="submit" class="btn btn-lg btn-success">
                                Client suivant
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-header">
                    <h4>Statistiques</h4>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <h5>Personnes en attente</h5>
                        <h2>${waitingCount}</h2>
                    </div>
                    <div class="mb-3">
                        <h5>Dernier numéro distribué</h5>
                        <h2>${queue.lastNumber}</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>