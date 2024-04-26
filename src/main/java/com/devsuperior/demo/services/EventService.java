package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;

	@Transactional
	public EventDTO update(Long id, EventDTO eventDto) {
		try {
			Event event = repository.getReferenceById(id);
			City city = new City(eventDto.getCityId(), null);
			event.setName(eventDto.getName());
			event.setDate(eventDto.getDate());
			event.setUrl(eventDto.getUrl());
			event.setCity(city);
			
			event = repository.save(event);
			return new EventDTO(event);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}
	}

}
