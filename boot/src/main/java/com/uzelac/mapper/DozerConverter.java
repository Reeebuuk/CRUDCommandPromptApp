package com.uzelac.mapper;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DozerConverter
{
    private DozerBeanMapper mapper;

    public DozerConverter()
    {
        this.mapper = new DozerBeanMapper();
    }

    public <F, T> T convert(F source, Class<T> destinationClass)
    {
        if (source == null)
        {
            return null;
        }
        return mapper.map(source, destinationClass);
    }

    public <F, T> List<T> convertAll(Iterable<F> source, Class<T> destinationClass)
    {
        List<T> converted = Lists.newArrayList();
        for (F item : source)
        {
            converted.add(convert(item, destinationClass));
        }
        return converted;
    }
}
