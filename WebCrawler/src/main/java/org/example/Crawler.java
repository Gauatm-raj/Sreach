package org.example;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.HashSet;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Crawler {
//    HashSet <String> urlset;
//    int max_depth=2;
//    Crawler(){
//        urlset=new HashSet<String>();
//    }
//    public void getTextLinks(String url,int depth) throws IOException {
//        if(urlset.contains(url)){
//            return;
//        }
//        if(depth>max_depth){
//            return;
//        }
//        depth++;
//
//        try {
//            Document document = Jsoup.connect(url).timeout(5000).get();
//           Indexer idx=new Indexer(document,url);
//        Elements availabeLinks=document.select("a[href]");
//        for(Element currLinks:availabeLinks){
//            getTextLinks(currLinks.attr("abs:href"),depth);
//
//          }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//
//        }
//
//    }
//
//    public static void main(String[] args) throws IOException {
//          Crawler crawler = new Crawler();
//          crawler.getTextLinks("https://www.geeksforgeeks.org/",1);
//
//    }
//}

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {

    private HashSet<String> visitedUrls;

    public Crawler() {
        visitedUrls = new HashSet<>();
    }

    public void crawl(String startUrl, int maxDepth) {
        crawlUrl(startUrl, 0, maxDepth);
    }

    private void crawlUrl(String url, int currentDepth, int maxDepth) {
        if (currentDepth >= maxDepth || visitedUrls.contains(url)) {
            return;
        }

        System.out.println("Depth: " + currentDepth + " - Visiting URL: " + url);

        try {
            Document document = Jsoup.connect(url).get();
            Indexer idx=new Indexer(document,url);

            // Extract information from the page as needed
            // For example, print the page title
            String pageTitle = document.title();
            //System.out.println("Page Title: " + pageTitle);

            // Extract links on the page
            Elements links = document.select("a[href]");
            for (Element link : links) {
                String nextUrl = link.absUrl("href");
                crawlUrl(nextUrl, currentDepth + 1, maxDepth);
            }

        } catch (IOException e) {
            System.err.println("Error fetching or parsing the page: " + url);
        }

        visitedUrls.add(url);
    }

    public static void main(String[] args) {
        String startUrl = "https://www.geeksforgeeks.org";
        int maxDepth = 2;

        Crawler webCrawler = new Crawler();
        webCrawler.crawl(startUrl, maxDepth);
    }
}
