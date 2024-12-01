package com.example.BookMarket.service.mappers;

import com.example.BookMarket.domain.Product;
import com.example.BookMarket.dto.Product.ProductDto;
import com.example.BookMarket.dto.Product.ProductEntry;
import com.example.BookMarket.dto.Product.ProductListDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface ProductMapper {
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "genre", source = "genre")
    ProductDto toProductDto(Product product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "genre", source = "genre.name")
    ProductEntry toProductEntry(Product product);

    default ProductListDto toProductListDto(List<Product> products) {
        return ProductListDto.builder().products(toProductEntries(products)).build();
    }

    List<ProductEntry> toProductEntries(List<Product> product);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "genre", source = "genre")
    Product toProduct(ProductDto productDto);
}


