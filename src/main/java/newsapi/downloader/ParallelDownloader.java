package newsapi.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class ParallelDownloader extends Downloader {

    ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public int process(List<String> urls) {
        int count = 0;
        String fileName = null;
        long begin = System.currentTimeMillis();
        List<Future<String>> futures = new ArrayList<>();
        for (String url : urls) {

            Callable<String> callable = new DownloaderCallable(url, this);
            futures.add(executor.submit(callable));

        }
        for (Future<String> future : futures){
            try {
                fileName = future.get();
            } catch (Exception ignore) {}

            if (fileName != null){
                count++;
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("Parallel execution time was: " + (end-begin) + " milliseconds" + System.lineSeparator());
        return count;
    }
}
