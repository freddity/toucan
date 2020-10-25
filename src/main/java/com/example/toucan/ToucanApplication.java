package com.example.toucan;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.service.notedetails.NoteDetailsImpl;
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

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<NoteDetailsImpl, DtoNote>() {
            @Override
            protected void configure() {
                map().setUuid(source.getUUID());
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                map().setCreationTimestamp(source.getCreationTimestamp());
                map().setOwnerUsername(source.getOwnerUsername());
            }
        });
        return modelMapper;
    }
}
