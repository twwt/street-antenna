import java.util.regex.Pattern
import edu.uci.ics.crawler4j.crawler.{CrawlController, CrawlConfig, WebCrawler, Page}
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.parser.HtmlParseData
import edu.uci.ics.crawler4j.robotstxt.{RobotstxtServer, RobotstxtConfig}
import com.typesafe.scalalogging.Logger
import edu.uci.ics.crawler4j.url.WebURL
import org.slf4j.LoggerFactory


/**
 * Created by taishi on 2016/01/23.
 */
class SearchCrawler extends WebCrawler {

  var siteName = "empty siteName"
  val siteData = new CrawlSiteFactory(siteName).create
  val ignorePatter: Pattern = Pattern.compile(siteData.ignoreRegex)

  override def shouldVisit(referringPage: Page, url: WebURL): Boolean = {
    val currentUrl = url.getURL.toLowerCase
    currentUrl.startsWith(siteData.baseUrl) && currentUrl.matches(siteData.fetchRegex)
  }

  override def visit(page: Page): Unit = {
    val currentUrl: String = page.getWebURL.toString
    Logger(LoggerFactory.getLogger("visit url => ")).debug(currentUrl)
    if (page.getParseData.isInstanceOf[HtmlParseData]) {
      val html = page.getParseData.asInstanceOf[HtmlParseData].getHtml
      ScrapSiteFactory(siteName, html)
    }
  }

  val crawlConfig = {
    val conf = new CrawlConfig
    conf.setProxyHost("localhost");
    conf.setProxyPort(5432);
    conf.setCrawlStorageFolder(siteData.cache)
    conf
  }

  val controller = {
    val fetcher = new PageFetcher(crawlConfig)
    val robotTextConf = new RobotstxtConfig
    val robotTextServer = new RobotstxtServer(robotTextConf, fetcher)
    new CrawlController(crawlConfig, fetcher, robotTextServer)
  }

  def start(): Unit = {
    controller.addSeed(siteData.baseUrl)
    controller.start(classOf[SearchCrawler], siteData.threads)
  }
}