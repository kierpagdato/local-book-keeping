<ol class="${domainClass.decapitalizedName}">
    <g:each in="${domainProperties}" var="p">
        <li">
            <span id="${p.name}-label">
                <strong>
                    <g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" />
                </strong>
            </span>
            <div  aria-labeled="${p.name}-label">${body(p)}</div>
        </li>
    </g:each>
</ol>