<#import "parts/common.ftl" as c>
<@c.page>
    <br>
    <br>
    <h3>List of found songs</h3>
    <br>
    <br>
    <form>
        <#list songs as song>
            <p>
            <b>${song.artist}    </b>
            <i>${song.name}    </i>
            <audio src="/aud/${song.filename}" controls></audio>
            </p>
        <#else>
            <h3> Song was not found</h3>
        </#list>
        <p>
            <button type="submit" class="btn btn-primary ml-1">Play all</button>
        </p>
    </form>
</@c.page>