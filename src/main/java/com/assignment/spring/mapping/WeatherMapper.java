package com.assignment.spring.mapping;

import com.assignment.spring.api.Clouds;
import com.assignment.spring.api.Coord;
import com.assignment.spring.api.Main;
import com.assignment.spring.api.Sys;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.api.Wind;
import com.assignment.spring.dto.CloudsDto;
import com.assignment.spring.dto.CoordDto;
import com.assignment.spring.dto.MainDto;
import com.assignment.spring.dto.SysDto;
import com.assignment.spring.dto.WeatherDataHolderDto;
import com.assignment.spring.dto.WindDto;
import com.assignment.spring.persistence.entity.CloudsEntity;
import com.assignment.spring.persistence.entity.CoordEntity;
import com.assignment.spring.persistence.entity.MainEntity;
import com.assignment.spring.persistence.entity.SysEntity;
import com.assignment.spring.persistence.entity.WeatherDataHolderEntity;
import com.assignment.spring.persistence.entity.WindEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface WeatherMapper {

    WeatherDataHolderEntity toWeatherDataHolderEntity(WeatherResponse body);

    CoordEntity toCoordEntity(Coord coord);

    MainEntity toMainEntity(Main main);

    WindEntity toWindEntity(Wind wind);

    CloudsEntity toCloudsEntity(Clouds clouds);

    SysEntity toSysEntity(Sys sys);

    WeatherDataHolderDto toWeatherDataHolderDto(WeatherDataHolderEntity weatherDataHolderEntity);

    CoordDto toCoordDto(CoordEntity coordEntity);

    MainDto toMainDto(MainEntity mainEntity);

    WindDto toWindDto(WindEntity windEntity);

    CloudsDto toCloudsDto(CloudsEntity cloudsEntity);

    SysDto toSysDto(SysEntity sysEntity);
}
