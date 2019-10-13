
<div class="container mt-5">
<form method="get" action="search" >
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group row">
        <input type="text" name="artist" placeholder="Enter artist">
        <button type="submit" class="btn btn-primary ml-1">Search by artist</button>
    </div>
</form>

<form method="get" action="searchByName" >
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group row">
        <input type="text" name="name" placeholder="Enter name of the song">
        <button type="submit" class="btn btn-primary ml-1">Search by name</button>
    </div>
</form>
    </div>