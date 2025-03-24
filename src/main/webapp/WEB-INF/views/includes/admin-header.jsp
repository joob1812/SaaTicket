<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="mb-4">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">
                <i class="bi bi-people-fill"></i> SaaTicket Admin
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link ${requestScope['javax.servlet.forward.servlet_path'] == '/admin/dashboard' ? 'active' : ''}"
                           href="/admin/dashboard">
                            <i class="bi bi-speedometer2"></i> Tableau de bord
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${requestScope['javax.servlet.forward.servlet_path'] == '/admin/services' ? 'active' : ''}"
                           href="/admin/services">
                            <i class="bi bi-grid"></i> Services
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${requestScope['javax.servlet.forward.servlet_path'] == '/admin/locations' ? 'active' : ''}"
                           href="/admin/locations">
                            <i class="bi bi-geo-alt"></i> Localisations
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${requestScope['javax.servlet.forward.servlet_path'] == '/admin/users' ? 'active' : ''}"
                           href="/admin/users">
                            <i class="bi bi-person"></i> Utilisateurs
                        </a>
                    </li>
                </ul>
                <div class="d-flex">
                    <span class="navbar-text me-3">
                        <i class="bi bi-person-circle"></i> ${pageContext.request.userPrincipal.name}
                    </span>
                    <a href="/logout" class="btn btn-outline-light btn-sm">
                        <i class="bi bi-box-arrow-right"></i> Déconnexion
                    </a>
                </div>
            </div>
        </div>
    </nav>
</header>
