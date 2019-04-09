package com.neusoft.managerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.SpuService;
import com.neusoft.javabean.po.BaseAttrValue;
import com.neusoft.javabean.po.SpuInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpuController {

    @Reference
    SpuService spuService;

    /**
     * 根据属性三级分类id查询对应spu属性
     */
    @RequestMapping("/spuList")
    @ResponseBody
    public List<SpuInfo> spuList(Long catalog3Id){
        List<SpuInfo> spuInfos = spuService.selectSpuList(catalog3Id);
        return spuInfos;
    }
}
