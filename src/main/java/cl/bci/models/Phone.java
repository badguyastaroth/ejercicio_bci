package cl.bci.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="PHONE")
@Getter
@Setter
@NoArgsConstructor
public class Phone {

    @Column(name="id_phone")
    @Id
    private Long idPhone;

    @Column(name="number")
    private Long number;

    @Column(name="city_code")
    private Integer cityCode;

    @Column(name="country_code")
    private String countryCode;

    @Column(name="id")
    private String id;
}
