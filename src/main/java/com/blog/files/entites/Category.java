package com.blog.files.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("posts") // Prevents infinite recursion
public class Category {

    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_description", nullable = false)
    private String categoryDescription;

    @Column(name = "category_title", nullable = false)
    private String categoryTitle;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Schema(description = "Flag to indicate if the user is deleted", example = "false")
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}
