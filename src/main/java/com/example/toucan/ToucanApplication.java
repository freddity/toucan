package com.example.toucan;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.dto.DtoShortNoteContainer;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.service.notedetails.NoteDetailsImpl;
import com.example.toucan.service.userdetails.UserDetailsImpl;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
public class ToucanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToucanApplication.class, args);
    }

    private ModelMapper modelMapper;

    @Bean
    public ModelMapper getModelMapper() {
        modelMapper = new ModelMapper();
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

        modelMapper.addMappings(new PropertyMap<EntityNote, NoteDetailsImpl>() {
            @Override
            protected void configure() {
                map().setUuid(source.getUuid());
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                map().setCreationTimestamp(source.getCreationTimestamp());
                map().setOwnerUsername(source.getOwner().getUsername());
            }
        });

        modelMapper.addMappings(new PropertyMap<EntityUser, UserDetailsImpl>() {
            @Override
            protected void configure() {
                map().setUsername(source.getUsername());
                map().setPassword(source.getPassword());
                map().setAuthorities(source.getRole());
                map().setLockStatus(source.isLocked());
            }
        });

        modelMapper.addMappings(new PropertyMap<EntityNote, DtoNote>() {
            @Override
            protected void configure() {
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                map().setCreationTimestamp(source.getCreationTimestamp());
            }
        });
        /*Converter<List<EntityNote>, List<DtoNote>> converter = context -> {
            List<DtoNote> list = new ArrayList<>();
            for(EntityNote n :context.getSource()) {
                list.add(modelMapper.map(n, DtoNote.class));
            }
            return list;
        };

        modelMapper.createTypeMap(List<EntityNote>, ArrayList<DtoNote>).setConverter(converter);*/

        /*modelMapper.addMappings(new PropertyMap<ArrayList<EntityNote>, DtoShortNoteContainer>() {
            @Override
            protected void configure() {
                map().setNoteList(entityListToDtoList(source));
            }
        });*/
        return modelMapper;
    }

    /*private List<DtoNote> entityListToDtoList(List<EntityNote> entitiesList) {
        List<DtoNote> list = new ArrayList<>();
        for(EntityNote n :entitiesList) {
            list.add(modelMapper.map(n, DtoNote.class));
        }
        return list;
    }*/
}