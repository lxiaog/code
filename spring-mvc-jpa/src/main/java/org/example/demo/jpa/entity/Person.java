package org.example.demo.jpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "jpa_person")
@Entity
@Data
@Accessors(chain = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String lastName;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birth;

    @JoinColumn(name = "address_id")
    @ManyToOne
    private Address address;

//    @JoinColumn(name = "person_id")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "person")
    private Set<Order> orders;

//    private Integer addressId;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", address=" + address +
                '}';
    }
}
