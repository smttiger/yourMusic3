<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="artist" placeholder="enter artist">
            <input type="text" name="name" placeholder="enter name of song">
<#--            <input type="file" name="file">-->
            <button type="submit">Upload</button>
        </form>
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



</@c.page>
