package org.example.demo.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

public class MockMvcUtil {
    /**
     * 使用@RequestBody注解的参数的接口，Json字符串
     * convenientBusinessTest(@RequestBody RequestInfo info) {
     *
     * @param info
     * @return
     */
    public static void requestJson(String url, Object info, MockMvc mvc) throws Exception {
        String json = JSON.toJSONString(info);
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType("application/json;charset=UTF-8")
                .content(json)
                .accept("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * 直接使用实体类接受参数的接口，是键值对类型
     * convenientBusinessTest(RequestInfo info)
     *
     * @return
     */
    public static void requestKeyAndValue(String url,
                                          MultiValueMap<String, String> params, MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(url)
                .params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    public static void requestKeyAndValue(String url, MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
