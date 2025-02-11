<aside class="menu">
    <p class="menu-label">General</p>
    <ul class="menu-list">
        <li>
            <g:link controller='book' action='index'>Books</g:link>
        </li>
        <li><a>Borrow</a></li>
    </ul>

    <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
        <p class="menu-label">Librarian</p>
        <ul class="menu-list">
            <li><a>Create user</a></li>
            <li>
                <g:link controller='user' action='index'>User masterlist</g:link>
            </li>
        </ul>
    </sec:ifAnyGranted>
</aside>