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

                <table class="table is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="ID" />
                            <g:sortableColumn property="title" title="Title" />
                            <g:sortableColumn property="author" title="Author" />
                            <g:sortableColumn property="isbn" title="ISBN" />
                            <g:sortableColumn property="status" title="Status" />
                            <g:sortableColumn property="dateCreated" title="Date created" />
                            <g:sortableColumn property="lastUpdated" title="Last updated" />
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${list}" var="bean">
                            <tr>
                                <td>
                                    <g:link method="GET" resource="${bean}">
                                        ${bean.id}
                                    </g:link>
                                </td>
                                <td>
                                    ${bean.title}
                                </td>
                                <td>
                                    ${bean.author}
                                </td>
                                <td>
                                    ${bean.isbn}
                                </td>
                                <td>
                                    ${bean.status.text}
                                </td>
                                <td>
                                    ${bean.dateCreated}
                                </td>
                                <td>
                                    ${bean.lastUpdated}
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>

                </br>

                <p>Query result: ${count}</p>
                <bl:paginate controller="book" action="index" total="${count}" max="5" params="${params}"/>
            </div>
        </div>
    </body>
</html>