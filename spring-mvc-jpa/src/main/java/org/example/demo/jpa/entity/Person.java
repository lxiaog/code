package org.example.demo.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "jpa_person")
@Entity
@Setter
@Getter
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    public Person(Date birth) {
        this.birth = birth;
    }

    public Person(Address address) {
        this.address = address;
    }

    public Person(Integer id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public Person() {
    }

//    @JoinColumn(name = "person_id")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
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
