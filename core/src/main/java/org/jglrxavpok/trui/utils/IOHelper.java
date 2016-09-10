package org.jglrxavpok.trui.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOHelper {

    public static byte[] readAll(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2*1024];
        int i;
        while((i = in.read(buffer)) != -1) {
            baos.write(buffer, 0, i);
        }

        baos.flush();
        baos.close();
        return baos.toByteArray();
    }
}
