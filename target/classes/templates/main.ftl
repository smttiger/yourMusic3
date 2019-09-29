<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="artist" placeholder="enter artist">
            <input type="text" name="name" placeholder="enter name of song">
            <input type="file" name="file">
            <button type="submit">Upload</button>
        </form>
    </div>
    <div>List of songs</div>
    <#list songs as song>
        <div>
            <b>${song.id}</b>
            <span>${song.artist}</span>
            <i>${song.name}</i>
            <i>${song.filename}</i>
<#--            <div>-->
<#--            <#if song.filename??>-->
<#--                <img src="/aud/${song.filename}" >-->
<#--                <br>-->
<#--            </#if>-->
<#--        </div>-->
        </div>
    <#else>
        Song was not found
    </#list>
    <div>
        <form method="post" action="search">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="search" placeholder="enter artist">
            <button type="submit">Search by artist</button>
        </form>
    </div>
    <div>
        <form method="post" action="searchByName">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="searchByName" placeholder="enter name of the song">
            <button type="submit">Search by name</button>
        </form>
    </div>

    <audio src="/aud/Green day-Wake me up when september ends.mp3" controls></audio>
    <br>
    <img src="/aud/Koala.jpg">

</@c.page>
