package com.biztantu.api.lead;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {
	
	@Autowired
	private LeadRepository repository;
	
	public Lead getLead(Long id) throws LeadException {
		Optional<Lead> lead = repository.findById(id);
		if(lead.isPresent()) {
            return lead.get();
        } else {
            throw new LeadException("No record exist for given id");
        }
	}
	
	public Lead addLead(Lead lead) {
		Lead createdLead = repository.save(lead);
		return createdLead;
	}
	
	public List<Lead> getLeadList() throws LeadException {
		return repository.findAll();
	}

}
