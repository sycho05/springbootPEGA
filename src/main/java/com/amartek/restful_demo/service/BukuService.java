package com.amartek.restful_demo.service;

import java.util.List;
import java.util.Optional;

import com.amartek.restful_demo.entity.Buku;
import com.amartek.restful_demo.repository.BukuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BukuService {
    @Autowired
    private BukuRepository bukuRepository;

    public List<Buku> getAll() {
        return bukuRepository.findAll();
    }

    public Buku getBukuById(Long id) {
        return bukuRepository.findById(id).get();
    }

    public List<Buku> getBukuByJUDUL(String judul) {
        return bukuRepository.findByJUDULContainsIgnoreCase(judul);
    }

    public List<Buku> getBukuByPENERBIT(String penerbit) {
        return bukuRepository.findByPENERBITContainsIgnoreCase(penerbit);
    }

    public Buku createBuku(Buku buku) {
        Optional<Buku> bukuOptional = bukuRepository.findByJUDUL(buku.getJUDUL());
		
		if (bukuOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "JUDUL SAMA");
		}
		else {
	        bukuRepository.save(buku);
	        throw new ResponseStatusException(HttpStatus.OK, "Buku berhasil ditambahkan!");
		}
    }
    
}
