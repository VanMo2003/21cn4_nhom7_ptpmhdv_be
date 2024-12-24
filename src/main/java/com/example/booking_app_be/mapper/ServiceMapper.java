package com.example.booking_app_be.mapper;


import com.example.booking_app_be.dto.request.ServiceRequest;
import com.example.booking_app_be.dto.response.ServiceResponse;
import com.example.booking_app_be.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "hotel", ignore = true)
    Service toService(ServiceRequest request);

    ServiceResponse toServiceResponse(Service service);

    @Mapping(target = "hotel", ignore = true)
    void updateService(@MappingTarget Service service, ServiceRequest request);
}
