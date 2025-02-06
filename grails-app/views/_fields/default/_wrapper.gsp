<div class="field">
    <label class="label ${invalid? 'has-text-danger' : ''}">${label + (required? ' *' : '')}</label>
    <div class="control ${invalid? 'has-icons-right' : ''}">
        ${widget}
    </div>

    <g:if test="${invalid}">
        <g:each in="${errors}" var="err">
            <p class="help is-danger">${err}</p>
        </g:each>
    </g:if>
</div>