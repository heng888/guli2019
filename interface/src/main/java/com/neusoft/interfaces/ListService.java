package com.neusoft.interfaces;

import com.neusoft.javabean.po.SkuLsInfo;
import com.neusoft.javabean.po.SkuLsParam;

import java.util.List;

public interface ListService {
    List<SkuLsInfo> search(SkuLsParam skuLsParam);
}
