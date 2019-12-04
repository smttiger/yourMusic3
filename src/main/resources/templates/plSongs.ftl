<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
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
                    <button type="submit" class="btn btn-success ml-1"><@spring.message "playlist.b3"/></button>
                </form>
            </div>
        </div>
    </div>

    <div class="container mt-5">
        <form method="get" action="/playlists/${currentUserId}/${playlist.id}/search">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group row">
                <input type="text" name="artist" placeholder="<@spring.message "search.place1"/>" size="30"/>
                <button type="submit" class="btn btn-primary ml-1"><@spring.message "search.b1"/></button>
            </div>
        </form>

        <form method="get" action="/playlists/${currentUserId}/${playlist.id}/searchByName" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group row">
                <input type="text" name="songName" placeholder="<@spring.message "search.place2"/>" size="30"
                />
                <button type="submit" class="btn btn-primary ml-1"><@spring.message "search.b2"/></button>
            </div>
        </form>

    </div>

    <#if songs??>

        <div class="container mt-5">
            <table class="table table-hover">
                <thead class="thead-inverse">
                <tr>
                    <th><@spring.message "plSongs.artist"/></th>
                    <th><@spring.message "plSongs.name"/></th>
                    <th><@spring.message "plSongs.audio"/></th>
                    <th><@spring.message "playlist.c2"/></th>
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
                                <#if artist??><input type="hidden" name="artist" value="${artist}"/></#if>
                            <#if songName??><input type="hidden" name="songName" value="${songName}"/></#if>
                                <button type="submit" class="btn btn-success ml-1"><@spring.message "plSongs.b3"/></button>
                            </form>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        <td><h3><@spring.message "plSongs.notFound"/></h3></td>
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
                <th><@spring.message "plSongs.artist"/></th>
                <th><@spring.message "plSongs.name"/></th>
                <th><@spring.message "plSongs.audio"/></th>
                <th><@spring.message "playlist.c2"/></th>
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
                        <#if artist??><input type="hidden" name="artist" value="${artist}"/></#if>
                    <#if songName??><input type="hidden" name="songName" value="${songName}"/></#if>
                    <button type="submit" class="btn btn-danger ml-1"><@spring.message "playlist.b2"/></button>
                    </form>
                </td>
            </tr>
            <#else>
            <tr>
                <td><h3><@spring.message "plSongs.empty"/></h3>
                    </#list>
            </tbody>
        </table>
    </div>

</@c.page>