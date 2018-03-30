import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by zhouliang on 2018/3/30 0030.
 */

public class TemplateXmlImpl implements TemplateXml {

    private Document document;
    private String[] labels;
    private String[] reaplceWords;

    @Override
    public void markerLabel(String pathName, String[] labels, String[] reaplaceWords) {

                //加载xml文件加载到document上面，并且将labels和reaplceWords赋值给私有属性，供解析替换使用
                loadingXml(pathName);
                this.labels=labels;
                this.reaplceWords=reaplaceWords;

                //遍历document将labels[i]替换为reaplaceWords[i],这里面也可以用hashMap实现
                parserXml();

                //将document转化为xml文件并且储存
                saveXml(pathName);
    }

    @Override
    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document= builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveXml(String pathName) {

    }

    @Override
    public void loadingXml(String pathName){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse(pathName);
        }catch (ParserConfigurationException pex){
            pex.printStackTrace();
        }
        catch (IOException ioex){
            ioex.printStackTrace();
        }
        catch (SAXException sex){
            sex.printStackTrace();
        }
    }

    @Override
    public void parserXml() {

    }
}
