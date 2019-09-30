<#import "parts/common.ftl" as c>
<@c.page>
    <br>
    <br>
    <h3>Upload your favourite song and everybody will enjoy it!</h3>
    <br>
    <div>
        <form method="post" enctype="multipart/form-data" action="upload" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="artist" placeholder="Enter artist">
            <input type="text" name="name" placeholder="Enter name of song">
            <input type="file" name="file" lang="en">
            <button class="btn btn-success" type="submit">Upload</button>
        </form>
</@c.page>