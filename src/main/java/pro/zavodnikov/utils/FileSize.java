package pro.zavodnikov.utils;

/**
 * Print file zie in human-readable format.
 */
public class FileSize {

    private final String[] POSTFIX = new String[] { "Bytes", "KiB", "MiB", "GiB", "TiB", "PiB" };

    public long value;

    public FileSize(final long size) {
        this.value = size;
    }

    public FileSize() {
        this(0);
    }

    @Override
    public String toString() {
        int count = 0;
        long div = 1;
        while (div <= this.value && count < POSTFIX.length) {
            ++count;
            div = div << 10;
        }
        --count;
        if (count < POSTFIX.length) {
            div = div >> 10;
        }

        final double val = div > 1 ? (double) this.value / div : this.value;
        String format = "%.1f %s";
        if (div == 1) {
            format = "%.0f %s";
        }
        return String.format(format, val, POSTFIX[count]);
    }
}
