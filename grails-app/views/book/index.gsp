<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" action="create">Create</g:link></li>
            </ul>
        </div>
        <div id="list-book" class="content scaffold-list" role="main">
            <f:table collection="${list}"
                properties="['title', 'user.name', 'status', 'dateCreated', 'lastUpdated']" />
            <g:paginate controller="book" action="index" total="${count}" max="5" />
        </div>
    </body>
</html>