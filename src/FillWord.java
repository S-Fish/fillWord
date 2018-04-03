/**
 * Created by zhouliang on 2018/3/29 0029.
 */

public interface FillWord {

    /*wordName是指生成的word文件名字（带路径），templateXMLName是指使用的word模板名字（带路径）
      data是指要填入的数据，相邻数据用;（分号）隔开
      markers对应XML文件的替换的位置标记，为一个个字符串，在xml形式为${markets[i]??if_exists}
    */
    void fillData(String wordName,String templateXMLName,String data ,String[] markets);
}
