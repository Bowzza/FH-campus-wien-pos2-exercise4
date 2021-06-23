package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader{

    @Override
    public int process(List<String> urls) {
        long startTime = System.nanoTime();

        Future<String> future1 = null;

        int numWorkers = Runtime.getRuntime().availableProcessors();

        ExecutorService pool = Executors.newFixedThreadPool(numWorkers);
        for (String url: urls) {
            future1 = pool.submit(() -> saveUrl2File(url));
        }

        while(!future1.isDone()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000000;
        System.out.println(duration + " Seconds");

        return 0;
    }


}
