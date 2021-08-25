package com.example.demo.util.log;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Date 2020/7/18
 * @Author tiger
 */

public class XmlParseUtil {
    //private static Logger logger = Logger.getLogger(XmlParseUtil.class);

    //rn.xmclasspath:tuomin-pattel
    //    public static void initTuoMinPatterns(String path) {
    //        try {
    //            patterns = getTuoMinPatternsFromXml(path);
    //            logger.info("=============脱敏模式加载成功====================");
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            logger.info("=========初始化脱敏模式失败=========");
    //        }
    //    }

    @SuppressWarnings("unchecked")
    public static List<PatternModel> getTuoMinPatternsFromXml() throws Exception {
        List<PatternModel> partterns = new ArrayList<PatternModel>();
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource resource = resolver.getResource("classpath:tuomin-pattern.xml");

//        String path = resource.getURI().getPath();
        //读取xml文件到Document中
        SAXReader reader = new SAXReader();
        reader.setEntityResolver(new XmlParseUtil.IgnoreDTDEntityResolver()); // ignore dtd
        //使用class.getResource()、new File()等方式读取配置文件，当打jar包的时候，就访问不到classpath，
        // 所以这里使用new ClassPathResource()的方式
        InputStream xmlFile =new ClassPathResource("tuomin-pattern.xml").getInputStream();
        Document doc = reader.read(xmlFile);

        //获取xml文件的根节点
        Element rootElement = doc.getRootElement();
        for (Iterator<Element> iter = rootElement.elementIterator("pattern"); iter.hasNext();) {
            PatternModel pm = new PatternModel();
            Element pattern = iter.next(); // 获取标签对象
            String name = pattern.element("name").getTextTrim();
            String regular = pattern.element("regular").getTextTrim();
            pm.setDifferInLen(Boolean.parseBoolean(pattern.attribute("differInLen").getStringValue()));
            pm.setName(name);
            pm.setRegular(regular);
            List<EncryptModel> ems = new ArrayList<EncryptModel>();
            for (Iterator<Element> it = pattern.element("encrypts").elementIterator("encrypt"); it.hasNext();) {
                Element encrypt = it.next(); // 获取标签对象
                EncryptModel em = new EncryptModel();
                List<Attribute> attributes = encrypt.attributes();
                //循环所有的属性
                for (Attribute attr : attributes) {
                    String attrName = attr.getName();
                    String attrVal = attr.getStringValue();
                    Method method = em.getClass().getMethod("set" + firstChartUpperCase(attrName), String.class);
                    method.invoke(em, attrVal);
                }
                ems.add(em);
            }
            pm.setEncrypts(ems);
            partterns.add(pm);
        }

        return partterns;
    }

    private static String firstChartUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    static class IgnoreDTDEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
            return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
        }
    }
}
