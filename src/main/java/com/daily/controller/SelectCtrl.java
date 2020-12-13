package com.daily.controller;

import com.daily.dto.*;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.CitiesCity;
import com.daily.pojo.UserInfo;
import com.daily.search.*;
import com.daily.service.*;
import com.daily.tool.NullUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SelectCtrl
 * @Description cache data
 * @Author metropolis-long
 * @Date 2020/10/18 18:44
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping("/api/select")
public class SelectCtrl {
    @Autowired
    private ICacheService cacheService;

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private CostService costService;

    @Autowired
    private LearnService learnService;

    @Autowired
    private TravelNoteService travelNoteService;

    @RequestMapping(value = {"/city"}, produces = {"application/json;charset=UTF-8"})
    public Object city(Long provinceCode) {
        List<CitiesCity> list = cacheService.city(provinceCode);
        return new ResultBody(list);
    }

    /**
     * test 4Mb request param.
     * @param text
     * @return
     */
    @RequestMapping(value = {"/save"}, produces = {"application/json;charset=UTF-8"})
    public Object save(String text) {
        System.out.println("test long text  ====================="+text);
        return new ResultBody(!NullUtil.isNull(text));
    }
    @RequestMapping(value = {"/province"}, produces = {"application/json;charset=UTF-8"})
    public Object province() {
        return new ResultBody(cacheService.province());
    }

    /**
     * full website search context.
     *
     * @param request
     * @param response
     * @param keywords
     * @return
     */
    @RequestMapping(value = {"/website"}, produces = {"application/json;charset=UTF-8"})
    public Object websiteSearch(HttpServletRequest request, HttpServletResponse response, String keywords) {
        if (NullUtil.isNull(keywords)) {
            return new ResultBody(ResultCodeMsg.PARAM_IS_BLANK.getCode(), ResultCodeMsg.PARAM_IS_BLANK.getMsg());
        }
        ResultBody result = new ResultBody();
        Integer relationTable = null;
        if (keywords.contains("::")) {
            String[] special = keywords.split("::");
            relationTable = NullUtil.getInt(special[1]);
            if (relationTable > 0 && relationTable < 5) {
                keywords = special[0];
            } else {
                relationTable = null;
            }
        }

        UserInfo user = cacheService.getUserInfo(request);
        final int number = 12;

        List<WebSite> list = new ArrayList<>(number);
        result.setData(list);
        DailySearch searchDTO = new DailySearch();
        searchDTO.setKeywords(keywords);
        if (!NullUtil.isNull(user)) {
            searchDTO.setUserId(user.getUserId());
        }
        searchDTO.setPage(new Page());
        searchDTO.getPage().setLength(number);

        CostSearch costSearch = new CostSearch();
        BeanUtils.copyProperties(searchDTO, costSearch);
        LearnSearch learnSearch = new LearnSearch();
        BeanUtils.copyProperties(searchDTO, learnSearch);
        TravelSearch travelNoteSearch = new TravelSearch();
        BeanUtils.copyProperties(searchDTO, travelNoteSearch);

        if (!NullUtil.isNull(relationTable)) {
            switch (relationTable) {
                case 1:
                    getDiary(null, number, keywords, list, searchDTO);
                    break;
                case 2:
                    getCost(null, number, keywords, list, costSearch);
                    break;
                case 3:
                    getLearn(null, number, keywords, list, learnSearch);
                    break;
                case 4:
                    getTravel(null, number, keywords, list, travelNoteSearch);
                    break;
                default:
                    return result;
            }
            return result;
        }

        List<DiaryDTO> ds = diaryService.findDiaryList(searchDTO);

        List<CostDTO> cs = costService.findMyCost(costSearch).getData();

        List<LearnDTO> ls = learnService.findMyLearn(learnSearch).getData();

        List<TravelDTO> ts = travelNoteService.findTravelNote(travelNoteSearch);

        int index = 0;

        while (true) {
            if (list.size() == number) {
                break;
            }
            if (index >= number) {
                break;
            }
            getDiary(ds, index, keywords, list, null);
            getCost(cs, index, keywords, list, null);
            getLearn(ls, index, keywords, list, null);
            getTravel(ts, index, keywords, list, null);

            index++;
        }
        return result;
    }

    private void getTravel(List<TravelDTO> ts, int index, String keywords, List<WebSite> list, TravelSearch travelNoteSearch) {
        if (!NullUtil.listIsNull(ts) && ts.size() > index) {
            TravelDTO t = ts.get(index);
            String show = null;
            if (NullUtil.isNull(show)) {
                show = getShow(t.getTravelTitle(), keywords);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(t.getTravelNote(), keywords);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(t.getTag(), keywords);
            }
            WebSite webSite = new WebSite(t.getTravelId(), 4, show);
            list.add(webSite);
        }
        if (!NullUtil.isNull(travelNoteSearch)) {
            ts = travelNoteService.findTravelNote(travelNoteSearch);
            ts.forEach(t -> {
                String show = null;
                if (NullUtil.isNull(show)) {
                    show = getShow(t.getTravelTitle(), keywords);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(t.getTravelNote(), keywords);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(t.getTag(), keywords);
                }
                WebSite webSite = new WebSite(t.getTravelId(), 4, show);
                list.add(webSite);
            });
        }
    }

    private void getLearn(List<LearnDTO> ls, int index, String keywords, List<WebSite> list, LearnSearch learnSearch) {
        if (!NullUtil.listIsNull(ls) && ls.size() > index) {
            LearnDTO learnDTO = ls.get(index);
            String show = null;
            if (NullUtil.isNull(show)) {
                show = getShow(learnDTO.getLearnTitle(), keywords);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(learnDTO.getLearnContext(), keywords);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(learnDTO.getTag(), keywords);
            }
            WebSite webSite = new WebSite(learnDTO.getLearnId(), 3, show);
            list.add(webSite);
        }
        if (!NullUtil.isNull(learnSearch)) {
            ls = learnService.findMyLearn(learnSearch).getData();
            ls.forEach(learnDTO -> {
                String show = null;
                if (NullUtil.isNull(show)) {
                    show = getShow(learnDTO.getLearnTitle(), keywords);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(learnDTO.getLearnContext(), keywords);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(learnDTO.getTag(), keywords);
                }
                WebSite webSite = new WebSite(learnDTO.getLearnId(), 3, show);
                list.add(webSite);
            });
        }

    }

    private void getCost(List<CostDTO> cs, int index, String keywords, List<WebSite> list, CostSearch costSearch) {
        if (!NullUtil.listIsNull(cs) && cs.size() > index) {
            CostDTO costDTO = cs.get(index);
            String show = null;
            if (NullUtil.isNull(show)) {
                show = getShow(costDTO.getCostContext(), keywords);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(costDTO.getTag(), keywords);
            }
            WebSite webSite = new WebSite(costDTO.getCostId(), 2, show);
            list.add(webSite);
        }
        if (!NullUtil.isNull(costSearch)) {
            cs = costService.findMyCost(costSearch).getData();
            cs.forEach(costDTO -> {
                String show = null;
                if (NullUtil.isNull(show)) {
                    show = getShow(costDTO.getCostContext(), keywords);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(costDTO.getTag(), keywords);
                }
                WebSite webSite = new WebSite(costDTO.getCostId(), 2, show);
                list.add(webSite);
            });
        }
    }

    private void getDiary(List<DiaryDTO> ds, int index, String keywords, List<WebSite> list, DailySearch searchDTO) {
        if (!NullUtil.listIsNull(ds) && ds.size() - 1 >= index) {
            DiaryDTO d = ds.get(index);
            String show = null;
            WebSite webSite1 = null;
            if (NullUtil.isNull(show)) {
                show = getShow(d.getDiaryContext(), keywords);
                webSite1 = new WebSite(d.getDiaryId(), 1, show);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(d.getMood(), keywords);
                webSite1 = new WebSite(d.getDiaryId(), 1, show);
            }
            if (NullUtil.isNull(show)) {
                show = getShow(d.getWeather(), keywords);
                webSite1 = new WebSite(d.getDiaryId(), 1, show);
            }
            list.add(webSite1);
        }
        if (!NullUtil.isNull(searchDTO)) {
            ds = diaryService.findDiaryList(searchDTO);
            ds.forEach(d -> {
                String show = null;
                WebSite webSite1 = null;
                if (NullUtil.isNull(show)) {
                    show = getShow(d.getDiaryContext(), keywords);
                    webSite1 = new WebSite(d.getDiaryId(), 1, show);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(d.getMood(), keywords);
                    webSite1 = new WebSite(d.getDiaryId(), 1, show);
                }
                if (NullUtil.isNull(show)) {
                    show = getShow(d.getWeather(), keywords);
                    webSite1 = new WebSite(d.getDiaryId(), 1, show);
                }
                list.add(webSite1);
            });
        }

    }

    private static String getShow(String source, String keywords) {
        if (source.contains(keywords)) {
            int start = source.indexOf(keywords);
            int length = source.length();
            if (start + 20 >length) {
               return  source.substring(start,length);
            }
            return source.substring(start, start+20);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getShow("<iframe src=\"//4>me>","4"));
    }


}
