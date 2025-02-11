<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>My Details</title>
    </head>
    <body>
        <div class="columns is-mobile is-centered">

            <div class="column is-half">

                <g:if test='${flash.message}'>
                    <div class="notification is-light pt-3 pb-3">
                        <span>
                            ${flash.message}
                        </span>
                    </div>
                    <br>
                </g:if>

                <div class="tags">
                    <span class="tag is-light">
                        <strong>Create date:</strong> ${this.user.dateCreated}
                    </span>
                    <span class="tag is-light">
                        <strong>Last modified date:</strong> ${this.user.lastUpdated}
                    </span>
                </div>

                <g:form controller="account" action="update" id="${user.id}" method="PUT">

                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">My details</p>
                        </header>
                        <div class="card-content">
                            <div class="content">

                                <div class="field">
                                    <label class="label ${hasErrors(field: 'firstName', bean: user, 'has-text-danger')}">First name</label>
                                    <div class="control ${hasErrors(field: 'firstName', bean: user, 'has-icons-right')}">
                                        <g:field class="input ${hasErrors(field: 'firstName', bean: user, 'is-danger')}" type="text"
                                                 name="firstName" id="firstName" placeholder="First name" value="${user.firstName}"/>

                                        <g:hasErrors bean="${user}" field="firstName">
                                            <span class="icon is-small is-right">
                                                <i class="fas fa-exclamation-triangle"></i>
                                            </span>
                                        </g:hasErrors>
                                    </div>

                                    <g:hasErrors bean="${user}" field="firstName">
                                        <p class="help is-danger">${book.errors.getFieldErrors("firstName")}</p>
                                    </g:hasErrors>
                                </div>

                                <div class="field">
                                    <label class="label ${hasErrors(field: 'lastName', bean: user, 'has-text-danger')}">Last name</label>
                                    <div class="control ${hasErrors(field: 'lastName', bean: user, 'has-icons-right')}">
                                        <g:field class="input ${hasErrors(field: 'lastName', bean: user, 'is-danger')}" type="text"
                                                 name="lastName" id="lastName" placeholder="First name" value="${user.lastName}"/>

                                        <g:hasErrors bean="${user}" field="lastName">
                                            <span class="icon is-small is-right">
                                                <i class="fas fa-exclamation-triangle"></i>
                                            </span>
                                        </g:hasErrors>
                                    </div>

                                    <g:hasErrors bean="${user}" field="lastName">
                                        <p class="help is-danger">${book.errors.getFieldErrors("lastName")}</p>
                                    </g:hasErrors>
                                </div>

                                <div class="field">
                                    <label class="label ${hasErrors(field: 'email', bean: user, 'has-text-danger')}">Email</label>
                                    <div class="control ${hasErrors(field: 'email', bean: user, 'has-icons-right')}">
                                        <g:field class="input ${hasErrors(field: 'email', bean: user, 'is-danger')}" type="text"
                                                 name="email" id="email" placeholder="Email" value="${user.email}"/>

                                        <g:hasErrors bean="${user}" field="email">
                                            <span class="icon is-small is-right">
                                                <i class="fas fa-exclamation-triangle"></i>
                                            </span>
                                        </g:hasErrors>
                                    </div>

                                    <g:hasErrors bean="${user}" field="email">
                                        <p class="help is-danger">${book.errors.getFieldErrors("email")}</p>
                                    </g:hasErrors>
                                </div>

                                <div class="field">
                                    <label class="label ${hasErrors(field: 'username', bean: user, 'has-text-danger')}">Username</label>
                                    <div class="control ${hasErrors(field: 'username', bean: user, 'has-icons-right')}">
                                        <g:field class="input ${hasErrors(field: 'username', bean: user, 'is-danger')}" type="text" name="username" id="username" placeholder="Username" value="${user.username}" readonly="readonly" />

                                        <g:hasErrors bean="${user}" field="username">
                                            <span class="icon is-small is-right">
                                                <i class="fas fa-exclamation-triangle"></i>
                                            </span>
                                        </g:hasErrors>
                                    </div>

                                    <g:hasErrors bean="${user}" field="username">
                                        <p class="help is-danger">${book.errors.getFieldErrors("username")}</p>
                                    </g:hasErrors>
                                </div>

                            </div>
                        </div>
                        <footer class="card-footer">
                            <g:submitButton name="updateMyDetails" class="button is-text card-footer-item" value="Update" />
                        </footer>
                    </div>
                </g:form>
            </div>
        </div>
    </body>
</html>
