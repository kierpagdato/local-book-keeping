<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <g:render template="breadcrumbs" model="[active:'list']"/>

                <f:table collection="${list}"
                    properties="${fieldProperties}" />

                </br>

                <p>Query result: ${count}</p>
                <bl:paginate controller="book" action="index" total="${count}" max="5" params="${params}"/>
            </div>
        </div>
    </body>
</html>