package com.sj.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ToStr {
	
	private static byte[] bytes = new byte[2048 * 1024];
	
	public static String tostr(InputStream in) throws IOException {
        int i;
       
        StringBuilder str = new StringBuilder();
        while ((i = in.read(bytes)) != -1) {
            str.append(new String(bytes, 0, i, StandardCharsets.UTF_8));
        }
        in.close();
        return str.toString();
    }

}
