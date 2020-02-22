package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.jpa.entity.Category;
import org.example.demo.jpa.entity.Product;
import org.example.demo.jpa.repository.CategoryRepository;
import org.example.demo.jpa.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/spring/spring-data-source.xml",
        "classpath:/spring/spring-jpa.xml",
        "classpath:/spring/spring-mvc.xml",
        "classpath:/spring/spring-mybatis.xml"

})
@WebAppConfiguration
@Slf4j
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testManyToManyAdd() {
        Product product1 = new Product();
        product1.setProductName("p-1");

        Product product2 = new Product();
        product2.setProductName("p-2");

        Category category1 = new Category();
        category1.setCategoryName("c-1");

        Category category2 = new Category();
        category2.setCategoryName("c-2");

        Set<Category> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);
        product1.setCategories(categories);
        product2.setCategories(categories);

//        Set<Product> products = new HashSet<>();
//        products.add(product1);
//        products.add(product2);
//        category1.setProducts(products);
//        category2.setProducts(products);


        categoryRepository.save(category1);
        categoryRepository.save(category2);

        productRepository.save(product1);
        productRepository.save(product2);


    }

    /**
     * ManyToMany 都是懒加载
     * 不管使用哪一方查询，获取数据的SQL语句相同
     */
    @Test
    public void testManyToManyFind() {
        Product product = productRepository.getOne(1);
        log.error(product.getProductName());

        Category category = categoryRepository.getOne(1);
        log.error(category.getCategoryName());
        log.error("" + category.getProducts().size());
    }

    @Test
    public void testJpaFirstCache() {
        Product product = productRepository.getOne(1);
        log.error("product=" + product.getProductName());
        Product product1 = productRepository.getOne(1);
        log.error("product1=" + product1.getProductName());

    }

}
