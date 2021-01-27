package com.fs.cta.service;

import java.util.List;
import java.util.Optional;

import com.fs.cta.bom.Marketing;

public interface MarketingService {
	
    public Marketing save( Marketing cliente) throws Exception;
	
	public String delete(Long id)  throws Exception;
	
    public Optional<Marketing> find(Long id);
	
	public List<Marketing> list();
}
