<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<br>
    <br>
            <form method="post" action="search" >
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group row">
                <input type="text" name="search" placeholder="Enter artist">
                <button type="submit" class="btn btn-primary ml-1">Search by artist</button>
                </div>
            </form>

            <form method="post" action="searchByName" >
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group row">
                <input type="text" name="searchByName" placeholder="Enter name of the song">
                <button type="submit" class="btn btn-primary ml-1">Search by name</button>
                </div>
            </form>
</@c.page>
