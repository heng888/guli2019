package com.neusoft.managerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.SpuService;
import com.neusoft.javabean.po.*;
import com.neusoft.managerweb.utils.MyUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 查询所有的销售属性
     */
    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> baseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrs = spuService.baseSaleAttrList();
        return baseSaleAttrs;
    }


    /**
     * 添加或者修改spu属性及spu销售属性值
     */
    @RequestMapping("/saveSpu")
    @ResponseBody
    public String saveSpu(SpuInfo spuInfo){
        if(spuInfo.getId() ==null){
            spuService.saveSpu(spuInfo);
            return "添加成功";
        }else{
            spuService.updateSpu(spuInfo);
            return "修改成功";
        }
    }

    /**
     * 根据spuId查询spuImage信息
     */
    @RequestMapping("/selectSpuAll")
    @ResponseBody
    public List<SpuSaleAttr> selectSpuAll(Long spuId){
        List<SpuSaleAttr> spuSaleAttrs = spuService.selectSpuAll(spuId);
        return spuSaleAttrs;
    }


    /**
     * 根据spuId查询spuImage信息
     */
    @RequestMapping("/selectSpuImage")
    @ResponseBody
    public List<SpuImage> selectSpuImageBySpuId(Long spuId){
        List<SpuImage> spuImageList = spuService.selectSpuImageBySpuId(spuId);
        return spuImageList;
    }

    /**
     * 根据spuId查询SpuSaleAttr
     */
    @RequestMapping("/selectSpuSaleAttrBySpuId")
    @ResponseBody
    public List<SpuSaleAttr> selectSpuSaleAttrBySpuId(Long spuId){
        List<SpuSaleAttr> spuSaleAttrs = spuService.selectSaleAttrAndAttrValueByspuId(spuId);
        return spuSaleAttrs;
    }


    /**
     * 根据saleAttrId和spuid查询SpuSaleAttrValue
     */
    @RequestMapping("/spuSaleAttrValueBySaleAttrId")
    @ResponseBody
    public List<SpuSaleAttrValue> spuSaleAttrValueBySaleAttrId(SpuSaleAttrValue spuSaleAttrValue){
        List<SpuSaleAttrValue> spuSaleAttrValueList = spuService.spuSaleAttrValueBySaleAttrId(spuSaleAttrValue);
        return spuSaleAttrValueList;
    }

    /**
     * 删除spu跟关联的spuImage、spuAttr、spuAttrValue
     */
    @RequestMapping("/deleteSpuInfo")
    @ResponseBody
    public String deleteSpuInfo(Long spuId){
        spuService.deleteSpuInfo(spuId);
        return "删除成功";
    }




    /**
     * 加载图片
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file){
        String imaUrl = MyUploadUtil.uploadImage(file);
        return imaUrl;
    }
}
