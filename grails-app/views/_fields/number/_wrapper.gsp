<div class="field">
    <label class="label ${invalid? 'has-text-danger' : ''}">${label + (required? '*' : '')}</label>
    <div class="control ${invalid? 'has-icons-right' : ''}">
        <input class="input ${invalid? 'is-danger':''}"
                type="number" placeholder="${label}"
                value="${value}"
                id="${property}" name="${property}"
                ${required? 'required': ''}>

            <g:if test="${invalid}">
                <span class="icon is-small is-right">
                  <i class="fas fa-exclamation-triangle"></i>
                </span>
            </g:if>
    </div>

    <g:if test="${invalid}">
        <g:each in="${errors}" var="err">
            <p class="help is-danger">${err}</p>
        </g:each>
    </g:if>
</div>