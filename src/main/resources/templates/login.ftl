<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>Please, login or create account</div>
    <@l.login "/login" />

<a href="/registration">Create account</a>
</@c.page>