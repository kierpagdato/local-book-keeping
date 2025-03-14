<%@ page import="com.bookkeeping.utils.SessionUtils;com.bookkeeping.book.Book" %>
<g:set var="borrowBasket" value="${SessionUtils.getBorrowBasket(session)}"/>

<table class="table is-hoverable is-fullwidth">
    <thead>
        <tr>
            <sec:ifLoggedIn>
                <th>
                    <g:checkBox name="headerSelected"/>
                </th>
            </sec:ifLoggedIn>
            <g:sortableColumn property="id" title="ID" />
            <g:sortableColumn property="title" title="Title" />
            <g:sortableColumn property="author" title="Author" />
            <g:sortableColumn property="isbn" title="ISBN" />
            <g:sortableColumn property="status" title="Status" />
            <g:sortableColumn property="dateCreated" title="Date created" />
            <g:sortableColumn property="lastUpdated" title="Last updated" />
        </tr>
    </thead>
    <tbody>
        <g:each in="${list}" var="bean">
            <tr>
                <sec:ifLoggedIn>
                    <td>
                        <g:checkBox name="selected" class="${bean.status != Book.Status.SHELVED?: 'checkbox-select'}" value="${bean.id}"
                                    checked="${borrowBasket?.bookIds?.contains(bean.id.toString())?: 'false'}"
                                    disabled="${bean.status != Book.Status.SHELVED}"
                                    form="borrowBasketForm"/>
                    </td>
                </sec:ifLoggedIn>
                <td>
                    <g:link method="GET" resource="${bean}">
                        ${bean.id}
                    </g:link>
                </td>
                <td>
                    ${bean.title}
                </td>
                <td>
                    ${bean.author}
                </td>
                <td>
                    ${bean.isbn}
                </td>
                <td>
                    <span class="tag ${bean.status.color}">${bean.status.text}</span>
                </td>
                <td>
                    ${bean.dateCreated}
                </td>
                <td>
                    ${bean.lastUpdated}
                </td>
            </tr>
        </g:each>
    </tbody>
</table>