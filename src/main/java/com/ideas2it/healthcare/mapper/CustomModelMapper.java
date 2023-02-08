package com.ideas2it.healthcare.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper extends ModelMapper{
	
	public  <S,D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
	        return sourceList.stream()
	                .map(source -> super.map(source, destinationClass))
	                .collect(Collectors.toList());
	    }
}
