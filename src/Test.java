/**
 * Created by zhouliang on 2018/3/29 0029.
 */
public class Test {

    public static void main(String[] args){

        FillWordImpl fillWord=new FillWordImpl();
        TemplateXmlImpl templateXml=new TemplateXmlImpl();

        String[] labels=new String[62];

        String[] replaceWords=new String[62];

        String data="";

        for(int i=0;i<62;i++){
            labels[i]="A"+(i+1);
            replaceWords[i]=labels[i];
           if(i!=61) data=data+i+"test;";
        }
        data=data+61;

        String templateXmlNmae="./file/test.xml";
        String saveXML=templateXmlNmae;
        String wordName="./1525122046.doc";


        templateXml.markerLabel(templateXmlNmae,saveXML,labels,replaceWords);
        System.out.println("T created");

        fillWord.fillData(wordName,templateXmlNmae,data,replaceWords);

    }
}
