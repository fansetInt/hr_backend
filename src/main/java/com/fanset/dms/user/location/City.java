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
//import java.util.SortedSet;
//import java.util.TreeSet;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//public class City {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @ManyToMany(mappedBy= "city")
//    @SortNatural
//    private SortedSet<Country> country = new TreeSet<>();
//}
