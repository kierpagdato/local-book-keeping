<div class="select is-expanded ${invalid? 'is-danger':''}">
    <g:select name="${property}" id="${property}"
              from="${type.values()}"
              key="${type.values()*.name()}"
              optionValue="text"
              value="${value}"/>
</div>