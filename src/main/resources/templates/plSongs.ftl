<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>

    <br>
    <br>
    <h3>${playlistName}</h3>
    <br>
    <#list playlistSongs as song>
        <div >
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls ></audio>
        </div>
        <br>
    <#else>
        <h3> You have no songs in this playlist</h3>
    </#list>

</@c.page>