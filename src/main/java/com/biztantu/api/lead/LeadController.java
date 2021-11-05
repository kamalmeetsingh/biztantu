package com.biztantu.api.lead;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biztantu.api.log.Logger;

@RestController
public class LeadController {

	@Autowired
	LeadService leadService;
	
	Logger logger = new Logger();
	
	@GetMapping(value="/leads/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Lead> getLead(@PathVariable Long id) throws LeadException{
		logger.info("Initiated flow");
		Lead lead = leadService.getLead(id);
		return new ResponseEntity<>(lead, HttpStatus.OK);
	}
	
	@GetMapping(value="/leads", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Lead>> getLead() throws LeadException{
		logger.info("Initiated flow");
		List<Lead> leads = leadService.getLeadList();
		return new ResponseEntity<>(leads, HttpStatus.OK);
	}
	
	@PostMapping(path="/leads", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addLead(@RequestBody Lead lead){
		Lead createdLead = leadService.addLead(lead);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLead.getLeadId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
