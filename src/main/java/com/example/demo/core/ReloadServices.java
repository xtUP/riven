package com.example.demo.core;

import com.example.demo.common.SpringContext;
import com.example.demo.entity.AdaptResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@WebServlet(urlPatterns = "/reloadReqParamapServlet",loadOnStartup = 0)
public class ReloadServices extends HttpServlet implements Runnable {

    private static final long serialVersionUID = 8557130016345109927L;

    public static final Logger LOG = LoggerFactory.getLogger(ReloadServices.class);

    private ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        //首次加载缓存
        LOG.warn("首次加载缓存");
        //执行方法---一定有
        // 从现在开始0分钟之后执行第一次，此后每隔一分钟执行一次
        pool.scheduleAtFixedRate(this, 1, 1, TimeUnit.MINUTES);
    }
    @Override
    public void run() {
        LOG.warn("=============【定时加载缓存开始】=====================");
        //执行方法---一定有
        LOG.warn("=============【定时加载缓存end】=====================");
    }
}
