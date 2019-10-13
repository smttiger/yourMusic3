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
            <input type="text" name="artist" placeholder="Enter artist">
            <button type="submit" class="btn btn-primary ml-1">Search by artist</button>
        </div>
    </form>

    <form method="get" action="/playlists/${currentUserId}/${playlist.id}/searchByName">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group row">
            <input type="text" name="name" placeholder="Enter name of the song">
            <button type="submit" class="btn btn-primary ml-1">Search by name</button>
        </div>
    </form>
    </div>
    <br>
    <#if songs??>
        <#list songs as song>
            <div class="form-row">
                <div class=" col-auto">
                    <b>${song.artist}    </b>
                </div>
                <div class="col-auto">
                    <i>${song.name}    </i>
                </div>
                <div class="col-auto">
                    <audio src="/aud/${song.filename}" controls></audio>
                </div>
                <div class="col-auto">
                    <form method="post" action="/playlists/${currentUserId}/${playlist.id}/${song.id}/add">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-success ml-1">Add</button>
                    </form>
                </div>
            </div>
            <br>
        <#else>
            <h3> Song was not found</h3>
        </#list>
    </#if>
    <br>
    <#list playlistSongs as song>
        <div class="form-row">
            <div class=" col-auto">
                <b>${song.artist}    </b>
            </div>
            <div class=" col-auto">
                <i>${song.name}    </i>
            </div>
            <div class=" col-auto">
                <audio src="/aud/${song.filename}" controls></audio>
            </div>
            <div class=" col-auto">
                <form method="post" action="/playlists/${currentUserId}/${playlist.id}/${song.id}/delete">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-danger ml-1">Delete</button>
                </form>
            </div>
        </div>
        <br>
    <#else>
        <h3> You have no songs in this playlist</h3>
    </#list>
    <#if addReport??><h5>${addReport}</h5></#if>
</@c.page>