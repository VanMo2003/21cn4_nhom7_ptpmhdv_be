package com.example.booking_app_be.mapper;

import com.example.booking_app_be.dto.request.StatisticalRequest;
import com.example.booking_app_be.dto.response.StatisticalResponse;
import com.example.booking_app_be.entity.Statistical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatisticalMapper {
    @Mapping(target = "hotel", ignore = true)
    Statistical toStatistical(StatisticalRequest request);

    @Mapping(target = "hotel", ignore = true)
    StatisticalResponse toStatisticalResponse(Statistical statistical);

    @Mapping(target = "hotel", ignore = true)
    void updateStatistical(@MappingTarget Statistical statistical, StatisticalRequest request);
}