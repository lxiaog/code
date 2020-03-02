package org.example.demo.jpa.repository;

import org.example.demo.jpa.entity.Person;
import org.example.demo.jpa.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;
/**
 * 1. Repository 是一个空接口. 即是一个标记接口
 * 2. 若我们定义的接口继承了 Repository, 则该接口会被 IOC 容器识别为一个 Repository Bean.
 * 纳入到 IOC 容器中. 进而可以在该接口中定义满足一定规范的方法.
 * <p>
 * 3. 实际上, 也可以通过 @RepositoryDefinition 注解来替代继承 Repository 接口
 */

/**
 * 在 Repository 子接口中声明方法
 * 1. 不是随便声明的. 而需要符合一定的规范
 * 2. 查询方法以 find | read | get 开头
 * 3. 涉及条件查询时，条件的属性用条件关键字连接
 * 4. 要注意的是：条件属性以首字母大写。
 * 5. 支持属性的级联查询. 若当前类有符合条件的属性, 则优先使用, 而不使用级联属性.
 * 若需要使用级联属性, 则属性之间使用 _ 进行连接.
 */
public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person>, BaseRepository<Person> {
    //根据 lastName 来获取对应的 Person
    Person getByLastName(String lastName);

    Person queryByLastName(String lastName);

    //WHERE lastName LIKE ?% AND id < ?
    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    List<Person> queryByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    //WHERE lastName LIKE %? AND id > ?
    List<Person> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

    List<Person> queryByLastNameEndingWithAndIdGreaterThan(String lastName, Integer id);

    //WHERE email IN (?, ?, ?) OR birth < ?
    List<Person> getByEmailInAndBirthLessThan(List<String> emails, Date birth);

    List<Person> queryByEmailIn(List<String> emails);

    //WHERE address_id > ?
    List<Person> queryByAddressIdGreaterThan(Integer id);

    List<Person> queryByAddress_IdGreaterThan(Integer id);
    /**
     * 1、如果person实体类中没有addressId字段，则两个方法都是关联查询
     * address.id>?
     * 2、如果person实体类中有addressId字段
     * queryByAddressIdGreaterThan：address_id>? 无关联查询
     * queryByAddress_IdGreaterThan：address.id>? 俩表关联查询
     * 但因为@JoinColumn(name = "address_id")与addressId 映射的字段重复，报错，所以不可能同时出现
     * 3、所以如果使用联级查询最好使用下划线分割的方式，这种一定是联级查询
     */

    /**
     * 查询id最大的person数据
     */
    @Query(value = "select p from Person p where p.id = (select max(p2.id) from Person p2)")
    Person queryMaxIdPerson();

    /**
     * @Query 传参方式一 占位符
     * 参数必须按顺序
     */
    @Query(value = "select p from Person p where p.lastName = ?1 and p.email = ?2")
    List<Person> queryLastNameAndEmail(String lstName, String email);

    /**
     * @Query 传参方式二 命名参数
     * 参数无顺序
     */
    @Query(value = "select p from Person p where p.lastName = :lastName and p.email = :email")
    List<Person> findLastNameAndEmail(@Param("email") String email, @Param("lastName") String lastName);

    /**
     * 传参需要带百分号
     */
    @Query(value = "select p from Person p where p.lastName like ?1 or p.email like ?2")
    List<Person> testQueryLikeParam(String lastName, String email);

    /**
     * 与占位符写到一起
     */
    @Query(value = "select p from Person p where p.lastName like %?1% and p.email like %?2%")
    List<Person> testQueryLikeParam1(String lastName, String email);

    /**
     * 与参数命名方法写到一起
     */
    @Query(value = "select p from Person p where p.lastName like %:lastName% or p.email like %:email%")
    List<Person> testQueryLikeParam2(@Param("email") String email, @Param("lastName") String lastName);

    /**
     * 设置nativeQuery=true即可使用原生SQL
     */
    @Query(value = "select count(id) from jpa_person", nativeQuery = true)
    long getTotalCount();

    /**
     * update语句
     */
    @Modifying
    @Query(value = "update Person p set p.email = :email where p.id = :id")
    int updateEmailById(@Param("id") Integer id, @Param("email") String email);

    /**
     * 全部字段查询 select 可以省略
     */
    @Query(value = "from Person p ")
    List<Person> testJpqlAll();

    /**
     * 部分字段查询，需要person的构造方法初始化赋值
     */
    @Query(value = "select new Person (p.id,p.lastName) from Person p")
    List<Person> testJpqlByIdAndLastName();

    /**
     * order by
     */
    @Query(value = "from Person p order by p.id desc ")
    List<Person> testJpqlOrderBy();

    /**
     * group by
     */
    @Query(value = "select new Person (p.birth) from Person p group by p.birth")
    List<Person> testJpqlGroupByBirth();

    /**
     * group by
     */
    @Query(value = "select new Person (p.address) from Person p group by p.address")
    List<Person> testJpqlGroupByddressId();

    /**
     * 不会出现关联查询
     * 两张表单独查询的语句
     */
    @Query(value = "select  p from Person p where p.address.id =1")
    List<Person> testJpqlByAddressId();

    /**
     * 两条查询语句
     * 一条是关联查询语句，但查询条件是 person.address_id =1
     * 另一条是address表的查询语句，条件是address.id = 1
     */
    @Query(value = "select  p from Person p left join  p.address where p.address.id =1")
    List<Person> testJpqlByAddressLeftJoin();

    /**
     * 只有一条关联查询语句
     * 查询条件是address.id=1
     */
    @Query(value = "select  p from Person p left join fetch p.address where p.address.id =1")
    List<Person> testJpqlByAddressIdFetch();



}
