<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "/spring.ftl" as spring/>
<@c.page>

<@p.pager url page />

<div class="container mt-5">
    <table class="table table-hover">
        <thead class="thead-inverse">
        <tr>
            <th><@spring.message "userList.name"/></th>
            <th><@spring.message "userList.roles"/></th>
            <th><@spring.message "playlist.c2"/></th>
            <th><@spring.message "playlist.c2"/></th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td>
                <form action="/user/${user.id}/Edit" method="get">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success ml-1"><@spring.message "userList.edit"/></button>
                </form>
            </td>
            <td>
                <form action="/user/${user.id}/Delete" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-danger ml-1"><@spring.message "playlist.b2"/></button>
                </form>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<@p.pager url page />
</@c.page>
