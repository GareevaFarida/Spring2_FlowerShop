package ru.geekbrains.persist.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "pictures_data")
public class PictureData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob

    //эти настройки нужны для выгрузки docker в heroku (начало)
//    @Type(type="org.hibernate.type.BinaryType") // для правильной работы PostgreSQL
//    @Column(name = "data", nullable = false, length = 33554430) // для правильной hibernate-валидации в MySQL
    //эти настройки нужны для выгрузки docker в heroku (конец)

    //при таких настройках работает тестирование с базой h2 (начало)
    @Column(name = "data", nullable = false)
    //при таких настройках работает тестирование с базой h2 (конец)

    private byte[] data;

    public PictureData() {
    }

    public PictureData(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
