<nav class="breadcrumb is-centered" aria-label="breadcrumbs">
  <ul>
    <li class="${active == 'list'?'is-active':''}">
        <g:link class="${active == 'list'?'has-text-black':''}" action="index">Borrow list</g:link>
    </li>
    <g:if test="${active == 'receipt'}">
        <li class="${active == 'receipt'?'is-active':''}">
            <g:link class="${active == 'receipt'?'has-text-black':''}">Receipt</g:link>
        </li>
    </g:if>
  </ul>
</nav>