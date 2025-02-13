<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="userList" scope="request"/>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="columns is-mobile is-centered">

            <div class="column is-four-fifths">


                <g:render template="breadcrumbs" model="[active:'list']"/>

                <br>

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

                <bl:paginate controller="user" action="index" total="${count}" max="5" params="${params}"/>
            </div>
        </div>

    </body>
</html>