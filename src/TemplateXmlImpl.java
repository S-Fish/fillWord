import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhouliang on 2018/3/30 0030.
 */

public class TemplateXmlImpl implements TemplateXml {

    private Document document;
    private String[] labels;
    private String[] reaplceWords;

    @Override
    public void markerLabel(String pathName,String saveName, String[] labels, String[] reaplaceWords) {

                //加载xml文件加载到document上面，并且将labels和reaplceWords赋值给私有属性，供解析替换使用
                loadingXml(pathName);
                this.labels=labels;
                this.reaplceWords=reaplaceWords;

                //遍历document将labels[i]替换为reaplaceWords[i],这里面也可以用hashMap实现
                org.w3c.dom.Element root=document.getDocumentElement();
                parserXml(root);

                //将document转化为xml文件并且储存
                saveXml(saveName);
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
        TransformerFactory transformerFactory=TransformerFactory.newInstance();

        PrintWriter printWriter=null;//出于安全角度要在finally里面流文件关闭

        try {
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(document);
            printWriter=new PrintWriter(new FileOutputStream(pathName));
            StreamResult result=new StreamResult(printWriter);
            transformer.transform(source,result);
            System.out.println("saved success");

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }finally {
            if(printWriter!=null){
                printWriter.close();
            }
        }

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
    public void parserXml(Element node) {
        if(node==null){ return;}

        NodeList childNodes=node.getChildNodes();

        for(int i=0;i<childNodes.getLength();i++){

            Node childNode=childNodes.item(i);

            //判断这个节点是不是TEXT是则为需要找到节点
            if(childNode.getNodeType()==Node.TEXT_NODE){
                String text=childNode.getNodeValue();
                //System.out.println(text);
                int index=getIndexOfLabels(text);
                if(index!=-1){
                    childNode.setNodeValue("${"+reaplceWords[index]+"?if_exists}");
                }
            }

            if (childNode.getNodeType()==Node.ELEMENT_NODE){
                Element element=(Element) childNode;
                parserXml(element);
            }

        }

    }

    private int getIndexOfLabels(String text){
        int index=-1;
        for (int i=0;i<labels.length;i++){
            if(labels[i].equals(text)){index=i;break;}
        }
        return index;
    }
}
