package org.example.demo.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "jpa_product")
@Setter
@Getter
@Accessors(chain = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    /**
     * - @ManyToMany
     * - @JoinTable(name="中间表名称,两张表关联的中间表",
     *      joinColumns=@joinColumn(name="本类的外键",
     *          referencedColumnName="本类与外键对应的主键"),
     *      inversejoinColumns=@JoinColumn(name="对方类的外键",
     *          referencedColunName="对方类与外键对应的主键")
     * )
     * 默认使用懒加载
     * 使用ManyToMany时，使用setter和getter注解
     * 使用@Data会出现死循环，报StackOverflowError异常
     */
    @JoinTable(
            name = "jpa_product_category",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Category> categories;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", categories=" + categories +
                '}';
    }
}
