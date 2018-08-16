package cn.leta.common.utils;

import cn.leta.common.lang.SimpleCache;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/12.
 *
 * @author Xie Gengcai
 */
public final class MessageDigestUtil {


    private final static SimpleCache<String, MessageDigest> MESSAGE_DIGEST_SIMPLE_CACHE = new SimpleCache<>();

    public enum Algorithm {
        MD5("MD5")
        , SHA_1("SHA-1")
        , SHA_224("SHA-224")
        , SHA_256("SHA-256")
        , SHA_384("SHA-384")
        , SHA_512("SHA-512");
        private String name;
        Algorithm(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }

        public static Algorithm which(String name) {
            for (Algorithm algorithm : Algorithm.values()) {
                if (algorithm.getName().equals(name)) {
                    return algorithm;
                }
            }
            throw new RuntimeException(String.format("不支持摘要方法%s", name));
        }
    }

    private MessageDigestUtil(){}

    /**
     *
     * @param algorithm
     * <ul>
     *  <li>{@code MD5}</li>
     *  <li>{@code SHA-1}</li>
     *  <li>{@code SHA-224}</li>
     *  <li>{@code SHA-256}</li>
     *  <li>{@code SHA-384}</li>
     *  <li>{@code SHA-512}</li>
     *  </ul>
     * These algorithms are described in the <a href=
     * "https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#MessageDigest">
     * MessageDigest section</a> of the
     *  Java Cryptography Architecture Standard Algorithm Name Documentation.
     * @return
     */
    public static MessageDigest getMessageDigest(String algorithm) {
        Algorithm algthm = Algorithm.which(algorithm);
        MessageDigest messageDigest = MESSAGE_DIGEST_SIMPLE_CACHE.get(algthm.getName());
        if (messageDigest != null) {
            return messageDigest;
        }
        try {
            messageDigest = MessageDigest.getInstance(algthm.getName());
            MESSAGE_DIGEST_SIMPLE_CACHE.putIfAbsent(algthm.getName(), messageDigest);
            return messageDigest;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(String.format("没有%s摘要方法", algthm.getName()), e.getCause());
        }
    }

    /**
     * 使用指定摘要实例生成摘要
     * @param messageDigest
     * @param message 源字符串信息
     * @return 十六
     */
    public static String digest(MessageDigest messageDigest, String ... message) {
        if (messageDigest == null) {
            throw new IllegalArgumentException("messageDigest不能为空");
        }
        if (message == null || message.length == 0) {
            throw new IllegalArgumentException("message不能为空");
        }
        StringBuilder source = new StringBuilder();
        for (String msg : message) {
            source.append(msg);
        }
        return digest(messageDigest, source.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 使用指定摘要实例生成摘要
     * @param messageDigest
     * @param input 源字节数组信息
     * @return
     */
    public static String digest(MessageDigest messageDigest, byte[] input) {
        if (messageDigest == null) {
            throw new IllegalArgumentException("messageDigest不能为空");
        }
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("input不能为空不能为空");
        }
        return bytes2HexStr(messageDigest.digest(input));
    }

    /**
     * 指定摘要方法生成摘要
     * @param algorithm
     * @param input 源字节数组信息
     * @return
     */
    public static String digest(String algorithm, byte[] input) {
        return digest(getMessageDigest(algorithm), input);
    }

    /**
     * 指定摘要方法生成摘要
     * @param algorithm
     * @param message
     * @return
     */
    public static String digest(String algorithm, String ... message) {
        return digest(getMessageDigest(algorithm), message);
    }

    public static String doubleEncrypt(String algorithm, String ... message) {
        MessageDigest messageDigest = getMessageDigest(algorithm);
        return digest(messageDigest, digest(messageDigest, message));
    }

/*
    public static byte[] toHexBytes(byte [] bytes) {
        for (byte b : bytes) {
            b = (byte) (b & 0xFF);
        }
        return bytes;
    }*/

    private static String bytes2HexStr(byte [] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("input不能为空不能为空");
        }
        StringBuilder result = new StringBuilder();
        for (byte b:input) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                result.append("0");
            }
            result.append(hex.toUpperCase());
        }
        return result.toString();
    }

/*
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(digest(Algorithm.MD5.getName(), "13800138000", "123456", uuid));
        System.out.println(digest(Algorithm.SHA_1.getName(), "13800138000", "123456", uuid));
        System.out.println(digest(Algorithm.SHA_224.getName(), "13800138000", "123456", uuid));
        System.out.println(digest(Algorithm.SHA_256.getName(), "13800138000", "123456", uuid));
        System.out.println(digest(Algorithm.SHA_384.getName(), "13800138000", "123456", uuid));
        System.out.println(digest(Algorithm.SHA_512.getName(), "13800138000", "123456", uuid));
    }
    */
}