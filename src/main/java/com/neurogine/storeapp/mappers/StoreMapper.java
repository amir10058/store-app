package com.neurogine.storeapp.mappers;

import com.neurogine.storeapp.dto.StoreRequestDTO;
import com.neurogine.storeapp.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store convertDTOToEntity(StoreRequestDTO storeRequestDTO);
}
