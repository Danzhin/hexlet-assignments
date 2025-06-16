package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public static Specification<Product> withTitleCont(String titleCont) {
        return (root, query, cb) -> {
            if (titleCont == null || titleCont.isBlank()) return cb.conjunction();
            String pattern = "%" + titleCont.toLowerCase() + "%";
            return cb.like(cb.lower(root.get("title")), pattern);
        };
    }

    public static Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return cb.conjunction();
            return cb.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, cb) -> {
            if (priceLt == null) return cb.conjunction();
            return cb.lessThan(root.get("price"), priceLt);
        };
    }

    public static Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, cb) -> {
            if (priceGt == null) return cb.conjunction();
            return cb.greaterThan(root.get("price"), priceGt);
        };
    }

    public static Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> {
            if (ratingGt == null) return cb.conjunction();
            return cb.greaterThanOrEqualTo(root.get("rating"), ratingGt);
        };
    }

    public static Specification<Product> build(ProductParamsDTO params) {
        return Specification
                .where(withTitleCont(params.getTitleCont()))
                .and(withCategoryId(params.getCategoryId()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withPriceGt(params.getPriceGt()))
                .and(withRatingGt(params.getRatingGt()));
    }

}
// END
