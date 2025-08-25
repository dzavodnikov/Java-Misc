package pro.zavodnikov.examples;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.prometheus.metrics.core.metrics.Histogram;
import io.prometheus.metrics.core.metrics.Summary;
import io.prometheus.metrics.exporter.httpserver.HTTPServer;

public class Prometheus implements Runnable, Closeable {

    private final int port;
    private HTTPServer metricsServer;

    private final Histogram histogram;
    private final Summary summary;

    public Prometheus(final int port) {
        this.port = port;

        // Histogram.
        this.histogram = Histogram.builder()
                .name("test_histogram")
                .help("Test Histogram with some values.")
                .register();

        // Summary.
        this.summary = Summary.builder()
                .name("test_summary")
                .quantile(0.50)
                .quantile(0.90)
                .quantile(0.99)
                .help("Test Summary with some values.")
                .register();
    }

    @Override
    public void run() {
        // Default JVM metrics.
        // JvmMetrics.builder().register();

        // Server itself.
        try {
            this.metricsServer = HTTPServer.builder().port(this.port).buildAndStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (this.metricsServer != null) {
            this.metricsServer.stop();
        }
    }

    public void register(final double value) {
        this.histogram.observe(value);
        this.summary.observe(value);
    }

    public static void main(final String[] args) {
        final Prometheus prometheus = new Prometheus(8065);
        final ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(prometheus);

        // Populate metrics.
        prometheus.register(0.05);
        for (int i = 0; i < 10; ++i) {
            prometheus.register(5);
        }
        for (int i = 0; i < 100; ++i) {
            prometheus.register(50);
        }
        for (int i = 0; i < 100; ++i) {
            prometheus.register(150);
        }
        prometheus.register(450);

        System.out.println("Done!");
    }
}
