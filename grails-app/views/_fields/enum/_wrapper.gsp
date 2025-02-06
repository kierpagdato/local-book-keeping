<div class="field">
    <label class="label ${invalid? 'has-text-danger' : ''}">${label + (required? '*' : '')}</label>
    <div class="control">
        <div class="select is-expanded ${invalid? 'is-danger':''}">
            <g:select name="${property}" id="${property}"
                      from="${type.values()}"
                      key="${type.values()*.name()}"
                      optionValue="text"
                      value="${value}"/>
        </div>
    </div>

    <g:if test="${invalid}">
        <g:each in="${errors}" var="err">
            <p class="help is-danger">${err}</p>
        </g:each>
    </g:if>
</div>