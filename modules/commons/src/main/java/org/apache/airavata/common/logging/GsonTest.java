///
// Copyright (c) 2016. Highfive Technologies, Inc.
///
package org.apache.airavata.common.logging;

import com.google.gson.Gson;

import java.util.HashMap;

public class GsonTest {
    public static void main(String[] args) {
        final String[] strings = {"roles"};
        final Exception exception = new Exception("message", strings, "test");
        final LogEntry logEntry = new LogEntry(new ServerId("serverId", "serverId", "serverId", strings), "serverId", "serverId", "serverId", "serverId", new HashMap<String, String>(), "thread");
        final LogEntry logEntry1 = new LogEntry(new ServerId("serverId", "serverId", "serverId", strings), "serverId", "serverId", "serverId", "serverId", new HashMap<String, String>(), "thread", exception);

        System.out.println(new Gson().toJson(logEntry));
        System.out.println(new Gson().toJson(logEntry1));
    }
}
