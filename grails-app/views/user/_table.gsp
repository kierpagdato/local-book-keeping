<table class="table is-hoverable is-fullwidth">
    <thead>
    <tr>
        <g:sortableColumn property="id" title="ID" />
        <g:sortableColumn property="firstName" title="First Name" />
        <g:sortableColumn property="lastName" title="Last Name" />
        <g:sortableColumn property="username" title="Username" />
        <g:sortableColumn property="enabled" title="Enabled" />
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
                ${bean.firstName}
            </td>
            <td>
                ${bean.lastName}
            </td>
            <td>
                ${bean.username}
            </td>
            <td>
                <g:if test="${bean.enabled}">
                    <span class="icon has-text-success">
                        <i class="fas fa-check-square"></i>
                    </span>
                </g:if>
                <g:else>
                    <span class="icon has-text-danger">
                        <i class="fas fa-ban"></i>
                    </span>
                </g:else>
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