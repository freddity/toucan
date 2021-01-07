package com.example.toucan;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

@Configuration
public class AppConfig {

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

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

}
