<#import "parts/common.ftl" as c>

<@c.page>
    <br>
  <h3> Name: ${user.username}</h3>
    <br>
    <form action="/user/${user.id}/Save" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
<#--        <input type="text" name="Username" value="${user.username}">-->
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <button type="submit" class="btn btn-success ml-1">Save</button>
    </form>
</@c.page>