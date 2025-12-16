package com.example.hethongquanlythuvien.ServiceLayer.Inteface;

import com.example.hethongquanlythuvien.model.BorrowCard;

import java.util.List;

public interface BorrowCardService {  boolean insert(BorrowCard borrowCard);
    boolean update(BorrowCard borrowCard);
    BorrowCard getById(Long borrowId);
    List<BorrowCard> getAll();
    List<BorrowCard> searchBorrowing(String bookName, String studentName);
}

