package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {
        int count = 0;
        long startTime = System.nanoTime();

        for (String url : urls) {
            String fileName = saveUrl2File(url);
            if(fileName != null)
                count++;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000000;
        System.out.println(duration + " Seconds");
        
        return count;
    }
}
