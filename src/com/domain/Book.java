package com.domain;

/**
 * Author: ziluxike
 * Time: 2022/12/26 11:05
 */
public class Book {
    private Integer id;
    private String idBook;
    private String nameBook;
    private float price;
    private String author;
    private String publisher;
    private String kind;

    public Book() {
    }

    public Book(Integer id, String idBook, String nameBook, float price, String author, String publisher, String kind) {
        this.id = id;
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.kind = kind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
