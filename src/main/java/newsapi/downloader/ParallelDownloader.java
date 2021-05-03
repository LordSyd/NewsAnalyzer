package newsapi.downloader;

import newsapi.NewsApiException;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ParallelDownloader extends Downloader {

    ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public int process(List<String> urls){
        int count = 0;
        final String[] fileName = new String[1];
        long begin = System.currentTimeMillis();
        for (String url : urls) {
            executor.execute(() -> {
                try {
                    fileName[0] = saveUrl2File(url);
                } catch (Exception ignored) {}
            });

        }
        if (fileName[0] != null){
                count++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Parallel execution time was: " + (end-begin) + " milliseconds" + System.lineSeparator());
        return count;
    }
}
