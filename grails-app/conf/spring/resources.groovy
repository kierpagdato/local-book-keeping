import com.bookkeeping.resources.ResourceHolder

// Place your Spring DSL code here
beans = {
    dataHolder(ResourceHolder) {
        resource = 'classpath:data.json'
    }
}