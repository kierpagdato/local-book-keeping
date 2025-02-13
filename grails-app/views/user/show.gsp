<!DOCTYPE html>
<html>
    <head>
        <g:set var="page" value="userList" scope="request"/>
        <meta name="layout" content="main" />
        <title>User info</title>
        <style>
        ol > li {
            list-style-type: none;
        }
        </style>
    </head>
    <body>

        <div class="columns is-mobile is-centered">

            <div class="column is-half">


                <g:render template="breadcrumbs" model="[active:'show']"/>

                <br>

                <div class="tags">
                    <span class="tag is-light">
                        <strong>Create date:</strong> ${this.user.dateCreated}
                    </span>
                    <span class="tag is-light">
                        <strong>Last modified date:</strong> ${this.user.lastUpdated}
                    </span>
                </div>

                <div class="card">
                    <header class="card-header">
                        <p class="card-header-title">User info</p>
                    </header>
                    <div class="card-content">
                        <div class="content">
                            <ol>
                                <li>
                                    <span>
                                        <strong>
                                            First name
                                        </strong>
                                    </span>
                                    <div>${user.firstName}</div>
                                </li>
                                <li>
                                    <span>
                                        <strong>
                                            Last name
                                        </strong>
                                    </span>
                                    <div>${user.lastName}</div>
                                </li>
                                <li>
                                    <span>
                                        <strong>
                                            Email
                                        </strong>
                                    </span>
                                    <div>${user.email}</div>
                                </li>
                                <li>
                                    <span>
                                        <strong>
                                            Username
                                        </strong>
                                    </span>
                                    <div>${user.username}</div>
                                </li>
                                <li>
                                    <span>
                                        <strong>
                                            Enabled
                                        </strong>
                                    </span>
                                    <div>
                                        <g:if test="${user.enabled}">
                                            <span class="icon has-text-success">
                                                <i class="fas fa-check-square"></i>
                                            </span>
                                        </g:if>
                                        <g:else>
                                            <span class="icon has-text-danger">
                                                <i class="fas fa-ban"></i>
                                            </span>
                                        </g:else>
                                    </div>
                                </li>
                                <li>
                                    <span>
                                        <strong>
                                            Roles
                                        </strong>
                                    </span>
                                    <div>
                                        <g:each in="${user.authorities}" var="auth">
                                            <ul>
                                                <li>${auth.text}</li>
                                            </ul>
                                        </g:each>
                                    </div>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
                        <g:form resource="${this.user}" method="DELETE">
                            <footer class="card-footer">
                                <g:link class="card-footer-item" action="edit" resource="${this.user}">Edit</g:link>
                                <g:submitButton name="delete" class="button is-text card-footer-item" value="Delete" />
                            </footer>
                        </g:form>
                    </sec:ifAnyGranted>
                </div>
            </div>
        </div>
    </body>
</html>