//package com.fanset.dms.user.location;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.SortNatural;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//public class Country {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "country_city",
//            joinColumns = @JoinColumn(name = "country_id"),
//            inverseJoinColumns = @JoinColumn(name = "city_id")
//    )
//    @SortNatural
//    private SortedSet<City> city = new TreeSet<>();
//
//}
