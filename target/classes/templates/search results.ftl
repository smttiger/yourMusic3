<#import "parts/common.ftl" as c>
<@c.page>
<br>
    <br>
    <h3>List of found songs</h3>
    <br>
    <br>
    <#list songs as song>
        <div >
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls ></audio>
        </div>
        <br>
    <#else>
        <h3> Song was not found</h3>
    </#list>
</@c.page>