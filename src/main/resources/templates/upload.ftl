<#import "parts/common.ftl" as c>
<@c.page>
    <br>
    <br>
    <h3>Upload your favourite song and everybody will enjoy it!</h3>
    <br>
        <form method="post" enctype="multipart/form-data" action="upload" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="artist" placeholder="Enter artist">
            <input type="text" name="name" placeholder="Enter name of song">
            <input type="file" name="file" lang="en">
            <button class="btn btn-success" type="submit">Upload</button>
        </form>
<br>

    <#if uploadReport??>
        <h5>${uploadReport}</h5>
    </#if>

</@c.page>