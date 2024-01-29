package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookBodyModel {
    String userId;
    List<IsbnBookModel> collectionOfIsbns;


}
