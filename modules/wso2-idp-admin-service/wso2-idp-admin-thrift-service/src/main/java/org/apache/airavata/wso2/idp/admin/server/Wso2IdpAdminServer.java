/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

package org.apache.airavata.wso2.idp.admin.server;

import org.apache.airavata.common.utils.IServer;
import org.apache.airavata.common.utils.ServerSettings;
import org.apache.airavata.wso2.idp.admin.cpi.Wso2IdpAdminService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Date;

public class Wso2IdpAdminServer implements IServer {

    private final static Logger logger = LoggerFactory.getLogger(Wso2IdpAdminServer.class);

    private static final String SERVER_NAME = "WSo 2 IS Admin Service";
    private static final String SERVER_VERSION = "0.1";

    private ServerStatus status;
    private TServer server;

    public Wso2IdpAdminServer() {
        setStatus(ServerStatus.STOPPED);
    }

    public void updateTime() {

    }

    public Date getTime() {
        return null;
    }

    public String getName() {
        return SERVER_NAME;
    }

    public String getVersion() {
        return SERVER_VERSION;
    }

    public void start() throws Exception {

        try {
            setStatus(ServerStatus.STARTING);
            final int serverPort = Integer.parseInt(ServerSettings.getWso2IdpAdminServerPort());
            final String serverHost = ServerSettings.getWso2IdpAdminServerHost();
            Wso2IdpAdminService.Processor processor = new Wso2IdpAdminService.Processor(new Wso2IdpAdminServerHandler());

            TServerTransport serverTransport;

            if (serverHost == null) {
                serverTransport = new TServerSocket(serverPort);
            } else {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(serverHost, serverPort);
                serverTransport = new TServerSocket(inetSocketAddress);
            }
            TThreadPoolServer.Args options = new TThreadPoolServer.Args(serverTransport);
            options.minWorkerThreads = 30;
            server = new TThreadPoolServer(options.processor(processor));

            new Thread() {
                public void run() {
                    server.serve();
                    setStatus(ServerStatus.STOPPED);
                    logger.info("WSo 2 IS Admin Service Stopped.");
                }
            }.start();
            new Thread() {
                public void run() {
                    while (!server.isServing()) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    if (server.isServing()) {
                        setStatus(ServerStatus.STARTED);
                        logger.info("Starting WSo 2 IS Admin Service on Port " + serverPort);
                        logger.info("Listening to WSo 2 IS Admin Service clients ....");
                    }
                }
            }.start();
        } catch (TTransportException e) {
            setStatus(ServerStatus.FAILED);
            throw new Exception("Error while starting the WSo 2 IS Admin Service", e);
        }
    }

    public void stop() throws Exception {

        if (server != null && server.isServing()) {
            setStatus(ServerStatus.STOPING);
            server.stop();
        }
    }

    public void restart() throws Exception {

        stop();
        start();
    }

    public void configure() throws Exception {

    }

    public ServerStatus getStatus() throws Exception {
        return status;
    }

    private void setStatus(ServerStatus stat) {
        status = stat;
        status.updateTime();
    }

    public TServer getServer() {
        return server;
    }

    public void setServer(TServer server) {
        this.server = server;
    }

    public static void main(String[] args) {
        try {
            new Wso2IdpAdminServer().start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
