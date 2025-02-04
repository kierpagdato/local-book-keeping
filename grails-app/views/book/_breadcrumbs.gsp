<nav class="breadcrumb is-centered" aria-label="breadcrumbs">
  <ul>
    <li class="${active == 'list'?'is-active':''}">
        <g:link class="${active == 'list'?'has-text-black':''}" action="index">Book list</g:link>
    </li>
    <li class="${active == 'add'?'is-active':''}">
        <g:link class="${active == 'add'?'has-text-black':''}" action="create">Add book</g:link>
    </li>
    <g:if test="${active == 'show'}">
        <li class="${active == 'show'?'is-active':''}">
            <g:link class="${active == 'show'?'has-text-black':''}" action="create">Book info</g:link>
        </li>
    </g:if>
  </ul>
</nav>