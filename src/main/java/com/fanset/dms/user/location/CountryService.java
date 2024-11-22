//package com.fanset.dms.user.location;
//
//import jakarta.persistence.EntityManager;
//
//public class CountryService {
//
//
//
//    private final CountryRepository countryRepository;
//    private final EntityManager entityManager;
//
//    public CountryService(CountryRepository countryRepository, EntityManager entityManager) {
//        this.countryRepository = countryRepository;
//        this.entityManager = entityManager;
//    }
//
//
//    //create country
//    public Country createCountry(CountryDto countryDto) {
//        // check if country already exists
//        Country country = countryRepository.findByName(countryDto.country());
//        country.setCity(countryDto.getCity());
//
//        // check if city already exists
//        country.getCity().getName().equals(countryDto.city());
//                cityRepository.findByName(countryDto.city());
//        if (country!=null || city!=null) {
//            throw new IllegalStateException("Country or City already exists");
//        }
//        // create new country and city
//        Country newCountry = Country.builder()
//               .name(countryDto.country())
//               .build();
//        City newCity = City.builder()
//               .name(countryDto.city())
//               .country(newCountry)
//               .build();
//        entityManager.persist(newCountry);
//        entityManager.persist(newCity);
//        return newCountry;
//    }
//
//
//
////        save country
////        save city
//
//    }
//    // create city
//    // update country
//    // delete country
//    // get country by id
//    // get all countries
//    // get cities by country id
//    // get cities by country name
//    // get countries by region
//    // get countries by population
//    // get countries by area
//    // get countries by currency
//    // get countries by language
//    // get cities
//}
