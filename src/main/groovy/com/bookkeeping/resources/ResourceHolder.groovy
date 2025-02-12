package com.bookkeeping.resources

import org.springframework.core.io.Resource

/**
 * Hold a resource from classpath for future usage
 * Can be used as bean and initialize on start up
 */
class ResourceHolder {

    Resource resource;
}
