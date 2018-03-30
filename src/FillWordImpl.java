import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouliang on 2018/3/29 0029.
 */

public class FillWordImpl implements FillWord {


    @Override//给外部调用将data填入word中最后形成文件名字为fileName
    public void fillData(String fileName, String data) {
            //exportTemplate("./","test.xml","test.doc");
            fillWordTmplate(fileName,null);
    }

    //打开pathName的XML文件，将里面的replaceWord[i]单词替换成${Labes[i]？if_exists}形成模板供填入使用
    public void markerLabel(String pathName,String[] Labels,String[] reaplaceWord){

    }

    //将data填入word模板中最后文件命名为fileName
    private void fillWordTmplate(String fileName,String[] data){

        Map<String, Object> cont = new HashMap<String, Object>();// 存储数据
        String[] keys=new String[62];
        for(int i=0;i<62;i++){
            keys[i]="A"+(i+1);
        }
        for(int i=0;i<keys.length;i++){
            cont.put(keys[i],"√");
        }

        try {
            //模板的路径
            File fir = new File("./");

            //生成文件的路径及文件名。
            File outFile = new File("./"+fileName+".docx");

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

            // 使用FileTemplateLoader
            //指定模板路径
            TemplateLoader templateLoader = null;
            templateLoader = new FileTemplateLoader(fir);
            String tempname = "AllTestTabel.xml";

            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(templateLoader);
            Template t = cfg.getTemplate(tempname, "UTF-8");

            t.process(cont, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

}
}
