/**
 * Created by zhouliang on 2018/3/29 0029.
 */
public class Test {

    public static void main(String[] args){

        FillWordImpl fillWord=new FillWordImpl();
        TemplateXmlImpl templateXml=new TemplateXmlImpl();

        String[] labels={"A1","A2","A3"};

        String[] replaceWords={"B1","B2","B3"};

        templateXml.markerLabel("./file/test.xml",labels,replaceWords);
        //System.out.println("sss");
        //fillWord.fillData("1525122048","sss");

    }
}
