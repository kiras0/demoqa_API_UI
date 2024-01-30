package models;

import lombok.Data;

@Data
public class IsbnBookModel {
    public IsbnBookModel(String isbn) {
        this.isbn = isbn;
    }
    private String isbn;
}
