package com.example.app.Category.entity;

import com.example.app.base.entity.BaseEntity;
import com.example.app.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class Category extends BaseEntity {
    @OneToMany(mappedBy = "category")
    private List<Bujeok> bujeoks;
    private String frontUrl;
}
