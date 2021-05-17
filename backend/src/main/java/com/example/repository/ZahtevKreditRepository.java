package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ZahtevKredit;

@Repository
public interface ZahtevKreditRepository extends JpaRepository<ZahtevKredit, Long>{

}
