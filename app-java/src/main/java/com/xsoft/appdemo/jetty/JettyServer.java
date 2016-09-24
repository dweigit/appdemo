//package com.xsoft.appdemo.jetty;
//
//
//import org.apache.commons.lang3.StringUtils;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.webapp.WebAppContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//
//
//public class JettyServer {
//
//    private static final Logger LOG = LoggerFactory.getLogger(JettyServer.class);
//
//    private static final String DEFAULT_CHARSET = "UTF-8";
//
//    private String contextPath;
//
//    private String webappPath;
//
//    private InetSocketAddress address;
//
//    private Server server;
//
//    private WebAppContext webapp;
//
//    private int minThreads;
//
//    private int maxThreads;
//
//    public static void main(String[] args) throws Exception {
//        String contextPath = System.getProperty("cooas.jetty.contextPath");
//        if (StringUtils.isBlank(contextPath)) {
//            contextPath = COOASProperties.getProperty("cooas.jetty.contextPath");
//        }
//
//        String webappPath = System.getProperty("cooas.jetty.webappPath");
//        if (StringUtils.isBlank(webappPath)) {
//            webappPath = COOASProperties.getProperty("cooas.jetty.webappPath");
//        }
//
//        String host = System.getProperty("cooas.jetty.host");
//        if (StringUtils.isBlank(host)) {
//            host = COOASProperties.getProperty("cooas.jetty.host");
//        }
//
//        String portStr = System.getProperty("cooas.jetty.port");
//        if (StringUtils.isBlank(portStr)) {
//            portStr = COOASProperties.getProperty("cooas.jetty.port");
//        }
//        if (StringUtils.isBlank(portStr)) {
//            throw new IllegalArgumentException("cooas.jetty.port is required");
//        }
//        int port = 0;
//        try {
//            port = Integer.parseInt(portStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException("cooas.jetty.port should be an integer between 1 and 65535");
//        }
//
//        int minThreads = 10;
//        int maxThreads = 300;
//        String minThreadsStr = COOASProperties.getProperty("cooas.jetty.minThreads");
//        String maxThreadsStr = COOASProperties.getProperty("cooas.jetty.maxThreads");
//        if (StringUtils.isNotBlank(minThreadsStr)) {
//            minThreads = Integer.parseInt(minThreadsStr);
//        }
//        if (StringUtils.isNotBlank(maxThreadsStr)) {
//            maxThreads = Integer.parseInt(maxThreadsStr);
//        }
//
//        JettyServer server = null;
//        if (StringUtils.isBlank(host)) {
//            server = new JettyServer(contextPath, webappPath, port, minThreads, maxThreads);
//        } else {
//            server = new JettyServer(contextPath, webappPath, host, port, minThreads, maxThreads);
//        }
//        server.start();
//    }
//
//    public JettyServer(String contextPath, String webappPath, int port, int minThreads, int maxThreads) {
//        this(contextPath, webappPath, new InetSocketAddress(port), minThreads, maxThreads);
//    }
//
//    public JettyServer(String contextPath, String webappPath, String hostname, int port, int minThreads, int maxThreads) {
//        this(contextPath, webappPath, new InetSocketAddress(hostname, port), minThreads, maxThreads);
//    }
//
//    public JettyServer(String contextPath, String webappPath, InetSocketAddress address, int minThreads, int maxThreads) {
//        if (StringUtils.isBlank(contextPath)) {
//            contextPath = "";
//        }
//        this.contextPath = contextPath;
//        if (StringUtils.isBlank(webappPath)) {
//            throw new IllegalArgumentException("webappPath is required");
//        }
//        this.webappPath = webappPath;
//        this.address = address;
//        this.minThreads = minThreads;
//        this.maxThreads = maxThreads;
//    }
//
//    public void start() throws Exception {
//        if (null == server || server.isStopped()) {
//            doStart();
//        } else {
//            throw new IllegalStateException("JettyServer already started.");
//        }
//    }
//
//    private void doStart() throws Exception {
//        if (!checkServerAddressAvailable()) {
//            throw new IllegalStateException("Server address already in use: " + address);
//        }
//
//        System.setProperty("org.eclipse.jetty.util.URI.charset", DEFAULT_CHARSET);
//        System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
//        System.setProperty("org.eclipse.jetty.server.Request.maxFormContentSize", "10000000");
//        webapp = new WebAppContext(webappPath, contextPath);
//        if (System.getProperty("hscooas.home") != null) {
//            String cooasHome = System.getProperty("hscooas.home");
//            String tmpPath = cooasHome + File.separator + "tmp";
//            String tmpDir = tmpPath + File.separator + "jetty";
//            File file = new File(tmpDir);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            webapp.setAttribute("org.eclipse.jetty.webapp.basetempdir", tmpPath);
//            webapp.setTempDirectory(file);
//        }
//
//        server = new Server(address);
//        server.setHandler(webapp);
//
//        long st = System.currentTimeMillis();
//        server.start();
//        long sp = System.currentTimeMillis() - st;
//        System.out.println("JettyServer started: " + String.format("%.2f sec", sp / 1000D));
//        server.join();
//    }
//
//    private boolean checkServerAddressAvailable() {
//        int port = address.getPort();
//        if (0 < port) {
//            ServerSocket ss = null;
//            try {
//                ss = new ServerSocket(port, 0, address.getAddress());
//            } catch (Exception e) {
//                LOG.error("check serverAddress failed", e);
//                return false;
//            } finally {
//                if (null != ss) {
//                    try {
//                        ss.close();
//                    } catch (IOException e) {
//                        LOG.error("close ServerSocket failed", e);
//                    }
//                }
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid port " + port);
//        }
//        return true;
//    }
//}
