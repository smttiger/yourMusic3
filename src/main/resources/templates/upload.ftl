<#import "parts/common.ftl" as c>
<@c.page>

    <div class="container mt-5">
    <h3>Upload your favourite song and everybody will enjoy it!</h3>
    </div>
<div class="form-group mt-5">
        <form method="post" enctype="multipart/form-data" action="upload" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
            <input type="text" class="form-control ${(artistError??)?string('is-invalid','')}"
             name="artist" placeholder="Enter artist" value="<#if song??>${song.artist}</#if>"/>
            <#if artistError??>
            <div class="invalid-feedback">
            ${artistError}
            </div>
            </#if>
            </div>
            <div class="form-group">
            <input type="text" name="name" placeholder="Enter name of song" class="form-control ${(nameError??)?string('is-invalid','')}"
            value="<#if song??>${song.name}</#if>"/>
            <#if nameError??>
                        <div class="invalid-feedback">
                        ${nameError}
                        </div>
                        </#if>
            </div>
            <div class="form-group">
            <input type="file" class="form-control ${(Error??)?string('is-invalid','')}" name="file" accept=".mp3">
                           <#if Error??>
                                                    <div class="invalid-feedback">
                                                    ${Error}
                                                    </div>
                                                    </#if>
                                                    </div>
            <div class="form-group">
            <button class="btn btn-success" type="submit">Upload</button>
            </div>
        </form>
</div>

<#if uploadReport??>
<div class="container mt-5">
        <div class="alert ${alert}" role="alert">
           <h3> ${uploadReport}</h3>
        </div>
    </div>
    </#if>
</@c.page>