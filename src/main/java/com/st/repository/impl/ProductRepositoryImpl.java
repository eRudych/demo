package com.st.repository.impl;

import com.st.db.tables.records.ProductRecord;
import com.st.db.tables.records.ProductsCategoryRecord;
import com.st.db.tables.records.ProductsMarkRecord;
import com.st.entity.Category;
import com.st.entity.Mark;
import com.st.entity.Product;
import com.st.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.st.db.tables.Product.PRODUCT;
import static com.st.db.tables.ProductsCategory.PRODUCTS_CATEGORY;
import static com.st.db.tables.ProductsMark.PRODUCTS_MARK;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final DSLContext dsl;


    private Long insert(Product product) {
        ProductRecord productsRecord = dsl.insertInto(
                PRODUCT, PRODUCT.MARK,PRODUCT.MODEL,PRODUCT.RANGE,PRODUCT.CATEGORY,PRODUCT.YEARS,PRODUCT.PHOTO1,PRODUCT.PHOTO2,PRODUCT.PHOTO3)
                .values(product.getMark(),product.getModel(),product.getRange(),product.getCategory(),product.getYears(),product.getPhoto1(),product.getPhoto2(),product.getPhoto3())
                .returning(PRODUCT.ID)
                .fetchOne();
        product.setId(productsRecord.getValue(PRODUCT.ID));
        log.info("Insert into db: {}", product.toString());
        return productsRecord.getValue(PRODUCT.ID);
    }

    private Long insertCategory(String name){
        ProductsCategoryRecord categoryRecord = dsl.insertInto(
                PRODUCTS_CATEGORY,PRODUCTS_CATEGORY.NAME)
                .values(name)
                .returning(PRODUCTS_CATEGORY.ID)
                .fetchOne();
        return categoryRecord.getId();
    }

    private Long insertMark(String name){
        ProductsMarkRecord markRecord = dsl.insertInto(
                PRODUCTS_MARK,PRODUCTS_MARK.NAME)
                .values(name)
                .returning(PRODUCTS_MARK.ID)
                .fetchOne();
        return markRecord.getId();
    }

    @Override
    public Product create(Product product) {
        return get(insert(product));
    }

    @Override
    public boolean update(Product product) {
        try {
             dsl.update(PRODUCT)
                    .set(PRODUCT.MARK, product.getMark())
                     .set(PRODUCT.MODEL, product.getModel())
                     .set(PRODUCT.RANGE, product.getRange())
                     .set(PRODUCT.CATEGORY, product.getCategory())
                     .set(PRODUCT.YEARS, product.getYears())
                     .set(PRODUCT.PHOTO1, product.getPhoto1())
                     .set(PRODUCT.PHOTO2, product.getPhoto2())
                     .set(PRODUCT.PHOTO3, product.getPhoto3())
                    .where(PRODUCT.ID.eq(product.getId())).execute();
            return true;
        }catch (Exception ex){
            log.error(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean remove(Long id) {
        try {
            dsl.delete(PRODUCT).where(PRODUCT.ID.eq(id)).execute();
            return true;
        }catch (Exception ex){
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public Product get(Long id) {
        return dsl.selectFrom(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .fetchOneInto(Product.class);
    }

    @Override
    public List<Product> getAll() {
        return dsl.selectFrom(PRODUCT)
                .orderBy(PRODUCT.ID.desc())
                .fetchInto(Product.class);
    }

    @Override
    public List<Product> searchProducts(Product product) {
//        return dsl.selectFrom(PRODUCT)
//                .where(PRODUCT.PRODUCT.eq(product.getProduct()))
//                .fetchInto(Product.class);
        return null;
    }

    @Override
    public Category createCategory(String name) {
        return new Category(insertCategory(name),name);
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("go...db" );
        List<Category> list= dsl.selectFrom(PRODUCTS_CATEGORY)
                .orderBy(PRODUCTS_CATEGORY.ID.desc())
                .fetchInto(Category.class);
        log.info(list.toString());
        return list;
    }


    @Override
    public Mark createMark(String name) {
        return new Mark(insertMark(name),name);
    }

    @Override
    public List<Mark> getAllMarks() {
        return dsl.selectFrom(PRODUCTS_MARK)
                .orderBy(PRODUCTS_MARK.ID.desc())
                .fetchInto(Mark.class);
    }

    @Override
    public List getProductsFromCategory(long id) {
        return Collections.singletonList(String.valueOf(dsl.select(PRODUCT.ID)
                .from(PRODUCT)
                .where(PRODUCTS_CATEGORY.ID.eq(id))
                .fetchInto(Product.class)));
    }

    private String getCategoryById(Long id){
       return String.valueOf(dsl.select(PRODUCTS_CATEGORY.NAME)
                .from(PRODUCTS_CATEGORY)
                .where(PRODUCTS_CATEGORY.ID.eq(id))
                .fetchOne());
    }

    private String getMarkById(Long id){
        return String.valueOf(dsl.select(PRODUCTS_MARK.NAME)
                .from(PRODUCTS_MARK)
                .where(PRODUCTS_MARK.ID.eq(id))
                .fetchOne());
    }
}
