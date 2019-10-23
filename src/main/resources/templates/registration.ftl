<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
   <div class="mb-3">Create a new account</div>
<@l.login "/registration" true />
    <br>
    <h3>${message?ifExists}</h3>
</@c.page>