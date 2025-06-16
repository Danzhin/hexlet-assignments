package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;
    // GET /products – список всех товаров
    @GetMapping
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::map)
                .toList();
    }

    // GET /products/{id} – один товар
    @GetMapping("/{id}")
    public ProductDTO getOne(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.map(product);
    }

    // POST /products – создание
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO dto) {
        if (!categoryRepository.existsById(dto.getCategoryId())) {
            throw new IllegalArgumentException("Category with id " + dto.getCategoryId() + " not found");
        }
        var product = productMapper.map(dto);
        productRepository.save(product);
        return productMapper.map(product);
    }

    // PUT /products/{id} – обновление
    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable long id, @Valid @RequestBody ProductUpdateDTO dto) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        dto.getCategoryId().ifPresent(categoryId -> {
            if (!categoryRepository.existsById(categoryId)) {
                throw new ResourceNotFoundException("Category with id " + categoryId + " not found");
            }
        });

        productMapper.update(dto, product);
        productRepository.save(product);
        return productMapper.map(product);
    }

    // DELETE /products/{id} – удаление
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }
    // END
}
