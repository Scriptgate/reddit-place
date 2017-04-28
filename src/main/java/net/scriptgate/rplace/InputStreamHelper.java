package net.scriptgate.rplace;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamHelper {

    public static int readUInt32(InputStream is) throws IOException {
        byte[] data = new byte[4];
        is.read(data, 0, data.length);

        return uInt32ToInteger(data);
    }

    protected static int uInt32ToInteger(byte[] data) {
        return (data[0] & 0xFF) | ((data[1] & 0xFF) << 8) | ((data[2] & 0xFF) << 16) | ((data[3] & 0xFF) << 24);
    }

}
