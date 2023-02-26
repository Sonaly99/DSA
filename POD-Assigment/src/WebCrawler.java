import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebCrawler {
    private final ExecutorService executor;
    private final int MAX_DEPTH = 3;

    public WebCrawler() {
        this.executor = Executors.newFixedThreadPool(5); // create a thread pool with 5 threads
    }

    public void crawl(String url, int depth) {
        if (depth > MAX_DEPTH) {
            return;
        }

        Future<String[]> future = executor.submit(new WebCrawlerTask(url)); // submit a new task to the executor
        try {
            String[] links = future.get(); // wait for the task to complete and get the results
            for (String link : links) {
                crawl(link, depth + 1); // recursively crawl each link
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        executor.shutdown(); // shut down the executor when finished
    }

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        crawler.crawl("http://www.example.com", 0);
        crawler.shutdown();
    }
}

class WebCrawlerTask implements Callable<String[]> {
    private final String url;

    public WebCrawlerTask(String url) {
        this.url = url;
    }

    @Override
    public String[] call() throws Exception {
        // retrieve the web page at the given URL and parse the links
        // return an array of links found on the page
        return new String[] { "http://www.example.com/page1.html", "http://www.example.com/page2.html" };
    }
}
