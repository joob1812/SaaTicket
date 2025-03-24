<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Votre Ticket</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="refresh" content="30">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center bg-primary text-white">
                    <h2>Votre E-Ticket</h2>
                </div>
                <div class="card-body text-center">
                    <div class="mb-4">
                        <h3>Votre numéro de ticket est</h3>
                        <h1 class="display-1 fw-bold">${ticketInfo.ticketNumber}</h1>
                    </div>

                    <div class="alert alert-info mb-4">
                        <h4>Vous êtes à la ${ticketInfo.position}ème position</h4>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Personnes devant vous</h5>
                                    <p class="card-text fs-1">${ticketInfo.peopleAhead}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Numéro en cours</h5>
                                    <p class="card-text fs-1">${ticketInfo.currentNumber}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer text-center">
                    <p class="text-muted">Cette page se rafraîchit automatiquement toutes les 30 secondes</p>
                    <a href="/" class="btn btn-outline-secondary">Retour à l'accueil</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>