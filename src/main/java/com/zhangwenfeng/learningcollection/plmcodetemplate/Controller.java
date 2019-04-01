package com.zhangwenfeng.learningcollection.plmcodetemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("plm")
public class Controller {
    @RequestMapping("index")
    @ResponseBody
    public ResultBean<String> index() {
        return new ResultBean<>("成功");
    }
}
