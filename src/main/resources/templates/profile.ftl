<#import "parts/common.ftl" as c>
<@c.page>
<h5>${username}</h5>
<div class="container mt-5">
    <form method="post" action="/user/profile/savePassword">
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="Enter new password"/>
                <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
</div>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<button class="btn btn-success" type="submit">Save new password</button>
</form>
</div>

<div class="container mt-5">
    <form method="post" action="/user/profile/saveEmail">
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" name="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                       placeholder="Enter new email"
                       value="${email!''}"/>
            <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
        </#if>
</div></div>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<button class="btn btn-success" type="submit">Save new email</button>
</form>
</div>

<#if passwordReport??>
<div class="row mt-5">
    <div class="alert alert-success" role="alert">
        <h5>${passwordReport}</h5>
    </div>
</div>
</#if>
<#if mailReport??>
<div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h5>${mailReport}</h5>
    </div>
</div>
</#if>
</@c.page>