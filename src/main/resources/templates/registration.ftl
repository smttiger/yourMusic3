<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
   <div class="mb-3"><h4>Create a new account</h4></div>
<@l.login "/registration" true />

</@c.page>