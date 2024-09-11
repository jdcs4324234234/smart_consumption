package com.unicauca.smart_consumption.infrastructure.embeddableEntity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CategoryEmbeddable {
    private  String name;
}
