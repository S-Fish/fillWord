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


    @Override
    public void fillData(String wordName,String templateXMLName,String data ,String[] markets) {
            //exportTemplate("./","test.xml","test.doc");
            String[] datas=data.split(";");
            fillWordTmplate(wordName,templateXMLName,datas,markets);

    }


    //将data填入word模板中被market标记的内容上面最后文件命名为fileName
    private void fillWordTmplate(String fileName,String templateXMLName,String[] data,String[] markets){

        Map<String, Object> cont = new HashMap<String, Object>();// 存储数据

        for(int i=0;i<markets.length;i++){
            //防止数组越界
            if(i<data.length) cont.put(markets[i],data[i]);
        }

        try {
            //模板的路径
            File fir = new File("./");

            //生成文件的路径及文件名。
            File outFile = new File(fileName);

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

            // 使用FileTemplateLoader
            //指定模板路径
            TemplateLoader templateLoader = null;
            templateLoader = new FileTemplateLoader(fir);
            String tempname = templateXMLName;

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
