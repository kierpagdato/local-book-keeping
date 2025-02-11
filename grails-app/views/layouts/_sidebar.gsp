<aside class="menu">
    <p class="menu-label">General</p>
    <ul class="menu-list">
        <li><a>Borrow</a></li>
    </ul>

    <sec:ifAnyGranted roles="ROLE_LIBRARIAN">
        <p class="menu-label">Librarian</p>
        <ul class="menu-list">
            <li><a>Create user</a></li>
            <li><a>User masterlist</a></li>
        </ul>
    </sec:ifAnyGranted>
</aside>