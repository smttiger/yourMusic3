<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>

    <br>
    <br>
    <h3>${playlist.name}</h3>
    <br>

    <form method="post" action="/playlists/${currentUserId}/${playlist.id}/search" >
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group row">
            <input type="text" name="artist" placeholder="Enter artist">
            <button type="submit" class="btn btn-primary ml-1">Search by artist</button>
        </div>
    </form>

    <form method="post" action="/playlists/${currentUserId}/${playlist.id}/searchByName" >
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group row">
            <input type="text" name="name" placeholder="Enter name of the song">
            <button type="submit" class="btn btn-primary ml-1">Search by name</button>
        </div>
    </form>
    <br>
    <#if songs??>
    <#list songs as song>
        <div>
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls></audio>
                <form method="post" action="/playlists/${currentUserId}/${playlist.id}/${song.id}/add" >
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-success ml-1">Add</button>
                </form>
        </div>
    <#else>
        <h3> Song was not found</h3>
    </#list>
    </#if>
    <br>
    <#list playlistSongs as song>
        <div >
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls ></audio>
            <form method="post" action="/playlists/${currentUserId}/${playlist.id}/${song.id}/delete" >
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-danger ml-1">Delete</button>
            </form>
        </div>
        <br>
    <#else>
        <h3> You have no songs in this playlist</h3>
    </#list>
<#if addReport??><h5>${addReport}</h5></#if>
</@c.page>