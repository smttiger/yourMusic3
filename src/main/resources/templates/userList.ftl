<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container mt-5">
    <table class="table table-hover">
        <thead class="thead-inverse">
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>Action</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td>
                    <form action="/user/${user.id}/Edit" method="get">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-success ml-1">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="/user/${user.id}/Delete" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger ml-1">Delete</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>
</@c.page>