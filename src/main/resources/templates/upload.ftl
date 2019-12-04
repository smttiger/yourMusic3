<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<div class="container mt-5">
    <h3><@spring.message "upload.word"/></h3>
</div>
<div class="container mt-5">
    <form method="post" enctype="multipart/form-data" action="upload">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group row">
            <div class="col-sm-3">
                <input type="text" class="form-control ${(artistError??)?string('is-invalid','')}"
                       name="artist" placeholder="<@spring.message "search.place1"/>" value="<#if song??>${song.artist}</#if>"/>
                <#if artistError??>
                <div class="invalid-feedback">
                    <@spring.message "artistError"/>
                </div>
            </#if>
        </div>
</div>
<div class="form-group row">
    <div class="col-sm-3">
        <input type="text" name="name" placeholder="<@spring.message "search.place2"/>"
               class="form-control ${(nameError??)?string('is-invalid','')}"
               value="<#if song??>${song.name}</#if>"/>
        <#if nameError??>
        <div class="invalid-feedback">
            <@spring.message "nameError"/>
        </div>
    </#if>
</div>
</div>
        <div class="form-group row">
<div class="col-sm-3">
    <input type="file"  placeholder="<@spring.message "search.file"/>"
           class="form-control ${(chosenError??||mp3Error??)?string('is-invalid','')}" name="file" accept=".mp3"/>
    <#if chosenError??>
    <div class="invalid-feedback">
        <@spring.message "chosenError"/>
    </div>
</#if>
            <#if mp3Error??>
            <div class="invalid-feedback">
                <@spring.message "mp3Error"/>
            </div>
        </#if>
</div>
    </div>
<div class="form-group">
    <button class="btn btn-success" type="submit"><@spring.message "upload.b"/></button>
</div>
</form>
</div>

<#if uploadReportSuccess??>
<div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h3> <@spring.message "uploadReportSuccess"/></h3>
    </div>
</div>
</#if>
<#if uploadReportFail??>
<div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h3> <@spring.message "uploadReportFail"/></h3>
    </div>
</div>
</#if>

</@c.page>