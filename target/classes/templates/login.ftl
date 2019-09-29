<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="container mt-5">Please, login</div>
    <br>
    <br>
    <br>
    <@l.login "/login" false />

</@c.page>