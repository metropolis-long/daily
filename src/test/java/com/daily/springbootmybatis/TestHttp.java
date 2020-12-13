package com.daily.springbootmybatis;

import com.daily.msg.ResultBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


/**
 * @ClassName TestHttp
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/27 23:49
 * @VERSION 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestHttp {

    private TestRestTemplate template = new TestRestTemplate();

    @Test
    public void testHome(){
        StringBuilder ss=new StringBuilder();
        for (int i = 0; i < 10240*1024; i++) {
            ss.append("3331");
        }
//        String url = "http://daily.zhuyelong.cn/api/select/save?provinceCode="+ss.toString();
        String url = "http://daily.zhuyelong.cn/api/select/save";

        ss.append("1");
        System.out.println(ss.toString().length()/1024);
        MultiValueMap<String, Object> json = new LinkedMultiValueMap<String, Object>();
        json.add("provinceCode", ss.toString());
        String result = template.postForObject(url, json,String.class);
        System.out.println(result);

    }
    public static ResultBody sendPostRequest(String url, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<ResultBody> response = client.exchange(url, method, requestEntity, ResultBody.class);

        return response.getBody();
    }


}
