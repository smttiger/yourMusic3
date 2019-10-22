<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>


    <div class="container mt-5">
        <div class="form-row">
            <div class=" col-auto">
                <h3>${playlist.name}</h3>
            </div>
            <div class="col-auto">
                <form method="get" action="/playlists/${currentUserId}/${playlist.id}/Listen">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success ml-1">Listen</button>
                </form>
            </div>
        </div>
    </div>

    <div class="container mt-5">
        <form method="get" action="/playlists/${currentUserId}/${playlist.id}/search">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group row">
                <input type="text" name="artist" placeholder="Enter artist" size="50">
                <button type="submit" class="btn btn-primary ml-1">Search by artist</button>
            </div>
        </form>

        <form method="get" action="/playlists/${currentUserId}/${playlist.id}/searchByName">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group row">
                <input type="text" name="name" placeholder="Enter name of the song" size="50">
                <button type="submit" class="btn btn-primary ml-1">Search by name</button>
            </div>
        </form>
    </div>

    <#if songs??>
        <div class="container mt-5">
            <table class="table table-hover">
                <thead class="thead-inverse">
                <tr>
                    <th>Artist</th>
                    <th>Name of the song</th>
                    <th>Audio</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <#list songs as song>
                    <tr>
                        <td>${song.artist}</td>
                        <td>${song.name}</td>
                        <td>
                            <audio src="/aud/${song.filename}" controls></audio>
                        </td>
                        <td>
                            <form method="post" action="/playlists/${currentUserId}/${playlist.id}/${song.id}/add">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-success ml-1">Add</button>
                            </form>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        <td><h3> Song was not found</h3></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#if>

    <div class="container mt-5">
        <table class="table table-hover">
            <thead class="thead-inverse">
            <tr>
                <th>Artist</th>
                <th>Name of the song</th>
                <th>Audio</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list playlistSongs as song>
            <tr>
                <td>${song.artist}</td>
                <td>${song.name}</td>
                <td>
                    <audio src="/aud/${song.filename}" controls></audio>
                </td>
                <td>
                    <form method="post" action="/playlists/${currentUserId}/${playlist.id}/${song.id}/delete">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger ml-1">Delete</button>
                    </form>
                </td>
            </tr>
            <#else>
            <tr>
                <td><h3> You have no songs in this playlist</h3>
                    </#list>
            </tbody>
        </table>
    </div>
</@c.page>