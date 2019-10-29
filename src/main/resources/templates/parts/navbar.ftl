<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Your music</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Search and listen</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/upload">Upload</a>
            </li>
            <#if known>
            <li class="nav-item">
                <a class="nav-link" href="/playlists/${currentUserId}">My playlists</a>
            </li>
            </#if>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
            </#if>
            <#if known>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">My profile</a>
                </li>
            </#if>
        </ul>
        <span class="navbar-text mr-3">${name}</span>
        <#if known>
        <@l.logout />
        </#if>
    </div>
</nav>