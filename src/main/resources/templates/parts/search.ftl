<#import "/spring.ftl" as spring/>
<div class="container mt-5">
<form method="get" action="search" >
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group row">
        <input type="text" name="artist" placeholder="<@spring.message "search.place1"/>" size="30"
        />
        <button type="submit" class="btn btn-primary ml-1"><@spring.message "search.b1"/></button>
    </div>
</form>

<form method="get" action="searchByName" >
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group row">
        <input type="text" name="songName" placeholder="<@spring.message "search.place2"/>" size="30"
        />
        <button type="submit" class="btn btn-primary ml-1"><@spring.message "search.b2"/></button>
    </div>
</form>
    </div>