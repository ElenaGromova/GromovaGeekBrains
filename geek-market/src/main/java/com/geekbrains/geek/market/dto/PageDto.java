package com.geekbrains.geek.market.dto;

import com.geekbrains.geek.market.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PageDto {
    private List<ProductDto> productDtos;
    private Pageable page;
    private long size;
    
    public PageDto(Page<Product> p){
        this.productDtos = p.stream().map(ProductDto::new).collect(Collectors.toList());
        this.page = p.getPageable();
        this.size = p.getTotalPages();
    }

}
