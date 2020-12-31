package com.example.toucan;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.entity.EntityNote;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToucanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToucanApplication.class, args);
    }

    private ModelMapper modelMapper;

    @Bean
    public ModelMapper getModelMapper() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<EntityNote, DtoNote>() {
            @Override
            protected void configure() {
                map().setUuid(source.getUuid());
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                map().setCreationTimestamp(source.getCreationTimestamp());
                map().setOwnerUsername(source.getOwner().getUsername());
            }
        });
        return modelMapper;
    }
}