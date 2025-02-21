grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.bookkeeping.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.bookkeeping.security.UserRole'
grails.plugin.springsecurity.authority.className = 'com.bookkeeping.security.Role'

grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/account/loginSuccess'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/health/check',   access: ['permitAll']],
        [pattern: '/swagger/**',     access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]


grails.plugin.auditLog.disabled = true