<#macro login path isRegisterForm>
<#import "/spring.ftl" as spring/>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form label"><@spring.message "login.username"/></label>
            <div class="col-sm-2">
                <input type="text" name="username"
                class="form-control ${(usernameError??||usernameExistsError??)?string('is-invalid','')}" placeholder="<@spring.message "login.place1"/>"
                value="<#if user??>${user.username}</#if>"/>
                <#if usernameError??>
                            <div class="invalid-feedback">
                                <@spring.message "username.empty"/>
                            </div>
                            </#if>
        <#if usernameExistsError??>
        <div class="invalid-feedback">
            <@spring.message "username.exists"/>
        </div>
    </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form label"><@spring.message "login.password"/></label>
            <div class="col-sm-2">
                <input type="password" name="password"
                class="form-control ${(passwordError??)?string('is-invalid','')}" placeholder="<@spring.message "login.place2"/>"
                value="<#if user??>${user.password}</#if>"/>
                <#if passwordError??>
                                            <div class="invalid-feedback">
                                                <@spring.message "passwordError"/>
                                            </div>
                                            </#if>
             </div>
         </div>

<#if isRegisterForm>
<div class="form-group row">
        <label class="col-sm-2 col-form label"><@spring.message "login.password"/></label>
        <div class="col-sm-2">
            <input type="password" name="password2"
            class="form-control ${(password2Error??||passwordDiffError??)?string('is-invalid','')}" placeholder="<@spring.message "login.place3"/>"
            value="<#if passwordConf??>${passwordConf}</#if>"/>
            <#if password2Error??>
                                                        <div class="invalid-feedback">
                                                            <@spring.message "password2Error"/>
                                                        </div>
                                                        </#if>
<#if passwordDiffError??>
<div class="invalid-feedback">
    <@spring.message "passwordDiffError"/>
</div>
</#if>
                                        </div>
        </div>

    <div class="form-group row">
    <label class="col-sm-2 col-form label"><@spring.message "login.email"/></label>
    <div class="col-sm-2">
        <input type="text" name="email"
        class="form-control ${(emailError??)?string('is-invalid','')}" placeholder="<@spring.message "login.place4"/>"
        value="<#if user??>${user.email}</#if>"/>
        <#if emailError??>
                                                    <div class="invalid-feedback">
                                                        <@spring.message "emailError"/>
                                                    </div>
                                                    </#if>
                                    </div>
    </div>
</#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration"><@spring.message "login.create"/></a></#if>
        <button class="btn btn-success" type="submit"><#if isRegisterForm><@spring.message "login.b1"/><#else><@spring.message "navbar.login"/></#if></button>
    </form>
</#macro>

<#macro logout>
<#import "/spring.ftl" as spring/>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-secondary" type="submit"><@spring.message "navbar.logout"/></button>
    </form>
</#macro>