
import org.w3c.dom.Element;


/**
 * Created by zhouliang on 2018/3/30 0030.
 * 在word中标记的字符格式要一致，而且格式和该标记格式前面一个字符不一样
 */
public interface TemplateXml {

    //打开pathName的XML文件，
    // 将里面的replaceWord[i]单词替换成${Labes[i]？if_exists}形成模板供填入
    // 最后保存到saveName中去
    public void markerLabel(String pathName,String saveName,String[] labels,String[] reaplaceWords);

    //初始化工厂
    public void init();

    //保存xml文件
    public void saveXml(String pathName);

    //解析xml文件,将xml文件加载到内存中形成树
    public void loadingXml(String pathName);

    //递归遍历Xml,并且完成替换工作
    public void parserXml(Element node);
}
