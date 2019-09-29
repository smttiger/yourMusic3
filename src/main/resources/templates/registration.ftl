<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Create account

<@l.login "/registration" />
    <br>
    ${message?ifExists}
</@c.page>