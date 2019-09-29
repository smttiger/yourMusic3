<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form label">User Name</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="Enter username"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form label">Password</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Enter password"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Create account</a></#if>
        <button class="btn btn-success" type="submit"><#if isRegisterForm>Create<#else>Sign in</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-secondary" type="submit">Sign Out</button>
    </form>
</#macro>