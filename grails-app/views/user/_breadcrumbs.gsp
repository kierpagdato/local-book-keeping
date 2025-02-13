<nav class="breadcrumb is-centered" aria-label="breadcrumbs">
  <ul>
    <li class="${active == 'list'?'is-active':''}">
        <g:link class="${active == 'list'?'has-text-black':''}" action="index">User masterlist</g:link>
    </li>
    <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
        <li class="${active == 'add'?'is-active':''}">
            <g:link class="${active == 'add'?'has-text-black':''}" action="create">Create user</g:link>
        </li>
    </sec:ifAnyGranted>
    <g:if test="${active == 'show'}">
        <li class="${active == 'show'?'is-active':''}">
            <g:link class="${active == 'show'?'has-text-black':''}" action="create">User info</g:link>
        </li>
    </g:if>
  </ul>
</nav>