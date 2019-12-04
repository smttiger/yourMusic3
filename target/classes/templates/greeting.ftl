<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<div class="container mt-5">
<h1><@spring.message "greeting"/></h1>
</div>

<div class="container mt-5">
    <h3><@spring.message "greeting.words"/></h3>
</div>
</@c.page>