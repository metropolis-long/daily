package com.daily.springbootmybatis;

import com.daily.dto.CostDTO;
import com.daily.dto.DiaryDTO;
import com.daily.dto.EventDTO;
import com.daily.dto.LearnDTO;
import com.daily.msg.ResultBody;
import com.daily.pojo.Label;
import com.daily.pojo.UserInfo;
import com.daily.search.CostSearch;
import com.daily.search.DailySearch;
import com.daily.search.LearnSearch;
import com.daily.search.Page;
import com.daily.service.*;
import com.daily.tool.DateUtil;
import com.daily.tool.RedisUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
    @Autowired
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private IDiaryService diaryService;
    @Autowired
    private EventService eventService;

    @Autowired
    private CostService costService;

    @Autowired
    private LearnService learnService;


    @Autowired
    private MailService mailService;
    //自动加载加密类
    @Autowired
    StringEncryptor encryptor;

    @Test
    public void savel() {

        LearnSearch saa = new LearnSearch();
//        saa.setLearnId(1L);
        learnService.findMyLearn(saa);
//        System.out.println(d.getData().getLabel().toArray().toString());
//        d.getData().setTag("给对方撒,,4,4,,4，443");
//        learnService.save(d.getData());
//        d = learnService.detail(saa);
//        System.out.println(d.getData().getLabel().toArray().toString());
    }

        @Test
    public void getEncryptor() {

        //对敏感信息进行加密
//        String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/daily?useUnicode=true&usessl=false&characterEncoding=utf8");
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/daily?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=UTC");
        String p = encryptor.encrypt("fcbdrmoxjyylgabj");
        String name = encryptor.encrypt("metropolis.long@foxmail.com");
        String smtp = encryptor.encrypt("smtp.foxmail.com");
        System.out.println(url);
        System.out.println(name);
        System.out.println(p);
        System.out.println(smtp);
    }

        @Test
    public void setMail() {
        mailService.sendMimeMessge("1402218209@qq.com", "祝好啊", "我是最帅的男人。不允许反驳！！！并且最最最nice");
    }

    //    @Test
    public void setRedis() {
        Object o = redisUtil.get("0fdd33fc-d25e-4850-a7ef-acead2618bb5");
        UserInfo userInfo = (UserInfo) o;
        System.out.println(userInfo.getUserName());
    }


    //    @Test
    public void le() {
        LearnDTO s = new LearnDTO();
        String ss = "";
//        for (int i = 0; i < 10000; i++) {
//            ss+="kihddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
//        }
        s.setUserId(1L);
        s.setLearnHtml(ss);
        s.setLearnTitle("ww");
        s.setTag("33");
        s.setLearnContext(ss);
        ResultBody<LearnDTO> r = learnService.save(s);
        System.out.println(r.getCode());
    }

    //    @Test
    public void costSum() {
        CostSearch s = new CostSearch();
        s.setUserId(1L);
        List<CostDTO> csList = costService.sumClassify4CostList(s);
        csList.forEach(new Consumer<CostDTO>() {
            @Override
            public void accept(CostDTO costDTO) {
                System.out.println(costDTO.getTimeStr() + ":" + costDTO.getSumMoney());
            }
        });
    }

    @Test
    public void events() {

//        for (int i=1;i<100;i++){
        EventDTO co = new EventDTO();
        co.setEventContext("3月\uD83D\uDDA5到期");
        System.out.println(co.getEventContext());
        co.setUserId(1L);
        eventService.save(co);
//        }
    }

    //    @Test
    public void cost() {
        for (int i = 83; i < 100; i++) {
            CostDTO co = new CostDTO();
            co.setCostContext("good" + i);
            co.setUserId(1L);
            co.setCreated(DateUtil.getTodayAfterDay(i * 2));
            co.setUpdated(DateUtil.getTodayAfterDay(i * 3));
            co.setTag("经济的" + i);
            co.setCostMoney(new BigDecimal("225." + i));
            costService.saveCost(co);
        }
    }


    //    @Test
    public void getDiaryService() {
        DailySearch search = new DailySearch();
        search.setUserId(1L);
        Page page = new Page(1, 20);
        search.setPage(page);
        List<DiaryDTO> list = diaryService.findDiaryList(search);
        for (DiaryDTO d : list) {
//            System.out.println(d.getDiaryContext());
            for (Label l : d.getLabel()) {
                System.err.println(l.getLabelContext());
            }
        }
    }

    //    @Test
    public void saveDiary() {
        DiaryDTO diary = new DiaryDTO();
        diary.setMood("yyuy,jkig,");
        diary.setTemperature(new BigDecimal("33.3"));
        diary.setWeather("雨打");
        diary.setDiaryContext("rstdyfughijol;llhggfdsd");
        diary.setUserId(1L);
        diary.setDialyDate(new Date());
        diaryService.saveDiary(diary);
    }
}
