<#include "security.ftl">
<#import "login.ftl" as l>
<#import "/spring.ftl" as spring/>
<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Your music</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <@spring.message "lang.change"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" >
                            <form >
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="hidden" name="artist" <#if artist??>value="${artist}"</#if>/>
                                <input type="hidden" name="songName" <#if songName??>value="${songName}"</#if>/>
                                <input type="hidden" name="lang"  value="en"/>
                                <button type="submit" class="btn btn-primary ml-1"><@spring.message "lang.eng"/></button>
                    </form>
                             </a>
                        <a class="dropdown-item" >
                            <form>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="hidden" name="artist"  <#if artist??>value="${artist}"</#if>/>
                                <input type="hidden" name="songName" <#if songName??>value="${songName}"</#if>/>
                                <input type="hidden" name="lang"  value="ru"/>
                                <button type="submit" class="btn btn-primary ml-1"><@spring.message "lang.ru"/></button>
                            </form>
                            </a>
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/"><@spring.message "navbar.home"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main"><@spring.message "navbar.search"/></a>
            </li>
            <#if !known>
            <li class="nav-item">
                <a class="nav-link" href="/login"><@spring.message "navbar.login"/></a>
            </li>
        </#if>
            <li class="nav-item">
                <a class="nav-link" href="/upload"><@spring.message "navbar.upload"/></a>
            </li>
            <#if known>
            <li class="nav-item">
                <a class="nav-link" href="/playlists/${currentUserId}"><@spring.message "navbar.playlist"/></a>
            </li>
            </#if>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user"><@spring.message "navbar.user"/></a>
            </li>
            </#if>
            <#if known>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile"><@spring.message "navbar.profile"/></a>
                </li>
            </#if>
        </ul>
        <span class="navbar-text mr-3">${name}</span>
        <#if known>
        <@l.logout />
        </#if>
    </div>
</nav>