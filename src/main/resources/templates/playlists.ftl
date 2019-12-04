<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#import "/spring.ftl" as spring/>
<@c.page>


    <form method="post" action="/playlists/${currentUserId}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" name="playlistName" placeholder="<@spring.message "playlist.place"/>" size="35"
                       class="form-control ${(nameError??)?string('is-invalid','')}"/>
            <#if nameError??>
            <div class="invalid-feedback">
                <@spring.message "playlist.name"/>
            </div>
        </#if>
</div>
</div>
<button type="submit" class="btn btn-success ml-1"><@spring.message "playlist.b1"/></button>
    </form>


<div class="container mt-5">
    <h3><@spring.message "playlist.list"/></h3>
</div>

<div class="container mt-5">
    <table class="table table-hover">
        <thead class="thead-inverse">
        <tr>
            <th><@spring.message "playlist.c1"/></th>
            <th><@spring.message "playlist.c2"/></th>
            <th><@spring.message "playlist.c2"/></th>
            <th><@spring.message "playlist.c2"/></th>
        </tr>
        </thead>
        <tbody>
        <#list playlists as playlist>
        <tr>
            <td><h5><b>${playlist.name}</b>
            </h5></td>
            <td>
                <form method="post" action="/playlists/${currentUserId}/${playlist.id}/delete">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-danger ml-1"><@spring.message "playlist.b2"/></button>
                </form>
            </td>
            <td>
                <form method="get" action="/playlists/${currentUserId}/${playlist.id}/Listen">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success ml-1"><@spring.message "playlist.b3"/></button>
                </form>
            </td>
            <td>
                <form method="get" action="/playlists/${currentUserId}/${playlist.id}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary ml-1"><@spring.message "playlist.b4"/></button>
                </form>
            </td>
        </tr>
        <#else>
        <tr>
            <td><h5><@spring.message "playlist.empty"/></h5></td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>


</@c.page>