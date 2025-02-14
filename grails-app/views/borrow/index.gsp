<!DOCTYPE html>
<html>
<head>
    <g:set var="page" value="borrow" scope="request"/>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="columns is-mobile is-centered">

    <div class="column is-full">

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
        <bl:paginate controller="borrow" action="index" total="${count}" max="5" params="${params}"/>
    </div>
</div>

</body>
</html>