<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
   <div class="mb-3"><h4>Create a new account</h4></div>
<@l.login "/registration" true />
<#if mailReport??>
<div class="container mt-5">
        <div class="alert ${alert}" role="alert">
            <h5>${mailReport}</h5>
        </div>
    </div>
    </#if>
</@c.page>