package com.stockinfo.manager;

import com.stockinfo.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * @Auther: gaofan
 * @Date: 2020/4/24 0024 10 10
 * @Description:通用添加编辑删除功能
 */
@Service
public class GeneralManager {
    public String generalDelete(String id,String type,String state){
        if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(type)){
            switch (type){

            }
        }
        return "";

    }
}
