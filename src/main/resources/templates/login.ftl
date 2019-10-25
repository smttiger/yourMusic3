<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h3>${message?ifExists}</h3>
    <div class="container mt-5">Please, login</div>

    <div class="container mt-5">
    <@l.login "/login" false />
    </div>
</@c.page>