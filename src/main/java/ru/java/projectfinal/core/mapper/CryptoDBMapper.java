package ru.java.projectfinal.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.java.projectfinal.core.model.CryptoApiDto;
import ru.java.projectfinal.db.entity.Crypto;

@Component
public class CryptoDBMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Crypto.class, CryptoApiDto.class)
                .byDefault()
                .register();
    }
}