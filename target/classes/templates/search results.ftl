<#import "parts/common.ftl" as c>
<@c.page>
    <br>
    <br>
    <h3>List of found songs</h3>
    <br>
    <br>
            <#list songs as song>
            <p>
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls></audio>
            </p>
        <#else>
            <h3> Song was not found</h3>
        </#list>
        <br>

</@c.page>