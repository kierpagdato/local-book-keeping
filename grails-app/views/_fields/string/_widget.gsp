<cin:input class="${invalid? 'is-danger':''}"
        placeholder="${label}"
        value="${value}"
        id="${property}" name="${property}"
        required="${required}"/>

<g:if test="${invalid}">
    <span class="icon is-small is-right">
      <i class="fas fa-exclamation-triangle"></i>
    </span>
</g:if>
