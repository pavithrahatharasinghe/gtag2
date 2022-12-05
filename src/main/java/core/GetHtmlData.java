package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.util.ArrayList;


public class GetHtmlData {


    public static void main(String[] args) {

        String url = "https://genius.com/Interlunium-level-up-altair-arrange-lyrics";


        System.out.println(getGenre(url));
        System.out.println(getAlbumName(url));
    }

    private static void GetHtml(String url) {
        try {
            Document document = Jsoup.connect(url).userAgent(USERT_AGENT).ignoreHttpErrors(true).followRedirects(true).timeout(100000).ignoreContentType(true).get();
            System.out.println(document.outerHtml());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static final String USERT_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0";

    public static String getAlbumName(String url) {

        return getAlbumName1(url) + getAlbumName2(url);
    }


    public static String getAlbumName1(String url) {
        String albumName = "";
        try {
            Document document = Jsoup.connect(url).userAgent(USERT_AGENT).ignoreHttpErrors(true).followRedirects(true).timeout(100000).ignoreContentType(true).get();
            albumName = document.body().select("div.HeaderTracklist__Album-sc-1qmk74v-2.kKxYZS a.Link__StyledLink-h3isu4-0.fUgcxf").text();
            //System.out.println(albumName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return albumName;
    }

    public static String getAlbumName2(String url) {
        String albumName = "";
        try {
            Document document = Jsoup.connect(url).userAgent(USERT_AGENT).ignoreHttpErrors(true).followRedirects(true).timeout(100000).ignoreContentType(true).get();
            albumName = document.body().select("div.HeaderTracklist__Album-sc-1qmk74v-2.kKSpls a.Link__StyledLink-h3isu4-0.fTLMop").text();
            // System.out.println(albumName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return albumName;
    }

    public static String getGenre(String url) {
        return getGenre1(url) + getGenre2(url);
    }

    public static String getGenre1(String url) {
        String genre = "";
        try {
            ArrayList songlist = new ArrayList();

            Document document = Jsoup.connect(url).userAgent(USERT_AGENT).ignoreHttpErrors(true).followRedirects(true).timeout(100000).ignoreContentType(true).get();


            for (Element row : document.select("a.SongTags__Tag-xixwg3-2.evrydK")) {
                if (row.select("a.SongTags__Tag-xixwg3-2.evrydK").text().equals("")) {
                    continue;
                } else {
                    String ticker = row.select("a.SongTags__Tag-xixwg3-2.evrydK").text();
                    // System.out.println(document.select("div.SongTags__Container-xixwg3-1.bZsZHM a").text());
                    //System.out.println(ticker);
                    songlist.add(ticker);
                }
            }
            for (Element row : document.select("a.SongTags__Tag-xixwg3-2.kykqAa")) {
                if (row.select("a.SongTags__Tag-xixwg3-2.kykqAa").text().equals("")) {
                    continue;
                } else {
                    String ticker = row.select("a.SongTags__Tag-xixwg3-2.kykqAa").text();
                    // System.out.println(document.select("div.SongTags__Container-xixwg3-1.bZsZHM a").text());
                    //System.out.println(ticker);
                    songlist.add(ticker);
                }
            }
            genre = songlist.get(0).toString();

            for (int i = 1; i < songlist.size(); ++i) {

                genre = songlist.get(i) + " , " + genre;
            }
            // System.out.println(genre);
        } catch (IndexOutOfBoundsException iobex) {
            //System.out.println("No Such file Css class found in the html...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return genre;
    }

    public static String getGenre2(String url) {
        String genre = "";
        try {
            ArrayList songlist = new ArrayList();

            Document document = Jsoup.connect(url).userAgent(USERT_AGENT).ignoreHttpErrors(true).followRedirects(true).timeout(100000).ignoreContentType(true).get();


            for (Element row : document.select("a.SongTags__Tag-xixwg3-2.fdHeQh")) {
                if (row.select("a.SongTags__Tag-xixwg3-2.fdHeQh").text().equals("")) {
                    continue;
                } else {
                    String ticker = row.select("a.SongTags__Tag-xixwg3-2.fdHeQh").text();
                    // System.out.println(document.select("div.SongTags__Container-xixwg3-1.bZsZHM a").text());
                    //System.out.println(ticker);
                    songlist.add(ticker);
                }
            }
            for (Element row : document.select("a.SongTags__Tag-xixwg3-2.gUEvJ")) {
                if (row.select("a.SongTags__Tag-xixwg3-2.gUEvJ").text().equals("")) {
                    continue;
                } else {
                    String ticker = row.select("a.SongTags__Tag-xixwg3-2.gUEvJ").text();
                    // System.out.println(document.select("div.SongTags__Container-xixwg3-1.bZsZHM a").text());
                    //System.out.println(ticker);
                    songlist.add(ticker);
                }
            }
            genre = songlist.get(0).toString();

            for (int i = 1; i < songlist.size(); ++i) {

                genre = songlist.get(i) + " , " + genre;
            }
            // System.out.println(genre);
        } catch (IndexOutOfBoundsException iobex) {
            //System.out.println("No Such file Css class found in the html...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return genre;
    }


}
