<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
   <div class="mb-3"><h4>Create a new account</h4></div>
<@l.login "/registration" true />

<#--    <div class="container mt-5">-->
<#--        <div class="alert alert-primary" role="alert">-->
<#--            <h5>${message}</h5>-->
<#--        </div>-->
<#--    </div>-->

</@c.page>