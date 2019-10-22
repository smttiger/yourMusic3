<#import "parts/common.ftl" as c>
<@c.page>

    <div class="container mt-5">
    <h3>List of found songs</h3>
    </div>

    <div class="container mt-5">
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
</div>
</@c.page>