package com.neusoft.managerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.AttrService;
import com.neusoft.javabean.po.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttrController {

    @Reference
    private AttrService managerService;


    /**
     * 查询所有的一级分类
     * @return 一级分类的集合
     */
    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> selectBaseCatalog1(){
        List<BaseCatalog1> baseCatalog1s = managerService.selectBaseCatalog1();
        return baseCatalog1s;
    }

    /**
     * 通过一级分类id查询对应的二级分类
     * @param map 前端接收的reques的参数
     * @return 二级分类集合
     */
    @RequestMapping("/getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> selectCatalog2ByCatalog1(String catalog1Id){
        List<BaseCatalog2> baseCatalog2s = managerService.selectCatalog2ByCatalog1(Long.parseLong(catalog1Id));
        return baseCatalog2s;
    }

    /**
     * 通过二级分类id查询对应的三级分类
     * @param map 前端接收的reques的参数
     * @return 三级分类的集合
     */
    @RequestMapping("/getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> selectCatalog3ByCatalog2(String catalog2Id){
        List<BaseCatalog3> baseCatalog3s = managerService.selectCatalog3ByCatalog2(Long.parseLong(catalog2Id));
        return baseCatalog3s;
    }


    /**
     * 查询属性
     * @param catalog3Id
     * @return
     */
    @RequestMapping("/getAttrList")
    @ResponseBody
    public List<BaseAttrInfo> selectAttrInfoByCatalog3(String catalog3Id){
        List<BaseAttrInfo> baseAttrInfos = managerService.selectAttrInfoByCatalog3(Long.parseLong(catalog3Id));
        return baseAttrInfos;
    }

    /**
     * 添加属性跟属性值
     */
    @RequestMapping("/saveAttr")
    @ResponseBody
    public String saveAttr(BaseAttrInfo baseAttrInfo){
        if(baseAttrInfo.getId()!=null){
            managerService.updateAttr(baseAttrInfo);
            return "修改成功";
        }else{
            managerService.insertAttr(baseAttrInfo);
            return "添加成功";
        }
    }

    /**
     * 删除属性
     * @param attrInfoId   属性id
     * @return
     */
    @RequestMapping("/deleteAttr")
    @ResponseBody
    public String deleteAttr(Long attrInfoId){
        managerService.deleteAttr(attrInfoId);
        return "删除成功";
    }

    /**
     * 根据属性id查询对应的属性值
     */
    @RequestMapping("/selectAttrValue")
    @ResponseBody
    public List<BaseAttrValue> selectAttrValue(Long attrInfoId){
        List<BaseAttrValue> baseAttrValues = managerService.selectAttrValueByAttrId(attrInfoId);
        return baseAttrValues;
    }
}
