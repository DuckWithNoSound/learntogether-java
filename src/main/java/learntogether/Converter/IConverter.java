package learntogether.Converter;

import org.springframework.stereotype.Component;

/*
  Created by Luvbert
*/

public interface IConverter<D, E> {
    D toDTO(E entity);
    E toEntity(D dto);
}
