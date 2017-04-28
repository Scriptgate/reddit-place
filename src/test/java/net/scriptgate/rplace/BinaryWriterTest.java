package net.scriptgate.rplace;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryWriterTest {

    @Test
    public void integerToUInt32() throws Exception {
        byte[] uint32 = BinaryWriter.integerToUInt32(65536);
        assertThat(uint32).isEqualTo(new byte[]{0, 0, 1, 0});
    }
}
