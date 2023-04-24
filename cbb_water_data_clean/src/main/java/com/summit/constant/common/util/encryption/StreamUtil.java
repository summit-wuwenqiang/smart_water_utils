package com.summit.constant.common.util.encryption;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 流工具类
 */
@Slf4j
public class StreamUtil {
    private static final Integer BUFFER_SIZE = 128;

    /**
     * 将requestBody的数据转成字符串
     *
     * @param inputStream
     * @return
     */
    public static String getBodyString(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            if (Objects.nonNull(inputStream)) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                int bytesRead;
                char[] charBuffer = new char[BUFFER_SIZE];
                while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            log.error("get body fail，{}", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return stringBuilder.toString();
    }

}