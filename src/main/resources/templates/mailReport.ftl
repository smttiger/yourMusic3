<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
<#if mailReportSuccess??>
    <div class="row mt-5">
        <div class="alert ${alert}" role="alert">
            <h3><@spring.message "mailReportSuccess"/></h3>
        </div>
    </div>
        </#if>
        <#if mailReportFail??>
<div class="row mt-5">
        <div class="alert ${alert}" role="alert">
            <h3><@spring.message "mailReportFail"/></h3>
        </div>
    </div>
</#if>
</@c.page>