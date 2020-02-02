package com.teoan.blog.service.impl;

import com.teoan.blog.entity.Options;
import com.teoan.blog.mapper.OptionsMapper;
import com.teoan.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 12:16
 */
@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired(required = false)
    private OptionsMapper optionsMapper;

    @Override
    @Cacheable(value = "default",key = "'options'") //使用缓存
    public Options getOptions() {
        return optionsMapper.getOptions();
    }

    @Override
    @CacheEvict(value = "default",key = "'options'")
    public void insertOptions(Options options) {
        optionsMapper.insert(options);
    }

    @Override
    @CacheEvict(value = "default",key = "'options'")
    public void updateOptions(Options options) {
        optionsMapper.update(options);
    }
}
