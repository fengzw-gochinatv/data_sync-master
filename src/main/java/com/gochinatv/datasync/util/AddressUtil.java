package com.gochinatv.datasync.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA. User: haoshihai Date: 13-2-22 Time: 上午9:54 To
 * change this template use File | Settings | File Templates.
 */
// AddressUtil 用于提供本机地址操作方法
public class AddressUtil {
    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    public static boolean allowAddress(String allowIp) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            log.info("localIP={},allowIp={}", ip, allowIp);
            if (!ip.equals(allowIp)) {
                log.info("不是指定执行任务的IP机器！");
                return false;
            }
        } catch (UnknownHostException e) {
            log.info("get local host is Error，errorInfo={}", new Object[]{e.toString()});
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static String getLocal() {
        Enumeration netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        String localip = null;
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
            ip = (InetAddress) ni.getInetAddresses().nextElement();
            if (ip.isSiteLocalAddress()) {
                localip = ip.getHostAddress();
            }
        }
        log.info("test jump localIP" + localip);
        return localip;
    }


}
