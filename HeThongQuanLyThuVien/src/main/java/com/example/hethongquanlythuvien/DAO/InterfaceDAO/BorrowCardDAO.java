package com.example.hethongquanlythuvien.DAO.InterfaceDAO;


import com.example.hethongquanlythuvien.model.BorrowCard;
import com.example.hethongquanlythuvien.model.BorrowCardView;

import java.util.List;

public interface BorrowCardDAO {

    boolean insert(BorrowCard borrowCard);
    boolean update(BorrowCard borrowCard);
    BorrowCard getById(Long borrowId);
    List<BorrowCard> getAll();
    List<BorrowCardView> searchBorrowing(String bookName, String studentName);
}

