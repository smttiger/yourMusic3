<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>

    <div class="container mt-5">
    <form method="post" action="/playlists/${currentUserId}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group row">
            <input type="text" name="playlistName" placeholder="Enter name of playlist" size="35"
            class="form-control ${(nameError??)?string('is-invalid','')}"/>

            <#if nameError??>
                        <div class="invalid-feedback">
                        ${nameError}
                        </div>
                        </#if>
                        </div>
            <button type="submit" class="btn btn-success ml-1">Create</button>
        </div>
    </form>
    </div>

    <div class="container mt-5">
    <h3>List of your playlists:</h3>
    </div>

<div class="container mt-5">
    <table class="table table-hover">
        <thead class="thead-inverse">
        <tr>
            <th>Name of the playlist</th>
            <th>Action</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <#list playlists as playlist>
        <tr>
            <td><h5><b><a class="alert-link" href="/playlists/${currentUserId}/${playlist.id}">${playlist.name}</a></b></h5></td>
            <td>
                <form method="post" action="/playlists/${currentUserId}/${playlist.id}/delete">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-danger ml-1">Delete</button>
                </form>
            </td>
            <td>
                <form method="get" action="/playlists/${currentUserId}/${playlist.id}/Listen">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success ml-1">Listen</button>
                </form>
            </td>
        </tr>
        <#else>
        <tr>
            <td><h5> You have no playlists</h5></td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>




</@c.page>