package com.example.app.Category.entity.dto;

import com.example.app.base.entity.BaseEntity;
import com.example.app.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class CategoryDto extends BaseEntity {
//    @OneToMany(mappedBy = "category")
//    private List<Bujeok> bujeoks;
    private String frontUrl;
}
