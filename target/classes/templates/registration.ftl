<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "/spring.ftl" as spring/>
<@c.page>
   <div class="mb-3"><h4><@spring.message "newAccount"/></h4></div>
<@l.login "/registration" true />
<#if mailReport??>
<div class="row mt-5">
        <div class="alert ${alert}" role="alert">
            <h5>${mailReport}</h5>
        </div>
    </div>
    </#if>
</@c.page>