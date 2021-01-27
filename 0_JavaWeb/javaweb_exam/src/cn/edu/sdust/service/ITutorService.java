package cn.edu.sdust.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.sdust.domain.Tutor;

public interface ITutorService {
	public List<Tutor> getAllTutors();
	public int addTutor(Tutor tutor);
}
