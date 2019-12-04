<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "/spring.ftl" as spring/>
<@c.page>
    <#if messageSuccess??>
        <div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h3><@spring.message "messageSuccess"/></h3>
    </div>
        </div>
    </#if>
<#if messageFail??>
<div class="row mt-5">
    <div class="alert ${alert}" role="alert">
        <h3><@spring.message "messageFail"/></h3>
    </div>
</div>
</#if>

    <div class="container mt-5"><h4><@spring.message "login.text"/></h4></div>
    <div class="container mt-5">
    <@l.login "/login" false />
    </div>
    <#if Session??&&Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="row mt-5" >
        <div class="alert alert-danger" role="alert">
            <h3><@spring.message "login.incorrect"/></h3>
        </div>
            </div>
        </#if>
</@c.page>