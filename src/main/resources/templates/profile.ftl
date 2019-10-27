<#import "parts/common.ftl" as c>
<@c.page>
   <h5>${username}</h5>
    <form  method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form label">Password</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Enter password"/>
            </div>
        </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form label">Email</label>
                <div class="col-sm-5">
                    <input type="email" name="email" class="form-control" placeholder="Enter email" value="${email!''}"/>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-success" type="submit">Save</button>
    </form>
</@c.page>