package com.bookkeeping.book

import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import org.mockserver.client.MockServerClient
import org.mockserver.junit.MockServerRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.http.HttpMethod
import org.testcontainers.containers.MySQLContainer
import spock.lang.Shared
import spock.lang.Specification

@Integration
class TestControllerFunctionSpec extends Specification implements GroovyObject {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt

    Integer mockServerPort
    private int grailsApplicationPort
    static MockServerClient mockServerClient
    static MySQLContainer mysqlContainer

    @Shared
    protected String jSessionId

    static {
        startMysqlContainer()

        int port = 1080
        System.setProperty("bookkeeping-mockserver-port", port.toString())
        startMockServer(port)
    }

    static startMockServer(int port){
        MockServerRule mockServer = new MockServerRule(this, port)
        mockServerClient = mockServer.clientAndServerFactory.newClientAndServer()
    }

    static void startMysqlContainer() {
        mysqlContainer = new MySQLContainer("mysql:5.7.34")

        if (!mysqlContainer.isRunning()) {
            println("*********** Starting MySQL container")
            mysqlContainer
                    .withInitScript("sql/init_db.sql")
                    .withUsername("root")
                    .withPassword("test")
                    .start()

            println("url: " + mysqlContainer.jdbcUrl)
            println("mysql: " + mysqlContainer.host + " : " + mysqlContainer.firstMappedPort)
            println("user: " + mysqlContainer.username + " : " + mysqlContainer.password)

            System.setProperty("tc_mysql_base_url", mysqlContainer.host + ":" + mysqlContainer.firstMappedPort)
            System.setProperty("tc_mysql_url", mysqlContainer.jdbcUrl + "?serverTimezone=UTC")
            System.setProperty("tc_mysql_username", mysqlContainer.username)
            System.setProperty("tc_mysql_password", mysqlContainer.password)
        }
    }

    def setup() {
        println("*********** ODRFunctionalSpec setup")
        grailsApplicationPort = webServerAppCtxt.getWebServer().getPort();
        if(mockServerClient.hasStopped() && this.mockServerPort){
            startMockServer(this.mockServerPort)
        }
    }


    def "test add to basket"() {
        expect:
            1 == 1
    }

}
