import java.io.StringReader
import nu.validator.htmlparser.common.XmlViolationPolicy
import nu.validator.htmlparser.sax.HtmlParser
import org.xml.sax.InputSource
import scala.xml.parsing.NoBindingFactoryAdapter
import scala.xml.{Node, NodeSeq}

trait Site {

  val siteId: Int

  def getTags(html: String, domString: Node => NodeSeq): List[String] = domString(html.toNode).map(_.text).toList

  protected class StringToNode(val string: String) {
    def toNode: Node = {
      val hp = new HtmlParser
      hp.setNamePolicy(XmlViolationPolicy.ALLOW)
      val saxer = new NoBindingFactoryAdapter
      hp.setContentHandler(saxer)
      hp.parse(new InputSource(new StringReader(string)))
      saxer.rootElem
    }
  }

  implicit protected def stringToNode(string: String): StringToNode = new StringToNode(string)
}

