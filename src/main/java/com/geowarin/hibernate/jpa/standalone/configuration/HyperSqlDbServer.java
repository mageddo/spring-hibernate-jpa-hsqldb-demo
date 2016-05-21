package com.geowarin.hibernate.jpa.standalone.configuration;

import java.io.IOException;
import java.util.Properties;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

public class HyperSqlDbServer implements SmartLifecycle {
    private final Logger logger = LoggerFactory.getLogger(HyperSqlDbServer.class);
    private HsqlProperties properties;
    private Server server;
    private boolean running = false;

    public HyperSqlDbServer() {

        Properties properties = new Properties();
        properties.setProperty("server.port", "33555");
        properties.setProperty("server.database.0", "file:d:/hsqldb/standalone-test");
        properties.setProperty("server.dbname.0", "testdb");
        properties.setProperty("server.remote_open", "true");
        properties.setProperty("server.silent", "true");
        this.properties = new HsqlProperties(properties);

    }
    public HyperSqlDbServer(Properties props) {
        properties = new HsqlProperties(props);
    }

    @Override
    public boolean isRunning() {
        if (server != null)
            server.checkRunning(running);
        return running;
    }

    @Override
    public void start() {
        if (server == null) {
            logger.info("Starting HSQL server...");
            server = new Server();
            try {
                server.setProperties(properties);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServerAcl.AclFormatException e) {
                e.printStackTrace();
            }
            server.start();
            System.out.println(server.getAddress());
            running = true;
        }
    }

    @Override
    public void stop() {
        logger.info("Stopping HSQL server...");
        if (server != null) {
            server.stop();
            running = false;
        }
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        stop();
        runnable.run();
    }
}