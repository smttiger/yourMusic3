<#import "parts/common.ftl" as c>
<@c.page>

    <br>
    <br>
    <#list playlistSongs as song>
        <div >
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls ></audio>
        </div>
        <br>
    <#else>
        <#list allSongs as song>
        <div >
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls ></audio>
        </div>
        <br>
<#--    <#else>-->
<#--        <h3> You have no songs in this playlist</h3>-->
    </#list>
    </#list>
</@c.page>