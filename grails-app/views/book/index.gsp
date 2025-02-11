<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Book list</title>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-four-fifths">

                <g:render template="breadcrumbs" model="[active:'list']"/>

                <g:if test="${flash.message}">
                    <div class="notification is-light pt-3 pb-3">
                        <span>
                            ${flash.message}
                        </span>
                    </div>
                    <br>
                </g:if>

                <g:render template="table"/>

                <br>

                <p>Query result: ${count}</p>
                <bl:paginate controller="book" action="index" total="${count}" max="5" params="${params}"/>
            </div>
        </div>
    </body>
</html>