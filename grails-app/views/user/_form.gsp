<%@ page import="com.bookkeeping.security.Role" %>

<g:render template="form_no_auth"/>

<div class="field">
    <label class="label ${hasErrors(field: 'authorities', bean: user, 'has-text-danger')}">Roles</label>
    <div class="control ${hasErrors(field: 'authorities', bean: user, 'has-icons-right')}">
        <div class="select is-multiple ${hasErrors(field: 'authorities', bean: user, 'is-danger')} ">
            <g:select name="roles" id="roles"
                      from="${Role.list()}"
                      optionKey="id"
                      optionValue="text"
                      multiple="multiple"
                      value="${user.authorities}"/>
        </div>

        <g:hasErrors bean="${user}" field="authorities">
            <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
            </span>
        </g:hasErrors>
    </div>

    <g:hasErrors bean="${user}" field="authorities">
        <p class="help is-danger"><g:fieldError bean="${user}" field="authorities" /></p>
    </g:hasErrors>
</div>