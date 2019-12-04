<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
<h5>${username}</h5>
<div class="container mt-5">
    <form method="post" action="/user/profile/savePassword">
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="<@spring.message "profile.place1"/>"/>
                <#if passwordError??>
                <div class="invalid-feedback">
                    <@spring.message "passwordError"/>
                </div>
            </#if>
        </div>
</div>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<button class="btn btn-success" type="submit"><@spring.message "profile.b1"/></button>
</form>
</div>

<div class="container mt-5">
    <form method="post" action="/user/profile/saveEmail">
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" name="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                       placeholder="<@spring.message "profile.place2"/>"
                       value="${email!''}"/>
            <#if emailError??>
            <div class="invalid-feedback">
                <@spring.message "emailError"/>
            </div>
        </#if>
</div></div>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<button class="btn btn-success" type="submit"><@spring.message "profile.b2"/></button>
</form>
</div>


    <#if mailReportSuccess??>
<div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h3><@spring.message "mailReportSuccess"/></h3>
    </div>
</div>
</#if>

    <#if mailReportFail??>
<div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h3><@spring.message "mailReportFail"/></h3>
    </div>
</div>
</#if>
</@c.page>