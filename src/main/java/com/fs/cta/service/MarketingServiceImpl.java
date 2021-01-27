package com.fs.cta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.cta.bom.Marketing;
import com.fs.cta.db.MarketingRepository;
import com.fs.cta.util.IDUtil;

@Service
public class MarketingServiceImpl implements MarketingService {
	
	@Autowired
	private MarketingRepository marketingRepository;
	
	@Override
	public Marketing save(Marketing marketing) throws Exception {
		if ( IDUtil.isValid(marketing.getId()) == false) {
			throw new Exception("O ID provided is invalid.");
		}
		boolean existsById = marketingRepository.existsById(marketing.getId());
		if ( existsById ) {
			marketing = marketingRepository.save(marketing); // Update
		} else {
			marketing = marketingRepository.save(marketing); // Insert
		}
		return marketing;
	}

	@Override
	public String delete(Long id) throws Exception {
		if ( id != null ) {
			Optional<Marketing> marketing = marketingRepository.findById(id);
			if ( marketing.isPresent() ) {
			    marketingRepository.delete(marketing.get());
			} else {
				throw new Exception("Marketing not found by id");
			}
		}
		return null;
	}

	@Override
	public Optional<Marketing> find(Long id) {
		Optional<Marketing> marketing = marketingRepository.findById(id);
		return marketing;
	}

	@Override
	public List<Marketing> list() {
	    Iterable<Marketing> marketings = marketingRepository.findAll();
	    return mapToList(marketings);
	}

	private List<Marketing> mapToList(Iterable<Marketing> marketings) {
	    List<Marketing> listOfMarketings = new ArrayList<>();
	    for (Marketing student : marketings) {
	    	listOfMarketings.add(student);
	    }
	    return listOfMarketings;
	}
}
