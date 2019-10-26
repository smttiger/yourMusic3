<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <#if message??>
        <div class="container mt-5">
    <div class="alert alert-primary" role="alert">
        <h3>${message}</h3>
    </div>
        </div>
    </#if>
    <div class="container mt-5"><h4>Please, login</h4></div>
    <div class="container mt-5">
    <@l.login "/login" false />
    </div>
</@c.page>