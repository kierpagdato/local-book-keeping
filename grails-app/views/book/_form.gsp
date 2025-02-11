<%@ page import="com.bookkeeping.Book" %>
<div class="field">
    <label class="label ${hasErrors(field: 'title', bean: book, 'has-text-danger')}">Title</label>
    <div class="control ${hasErrors(field: 'title', bean: book, 'has-icons-right')}">
        <g:field class="input ${hasErrors(field: 'title', bean: book, 'is-danger')}" type="text" name="title" id="title" placeholder="Title" value="${book.title}"/>

        <g:hasErrors bean="${book}" field="title">
            <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
            </span>
        </g:hasErrors>
    </div>

    <g:hasErrors bean="${book}" field="title">
        <p class="help is-danger"><g:fieldError bean="${book}" field="title" /></p>
    </g:hasErrors>
</div>

<div class="field">
    <label class="label ${hasErrors(field: 'author', bean: book, 'has-text-danger')}">Author</label>
    <div class="control ${hasErrors(field: 'author', bean: book, 'has-icons-right')}">
        <g:field class="input ${hasErrors(field: 'author', bean: book, 'is-danger')}" type="text" name="author" id="author" placeholder="Author" value="${book.author}"/>

        <g:hasErrors bean="${book}" field="author">
            <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
            </span>
        </g:hasErrors>
    </div>

    <g:hasErrors bean="${book}" field="author">
        <p class="help is-danger"><g:fieldError bean="${book}" field="author" /></p>
    </g:hasErrors>
</div>

<div class="field">
    <label class="label ${hasErrors(field: 'isbn', bean: book, 'has-text-danger')}">ISBN</label>
    <div class="control ${hasErrors(field: 'isbn', bean: book, 'has-icons-right')}">
        <g:field class="input ${hasErrors(field: 'isbn', bean: book, 'is-danger')}" type="text" name="isbn" id="isbn" placeholder="ISBN" value="${book.isbn}"/>

        <g:hasErrors bean="${book}" field="isbn">
            <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
            </span>
        </g:hasErrors>
    </div>

    <g:hasErrors bean="${book}" field="isbn">
        <p class="help is-danger"><g:fieldError bean="${book}" field="isbn" /></p>
    </g:hasErrors>
</div>

<div class="field">
    <label class="label ${hasErrors(field: 'status', bean: book, 'has-text-danger')}">Status</label>
    <div class="control ${hasErrors(field: 'status', bean: book, 'has-icons-right')}">
        <div class="select is-expanded ${hasErrors(field: 'status', bean: book, 'is-danger')} ">
            <g:select name="status" id="status"
                      from="${Book.Status.values()}"
                      key="${Book.Status.values()*.name()}"
                      optionValue="text"
                      value="${book.status}"/>
        </div>

        <g:hasErrors bean="${book}" field="status">
            <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
            </span>
        </g:hasErrors>
    </div>

    <g:hasErrors bean="${book}" field="status">
        <p class="help is-danger"><g:fieldError bean="${book}" field="status" /></p>
    </g:hasErrors>
</div>

<div class="field">
    <label class="label ${hasErrors(field: 'description', bean: book, 'has-text-danger')}">Description</label>
    <div class="control ${hasErrors(field: 'description', bean: book, 'has-icons-right')}">
        <g:textArea class="textarea ${hasErrors(field: 'description', bean: book, 'is-danger')}" name="description" id="description" placeholder="Description" value="${book.description}"/>

        <g:hasErrors bean="${book}" field="description">
            <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
            </span>
        </g:hasErrors>
    </div>

    <g:hasErrors bean="${book}" field="description">
        <p class="help is-danger"><g:fieldError bean="${book}" field="description" /></p>
    </g:hasErrors>
</div>