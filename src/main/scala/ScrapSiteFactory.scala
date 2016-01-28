/**
 * Created by taishi on 2016/01/27.
 */
object ScrapSiteFactory {
  def apply(siteName: String, html: String) =
    siteName.toLowerCase match {
      case "fashionsnap" => new FashionSnapScrap(html)
    }
}