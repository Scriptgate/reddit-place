package net.scriptgate.rplace;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputStreamHelperTest {

    @Test
    public void uInt32ToInteger() throws Exception {
        int unsigned = InputStreamHelper.uInt32ToInteger(new byte[]{0, 0, 1, 0});
        assertThat(unsigned).isEqualTo(65536);
    }

}
