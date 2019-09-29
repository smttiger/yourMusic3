<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <@l.logout />
</div>
    <div>List of songs</div>
    <#list songs as song>
        <div>
            <b>${song.artist}</b>
            <i>${song.name}</i>
            <audio src="/aud/${song.filename}" controls ></audio>
        </div>
        <br>
    <#else>
        Song was not found
    </#list>
    <div>
        <a href="/main">Main page</a>
</@c.page>