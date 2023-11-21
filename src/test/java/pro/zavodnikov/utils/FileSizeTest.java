package pro.zavodnikov.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link FileSize}.
 */
public class FileSizeTest {

    @Test
    public void testToStringBytes() {
        final FileSize fz1 = new FileSize(1L);
        System.out.println(fz1);
        assertEquals("1 Bytes", fz1.toString());
    }

    @Test
    public void testToStringKib() {
        final FileSize fz2k = new FileSize(2_000L);
        System.out.println(fz2k);
        assertEquals("2.0 KiB", fz2k.toString());
    }

    @Test
    public void testToStringMib() {
        final FileSize fz3m = new FileSize(3_000_000L);
        System.out.println(fz3m);
        assertEquals("2.9 MiB", fz3m.toString());
    }

    @Test
    public void testToStringGib() {
        final FileSize fz4g = new FileSize(4_000_000_000L);
        System.out.println(fz4g);
        assertEquals("3.7 GiB", fz4g.toString());
    }

    @Test
    public void testToStringTib() {
        final FileSize fz5t = new FileSize(5_000_000_000_000L);
        System.out.println(fz5t);
        assertEquals("4.5 TiB", fz5t.toString());
    }
}
