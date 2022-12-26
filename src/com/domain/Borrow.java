package com.domain;

/**
 * Author: ziluxike
 * Time: 2022/12/26 14:50
 */
public class Borrow {
    private Integer id;
    private String idReader;
    private String idBook;
    private String dueDate;
    private String overtime;
    private String lendDate;

    public Borrow() {
    }

    public Borrow(Integer id, String idReader, String idBook, String dueDate, String overtime, String lendDate) {
        this.id = id;
        this.idReader = idReader;
        this.idBook = idBook;
        this.dueDate = dueDate;
        this.overtime = overtime;
        this.lendDate = lendDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", idReader='" + idReader + '\'' +
                ", idBook='" + idBook + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", overtime='" + overtime + '\'' +
                ", lendDate='" + lendDate + '\'' +
                '}';
    }
}
