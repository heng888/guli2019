package com.neusoft.interfaces;

import com.neusoft.javabean.po.SpuInfo;

import java.util.List;

public interface SpuService {
    List<SpuInfo> selectSpuList(Long catalog3Id);
}
